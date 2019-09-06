package com.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.classmate.TypeResolver;
import com.menu.util.LoggingInterceptor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }
	/*
	 * @Bean public Docket docket() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
	 * .paths(PathSelectors.any()) .build().apiInfo(generateApiInfo()); }
	 */

	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build();                                           
	    }
	private ApiInfo generateApiInfo() {
		return new ApiInfo("food menu app",
				"This service is to check the technology knowledge of a server applicant for menu app.",
				"Version 1.0 - mw", "urn:tos", "test", "Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0");
	}
	 @Bean
	    public TypeResolver typeResolver(){
	        return new TypeResolver();
	    }

	    @Bean
	    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
	        return new RequestMappingHandlerMapping();
	    }
}
