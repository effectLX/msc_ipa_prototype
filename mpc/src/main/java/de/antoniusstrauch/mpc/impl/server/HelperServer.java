package de.antoniusstrauch.mpc.impl.server;

import com.google.gson.Gson;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.impl.config.HelperConfig;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class HelperServer implements IHelperServer {

  private final RestTemplate template;
  private final HelperConfig config;
  private final Gson gson;


  public HelperServer(RestTemplate template, HelperConfig config, Gson gson) {
    this.template = template;
    this.config = config;
    this.gson = gson;
  }

  @Override
  public @Nullable EventBatch decryptBlindShuffle(EventBatch batch) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(gson.toJson(batch), headers);
    return template.postForObject(config.getServerURL() + "/decryptBlindShuffle", request,
        EventBatch.class);
  }
}
