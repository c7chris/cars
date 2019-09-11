/**
 * 
 */
package com.chris.cars.exposure;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.chris.cars.data.Car;
import com.chris.cars.service.CarServiceImpl;

/**
 * @author chris
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CarControllerImpl.class)
public class CarControllerImplTest {
	
    @Autowired
    private MockMvc mvc;

    @MockBean
	CarServiceImpl carService;
    
    @Test
    public void shouldAddCar() throws Exception {
    	Car car = createCar(1, "Black", "Hyundai", "Creta", 2018);
    	Mockito.doNothing().when(carService).addCar(any(Car.class));
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.post("/car")
    			.content(CarToJSON(car))
    			.contentType(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mvc.perform(requestBuilder).andReturn();
    	MockHttpServletResponse response = result.getResponse();
    	
    	assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    
    @Test
    public void shouldNotAddInvalidCar() throws Exception {
    	Mockito.doNothing().when(carService).addCar(any(Car.class));
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.post("/car")
    			.content("{\"make\": \"Nissan\"}")
    			.contentType(MediaType.APPLICATION_JSON);
    	
    	MvcResult result = mvc.perform(requestBuilder).andReturn();
    	MockHttpServletResponse response = result.getResponse();
    	
    	assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
	
	@Test
	public void shouldRetrieveCar() throws Exception {
		Car car = createCar(1, "Black", "Hyundai", "Creta", 2018);
		Mockito.when(carService.retrieveCar(anyInt())).thenReturn(car);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/car/1")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals("{'id': 1,'make': 'Hyundai','model': 'Creta', 'colour': 'Black', 'year': 2018}", 
				result.getResponse().getContentAsString(), false);
		assertEquals(HttpStatus.FOUND.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void shouldDeleteCar() throws Exception {
		Mockito.doNothing().when(carService).removeCar(anyInt()); 
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/car/1")
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		Mockito.verify(carService, times(1)).removeCar(anyInt());
		
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
	
	private String CarToJSON(Car car) {
		return "{\"make\": \""+car.getMake()+"\",\"model\": \""+car.getModel()+"\",\"colour\": \""
	+car.getColour()+"\",\"year\": "+car.getYear()+"}";
	}
}
