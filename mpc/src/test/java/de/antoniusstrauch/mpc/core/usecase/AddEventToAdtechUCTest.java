package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AddEventToAdtechUCTest {

  private CheckReportTriggerUC checkReport;
  private AddEventToAdtechUC addEvent;
  Event event = new Event(EventType.SOURCE, "100", "1");

  @BeforeEach
  void initTest(){
    checkReport = new CheckReportTriggerUC();
    addEvent = new AddEventToAdtechUC(checkReport);
  }

  @Test
  void runUsecaseFalse() {
    boolean response = addEvent.runUsecase(event);
    Assertions.assertFalse(response);
  }

  @Test
  void runUsecaseTrue() {

    int countReports = 0;
    boolean response = false;

    while(!response){
      response = addEvent.runUsecase(event);
      countReports++;
    }
    Assertions.assertEquals(10, countReports);
  }

  @Test
  void getEventArray() {
    addEvent.runUsecase(event);
    addEvent.runUsecase(event);
    Assertions.assertEquals(addEvent.getEventArray().size(), 2);
  }
}