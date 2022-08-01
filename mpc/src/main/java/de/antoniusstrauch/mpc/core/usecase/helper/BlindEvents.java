package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;

public class BlindEvents extends AUsecase<Integer, Integer> {


  private final Integer blindingFactor;

  public BlindEvents(Integer blindingFactor) {
    this.blindingFactor = blindingFactor;
  }

  @Override
  public Integer runUsecase(Integer inputMatchKey) {
    return inputMatchKey * blindingFactor;
  }
}
