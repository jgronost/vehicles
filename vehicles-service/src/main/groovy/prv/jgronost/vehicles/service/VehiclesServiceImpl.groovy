package prv.jgronost.vehicles.service

import java.util.ArrayList;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import prv.jgronost.vehicles.dao.VehiclesDao
import prv.jgronost.vehicles.model.Vehicle;

@Service
class VehiclesServiceImpl implements VehiclesService {
	
	@Resource
	VehiclesDao vehiclesDao

	@Override
	public ArrayList<Vehicle> listVehicles() {
		return vehiclesDao.listVehicles();
	}
	

}
