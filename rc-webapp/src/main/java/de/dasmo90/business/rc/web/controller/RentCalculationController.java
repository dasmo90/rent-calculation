package de.dasmo90.business.rc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentCalculationController {

	@RequestMapping(path = "/create", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Void> create() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
