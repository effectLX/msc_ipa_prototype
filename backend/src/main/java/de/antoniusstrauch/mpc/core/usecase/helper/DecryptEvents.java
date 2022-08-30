package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;


public class DecryptEvents extends AUsecase<DecryptEventsCommand, Long> {


  private final Long privateKey;

  public DecryptEvents(Long privateKey) {
    this.privateKey = privateKey;
  }

  // Simplification, protocol proposes ElGamal encryption
  // Prototype encryption based on explanation in project report (see Analysis on IPA attack space)
  @Override
  public Long runUsecase(DecryptEventsCommand command) {
    return command.getInputMatchKey() / (long) Math.pow(command.getClientKey(), privateKey);
  }
}
