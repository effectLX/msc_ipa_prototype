package de.antoniusstrauch.mpc.controller;

import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import de.antoniusstrauch.mpc.core.usecase.leader.SeparateBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderController {

  private final SeparateBatch separateBatch;

  @Autowired
  public LeaderController(SeparateBatch separateBatch) {
    this.separateBatch = separateBatch;
  }

  @PostMapping("/seperateBatch")
  EventBatchPair seperateBatch(@RequestBody EventBatch eventBatch) {
    return separateBatch.runUsecase(eventBatch);
  }
}
