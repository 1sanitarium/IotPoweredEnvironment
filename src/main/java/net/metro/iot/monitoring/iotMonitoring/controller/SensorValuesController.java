package net.metro.iot.monitoring.iotMonitoring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor_values")
public class SensorValuesController {

    @RequestMapping(value = "/{values}", method = RequestMethod.POST)
    public String get(@PathVariable("values") String values) throws Exception {
        System.out.println(values);
        return values;
    }

}