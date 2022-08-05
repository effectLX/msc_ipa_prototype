package de.antoniusstrauch.mpc.core.usecase.adtec;

import de.antoniusstrauch.mpc.core.entity.Event;
import java.util.LinkedList;
import org.jetbrains.annotations.NotNull;

public class CheckReportTrigger {

  public CheckReportTrigger() {}

  public boolean runUsecase(LinkedList<Event> eventArray) {
    return eventArray.size() >= 10;
  }
}
