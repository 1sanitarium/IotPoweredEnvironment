package net.metro.iot.monitoring.iotMonitoring.service;

import java.lang.reflect.Field;
import java.util.*;

import com.google.common.collect.ImmutableMap;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.distribution.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.metro.iot.monitoring.iotMonitoring.dao.SensorValueDao;
import net.metro.iot.monitoring.iotMonitoring.domain.SensorValue;
import net.metro.iot.monitoring.iotMonitoring.dto.SensorValueDto;
import net.metro.iot.monitoring.iotMonitoring.util.UnitsEnum;

@Service
public class SensorValueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorValueService.class);

    private final SensorValueDao sensorValueDao;

    private final MeterRegistry registry;

    private final Map<String,Map<String, String>> configMap = new HashMap<>();

    @Autowired
    public SensorValueService(SensorValueDao sensorValueDao, MeterRegistry registry) {
        this.sensorValueDao = sensorValueDao;
        this.registry = registry;


        configMap.put("wemo1", ImmutableMap.of(
                "Location1", "Bucuresti",
                "Sensor", "SPG30",
                "Location2", "Etaj 7",
                "Location3", "Agile Room"
        ));

        configMap.put("vemo2", ImmutableMap.of(
                "Location1", "Brasov",
                "Sensor", "SPG30",
                "Location2", "Business Park",
                "Location3", "Metro 1"
        ));
    }

    public void save(SensorValueDto sensorValueDto) {
        Field[] fields = SensorValueDto.class.getDeclaredFields();
        LOGGER.info(fields.toString());
        Arrays.asList(fields).stream().filter(filter -> !filter.getName().equals("deviceId")).forEach(field -> {
            field.setAccessible(true);
            String value;
            try {
                value = (String) field.get(sensorValueDto);
                LOGGER.info(value);

                if (Objects.nonNull(value)) {
                    SensorValue sensorValue = new SensorValue(sensorValueDto.getDeviceId(), value, new Date(), UnitsEnum.getUnit(field.getName().toUpperCase()),
                            field.getName());
                    sensorValueDao.save(sensorValue);
                    DistributionSummary.Builder builder = DistributionSummary.builder("iot.sensorData.histogram")
                            .description("Histogram of values")
                            .baseUnit(UnitsEnum.getUnit(field.getName().toUpperCase()))
                            .tag("unit", UnitsEnum.getUnit(field.getName().toUpperCase()))
                            .tag("type", field.getName());

                    Map<String, String> tags = configMap.get(sensorValueDto.getDeviceId());

                    tags.forEach( (k,v) -> builder.tags(k, v));

                    DistributionSummary summary = builder.register(registry);
                    summary.record(Long.valueOf(value));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                LOGGER.info(e.toString());
            }

        });
    }

}
