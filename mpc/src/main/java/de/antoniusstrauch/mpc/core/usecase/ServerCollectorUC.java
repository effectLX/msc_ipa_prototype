package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.AUsecaseTwo;
import de.antoniusstrauch.mpc.core.entity.Event;
import java.util.LinkedList;

public class ServerCollectorUC extends AUsecaseTwo<LinkedList<Event>, Integer> {

  private final HelperAttributionUC attribution = new HelperAttributionUC();

  @Override
  public Integer runUsecase(LinkedList<Event> batchOne, LinkedList<Event> batchTwo) {

    LinkedList<Event> events = new LinkedList<>();
    events.addAll(batchOne);
    events.addAll(batchTwo);

    return attribution.runUsecase(events);
  }
}
