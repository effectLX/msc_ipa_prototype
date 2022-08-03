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
import de.antoniusstrauch.mpc.core.usecase.helper.ShuffleEvents;
import de.antoniusstrauch.mpc.core.usecase.leader.SeparateBatch;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import de.antoniusstrauch.mpc.impl.util.LocalDateTimeAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Configuration
public class Beans {

  @Bean
  public RestTemplate restTemplate(@NotNull RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  public @NotNull Gson getGson() {
    return new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
        new LocalDateTimeAdapter()).create();
  }

  //USECASES

  @Bean
  @Autowired
  @NotNull
  RequestAttribution getRequestAttribution(ILeaderServer leader,
      @Qualifier("HelperServerOne") IHelperServer helperOne,
      @Qualifier("HelperServerTwo") IHelperServer helperTwo, ICollectorServer collector) {
    return new RequestAttribution(leader, helperOne, helperTwo, collector);
  }

  @Bean
  @NotNull
  SeparateBatch getSeparateBatch() {
    return new SeparateBatch();
  }

  @Bean
  public @NotNull DecryptBlindShuffleEvents getServerHelperUC1(
      DecryptEvents decryptEvents, BlindEvents blindEvents,
      ShuffleEvents shuffleEvents) {
    return new DecryptBlindShuffleEvents(decryptEvents, blindEvents, shuffleEvents);
  }

  @Bean
  @Autowired
  public @NotNull DecryptEvents getDecryptEvents(@NotNull AppConfig config) {
    return new DecryptEvents(config.getHelper().getDecryptionFactor());
  }

  @Bean
  @Autowired
  public @NotNull BlindEvents getBlindEvents(@NotNull AppConfig config) {
    return new BlindEvents(config.getHelper().getBlindingFactor());
  }

  @Bean
  public @NotNull ShuffleEvents getShuffleEvents() {
    return new ShuffleEvents();
  }

  @Bean
  @Autowired
  public @NotNull MergeAttributeEvents getMergeAttributeEvents(MergeBatches mergeBatches,
      AttributeEvents attributeEvents) {
    return new MergeAttributeEvents(mergeBatches, attributeEvents);
  }

  @Bean
  public @NotNull MergeBatches getMergeBatches() {
    return new MergeBatches();
  }

  @Bean
  public @NotNull AttributeEvents getAttributeEvents() {
    return new AttributeEvents();
  }

}
