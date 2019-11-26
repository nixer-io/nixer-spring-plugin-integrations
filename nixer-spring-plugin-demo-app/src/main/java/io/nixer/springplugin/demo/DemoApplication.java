package io.nixer.springplugin.demo;

import io.nixer.nixerplugin.core.detection.events.log.EventLogConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(EventLogConfiguration.class)
public class DemoApplication {

    public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
        }

}
