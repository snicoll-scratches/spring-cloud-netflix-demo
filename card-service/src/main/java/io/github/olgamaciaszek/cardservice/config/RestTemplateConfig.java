package io.github.olgamaciaszek.cardservice.config;

import io.github.olgamaciaszek.excluded.CustomDefaultLoadBalancerConfiguration;
import io.github.olgamaciaszek.excluded.CustomLoadBalancerConfiguration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Olga Maciaszek-Sharma
 */
@Configuration
@LoadBalancerClients(value = {@LoadBalancerClient(value = "fraud-verifier", configuration = { CustomLoadBalancerConfiguration.class, CustomDefaultLoadBalancerConfiguration.class}),
		@LoadBalancerClient(value = "test", configuration = CustomLoadBalancerConfiguration.class)}, defaultConfiguration = CustomDefaultLoadBalancerConfiguration.class)
public class RestTemplateConfig {

	@Bean
	@LoadBalanced
	@Qualifier("loadBalancedRestTemplate")
	RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Qualifier("restTemplate")
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}



