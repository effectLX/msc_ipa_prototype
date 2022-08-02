package de.antoniusstrauch.mpc.impl.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.impl.config.HelperConfig;
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
  public EventBatch decryptBlindShuffle(EventBatch batch) {
    ObjectMapper mapper = new ObjectMapper();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = null;
    try {
      request = new HttpEntity<>(mapper.writeValueAsString(batch), headers);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return template.postForObject(config.getServerURL(), request, EventBatch.class);
  }
}
