package com.staff.personal;

import com.staff.personal.security.JwtFilter;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@ComponentScan
public class StaffPersonalApplication {
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(StaffPersonalApplication.class);
//	}
//
//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
//		return hemf.getSessionFactory();
//	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(StaffPersonalApplication.class, args);
	}
}
