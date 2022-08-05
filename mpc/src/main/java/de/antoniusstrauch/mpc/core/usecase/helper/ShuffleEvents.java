package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

public class ShuffleEvents extends AUsecase<EventBatch, EventBatch> {

  @Override
  public EventBatch runUsecase(EventBatch events) {
    Collections.shuffle(events.getEvents());
    return events;
  }
}

