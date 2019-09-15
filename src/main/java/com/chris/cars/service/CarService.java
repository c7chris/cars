/**
 * 
 */
package com.chris.cars.service;

import com.chris.cars.data.Car;
import com.chris.cars.exception.CarNotFoundException;
import com.chris.cars.service.objects.CarResponse;

/**
 * @author chris
 *
 */
public interface CarService {
	
	Car addCar(Car car);
	
	CarResponse retrieveCar(int id) throws CarNotFoundException;
	
	void removeCar(int id) throws CarNotFoundException;
	
	void updateCar(Car car) throws CarNotFoundException;

}
