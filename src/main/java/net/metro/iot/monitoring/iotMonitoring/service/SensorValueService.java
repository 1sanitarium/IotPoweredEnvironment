package net.metro.iot.monitoring.iotMonitoring.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;

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
        Field[] fields = SensorValueDto.class.getFields();
        Arrays.asList(fields).stream().filter(filter -> filter.getName().equals("devideId")).forEach(field -> {
            field.setAccessible(true);
            String value = "";
            try {
                value = (String) field.get(sensorValueDto);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            SensorValue sensorValue = new SensorValue(sensorValueDto.getDeviceId(), value, new Date(), null, field.getName());

            if (!sensorValue.getValue().isEmpty()) {
                sensorValueDao.save(sensorValue);
            }

        });
    }

}
