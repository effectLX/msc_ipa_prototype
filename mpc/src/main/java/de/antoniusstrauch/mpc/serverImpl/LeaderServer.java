package de.antoniusstrauch.mpc.serverImpl;

import de.antoniusstrauch.mpc.config.AppConfig;
import de.antoniusstrauch.mpc.core.bridge.ILeaderServer;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class LeaderServer implements ILeaderServer {

  private final RestTemplate template;
  private final AppConfig config;

  @Autowired
  public LeaderServer(RestTemplate template, AppConfig config) {
    this.template = template;
    this.config = config;
  }

  @Override
  public EventBatchPair seperatedBatch(EventBatch events) {
    return template.getForObject(config.getLeader().getServerURL(), EventBatchPair.class);
  }
}
