package net.metro.iot.monitoring.iotMonitoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.metro.iot.monitoring.iotMonitoring.dto.SensorValueDto;
import net.metro.iot.monitoring.iotMonitoring.service.SensorValueService;

@RestController
@RequestMapping("/sensor_value")
public class SensorValuesController {

    @Autowired
    private SensorValueService sensorValueService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void get(@RequestBody SensorValueDto sensorValue) throws Exception {
        sensorValueService.save(sensorValue);
    }

}