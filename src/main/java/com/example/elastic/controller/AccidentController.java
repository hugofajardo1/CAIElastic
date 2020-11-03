package com.example.elastic.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import com.example.elastic.dto.AccidentDTO;
import com.example.elastic.dto.CommonSeverityDTO;
import com.example.elastic.dto.DistanceDTO;
import com.example.elastic.dto.PuntoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.elastic.services.IAccidentService;

@RestController
public class AccidentController {

	@Inject
	private IAccidentService accidentsService;

	@GetMapping(value = "/api/accident/dates")
	public ResponseEntity<?> getAccidentsBetween(@RequestParam String desde,@RequestParam String hasta) {

		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		result.addAll(this.getAccidentsService().getAccidentsBetween(desde, hasta));
		response = ResponseEntity.ok(result);

		return response;
	}


	@GetMapping(value = "/api/accident/radius")
	public ResponseEntity<?> getAccidentsInRadius(@RequestParam Float latitud, @RequestParam Float longitud, @RequestParam Integer radius) {

		ResponseEntity<?> response = null;
		Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
		result.addAll(this.getAccidentsService().getAccidentsInRadius(latitud, longitud, radius));
		response = ResponseEntity.ok(result);

		return response;
	}

	@GetMapping(value = "/api/accident/average")
	public ResponseEntity<?> getAverageDistance() {

		ResponseEntity<?> response = null;
		Collection<DistanceDTO> result = new ArrayList<DistanceDTO>();
		result.addAll(this.getAccidentsService().getAverageDistance());
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/api/accident/common")
	public ResponseEntity<?> getCommonConditions() {

		ResponseEntity<?> response = null;
		Collection<CommonSeverityDTO> result = new ArrayList<CommonSeverityDTO>();
		result.addAll(this.getAccidentsService().getCommonConditions());
		response = ResponseEntity.ok(result);
		return response;
	}

	@GetMapping(value = "/api/accident/dangerousPointsInRadio")
	public ResponseEntity<?> getAccidentsDangerousPointsInRadius(@RequestParam Float latitud, @RequestParam Float longitud, @RequestParam Integer radius) {

		ResponseEntity<?> response = null;
		Collection<PuntoDTO> result = new ArrayList<PuntoDTO>();
		result.addAll(this.getAccidentsService().getAccidentsDangerousPointsInRadius(latitud, longitud, radius));
		response = ResponseEntity.ok(result);

		return response;
	}

	public IAccidentService getAccidentsService() {
		return this.accidentsService;
	}

}
