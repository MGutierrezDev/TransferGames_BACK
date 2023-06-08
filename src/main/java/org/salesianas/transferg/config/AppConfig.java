package org.salesianas.transferg.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class AppConfig {
	@Bean
	public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
