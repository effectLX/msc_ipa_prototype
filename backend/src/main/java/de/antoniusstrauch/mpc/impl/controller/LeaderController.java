package de.antoniusstrauch.mpc.impl.controller;

import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import de.antoniusstrauch.mpc.core.usecase.leader.MergeAttributeEvents;
import de.antoniusstrauch.mpc.core.usecase.leader.SeparateBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderController {

  private final SeparateBatch separateBatch;
  private final MergeAttributeEvents mergeAttributeEvents;

  @Autowired
  public LeaderController(SeparateBatch separateBatch, MergeAttributeEvents mergeAttributeEvents) {
    this.separateBatch = separateBatch;
    this.mergeAttributeEvents = mergeAttributeEvents;
  }

  @PostMapping("/seperateBatch")
  EventBatchPair seperateBatch(@RequestBody EventBatch eventBatch) {
    return separateBatch.runUsecase(eventBatch);
  }

  // Task currently expected with Leader Server, however protocol is opaque on final MPC architecture
  @PostMapping("/mergeAttribute")
  public AttributionResult collect(@RequestBody EventBatchPair pair) {
    return mergeAttributeEvents.runUsecase(pair);
  }
}
