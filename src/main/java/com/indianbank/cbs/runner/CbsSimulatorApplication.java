package com.indianbank.cbs.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.indianbank.cbs"})
@SpringBootApplication
public class CbsSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbsSimulatorApplication.class, args);
	}

}
