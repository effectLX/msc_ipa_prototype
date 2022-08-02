package de.antoniusstrauch.mpc.core.usecase.collector;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventType;

public class AttributeEvents extends AUsecase<EventBatch, Integer> {

  @Override
  public Integer runUsecase(EventBatch batch) {

    int attribution = 0;

    for (Event triggerEvent : batch.getEvents()) {
      if (triggerEvent.getType().equals(EventType.TRIGGER)) {
        for (Event sourceEvent : batch.getEvents()) {
          if (sourceEvent.getType().equals(EventType.SOURCE)) {
            if (triggerEvent.getMatchKey().equals(sourceEvent.getMatchKey())
                && triggerEvent.getTimestamp().isAfter(sourceEvent.getTimestamp())) {
              attribution++;
              break;
            }
          }
        }
      }
    }
    return attribution;
  }
}
