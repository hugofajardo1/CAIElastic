package com.example.elastic.services;

import java.util.Collection;

import com.example.elastic.dto.AccidentDTO;
import com.example.elastic.dto.CommonSeverityDTO;
import com.example.elastic.dto.DistanceDTO;
import com.example.elastic.dto.PuntoDTO;

public interface IAccidentService {

	Collection<AccidentDTO> getAccidentsBetween(String pDesde, String pHasta);

	Collection<AccidentDTO> getAccidentsInRadius(Float pLatitud, Float pLongitud, Integer pRadius);

	Collection<DistanceDTO> getAverageDistance();

	Collection<CommonSeverityDTO>  getCommonConditions();

    Collection<PuntoDTO> getAccidentsDangerousPointsInRadius(Float pLatitud, Float pLongitud, Integer pRadius);
}
