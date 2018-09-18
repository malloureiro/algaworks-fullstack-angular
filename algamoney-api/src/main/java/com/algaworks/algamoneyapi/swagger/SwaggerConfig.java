package com.algaworks.algamoneyapi.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.google.common.base.Predicate;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
@PropertySource("classpath:swagger-docs.properties")
public class SwaggerConfig {
	
	@Bean
	public Docket allApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("algamoney-api/all")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.algaworks.algamoneyapi"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	@Bean
	public Docket pessoaApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("algamoney-api/pessoas")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.algaworks.algamoneyapi"))
				.paths(pessoaPath())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Algamoney Api",
				"Documentação de API Restful da aplicação Algamoney <br> Para mais informações: http://springfox.github.io/springfox/docs/current" ,
				"0.1",
				"TERMS OF SERVICE",
				new Contact("Mariana Loureiro", "https://github.com/malloureiro/algaworks-fullstack-angular", "ma.lloureiro@gmail.com"),
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				Collections.emptyList());
	}
	
	 private Predicate<String> pessoaPath() {
	        return regex("/pessoas.*");
	 }
}
