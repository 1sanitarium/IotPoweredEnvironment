package net.metro.iot.monitoring.iotMonitoring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.metro.iot.monitoring.iotMonitoring.dto.SensorValueDto;
import net.metro.iot.monitoring.iotMonitoring.service.SensorValueService;

@RestController
@RequestMapping("/sensor_value")
public class SensorValuesController {

    private final MeterRegistry registry;

    private final SensorValueService sensorValueService;

    @Autowired
    public SensorValuesController(MeterRegistry registry, SensorValueService sensorValueService) {
        this.registry = registry;
        this.sensorValueService = sensorValueService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody SensorValueDto sensorValueDto, HttpServletRequest request) throws Exception {

        Counter.builder("iot.sensorConfigService.retrieveALLCounter").register(registry).increment();

        sensorValueService.save(sensorValueDto);
    }
}