package de.antoniusstrauch.mpc.impl.server;


import com.google.gson.Gson;
import de.antoniusstrauch.mpc.impl.config.AppConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HelperServerInit {

  private final RestTemplate restTemplate;
  private final AppConfig config;
  private final Gson gson;

  @Autowired
  public HelperServerInit(RestTemplate restTemplate, AppConfig config, Gson gson) {
    this.restTemplate = restTemplate;
    this.config = config;
    this.gson = gson;
  }

  @Bean(name = "HelperServerOne")
  public @NotNull HelperServer helperServerOne() {
    return new HelperServer(restTemplate, config.getHelper(), gson);
  }

  @Bean(name = "HelperServerTwo")
  public @NotNull HelperServer helperServerTwo() {
    return new HelperServer(restTemplate, config.getHelper(), gson);
  }
}
