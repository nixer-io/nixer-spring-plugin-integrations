package io.nixer.springplugin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.export.influx.InfluxMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = InfluxMetricsExportAutoConfiguration.class)
public class DemoApplication {

    public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
        }

}
