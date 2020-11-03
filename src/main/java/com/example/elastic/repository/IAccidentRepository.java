package com.example.elastic.repository;

import com.example.elastic.model.Accident;
import com.example.elastic.model.CommonSeverity;
import com.example.elastic.model.Distance;
import com.example.elastic.model.Punto;

import java.util.Collection;

public interface IAccidentRepository {

    Collection<Accident> findByDateBetween(String pDesde, String pHasta);

    Collection<Accident> findInRadius(Float pLatitud, Float pLongitud, Integer pRadius);

    Collection<Distance> getAverageDistance();

    Collection<CommonSeverity> getCommonConditions();

    Collection<Punto> getAccidentsDangerousPointsInRadius(Float pLatitud, Float pLongitud, Integer pRadius);
}
