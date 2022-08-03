package de.antoniusstrauch.mpc.core.usecase.collector;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class AttributeEvents extends AUsecase<EventBatch, Integer> {

  @Override
  public @NotNull Integer runUsecase(@NotNull EventBatch batch) {

    LinkedList<Event> attributedEvents = new LinkedList<>(); // built-in if attributed events got relevant
    int attribution = 0;

    // Take a trigger event from the batch
    for (Event triggerEvent : batch.getEvents()) {
      if (triggerEvent.getType().equals(EventType.TRIGGER)) {
      Event recentSourceEvent = null;

        // Iterate through all source events from the batch
        for (Event sourceEvent : batch.getEvents()) {
          if (sourceEvent.getType().equals(EventType.SOURCE)) {

            // Test every source event against the selected trigger event
            if (triggerEvent.getMatchKey().equals(sourceEvent.getMatchKey())
                && triggerEvent.getTimestamp().isAfter(sourceEvent.getTimestamp())) {
              if((recentSourceEvent == null) || (sourceEvent.getTimestamp().isAfter(recentSourceEvent.getTimestamp()))){
                recentSourceEvent = sourceEvent;
              }
            }
          }
        }
        if (recentSourceEvent != null) {
          attribution++;
          attributedEvents.add(triggerEvent);
          attributedEvents.add(recentSourceEvent);
        }
      }
    }
    return attribution;
  }
}
