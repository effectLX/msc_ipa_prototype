package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;

public class DecryptEvents extends AUsecase<Integer, Integer> {


  private final Integer decryptionFactor;

  public DecryptEvents(Integer decryptionFactor) {
    this.decryptionFactor = decryptionFactor;
  }

  @Override
  public Integer runUsecase(Integer inputMatchKey) {
    return inputMatchKey / decryptionFactor;
  }
}
