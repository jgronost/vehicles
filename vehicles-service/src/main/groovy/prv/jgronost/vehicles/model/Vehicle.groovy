package prv.jgronost.vehicles.model

import groovy.transform.EqualsAndHashCode;
import org.bson.types.ObjectId

@EqualsAndHashCode (excludes = "_id")
abstract class Vehicle {
	ObjectId _id;
	String type;
	String brand;
	String model;
	String description;
	String colour;
	// in m/s
	double accleraton;
	double maxSpeed;
	

}
