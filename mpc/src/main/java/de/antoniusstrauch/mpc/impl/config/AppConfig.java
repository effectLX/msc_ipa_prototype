package de.antoniusstrauch.mpc.impl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mpc")
@Data
public class AppConfig {

  LeaderConfig leader;
  HelperConfig helperOne;
  HelperConfig helperTwo;
  CollectorConfig collector;

}
