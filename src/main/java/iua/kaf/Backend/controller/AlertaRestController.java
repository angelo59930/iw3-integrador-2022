package iua.kaf.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import iua.kaf.Backend.model.Alerta;
import iua.kaf.Backend.model.business.IAlertaBusiness;
import iua.kaf.Backend.model.business.exception.BusinessException;
import iua.kaf.Backend.model.business.exception.FoundException;
import iua.kaf.Backend.model.business.exception.NotFoundException;
import iua.kaf.Backend.util.IStandardResponseBusiness;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(Constantes.URL_ALERTA)
public class AlertaRestController {

	@Autowired
	private IStandardResponseBusiness responseBusiness;
	
	
	@Autowired
	private IAlertaBusiness alertaBusiness;

	@PostMapping(value = "")
	public ResponseEntity<?> add(@RequestBody Alerta alerta){
		
		try {
			
			Alerta response = alertaBusiness.add(alerta);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_ALERTA + "/" + response.getId());
			return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
			
		} catch (FoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);
			
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		
		try {
			
			alertaBusiness.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
			

		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> load(@PathVariable("id") long id){
		
		try {
			
			return new ResponseEntity<>(alertaBusiness.load(id), HttpStatus.OK);
			

		} catch (NotFoundException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list(){
		
		try {
			return new ResponseEntity<>(alertaBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(responseBusiness.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}