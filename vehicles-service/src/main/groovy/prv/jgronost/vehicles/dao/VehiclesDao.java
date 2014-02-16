
package prv.jgronost.vehicles.dao;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import prv.jgronost.vehicles.model.Bicycle;
import prv.jgronost.vehicles.model.Vehicle;

public interface VehiclesDao {
	public void saveBicycle(Bicycle bicycle);
	public Bicycle findBicycle(String brand, String model);
	public Bicycle findBicycle(ObjectId id);
	public ArrayList<Vehicle> listVehicles();
}
