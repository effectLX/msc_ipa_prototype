package de.antoniusstrauch.mpc.core.usecase.leader;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventType;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttributeEvents extends AUsecase<EventBatch, Long> {

  @Override
  public Long runUsecase(EventBatch batch) {

    // Attribution based on last touch mode
    // Prototype does not include sum/avg on event's trigger values, to be added

    LinkedList<Event> attributedEvents = new LinkedList<>(); // built-in if attributed events got relevant
    LinkedList<Event> remainingEvents = new LinkedList<>(batch.getEvents());
    Long attribution = 0L;

    // Take a trigger event from the batch
    for (Event triggerEvent : batch.getEvents()) {
      if (triggerEvent.getType().equals(EventType.TRIGGER)) {
        Event relevantSourceEvent = null;

        // Iterate through all source events from the batch
        for (Event sourceEvent : remainingEvents) {
          if (sourceEvent.getType().equals(EventType.SOURCE)) {

            // Test every source event against the selected trigger event
            if (triggerEvent.getMatchKey().equals(sourceEvent.getMatchKey())
                && triggerEvent.getTimestamp().isAfter(sourceEvent.getTimestamp())) {
              if ((relevantSourceEvent == null) || (sourceEvent.getTimestamp()
                  .isAfter(relevantSourceEvent.getTimestamp()))) {
                relevantSourceEvent = sourceEvent;
              }
            }
          }
        }
        if (relevantSourceEvent != null) {
          attribution++;
          attributedEvents.add(triggerEvent);
          attributedEvents.add(relevantSourceEvent);
          remainingEvents.remove(triggerEvent);
          remainingEvents.remove(relevantSourceEvent);
        }
      }
    }
    log.info("Attributed: " + attribution + " for " + batch.getEvents().size() + " events.");
    return attribution;
  }
}

