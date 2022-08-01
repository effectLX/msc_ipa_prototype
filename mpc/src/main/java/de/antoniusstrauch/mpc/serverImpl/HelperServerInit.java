package de.antoniusstrauch.mpc.serverImpl;


import de.antoniusstrauch.mpc.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HelperServerInit {

  private final RestTemplate restTemplate;
  private final AppConfig config;

  @Autowired
  public HelperServerInit(RestTemplate restTemplate, AppConfig config) {
    this.restTemplate = restTemplate;
    this.config = config;
  }

  @Bean
  public HelperServer helperServerOne() {
    return new HelperServer(restTemplate, config.getHelperOne());
  }

  @Bean
  public HelperServer helperServerTwo() {
    return new HelperServer(restTemplate, config.getHelperTwo());
  }
}
