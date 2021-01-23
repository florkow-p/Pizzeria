package pl.pizzeria.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PizzeriaDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaDiscoveryApplication.class, args);
	}

}
