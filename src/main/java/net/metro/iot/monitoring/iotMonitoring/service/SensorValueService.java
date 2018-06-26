package net.metro.iot.monitoring.iotMonitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.metro.iot.monitoring.iotMonitoring.dao.SensorValueDao;
import net.metro.iot.monitoring.iotMonitoring.domain.SensorValue;
import net.metro.iot.monitoring.iotMonitoring.dto.SensorValueDto;

@Service
public class SensorValueService {

    private final SensorValueDao sensorValueDao;

    @Autowired
    public SensorValueService(SensorValueDao sensorValueDao) {
        this.sensorValueDao = sensorValueDao;
    }

    public void save(SensorValueDto sensorValueDto) {
        SensorValue sensorConfig = new SensorValue();

        sensorValueDao.save(sensorConfig);
    }

}
