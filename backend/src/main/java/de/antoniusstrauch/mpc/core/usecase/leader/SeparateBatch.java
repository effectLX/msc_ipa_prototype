package de.antoniusstrauch.mpc.core.usecase.leader;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import java.util.LinkedList;
import java.util.UUID;

public class SeparateBatch extends AUsecase<EventBatch, EventBatchPair> {

  @Override
  public EventBatchPair runUsecase(EventBatch eventBatch) {

    LinkedList<Event> batchOne = new LinkedList<>();
    LinkedList<Event> batchTwo = new LinkedList<>();
    UUID eventBatchPairId = eventBatch.getEventBatchPairId();

    int eventCount = eventBatch.getEvents().size();
    int batchSize = eventCount / 2;

    for (int i = 0; i < eventCount; i++) {
      Event thisEvent = eventBatch.getEvents().get(i);
      if (i < batchSize) {
        batchOne.add(thisEvent);
      } else {
        batchTwo.add(thisEvent);
      }
    }

    return EventBatchPair.builder().eventBatchPairId(eventBatchPairId)
        .batchOne(EventBatch.builder().eventBatchPairId(eventBatchPairId).events(batchOne).build())
        .batchTwo(EventBatch.builder().eventBatchPairId(eventBatchPairId).events(batchTwo).build())
        .build();
  }

}
