package de.antoniusstrauch.mpc.impl.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.antoniusstrauch.mpc.core.bridge.ICollectorServer;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.bridge.ILeaderServer;
import de.antoniusstrauch.mpc.core.usecase.collector.AttributeEvents;
import de.antoniusstrauch.mpc.core.usecase.collector.MergeAttributeEvents;
import de.antoniusstrauch.mpc.core.usecase.collector.MergeBatches;
import de.antoniusstrauch.mpc.core.usecase.helper.BlindEvents;
import de.antoniusstrauch.mpc.core.usecase.helper.DecryptBlindShuffleEvents;
import de.antoniusstrauch.mpc.core.usecase.helper.DecryptEvents;
import de.antoniusstrauch.mpc.core.usecase.helper.GetPublicKey;
import de.antoniusstrauch.mpc.core.usecase.helper.ShuffleEvents;
import de.antoniusstrauch.mpc.core.usecase.leader.SeparateBatch;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestPublicKey;
import de.antoniusstrauch.mpc.impl.repository.BlindingFactorListRepository;
import de.antoniusstrauch.mpc.impl.util.LocalDateTimeAdapter;
import java.time.LocalDateTime;
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

  @Bean
  public Gson getGson() {
    return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .create();
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

  @Bean
  public DecryptBlindShuffleEvents getServerHelperUC1(DecryptEvents decryptEvents,
      BlindEvents blindEvents, ShuffleEvents shuffleEvents) {
    return new DecryptBlindShuffleEvents(decryptEvents, blindEvents, shuffleEvents);
  }

  @Bean
  @Autowired
  public DecryptEvents getDecryptEvents(AppConfig config) {
    return new DecryptEvents(config.getHelper().getDecryptionFactor());
  }

  @Bean
  @Autowired
  public BlindEvents getBlindEvents(BlindingFactorListRepository blindingFactorListRepository) {
    return new BlindEvents(blindingFactorListRepository);
  }

  @Bean
  public ShuffleEvents getShuffleEvents() {
    return new ShuffleEvents();
  }

  @Bean
  @Autowired
  public MergeAttributeEvents getMergeAttributeEvents(MergeBatches mergeBatches,
      AttributeEvents attributeEvents) {
    return new MergeAttributeEvents(mergeBatches, attributeEvents);
  }

  @Bean
  public MergeBatches getMergeBatches() {
    return new MergeBatches();
  }

  @Bean
  public AttributeEvents getAttributeEvents() {
    return new AttributeEvents();
  }

  @Bean
  @Autowired
  GetPublicKey getPublicKey(AppConfig config) {
    return new GetPublicKey(config.getHelper().getPublicKey());
  }

  @Bean
  @Autowired
  RequestPublicKey requestPublicKey(@Qualifier("HelperServerOne") IHelperServer helperOne,
      @Qualifier("HelperServerTwo") IHelperServer helperTwo) {
    return new RequestPublicKey(helperOne, helperTwo);
  }

}
