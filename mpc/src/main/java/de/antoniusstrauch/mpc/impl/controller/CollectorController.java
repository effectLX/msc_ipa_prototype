package de.antoniusstrauch.mpc.impl.controller;

import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import de.antoniusstrauch.mpc.core.usecase.collector.MergeAttributeEvents;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectorController {

  private final MergeAttributeEvents mergeAttributeEvents;

  @Autowired
  public CollectorController(MergeAttributeEvents mergeAttributeEvents) {
    this.mergeAttributeEvents = mergeAttributeEvents;
  }

  @PostMapping("/mergeAttribute")
  public AttributionResult collect(@RequestBody @NotNull EventBatchPair pair) {
    return mergeAttributeEvents.runUsecase(pair);
  }

}
