package de.dasmo90.business.rc.web.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("de.dasmo90.business.rc")
public class SpringWebConfiguration {

}
