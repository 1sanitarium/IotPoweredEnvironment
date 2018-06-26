//package net.metro.iot.monitoring.iotMonitoring.datadog.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.micrometer.core.instrument.MeterRegistry;
//import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
//import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
//import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
//import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
//import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
//
//@Configuration
//public class DataDogConfig {
//
//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> registryCustomizer(@Value("${metrics.serviceName}") String serviceName, @Value("${metrics.host}") String hostName,
//            @Value("${metrics.vertical}") String verticalName, @Value("${metrics.stage}") String stageName) {
//        return registry -> {
//            registry.config().commonTags("service", serviceName, "host ", hostName, "vertical", verticalName, "stage", stageName);
//
//            new JvmMemoryMetrics().bindTo(registry);
//            new ClassLoaderMetrics().bindTo(registry);
//            new JvmGcMetrics().bindTo(registry);
//            new ProcessorMetrics().bindTo(registry);
//            new JvmThreadMetrics().bindTo(registry);
//            //new HazelcastCacheMetrics().bindTo(registry);
//        };
//    }
//
//    //    @Bean
//    //    public TimedAspect timedAspect(MeterRegistry registry) {
//    //        return new TimedAspect(registry);
//    //    }
//
//}