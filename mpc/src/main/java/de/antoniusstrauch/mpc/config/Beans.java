package de.antoniusstrauch.mpc.config;

import de.antoniusstrauch.mpc.core.bridge.ICollectorServer;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.bridge.ILeaderServer;
import de.antoniusstrauch.mpc.core.usecase.helper.DecryptBlindShuffleEvents;
import de.antoniusstrauch.mpc.core.usecase.leader.SeparateBatch;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  @Autowired
  RequestAttribution getRequestAttribution(ILeaderServer leader, IHelperServer helperOne,
                                           IHelperServer helperTwo, ICollectorServer collector) {
    return new RequestAttribution(leader, helperOne, helperTwo, collector);
  }

  //USECASES
  @Bean
  SeparateBatch getSeparateBatch() {
    return new SeparateBatch();
  }

  @Bean(name = "ServerHelperOne")
  public DecryptBlindShuffleEvents getServerHelperUC1() {
    return new DecryptBlindShuffleEvents();
  }

  @Bean(name = "ServerHelperTwo")
  public DecryptBlindShuffleEvents getServerHelperUC2() {
    return new DecryptBlindShuffleEvents();
  }

}
