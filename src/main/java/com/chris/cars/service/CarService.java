/**
 * 
 */
package com.chris.cars.service;

import com.chris.cars.data.Car;
import com.chris.cars.exception.CarNotFoundException;

/**
 * @author chris
 *
 */
public interface CarService {
	
	void addCar(Car car);
	
	Car retrieveCar(int id) throws CarNotFoundException;
	
	void removeCar(int id) throws CarNotFoundException;
	
	void updateCar(Car car) throws CarNotFoundException;

}
