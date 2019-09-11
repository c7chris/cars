/**
 * 
 */
package com.chris.cars.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.chris.cars.data.Car;
import com.chris.cars.data.CarRepository;
import com.chris.cars.exception.CarNotFoundException;

/**
 * @author chris
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {
	
	@Mock
	CarRepository carRepository;
	
	@InjectMocks
	CarServiceImpl carService;
	
	@Before
	public void setUp() {
		Car car = createCar(1, "Grey", "Toyota", "Fortuner", 2016);
		Mockito.when(carRepository.save(any(Car.class)))
		.thenReturn(car);
		Mockito.doNothing().when(carRepository).deleteById(anyInt());
		Mockito.when(carRepository.findById(anyInt()))
		.thenReturn(Optional.of(car));
	}
	
	private Car createCar(int id, String colour, String make, String model, int year) {
		Car car = new Car();
		car.setId(id);
		car.setColour(colour);
		car.setMake(make);
		car.setModel(model);
		car.setYear(year);
		return car;
	}
	
	@Test
	public void shouldCreateCar() {
		carService.addCar(new Car());
		Mockito.verify(carRepository, times(1)).save(any(Car.class));
	}
	
	@Test
	public void shouldRetrieveCar() throws CarNotFoundException {
		carService.retrieveCar(1);
		Mockito.verify(carRepository, times(1)).findById(anyInt());
	}
	
	@Test
	public void shouldRemoveCar() throws CarNotFoundException {
		carService.removeCar(1);
		Mockito.verify(carRepository, times(1)).deleteById(anyInt());
	}

}
