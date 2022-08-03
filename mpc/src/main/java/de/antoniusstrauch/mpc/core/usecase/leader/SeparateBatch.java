package de.antoniusstrauch.mpc.core.usecase.leader;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class SeparateBatch extends AUsecase<EventBatch, EventBatchPair> {

  @Override
  public EventBatchPair runUsecase(@NotNull EventBatch eventBatch) {

    LinkedList<Event> batchOne = new LinkedList<>();
    LinkedList<Event> batchTwo = new LinkedList<>();

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

    return EventBatchPair.builder()
        .batchOne(EventBatch.builder().events(batchOne).build())
        .batchTwo(EventBatch.builder().events(batchTwo).build()).build();
  }

}
