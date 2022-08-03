package de.antoniusstrauch.mpc.impl.server;

import com.google.gson.Gson;
import de.antoniusstrauch.mpc.core.bridge.ILeaderServer;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import de.antoniusstrauch.mpc.impl.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class LeaderServer implements ILeaderServer {

  private final RestTemplate template;
  private final AppConfig config;
  private final Gson gson;

  @Autowired
  public LeaderServer(RestTemplate template, AppConfig config, Gson gson) {
    this.template = template;
    this.config = config;
    this.gson = gson;
  }

  @Override
  public @Nullable EventBatchPair seperatedBatch(EventBatch events) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(gson.toJson(events), headers);
    return template.postForObject(config.getLeader().getServerURL() + "/seperateBatch", request,
        EventBatchPair.class);
  }

}
