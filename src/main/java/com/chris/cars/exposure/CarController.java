/**
 * 
 */
package com.chris.cars.exposure;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chris.cars.data.Car;
import com.chris.cars.exception.CarNotFoundException;

/**
 * @author chris
 *
 */

@RequestMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CarController {
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> addCar(Car car);
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Object> retrieveCar(int id) throws CarNotFoundException;
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<String> removeCar(int id) throws CarNotFoundException;

}
