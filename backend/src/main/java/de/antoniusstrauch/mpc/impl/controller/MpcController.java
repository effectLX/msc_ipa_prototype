package de.antoniusstrauch.mpc.impl.controller;

import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.PublicEncryptionFactor;
import de.antoniusstrauch.mpc.core.entity.PublicKey;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestPublicEncryptionFactor;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MpcController {

  private final RequestAttribution requestAttribution;
  private final RequestPublicKey requestPublicKey;
  private final RequestPublicEncryptionFactor requestPublicEncryptionFactor;

  // MPC server not proposed in the protocol, but added for simplification
  @Autowired
  public MpcController(RequestAttribution requestAttribution, RequestPublicKey requestPublicKey,
      RequestPublicEncryptionFactor requestPublicEncryptionFactor) {
    this.requestAttribution = requestAttribution;
    this.requestPublicKey = requestPublicKey;
    this.requestPublicEncryptionFactor = requestPublicEncryptionFactor;
  }

  @PostMapping("/requestAttribution")
  AttributionResult requestAttribution(@RequestBody EventBatch batch) {
    return requestAttribution.runUsecase(batch);
  }

  // Protocol does not clearly define, which server will take this task
  @GetMapping("/requestPublicKey")
  PublicKey getPublicKey() {
    return requestPublicKey.runUsecase(null);
  }

  // Protocol does not clearly define, which server will take this task
  @GetMapping("/requestPublicEncryptionFactor")
  PublicEncryptionFactor requestPublicEncryptionFactor() {
    return requestPublicEncryptionFactor.runUsecase(null);
  }

}
