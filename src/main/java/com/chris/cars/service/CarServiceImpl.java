/**
 * 
 */
package com.chris.cars.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chris.cars.data.Car;
import com.chris.cars.data.CarRepository;
import com.chris.cars.exception.CarNotFoundException;

/**
 * @author chris
 *
 */
@Service
public class CarServiceImpl implements CarService {
	
	private CarRepository carRepository;
	
	@Autowired
	public CarServiceImpl(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}

	@Override
	public void addCar(Car car) {
		carRepository.save(car);

	}

	@Override
	public Car retrieveCar(int id) throws CarNotFoundException {
		return carRepository.findById(id)
				.orElseThrow(() -> new CarNotFoundException());
	}

	@Override
	public void removeCar(int id) throws CarNotFoundException {
		carRepository.findById(id)
		.orElseThrow(() -> new CarNotFoundException());
		carRepository.deleteById(id);
	}

	@Override
	public void updateCar(Car car) throws CarNotFoundException {
		Car existingCar = carRepository.findById(car.getId())
		.orElseThrow(() -> new CarNotFoundException());
		existingCar.update(car);
		carRepository.save(existingCar);
	}

}
