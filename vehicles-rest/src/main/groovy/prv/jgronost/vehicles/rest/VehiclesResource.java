package prv.jgronost.vehicles.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import prv.jgronost.vehicles.model.Vehicle;
import prv.jgronost.vehicles.service.VehiclesService;

@Path(value = "/vehicles")
public class VehiclesResource {
	@Autowired
	private VehiclesService vehiclesService;
	
	@GET @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Vehicle> findAll(){
		//return new ArrayList<Vehicle>();
		return vehiclesService.listVehicles();
	}

}
