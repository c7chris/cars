/**
 * 
 */
package com.chris.cars.exposure;

import java.util.HashMap;
import java.util.Map;

import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.chris.cars.data.Car;
import com.chris.cars.exception.CarNotFoundException;
import com.chris.cars.service.CarService;

/**
 * @author chris
 *
 */
@RestController
public class CarControllerImpl implements CarController {
	
	private CarService carService;

	/**
	 * @param carService
	 */
	@Autowired
	public CarControllerImpl(CarService carService) {
		super();
		this.carService = carService;
	}

	@Override
	public ResponseEntity<String> addCar(@Valid @RequestBody Car car) {
		carService.addCar(car);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Car added");
	}

	@Override
	public ResponseEntity<Object> retrieveCar(@PathVariable int id) throws CarNotFoundException {
		Car car = null;
		car = carService.retrieveCar(id);
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(car);
	}

	@Override
	public ResponseEntity<String> removeCar(@PathVariable int id) throws CarNotFoundException {
		if(id == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Please provide a valid car id.");
		}
		carService.removeCar(id);
		return ResponseEntity.ok("Car with id "+ id +" removed");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	@ExceptionHandler({CarNotFoundException.class})
	public ResponseEntity<String> handleCarNotFoundException() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No such car found");
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> handleException() {
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body("Unable to process request.");
	}

	@Override
	public ResponseEntity<String> updateCar(@RequestBody Car car) throws CarNotFoundException {
		carService.updateCar(car);
		return ResponseEntity.ok("Car with id "+ car.getId() +" updated");	}

}
