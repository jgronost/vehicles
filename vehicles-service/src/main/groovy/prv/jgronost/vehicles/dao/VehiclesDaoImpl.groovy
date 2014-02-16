package prv.jgronost.vehicles.dao

import java.util.ArrayList;

import org.bson.types.ObjectId;

import prv.jgronost.vehicles.model.Bicycle;
import prv.jgronost.vehicles.model.Vehicle;

import com.gmongo.GMongo
import com.mongodb.DB
import com.mongodb.DBCursor
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Repository

@Repository
class VehiclesDaoImpl implements VehiclesDao {
	static String dbName = 'vehiclesDB'
	GMongo gMongo 
	
	
	def getVehicles(){
		List vehicles = []
		def DB db = getDb()
		db.vehicles.findAll()
		
	}

	@Override
	public void saveBicycle(Bicycle bicycle) {
		// TODO Auto-generated method stub
		def DB db = getDb()
		db.vehicles << bicycle.properties.findAll { !['class', 'metaClass'].contains(it.key) }

		
	}

	@Override
	public Bicycle findBicycle(String brand, String model) {
		def DB db = getDb()
		return db.vehicles.findOne(brand: brand, model: model)//.findAll { it.key != '_id' }
		
	}
	

	@Override
	public Bicycle findBicycle(ObjectId id) {
		def DB db = getDb()
		return db.vehicles.findOne(_id: id);
	}
	
	def DB getDb(){
		DB db = gMongo.getDB(dbName)
	}

	@Override
	public ArrayList<Vehicle> listVehicles() {
		def DB db = getDb();
		DBCursor cursor = db.vehicles.find()
		List<Vehicle> vehicles = new ArrayList<Vehicle>()
		while (cursor.hasNext()){
			vehicles << cursor.next()
		}
		return vehicles
	}
	
	
}
