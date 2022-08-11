package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.bridge.IBlindingFactorRepository;

public class BlindEvents extends AUsecase<BlindEventsCommand, Long> {


  private final IBlindingFactorRepository blindingFactorRepository;

  public BlindEvents(IBlindingFactorRepository blindingFactorRepository) {
    this.blindingFactorRepository = blindingFactorRepository;
  }

  @Override
  public Long runUsecase(BlindEventsCommand command) {
    Long blindingFactor = blindingFactorRepository.getBlindingFactor(
        command.getEventBatchPairId());

    return command.getMatchKey() * blindingFactor;
  }
}
