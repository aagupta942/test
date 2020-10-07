package com.learning.mylearning.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

@Configuration
public class ApplicationConfig {

	@Value("${spring.security.oauth2.resourceserver.opaquetoken.introspection-uri}")
	String introspectionUri;
	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
	String clientId;
	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
	String clientSecret;

	@Bean
	public OpaqueTokenIntrospector introspector() {
		return new NimbusOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret);
	}

}
