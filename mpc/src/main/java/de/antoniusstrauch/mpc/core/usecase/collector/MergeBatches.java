package de.antoniusstrauch.mpc.core.usecase.collector;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;

import java.util.LinkedList;
import java.util.List;

public class MergeBatches extends AUsecase<MergeBatchesCommand, List<Event>> {

  @Override
  public List<Event> runUsecase(MergeBatchesCommand command) {

    LinkedList<Event> events = new LinkedList<>();
    events.addAll(command.getBatchOne());
    events.addAll(command.getBatchTwo());

    return events;
  }
}
