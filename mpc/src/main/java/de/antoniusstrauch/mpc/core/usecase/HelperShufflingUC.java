package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;

import java.util.Collections;
import java.util.LinkedList;

public class HelperShufflingUC extends AUsecase<LinkedList<Event>, LinkedList<Event>> {

  @Override
  public LinkedList<Event> runUsecase(LinkedList<Event> events) {
    Collections.shuffle(events);
    return events;
  }
}

