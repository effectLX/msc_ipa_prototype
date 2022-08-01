package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

class CheckReportTriggerUCTest {

  private CheckReportTriggerUC checkReport = new CheckReportTriggerUC();
  private LinkedList<Event> array = new LinkedList<>();
  Event event = new Event(EventType.SOURCE, "100", "1");

  @Test
  void runUsecaseReportDue() {

    int countReports = 10;
    while(countReports > 0){
      array.add(event);
      countReports--;
    }
    boolean response = checkReport.runUsecase(array);
    Assertions.assertTrue(response);
  }

  @Test
  void runUsecaseReportOnHold() {

    int countReports = 5;
    while(countReports > 0){
      array.add(event);
      countReports--;
    }
    boolean response = checkReport.runUsecase(array);
    Assertions.assertFalse(response);
  }
}