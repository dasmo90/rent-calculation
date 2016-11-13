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
@RequestMapping(RentCalculationController.BASE_PATH)
public class RentCalculationController extends AbstractWebUserController {

	private static final Logger LOG = LoggerFactory.getLogger(RentCalculationController.class);

	public static final String BASE_PATH = "/web";
	public static final String FETCH_METHOD = "/fetch";
	public static final String CREATE_METHOD = "/create";
	public static final String SAVE_METHOD = "/save";

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private PermissionService permissionService;

	@RequestMapping(path = CREATE_METHOD, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RentCalculation> create() {
		User user = retrieveUser();
		RentCalculation rentCalculation = this.persistenceService.create(user);
		LOG.debug("User {} created calculation with id {}.", user.getName(), rentCalculation.getId());
		return new ResponseEntity<>(rentCalculation, HttpStatus.OK);
	}

	@RequestMapping(path = FETCH_METHOD, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RentCalculation> fetch(@RequestParam Long id) {
		User user = retrieveUser();
		AuditableRentCalculation auditableRentCalculation =
				this.persistenceService.fetchAuditableRentCalculation(id);
		RentCalculationPermission permission =
				this.permissionService.fetchPermission(user, auditableRentCalculation);
		if (permission.getCanRead()) {
			LOG.debug("User {} fetched calculation with id {}.", user.getName(), auditableRentCalculation.getId());
			return new ResponseEntity<>(auditableRentCalculation, HttpStatus.OK);
		} else {
			LOG.debug("User {} was not allowed to fetch calculation with id",
					user.getName(), auditableRentCalculation.getId());
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(path = SAVE_METHOD, method = RequestMethod.POST,
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
