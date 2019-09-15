/**
 * 
 */
package com.chris.cars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.chris.cars.data.Car;
import com.chris.cars.data.CarRepository;
import com.chris.cars.exception.CarNotFoundException;
import com.chris.cars.service.objects.CarResponse;
import com.chris.cars.service.objects.DatamuseWord;
import com.chris.cars.service.objects.Model;

/**
 * @author chris
 *
 */
@Service
public class CarServiceImpl implements CarService {
	
	private RestTemplate restTemplate;
	
	private CarRepository carRepository;
		
	/**
	 * @param restTemplate
	 * @param carRepository
	 */
	@Autowired
	public CarServiceImpl(RestTemplate restTemplate, CarRepository carRepository) {
		super();
		this.restTemplate = restTemplate;
		this.carRepository = carRepository;
	}

	@Override
	public Car addCar(Car car) {
		return carRepository.save(car);

	}

	@Override
	public CarResponse retrieveCar(int id) throws CarNotFoundException {
		Car car = carRepository.findById(id)
				.orElseThrow(() -> new CarNotFoundException());
		
		CarResponse carResponse = new CarResponse(car.getId()
				, car.getMake()
				, car.getColour()
				, car.getYear());
		
		ResponseEntity<List<DatamuseWord>> response = restTemplate.exchange(
				"https://api.datamuse.com/words?sl="+car.getModel()+"&max=10",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<DatamuseWord>>(){});
		
		String soundsLike = "";
		for(DatamuseWord datamuseWord: response.getBody()) {
			soundsLike += datamuseWord.getWord() + ",";
		}
		
		/*
		 * Remove trailing comma, if any
		 */
		soundsLike = Optional.of(soundsLike)
		   .filter(sStr -> sStr.length() != 0)
		   .map(sStr -> sStr.substring(0, sStr.length() - 1))
		   .orElse(soundsLike);
		
		carResponse.setModel(new Model(car.getModel(), soundsLike));		
		return carResponse;
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
