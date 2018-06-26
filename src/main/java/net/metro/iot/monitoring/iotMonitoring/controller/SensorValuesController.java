package net.metro.iot.monitoring.iotMonitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor_value")
public class SensorValuesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorValuesController.class);

    //    @Autowired
    //    private SensorValueService sensorValueService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void get(@RequestBody String sensorValue) throws Exception {
        LOGGER.info(sensorValue);
    }

}