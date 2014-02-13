
package prv.jgronost.vehicles.dao;

import org.bson.types.ObjectId;

import prv.jgronost.vehicles.model.Bicycle;

public interface VehiclesDao {
	public void saveBicycle(Bicycle bicycle);
	public Bicycle findBicycle(String brand, String model);
	public Bicycle findBicycle(ObjectId id);
}
