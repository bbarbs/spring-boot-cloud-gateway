package com.gateway.config;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    /*@Bean
    public RouteLocator locator(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
                .route("customers", r -> r
                        .path("/customers/**")
                        .uri("http://localhost:8090")
                )
                .build();
        //@formatter:on
    }

    @Bean
    public RouteLocatorBuilder builder(ConfigurableApplicationContext context) {
        return new RouteLocatorBuilder(context);
    }*/

    @Bean
    public DiscoveryClientRouteDefinitionLocator routeDefinitionLocator(DiscoveryClient discoveryClient) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient);
    }
}
