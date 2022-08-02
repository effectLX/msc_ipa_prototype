package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Event;

import java.util.LinkedList;

public class CheckReportTrigger {

  public CheckReportTrigger() {}

  public boolean runUsecase(LinkedList<Event> eventArray) {
    return eventArray.size() >= 10;
  }
}
