package prv.jgronost.vehicles.rest

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*
import org.springframework.test.context.ContextConfiguration

import prv.jgronost.vehicles.service.VehiclesService;
import spock.lang.Specification
import spock.lang.Shared

@ContextConfiguration(locations = "classpath:applicationContext.xml")
class VehiclesResourceTest extends Specification {
	@Shared static HttpServer server;
	RESTClient client = new RESTClient('http://localhost:1234/', ContentType.JSON);

	
	void setupSpec(){
		server = GrizzlyHttpServerFactory.createHttpServer('http://localhost:1234/'.toURI(), new VehiclesApplication())
	}
	
	void cleanupSpec(){
		server.stop();
	}

	
	def 'server is running'() {
		expect: server.started
	}
	
	def 'findAll returns something'(){
		when:
		def response = client.get(path : 'vehicles')
		
		then:
		response.status == 200
		response.contentType == 'application/json'
		response.data.size() > 0 
		println(response.data)
	}

}
