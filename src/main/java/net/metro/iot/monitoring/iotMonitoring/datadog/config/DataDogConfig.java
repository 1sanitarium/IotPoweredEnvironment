package net.metro.iot.monitoring.iotMonitoring.datadog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.metrics.instrument.binder.ClassLoaderMetrics;
import org.springframework.metrics.instrument.binder.JvmGcMetrics;
import org.springframework.metrics.instrument.binder.JvmMemoryMetrics;
import org.springframework.metrics.instrument.binder.ProcessorMetrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;

@Configuration
public class DataDogConfig {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> registryCustomizer(@Value("${metrics.serviceName}") String serviceName, @Value("${metrics.host}") String hostName,
            @Value("${metrics.vertical}") String verticalName, @Value("${metrics.stage}") String stageName) {
        return registry -> {
            registry.config().commonTags("service", serviceName, "host ", hostName, "vertical", verticalName, "stage", stageName);

            new JvmMemoryMetrics().bindTo((org.springframework.metrics.instrument.MeterRegistry) registry);
            new ClassLoaderMetrics().bindTo((org.springframework.metrics.instrument.MeterRegistry) registry);
            new JvmGcMetrics().bindTo((org.springframework.metrics.instrument.MeterRegistry) registry);
            new ProcessorMetrics().bindTo((org.springframework.metrics.instrument.MeterRegistry) registry);
            new JvmThreadMetrics().bindTo(registry);
            //new HazelcastCacheMetrics().bindTo(registry);
        };
    }

    //    @Bean
    //    public TimedAspect timedAspect(MeterRegistry registry) {
    //        return new TimedAspect(registry);
    //    }

}