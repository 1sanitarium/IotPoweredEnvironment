package net.metro.iot.monitoring.iotMonitoring.controller;

import javax.servlet.http.HttpServletRequest;

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

    //private static final Logger LOGGER = LoggerFactory.getLogger(SensorValuesController.class);

    @Autowired
    private SensorValueService sensorValueService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody SensorValueDto sensorValueDto, HttpServletRequest request) throws Exception {
        sensorValueService.save(sensorValueDto);
    }

}