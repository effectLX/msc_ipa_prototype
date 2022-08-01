package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.AUsecaseTwo;

public class HelperBlindingUC extends AUsecaseTwo<Integer, Integer> {

  @Override
  public Integer runUsecase(Integer inputMatchKey, Integer blindingFactor) {
    return inputMatchKey * blindingFactor;
  }
}
