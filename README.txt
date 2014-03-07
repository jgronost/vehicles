gradle test
gradle build
#to create elipse projects:
gradle eclipse

#to clean
gradle cleanEclipse

#to run jetty
gradle :vehicles-rest:jettyRun
and type in browser: http://localhost:8081/vehicles-rest/vehicles

#then in separate window
gradle :vehicles-web:jettyRun
http://localhost:8080/vehicles-web