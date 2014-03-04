package prv.jgronost.vehicles.service;

import javax.annotation.Resource;

import org.bson.types.ObjectId
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*
import org.springframework.test.context.ContextConfiguration

import com.gmongo.GMongo;

import prv.jgronost.vehicles.dao.VehiclesDao;
import prv.jgronost.vehicles.model.Bicycle
import prv.jgronost.vehicles.model.Vehicle
import spock.lang.Specification;

@ContextConfiguration(locations = "file:target/resources/integrationTest/serviceContext.xml")
public class VehiclesServiceTest extends Specification {
	
	GMongo gMongo;
	
	def setup(){
		gMongo = new GMongo('localhost:27017')
		gMongo.getDB('vehiclesDB').dropDatabase()
	}
	
	@Autowired
	VehiclesDao vehiclesDao;
	
	@Autowired
	VehiclesService vehiclesService;
	
	def 'dependencies are injected'(){
		expect:
		vehiclesDao
		vehiclesService	
	}
	
	def 'test listVehicles'(){
		given:
		def brand = 'bicycle_brand_2'
		def model = 'bicycle_model_2'
		def id = new ObjectId();
		def id_2 = new ObjectId();
		Bicycle bicycle = new Bicycle(_id: id, brand: brand, model: model)
		Bicycle bicycle2 = new Bicycle(_id: id_2, brand: brand+3, model: model+3)
		vehiclesDao.saveBicycle(bicycle)
		vehiclesDao.saveBicycle(bicycle2)
		
		when:
		ArrayList<Vehicle> vehicles = vehiclesService.listVehicles()
		
		then:
		vehicles 
		vehicles.empty != true
		vehicles.size == 2
		vehicles.each{
			it._id 
		}
	}

}
