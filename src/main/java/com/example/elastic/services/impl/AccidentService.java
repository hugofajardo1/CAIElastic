package com.example.elastic.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.example.elastic.dto.CommonSeverityDTO;
import com.example.elastic.dto.DistanceDTO;
import com.example.elastic.dto.PuntoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.elastic.dto.AccidentDTO;
import com.example.elastic.repository.IAccidentRepository;
import com.example.elastic.services.IAccidentService;

@Service
@Transactional
public class AccidentService implements IAccidentService {

	@Inject
	public IAccidentRepository accidentRepository;

	@Override
	public Collection<AccidentDTO> getAccidentsBetween(String pDesde, String pHasta) {

		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

		this.getAccidentRepository().findByDateBetween(pDesde, pHasta).stream().map(a -> new AccidentDTO(a))
				.collect(Collectors.toCollection(() -> result));
		return result;
	}

	@Override
	public Collection<AccidentDTO> getAccidentsInRadius(Float pLatitud, Float pLongitud, Integer pRadius) {
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

		this.getAccidentRepository().findInRadius(pLatitud, pLongitud, pRadius).stream().map(a -> new AccidentDTO(a))
				.collect(Collectors.toCollection(() -> result));
		return result;
	}

	@Override
	public Collection<DistanceDTO> getAverageDistance() {

		Collection<DistanceDTO> result = new ArrayList<DistanceDTO>();

		this.getAccidentRepository().getAverageDistance().stream().map(a -> new DistanceDTO(a))
				.collect(Collectors.toCollection(() -> result));
		return result;
	}

	@Override
	public Collection<CommonSeverityDTO> getCommonConditions() {
		Collection<CommonSeverityDTO> result = new ArrayList<CommonSeverityDTO>();

		this.getAccidentRepository().getCommonConditions().stream().map(a -> new CommonSeverityDTO(a))
				.collect(Collectors.toCollection(() -> result));
		return result;
	}

	@Override
	public Collection<PuntoDTO> getAccidentsDangerousPointsInRadius(Float pLatitud, Float pLongitud, Integer pRadius) {
		Collection<PuntoDTO> result = new ArrayList<PuntoDTO>();

		this.getAccidentRepository().getAccidentsDangerousPointsInRadius(pLatitud, pLongitud, pRadius).stream().map(a -> new PuntoDTO(a))
				.collect(Collectors.toCollection(() -> result));
		return result;
	}

	public IAccidentRepository getAccidentRepository() {
		return this.accidentRepository;
	}

	public void setAccidentRepository(IAccidentRepository aRepository) {
		this.accidentRepository = aRepository;
	}

}