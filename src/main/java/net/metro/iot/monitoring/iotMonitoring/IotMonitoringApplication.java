package net.metro.iot.monitoring.iotMonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class IotMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IotMonitoringApplication.class, args);
	}
}
