package de.antoniusstrauch.mpc.impl.server;

import de.antoniusstrauch.mpc.core.bridge.ICollectorServer;
import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import de.antoniusstrauch.mpc.impl.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CollectorServer implements ICollectorServer {

  private final RestTemplate template;
  private final AppConfig config;

  @Autowired
  public CollectorServer(RestTemplate template, AppConfig config) {
    this.template = template;
    this.config = config;
  }

  @Override
  public AttributionResult collect(EventBatchPair pair) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(pair.toString(), headers);
    return template.postForObject(config.getCollector().getServerURL(), request,
        AttributionResult.class);
  }
}
