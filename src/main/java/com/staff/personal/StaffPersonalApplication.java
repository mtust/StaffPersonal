package com.staff.personal;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@ComponentScan
public class StaffPersonalApplication extends SpringBootServletInitializer {
	
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(StaffPersonalApplication.class);
//	}
//
//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
//		return hemf.getSessionFactory();
//	}

	public static void main(String[] args) {
		SpringApplication.run(StaffPersonalApplication.class, args);
	}
}
