package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Encryption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetPublicKeyUCTest {

  @Test
  void runUsecaseRandomization() {
    GetPublicKeyUC getKey1 = new GetPublicKeyUC(new Encryption());
    GetPublicKeyUC getKey2 = new GetPublicKeyUC(new Encryption());

    Assertions.assertNotEquals(getKey1.runUsecase(), getKey2.runUsecase());
  }
}