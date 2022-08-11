package de.antoniusstrauch.mpc.impl.controller;

import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.PublicKey;
import de.antoniusstrauch.mpc.core.usecase.helper.DecryptBlindShuffleEvents;
import de.antoniusstrauch.mpc.core.usecase.helper.GetPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelperController {

  private final DecryptBlindShuffleEvents decryptBlindShuffleEvents;
  private final GetPublicKey getPublicKey;

  @Autowired
  public HelperController(DecryptBlindShuffleEvents decryptBlindShuffleEvents,
      GetPublicKey getPublicKey) {
    this.decryptBlindShuffleEvents = decryptBlindShuffleEvents;
    this.getPublicKey = getPublicKey;
  }

  @PostMapping("/decryptBlindShuffle")
  public EventBatch decryptBlindShuffle(@RequestBody EventBatch batch) {
    return decryptBlindShuffleEvents.runUsecase(batch);
  }

  @GetMapping("/getPublicKey")
  public PublicKey getPublicKey() {
    return getPublicKey.runUsecase(null);
  }


}
