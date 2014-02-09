package prv.jgronost.vehicles.dao

import com.gmongo.GMongo
import prv.jgronost.vehicles.model.Bicycle
import spock.lang.Specification;

class VehiclesDaoImplSpec extends Specification {
	GMongo gMongo;
	
	def setup(){
		gMongo = new GMongo('localhost:27017')
		gMongo.getDB(VehiclesDaoImpl.dbName).dropDatabase()
	}
	
	def 'saveBicycle and findBicycle by Id'(){
		given:
		def brand = 'bicycle_brand_1'
		def model = 'bicycle_model_1'
		Bicycle bicycle = new Bicycle(brand: brand, model: model) 
		VehiclesDao dao = new VehiclesDaoImpl(gMongo: gMongo)
		
		when:
		dao.saveBicycle(bicycle)
		Bicycle foundBicycle = dao.findBicycle(brand, model)
		
		then:
		foundBicycle != null
		foundBicycle == bicycle
	}

}
