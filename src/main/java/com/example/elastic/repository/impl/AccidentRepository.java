package com.example.elastic.repository.impl;

import com.example.elastic.model.Accident;
import com.example.elastic.model.CommonSeverity;
import com.example.elastic.model.Distance;
import com.example.elastic.model.Punto;
import com.example.elastic.repository.IAccidentRepository;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class AccidentRepository implements IAccidentRepository {

    @Autowired
    private final ElasticsearchRestTemplate elasticsearchTemplate;

    public AccidentRepository(ElasticsearchRestTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Collection<Accident> findByDateBetween(String pDesde, String pHasta) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.rangeQuery("@timestamp").gte(pDesde).lte(pHasta));
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        SearchHitsIterator<Accident> searchHits = elasticsearchTemplate.searchForStream(searchQuery, Accident.class, IndexCoordinates.of("accidentes"));
        List<Accident> result = new ArrayList();
        while(searchHits.hasNext()){
            result.add(searchHits.next().getContent());
        }
        return  result;
    }


    @Override
    public Collection<Accident> findInRadius(Float pLatitud, Float pLongitud, Integer pRadius) {
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("start_location").point((double) pLongitud, (double) pLatitud).distance(pRadius.toString(), DistanceUnit.KILOMETERS);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery()).filter(geoDistanceQueryBuilder);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        SearchHitsIterator<Accident> searchHits = elasticsearchTemplate.searchForStream(searchQuery, Accident.class, IndexCoordinates.of("accidentes"));
        List<Accident> result = new ArrayList();
        while(searchHits.hasNext()){
            result.add(searchHits.next().getContent());
        }
        return  result;
    }

    @Override
    public Collection<Distance> getAverageDistance() {
        AvgAggregationBuilder aggregation = AggregationBuilders.avg("Distance").field("Distance(mi)").script(new Script("_value * 1609"));
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).addAggregation(aggregation).build();
        SearchHits<Accident> searchHits = elasticsearchTemplate.search(searchQuery, Accident.class, IndexCoordinates.of("accidentes"));
        Avg average = searchHits.getAggregations().get("Distance");
        List<Distance> result = new ArrayList<>();
        result.add(new Distance("1", average.getValueAsString()));
        return result;
    }

    @Override
    public Collection<CommonSeverity> getCommonConditions() {
        AbstractAggregationBuilder aggregation = AggregationBuilders.terms("Severity").field("Severity.keyword");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).addAggregation(aggregation).build();
        SearchHits<Accident> searchHits = elasticsearchTemplate.search(searchQuery, Accident.class, IndexCoordinates.of("accidentes"));
        ParsedStringTerms buck = (ParsedStringTerms) searchHits.getAggregations().asMap().get("Severity");
        List<CommonSeverity> result = new ArrayList<>();
        buck.getBuckets().forEach(aBucket -> {
            result.add(new CommonSeverity(aBucket.getKeyAsString(),(int) aBucket.getDocCount()));
        });
        return result;
    }

    @Override
    public Collection<Punto> getAccidentsDangerousPointsInRadius(Float pLatitud, Float pLongitud, Integer pRadius) {
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery("start_location").point((double) pLongitud, (double) pLatitud).distance(pRadius.toString(), DistanceUnit.KILOMETERS);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery()).filter(geoDistanceQueryBuilder);
        AbstractAggregationBuilder aggregationDanger = AggregationBuilders.terms("Dangerous").field("slocation.keyword").minDocCount(1).size(5);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).addAggregation(aggregationDanger).build();
        SearchHits<Accident> searchHits = elasticsearchTemplate.search(searchQuery, Accident.class, IndexCoordinates.of("accidentes"));
        ParsedStringTerms aBucketResult = (ParsedStringTerms) searchHits.getAggregations().asMap().get("Dangerous");
        List<Punto> result = new ArrayList<>();
        aBucketResult.getBuckets().forEach(aBucket -> {
            result.add(new Punto(aBucket.getKey().toString(), (int) aBucket.getDocCount()));
        });
        return result;
    }
}