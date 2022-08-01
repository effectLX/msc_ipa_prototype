package de.antoniusstrauch.mpc.serverImpl;

import de.antoniusstrauch.mpc.config.HelperConfig;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class HelperServer implements IHelperServer {

  private final RestTemplate template;
  private final HelperConfig config;


  public HelperServer(RestTemplate template, HelperConfig config) {
    this.template = template;
    this.config = config;
  }

  @Override
  public EventBatch decryptBlindShuffle(EventBatch batchOne) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request =
        new HttpEntity<>(batchOne.toString(), headers);
    return template.postForObject(config.getServerURL(), request, EventBatch.class);
  }
}
