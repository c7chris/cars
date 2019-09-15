/**
 * 
 */
package com.chris.cars.service.objects;

/**
 * @author chris
 *
 */
public class CarResponse {

	private int id;
	
	private String make;
	
	private Model model;
	
	private String colour;
	
	private int year;

	/**
	 * @param id
	 * @param make
	 * @param colour
	 * @param year
	 */
	public CarResponse(int id, String make, String colour, int year) {
		super();
		this.id = id;
		this.make = make;
		this.colour = colour;
		this.year = year;
	}

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
	public Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(Model model) {
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
	
}
