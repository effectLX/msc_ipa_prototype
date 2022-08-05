package de.antoniusstrauch.mpc.core.usecase.collector;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class MergeAttributeEvents extends AUsecase<EventBatchPair, AttributionResult> {

  private final MergeBatches mergeBatches;
  private final AttributeEvents attributeEvents;

  public MergeAttributeEvents(MergeBatches mergeBatches, AttributeEvents attributeEvents) {
    this.mergeBatches = mergeBatches;
    this.attributeEvents = attributeEvents;
  }

  @Override
  public AttributionResult runUsecase( EventBatchPair eventBatchPair) {
    log.info("MergeAttributeEvents for eventBatchPair: " + eventBatchPair.getEventBatchPairId());
    EventBatch eventBatch = mergeBatches.runUsecase(eventBatchPair);
    Integer result = attributeEvents.runUsecase(eventBatch);
    return AttributionResult.builder().result(result).build();
  }
}
