package net.metro.iot.monitoring.iotMonitoring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.metro.iot.monitoring.iotMonitoring.dao.SensorConfigDao;
import net.metro.iot.monitoring.iotMonitoring.domain.SensorConfig;
import net.metro.iot.monitoring.iotMonitoring.dto.SensorConfigDto;

@Service
public class SensorConfigService {

    private final SensorConfigDao sensorConfigDao;

    @Autowired
    public SensorConfigService(SensorConfigDao sensorConfigDao) {
        this.sensorConfigDao = sensorConfigDao;
    }

    public void save(SensorConfigDto sensorConfigDto) {
        SensorConfig sensorConfig = new SensorConfig();
        sensorConfig.setId(sensorConfigDto.getId());
        sensorConfig.setLocation(sensorConfigDto.getLocation());
        sensorConfig.setSensor(sensorConfigDto.getSensor());
        sensorConfigDao.save(sensorConfig);
    }

    public SensorConfigDto getByPrimaryKey(String id) throws Exception {
        return sensorConfigDao.getByPrimaryKey(id).toDto();
    }

    public List<SensorConfigDto> getAll() {
        return sensorConfigDao.getAll().stream().map(SensorConfig::toDto).collect(Collectors.toList());
    }

}
