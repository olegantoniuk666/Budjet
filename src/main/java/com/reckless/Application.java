package com.reckless;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.reckless.config.SpringContainerConfiguration;
//import com.reckless.config.WebMvcConfig;

@SpringBootApplication
@Import(value = {SpringContainerConfiguration.class})

public class Application {
	private static Log logger = LogFactory.getLog(Application.class);
	

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);

	}
}
