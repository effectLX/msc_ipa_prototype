package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.bridge.IBlindingFactorRepository;

public class BlindEvents extends AUsecase<BlindEventsCommand, Long> {


  private final IBlindingFactorRepository blindingFactorRepository;

  // Simplification, blinding factor constant over different adtech request
  public BlindEvents(IBlindingFactorRepository blindingFactorRepository) {
    this.blindingFactorRepository = blindingFactorRepository;
  }

  // Arithmetic multiplication as homomorphic encryption operation assumed
  @Override
  public Long runUsecase(BlindEventsCommand command) {
    Long blindingFactor = blindingFactorRepository.getBlindingFactor(
        command.getEventBatchPairId());

    return command.getMatchKey() * blindingFactor;
  }
}
