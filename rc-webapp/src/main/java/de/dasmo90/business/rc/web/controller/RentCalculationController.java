package de.dasmo90.business.rc.web.controller;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.RentCalculationDto;
import de.dasmo90.business.rc.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentCalculationController {

	@Autowired
	private PersistenceService persistenceService;

	@RequestMapping(path = "/create", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RentCalculation> create() {
		RentCalculation rentCalculation = this.persistenceService.create();
		return new ResponseEntity<>(rentCalculation, HttpStatus.OK);
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST,
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<RentCalculation> save(@RequestBody RentCalculationDto rentCalculation) {
		return new ResponseEntity<>(rentCalculation, HttpStatus.OK);
	}

}
