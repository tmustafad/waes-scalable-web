package com.rsouza01.waesscalableweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Swagger default config for API documentation
 * 
 * @see <a href="https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api">Setting Up Swagger 2 with a Spring REST API</a>
 * 
 * @author Rodrigo Souza (rsouza01)
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	/**
	 * Greeting API
	 *  
	 * @return a Docket with the greeting
	 */
	@Bean
	public Docket greetingApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rsouza01.waesscalableweb.controller"))
				.build()
				.apiInfo(metaData());

	}

	/**
	 * Sets the API metadata for the application
	 * 
	 * @return the API info
	 */
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("WAES Scalable Web - Java Technical Assessment")
				.description("\"Spring Boot REST API for computing differences between base-64 data\"")
				.version("1.0.0")
				.license("MIT License")
				.licenseUrl("https://opensource.org/licenses/MIT\"")
                .contact(new Contact("Rodrigo Souza", "http://rodrigosouza.net.br", "rsouza01@gmail.com"))
				.build();
	}

	/**
	 * Specifies the UI and jar resources for Swagger handling.
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}