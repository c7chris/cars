/**
 * 
 */
package com.chris.cars.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

/**
 * @author chris
 *
 */
@Entity
public class Car {

	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Make is mandatory")
	private String make;
	
	@NotBlank(message = "Model is mandatory")
	private String model;
	
	@NotBlank(message = "Colour is mandatory")
	private String colour;
	
	private int year;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	public void update(Car car) {
		this.make = StringUtils.isEmpty(car.getMake()) ? this.make : car.getMake();
		this.model = StringUtils.isEmpty(car.getModel()) ? this.model : car.getModel();
		this.colour = StringUtils.isEmpty(car.getColour()) ? this.colour : car.getColour();
		this.year = car.getYear() == 0? this.year : car.getYear();
	}

}
