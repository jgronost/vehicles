package prv.jgronost.vehicles.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.ProcessingException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;

public class VehiclesApplication extends ResourceConfig {
	public VehiclesApplication() {
		super(VehiclesResource.class, JacksonFeature.class);
	}
	
	public static void main(String[] args) throws IOException, ProcessingException, URISyntaxException {
        // Initialize Grizzly HttpServer
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(new URI("http://localhost:1234/") , new VehiclesApplication()); // new HttpServer(); //GrizzlyHttpServerFactory.createHttpServer(new URI("http://localhost:1234/") , new VehiclesApplication());
//        NetworkListener listener = new NetworkListener("grizzly2", "localhost", 1234);
//        server.addListener(listener);
        
        // Initialize and add Spring-aware Jersey resource
        WebappContext ctx = new WebappContext("ctx", "/");
        final ServletRegistration reg = ctx.addServlet("spring", new org.glassfish.jersey.servlet.ServletContainer());
        reg.addMapping("/*");
        ctx.addContextInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
        ctx.addListener("org.springframework.web.context.ContextLoaderListener");
        ctx.addListener("org.springframework.web.context.request.RequestContextListener");
        ctx.deploy(server);
        
//        // Add the StaticHttpHandler to serve static resources from the static1 folder
//        server.getServerConfiguration().addHttpHandler(
//                new StaticHttpHandler("src/main/resources/webapp/static1/"), "/static");
//
//        // Add the CLStaticHttpHandler to serve static resources located at
//        // the static2 folder from the jar file jersey1-grizzly2-spring-1.0-SNAPSHOT.jar
//        server.getServerConfiguration().addHttpHandler(
//                new CLStaticHttpHandler(new URLClassLoader(new URL[] {
//                    new File("target/jersey1-grizzly2-spring-1.0-SNAPSHOT.jar").toURI().toURL()}), "webapp/static2/"),
//                "/jarstatic");

        try {
            server.start();
            
            System.out.println("In order to test the server please try the following urls:");
            System.out.println("http://localhost:1234/vehicles ");
            
            System.out.println();
            System.out.println("Press enter to stop the server...");
            System.in.read();
        } finally {
            server.stop(); //shutdownNow();
        }


		
	}
}
