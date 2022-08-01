package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Event;

import java.util.LinkedList;

public class CheckReportTrigger {

  public CheckReportTrigger() {}

  public boolean runUsecase(LinkedList<Event> eventArray) {
    if(eventArray.size() >= 10) {
      return true;
    }
      return false;
  }
}
