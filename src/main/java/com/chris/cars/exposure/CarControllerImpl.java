/**
 * 
 */
package com.chris.cars.exposure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<String> addCar(@RequestBody Car car) {
		if(null == car) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Please provide a valid car request object.");
		}
		carService.addCar(car);
		return ResponseEntity.ok("Car added");
	}

	@Override
	public ResponseEntity<Object> retrieveCar(@PathVariable int id) throws CarNotFoundException {
		Car car = null;
		car = carService.retrieveCar(id);
		return ResponseEntity.ok(car);
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

}
