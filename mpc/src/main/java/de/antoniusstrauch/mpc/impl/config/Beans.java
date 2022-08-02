package de.antoniusstrauch.mpc.impl.config;

import de.antoniusstrauch.mpc.core.bridge.ICollectorServer;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.bridge.ILeaderServer;
import de.antoniusstrauch.mpc.core.usecase.helper.BlindEvents;
import de.antoniusstrauch.mpc.core.usecase.helper.DecryptBlindShuffleEvents;
import de.antoniusstrauch.mpc.core.usecase.helper.DecryptEvents;
import de.antoniusstrauch.mpc.core.usecase.helper.ShuffleEvents;
import de.antoniusstrauch.mpc.core.usecase.leader.SeparateBatch;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

  //USECASES

  @Bean
  @Autowired
  RequestAttribution getRequestAttribution(ILeaderServer leader,
      @Qualifier("HelperServerOne") IHelperServer helperOne,
      @Qualifier("HelperServerTwo") IHelperServer helperTwo, ICollectorServer collector) {
    return new RequestAttribution(leader, helperOne, helperTwo, collector);
  }

  @Bean
  SeparateBatch getSeparateBatch() {
    return new SeparateBatch();
  }

  @Bean(name = "ServerHelperOne")
  public DecryptBlindShuffleEvents getServerHelperUC1(
      DecryptEvents decryptEvents, BlindEvents blindEvents,
      ShuffleEvents shuffleEvents) {
    return new DecryptBlindShuffleEvents(decryptEvents, blindEvents, shuffleEvents);
  }

  @Bean(name = "ServerHelperTwo")
  public DecryptBlindShuffleEvents getServerHelperUC2(DecryptEvents decryptEvents,
      BlindEvents blindEvents,
      ShuffleEvents shuffleEvents) {
    return new DecryptBlindShuffleEvents(decryptEvents, blindEvents, shuffleEvents);
  }

  @Bean
  @Autowired
  public DecryptEvents getDecryptEvents(AppConfig config) {
    return new DecryptEvents(config.getHelperOne().getDecryptionFactor());
  }

  @Bean
  @Autowired
  public BlindEvents getBlindEvents(AppConfig config) {
    return new BlindEvents(config.getHelperOne().getBlindingFactor());
  }

  @Bean
  public ShuffleEvents getShuffleEvents() {
    return new ShuffleEvents();
  }

}
