package de.antoniusstrauch.mpc.impl.controller;

import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
public class MpcController {

  private final RequestAttribution requestAttribution;

  @Autowired
  public MpcController(RequestAttribution requestAttribution, PublicKey publicKey) {
    this.requestAttribution = requestAttribution;
  }


  @PostMapping("/requestAttribution")
  AttributionResult requestAttribution(@RequestBody EventBatch batch) {
    return requestAttribution.runUsecase(batch);
  }

  @GetMapping("/requestPublicKey")
  PublicKey getPublicKey() {
    return requestPublicKey.runUsecase(null); //TODO USecase muss an helper geben, helper brauchen etpoint für public key und muss in config
  }

}
