package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.AUsecaseTwo;

public class HelperDecryptionUC extends AUsecaseTwo<Integer, Integer> {

  @Override
  public Integer runUsecase(Integer inputMatchKey, Integer decryptionFactor) {
    return inputMatchKey / decryptionFactor;
  }
}
