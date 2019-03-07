package com.rsouza01.waesscalableweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rsouza01.waesscalableweb.util.PropertyLogger;

import lombok.extern.slf4j.Slf4j;

/***
 * The Spring boot application.
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@SpringBootApplication
@Slf4j
public class WaesScalableWebApplication {

	
	
	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(WaesScalableWebApplication .class);
		springApplication.addListeners(new PropertyLogger());
		springApplication.run(args);        

	}

}

