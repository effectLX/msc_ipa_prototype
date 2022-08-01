package de.antoniusstrauch.mpc.core.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperBlindingUCTest {

  private HelperBlindingUC helper = new HelperBlindingUC( );

  @Test
  void runUsecaseTrue() {
    int response = helper.runUsecase(111, 2);
    Assertions.assertEquals(222, response);
  }

  @Test
  void runUsecaseFalse() {
    int response = helper.runUsecase(111, 3);
    Assertions.assertNotEquals(222, response);
  }
}