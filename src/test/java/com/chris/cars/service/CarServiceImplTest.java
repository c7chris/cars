/**
 * 
 */
package com.chris.cars.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.chris.cars.data.Car;
import com.chris.cars.data.CarRepository;
import com.chris.cars.exception.CarNotFoundException;
import com.chris.cars.service.objects.DatamuseWord;

/**
 * @author chris
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

	@Mock
    private RestTemplate restTemplate;
 
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
	
	private DatamuseWord createDatamuseWord(String word, int score, int numSyllables) {
		DatamuseWord datamuseWord = new DatamuseWord();
		datamuseWord.setWord(word);
		datamuseWord.setScore(score);
		datamuseWord.setNumSyllables(numSyllables);
		return datamuseWord;
	}
	
	@Test
	public void shouldCreateCar() {
		carService.addCar(new Car());
		Mockito.verify(carRepository).save(any(Car.class));
	}
	
	@Test
	public void shouldRetrieveCar() throws CarNotFoundException {
		
		DatamuseWord datamuseWord1 = createDatamuseWord("fortune", 90, 2);
		DatamuseWord datamuseWord2 = createDatamuseWord("foreigner", 90, 3);
		List<DatamuseWord> datamuseWords = new ArrayList<>();
		datamuseWords.add(datamuseWord1);
		datamuseWords.add(datamuseWord2);
		
        Mockito.when(restTemplate.exchange(
				"https://api.datamuse.com/words?sl=Fortuner&max=10",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<DatamuseWord>>(){}))
        .thenReturn(ResponseEntity.status(HttpStatus.FOUND)
				.body(datamuseWords));
        
		carService.retrieveCar(1);
		Mockito.verify(carRepository).findById(anyInt());
	}
	
	@Test
	public void shouldRemoveCar() throws CarNotFoundException {
		carService.removeCar(1);
		Mockito.verify(carRepository).deleteById(anyInt());
	}

	@Test
	public void shouldUpdateCar() throws CarNotFoundException {
		carService.updateCar(new Car());
		Mockito.verify(carRepository).save(any(Car.class));
	}
	
	@Test
	public void shouldNotUpdateNonExistantCar() {
		Mockito.when(carRepository.findById(anyInt()))
		.thenReturn(Optional.ofNullable(null));
	
		assertThatExceptionOfType(CarNotFoundException.class)
		.isThrownBy(() -> { carService.updateCar(new Car()); }); 
		
		Mockito.verify(carRepository, never()).save(any(Car.class));
	}
	
}
