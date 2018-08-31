package com.car.predict.domain.object;

import java.util.Date;

public class Car {

	int kilometers;
	int owner;
	RCType rcType;
	EngineType engineType;
	TransmissionType transmissionType;
	Date registrationyear;
	String city;
	ModelType modelType;
	float price;
	public int getKilometers() {
		return kilometers;
	}
	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public RCType getRcType() {
		return rcType;
	}
	public void setRcType(RCType rcType) {
		this.rcType = rcType;
	}
	public EngineType getEngineType() {
		return engineType;
	}
	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}
	public TransmissionType getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(TransmissionType transmissionType) {
		this.transmissionType = transmissionType;
	}
	public Date getRegistrationyear() {
		return registrationyear;
	}
	public void setRegistrationyear(Date registrationyear) {
		this.registrationyear = registrationyear;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public ModelType getModelType() {
		return modelType;
	}
	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	
	
	
}
