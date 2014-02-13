package prv.jgronost.vehicles.model

import groovy.transform.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
class Bicycle extends Vehicle {
	public static String VEHICLE_TYPE;
	boolean sideWheels;
	int derailleurGears;

	public Bicycle() {
		type = VEHICLE_TYPE; 
	}
	
}
