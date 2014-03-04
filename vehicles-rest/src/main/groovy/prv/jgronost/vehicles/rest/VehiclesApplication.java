package prv.jgronost.vehicles.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;


public class VehiclesApplication extends ResourceConfig {
	public VehiclesApplication() {
		super(VehiclesResource.class, JacksonFeature.class);
	}
	
}
