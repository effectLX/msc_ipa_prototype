package de.antoniusstrauch.mpc.core.bridge;

import java.util.UUID;

public interface IBlindingFactorRepository {

  Long getBlindingFactor(UUID eventBatchId);
}
