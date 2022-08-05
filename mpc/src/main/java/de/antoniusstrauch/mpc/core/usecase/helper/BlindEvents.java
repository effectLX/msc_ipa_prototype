package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.bridge.IBlindingFactorRepository;

public class BlindEvents extends AUsecase<BlindEventsCommand, Integer> {


  private final IBlindingFactorRepository blindingFactorRepository;

  public BlindEvents(IBlindingFactorRepository blindingFactorRepository) {
    this.blindingFactorRepository = blindingFactorRepository;
  }

  @Override
  public Integer runUsecase(BlindEventsCommand command) {
    Integer blindingFactor = blindingFactorRepository.getBlindingFactor(
        command.getEventBatchPairId());

    return command.getMatchKey() * blindingFactor;
  }
}
