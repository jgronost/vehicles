package prv.jgronost.vehicles.dao

import com.gmongo.GMongo
import org.bson.types.ObjectId
import prv.jgronost.vehicles.model.Bicycle
import prv.jgronost.vehicles.model.Vehicle
import spock.lang.Specification;
import javax.annotation.Resource;

class VehiclesDaoImplSpec extends Specification {
	GMongo gMongo;
	
	def setup(){
		gMongo = new GMongo('localhost:27017')
		gMongo.getDB(VehiclesDaoImpl.dbName).dropDatabase()
	}
	
	def cleanup(){
		gMongo.getDB(VehiclesDaoImpl.dbName).dropDatabase()
	}
	
	def 'saveBicycle and findBicycle by brand+model '(){
		given:
		def brand = 'bicycle_brand_1'
		def model = 'bicycle_model_1'
		Bicycle bicycle = new Bicycle( brand: brand, model: model) 
		VehiclesDao dao = new VehiclesDaoImpl(gMongo: gMongo)
		
		when:
		dao.saveBicycle(bicycle)
		Bicycle foundBicycle = dao.findBicycle(brand, model)
		
		then:
		foundBicycle != null
		foundBicycle == bicycle
	}
	
	def 'saveBicycle and findBicycle by ObjectId '(){
		given:
		def brand = 'bicycle_brand_2'
		def model = 'bicycle_model_2'
		def id = new ObjectId();
		def id_2 = new ObjectId();
		Bicycle bicycle = new Bicycle(_id: id, brand: brand, model: model)
		Bicycle bicycle2 = new Bicycle(_id: id_2, brand: brand+3, model: model+3)
		VehiclesDao dao = new VehiclesDaoImpl(gMongo: gMongo)
		
		when:
		dao.saveBicycle(bicycle)
		Bicycle foundBicycle = dao.findBicycle(id)
		
		then:
		bicycle != bicycle2
		foundBicycle != null
		foundBicycle == bicycle
		
		when:
		dao.saveBicycle(bicycle2)
		Bicycle foundBicycle2 = dao.findBicycle(id_2)
		
		then:
		foundBicycle2 != null
		foundBicycle2 != foundBicycle
		foundBicycle2 == bicycle2
		foundBicycle2 != bicycle
	
	}
	
	def 'save and retrieve vehicles - Bicycles as for now'(){
		given:
		def brand = 'bicycle_brand_2'
		def model = 'bicycle_model_2'
		def id = new ObjectId();
		def id_2 = new ObjectId();
		Bicycle bicycle = new Bicycle(_id: id, brand: brand, model: model)
		Bicycle bicycle2 = new Bicycle(_id: id_2, brand: brand+3, model: model+3)
		VehiclesDao dao = new VehiclesDaoImpl(gMongo: gMongo)
		dao.saveBicycle(bicycle)
		dao.saveBicycle(bicycle2)

		
		when:
		ArrayList<Vehicle> vehicles = dao.listVehicles()
		
		then:
		vehicles != null
		vehicles.size == 2
		vehicles.each{ 
			it._id != null
			it.brand.empty != true
			it.model.empty != true
		}
		
	}
	
	
	
	

}
