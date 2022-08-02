package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Event;
import lombok.Getter;

import java.util.LinkedList;

@Getter
public class AddEventToAdtech {

  private final LinkedList<Event> eventArray = new LinkedList<>();
  private final CheckReportTrigger checkReport;

  public AddEventToAdtech(CheckReportTrigger checkReport) {
    this.checkReport = checkReport;
  }

  public boolean runUsecase(Event event) {
    eventArray.add(event);
    return checkReport.runUsecase(eventArray);
  }
}
