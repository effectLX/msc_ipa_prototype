package de.antoniusstrauch.mpc.core.usecase.adtec;

import de.antoniusstrauch.mpc.core.entity.Event;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class CheckReportTrigger {

  public CheckReportTrigger() {}

  public boolean runUsecase(@NotNull LinkedList<Event> eventArray) {
    return eventArray.size() >= 10;
  }
}
