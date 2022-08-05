package de.antoniusstrauch.mpc.impl.controller;

import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.PublicKey;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MpcController {

  private final RequestAttribution requestAttribution;
  private final RequestPublicKey requestPublicKey;

  @Autowired
  public MpcController(RequestAttribution requestAttribution, RequestPublicKey requestPublicKey) {
    this.requestAttribution = requestAttribution;
    this.requestPublicKey = requestPublicKey;
  }


  @PostMapping("/requestAttribution")
  AttributionResult requestAttribution(@RequestBody EventBatch batch) {
    return requestAttribution.runUsecase(batch);
  }

  @GetMapping("/requestPublicKey")
  PublicKey getPublicKey() {
    return requestPublicKey.runUsecase(null);
  }

}
