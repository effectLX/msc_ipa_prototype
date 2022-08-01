package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventType;

import java.util.LinkedList;

public class HelperAttributionUC extends AUsecase<LinkedList<Event>, Integer> {

  @Override
  public Integer runUsecase(LinkedList<Event> events) {

    int attribution = 0;

    for(Event triggerEvent : events) {
      if (triggerEvent.getType().equals(EventType.TRIGGER)) {

        for (Event sourceEvent : events) {
          if (sourceEvent.getType().equals(EventType.SOURCE)) {

            if (triggerEvent.getMatchKey() == sourceEvent.getMatchKey()) {
              if (triggerEvent.getTimestamp() > sourceEvent.getTimestamp()) {
                attribution++;
                break;
              }
            }
          }
        }
      }
    }
    return attribution;
  }
}
