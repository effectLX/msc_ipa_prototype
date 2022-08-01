package de.antoniusstrauch.mpc.core.usecase.collector;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventType;

public class AttributeEvents extends AUsecase<AttributeEventsCommand, Integer> {

  @Override
  public Integer runUsecase(AttributeEventsCommand command) {

    int attribution = 0;

    for (Event triggerEvent : command.getEvents()) {
      if (triggerEvent.getType().equals(EventType.TRIGGER)) {
        for (Event sourceEvent : command.getEvents()) {
          if (sourceEvent.getType().equals(EventType.SOURCE)) {
            if (triggerEvent.getMatchKey() == sourceEvent.getMatchKey()
                && triggerEvent.getTimestamp() > sourceEvent.getTimestamp()) {
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
