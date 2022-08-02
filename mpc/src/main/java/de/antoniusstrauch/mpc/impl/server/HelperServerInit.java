package de.antoniusstrauch.mpc.impl.server;


import de.antoniusstrauch.mpc.impl.config.AppConfig;
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

  @Bean(name="HelperServerOne")
  public HelperServer helperServerOne() {
    return new HelperServer(restTemplate, config.getHelperOne());
  }

  @Bean(name="HelperServerTwo")
  public HelperServer helperServerTwo() {
    return new HelperServer(restTemplate, config.getHelperTwo());
  }
}
