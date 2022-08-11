package de.antoniusstrauch.mpc.impl.server;

import de.antoniusstrauch.mpc.core.bridge.IMpcServer;
import de.antoniusstrauch.mpc.core.entity.PublicEncryptionFactor;
import de.antoniusstrauch.mpc.impl.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MpcServer implements IMpcServer {

  private final RestTemplate template;
  private final AppConfig config;

  @Autowired
  public MpcServer(RestTemplate template, AppConfig config) {
    this.template = template;
    this.config = config;
  }

  @Override
  public PublicEncryptionFactor requestPublicEncryptionFactor() {
    return template.getForObject(config.getMpc().getServerURL() + "/requestPublicEncryptionFactor",
        PublicEncryptionFactor.class);
  }
}
