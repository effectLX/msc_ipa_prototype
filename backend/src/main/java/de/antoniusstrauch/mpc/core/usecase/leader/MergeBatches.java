package de.antoniusstrauch.mpc.core.usecase.leader;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;
import java.util.LinkedList;

public class MergeBatches extends AUsecase<EventBatchPair, EventBatch> {

  @Override
  public EventBatch runUsecase(EventBatchPair pair) {

    // Merge of batches assumed given leader server role (opaque definition in architecture)
    LinkedList<Event> events = new LinkedList<>();
    events.addAll(pair.getBatchOne().getEvents());
    events.addAll(pair.getBatchTwo().getEvents());

    return EventBatch.builder().events(events).build();
  }
}
