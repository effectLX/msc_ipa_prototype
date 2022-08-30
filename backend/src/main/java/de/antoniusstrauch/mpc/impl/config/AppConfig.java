package de.antoniusstrauch.mpc.impl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mpc")
@Data
public class AppConfig {

  MpcConfig mpc;
  LeaderConfig leader;
  HelperConfig helper;
  CollectorConfig collector;

}
