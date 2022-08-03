package de.antoniusstrauch.mpc.impl.controller;

import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.usecase.helper.DecryptBlindShuffleEvents;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
public class HelperController {

  private final DecryptBlindShuffleEvents decryptBlindShuffleEvents;

  @Autowired
  public HelperController(DecryptBlindShuffleEvents decryptBlindShuffleEvents) {
    this.decryptBlindShuffleEvents = decryptBlindShuffleEvents;
  }

  @PostMapping("/decryptBlindShuffle")
  public EventBatch decryptBlindShuffle(@RequestBody @NotNull EventBatch batch) {
    return decryptBlindShuffleEvents.runUsecase(batch);
  }

  @GetMapping("/getPublicKey")
  public PublicKey getPublicKey() {

  }


}
