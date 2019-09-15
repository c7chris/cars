/**
 * 
 */
package com.chris.cars.service.objects;

/**
 * @author chris
 *
 */
public class Model {
	
	private String modelName;
	
	private String soundsLike;

	/**
	 * @param modelName
	 * @param soundsLike
	 */
	public Model(String modelName, String soundsLike) {
		super();
		this.modelName = modelName;
		this.soundsLike = soundsLike;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the soundsLike
	 */
	public String getSoundsLike() {
		return soundsLike;
	}

	/**
	 * @param soundsLike the soundsLike to set
	 */
	public void setSoundsLike(String soundsLike) {
		this.soundsLike = soundsLike;
	}	

}
