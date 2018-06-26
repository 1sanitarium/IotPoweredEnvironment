package net.metro.iot.monitoring.iotMonitoring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor_value")
public class SensorValuesController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(SensorValuesController.class);

    //    @Autowired
    //    private SensorValueService sensorValueService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(HttpServletRequest request) throws Exception {
        throw new Exception(request.toString());
    }

}