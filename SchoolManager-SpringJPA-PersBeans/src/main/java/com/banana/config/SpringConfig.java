package com.banana.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PropertiesConfig.class, PropertiesConfigDev.class, /*ReposConfig.class,*/ ServicesConfig.class/*, PersistenceConfig.class*/})
@ComponentScan(basePackages = {"com.banana.persistence", "com.banana.services"})
@EntityScan("com.banana.models")
//@Import({StudentsRepository.class, StudentsService.class})
public class SpringConfig {

}
