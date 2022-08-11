package de.antoniusstrauch.mpc.impl.server;

import com.google.gson.Gson;
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
  private final Gson gson;

  @Autowired
  public CollectorServer(RestTemplate template, AppConfig config, Gson gson) {
    this.template = template;
    this.config = config;
    this.gson = gson;
  }

  @Override
  public AttributionResult collect(EventBatchPair pair) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(gson.toJson(pair), headers);
    return template.postForObject(config.getCollector().getServerURL() + "/mergeAttribute", request,
        AttributionResult.class);
  }
}
