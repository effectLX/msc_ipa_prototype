package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;

import java.util.LinkedList;

public class ServerLeaderUC extends AUsecase<LinkedList<Event>, LinkedList<LinkedList<Event>>> {

  @Override
  public LinkedList<LinkedList<Event>> runUsecase(LinkedList<Event> events) {

    LinkedList<Event> batchOne = new LinkedList<>();
    LinkedList<Event> batchTwo = new LinkedList<>();

    int eventNo = events.size();
    int batchSize = eventNo / 2;

    for (int i = 0; i < eventNo; i++) {
      Event thisEvent = events.get(i);
      if (i < batchSize) {
        batchOne.add(thisEvent);
      } else {
        batchTwo.add(thisEvent);
      }
    }

    LinkedList<LinkedList<Event>> batches = new LinkedList<>();
    batches.add(batchOne);
    batches.add(batchTwo);

    return batches;
  }
}
