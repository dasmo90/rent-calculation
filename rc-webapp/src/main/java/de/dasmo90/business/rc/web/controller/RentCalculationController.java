package de.dasmo90.business.rc.web.controller;

import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.RentCalculationDto;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
import de.dasmo90.business.rc.service.PermissionService;
import de.dasmo90.business.rc.service.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class RentCalculationController extends AbstractWebUserController {

	private static final Logger LOG = LoggerFactory.getLogger(RentCalculationController.class);

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private PermissionService permissionService;

	@RequestMapping(path = "/create", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RentCalculation> create() {
		User user = retrieveUser();
		RentCalculation rentCalculation = this.persistenceService.create(user);
		LOG.debug("User {} created calculation.", user.getName());
		return new ResponseEntity<>(rentCalculation, HttpStatus.OK);
	}

	@RequestMapping(path = "/fetch", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RentCalculation> fetch(@RequestParam Long id) {
		User user = retrieveUser();
		AuditableRentCalculation auditableRentCalculation =
				this.persistenceService.fetchAuditableRentCalculation(id);
		RentCalculationPermission permission =
				this.permissionService.fetchPermission(user, auditableRentCalculation);
		if (permission.getCanRead()) {
			LOG.debug("User {} fetched calculation.", user.getName());
			return new ResponseEntity<>(auditableRentCalculation, HttpStatus.OK);
		} else {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST,
			produces = "application/json", consumes = "application/json")
	public ResponseEntity<RentCalculation> save(@RequestBody RentCalculationDto rentCalculation) {
		User user = retrieveUser();
		AuditableRentCalculation auditableRentCalculation =
				this.persistenceService.fetchAuditableRentCalculation(rentCalculation.getId());
		RentCalculationPermission permission =
				this.permissionService.fetchPermission(user, auditableRentCalculation);
		if (permission.getCanUpdate()) {
			this.persistenceService.save(user, auditableRentCalculation);
			LOG.debug("User {} saved calculation.", user.getName());
			return new ResponseEntity<>(auditableRentCalculation, HttpStatus.OK);
		} else {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
	}

}
