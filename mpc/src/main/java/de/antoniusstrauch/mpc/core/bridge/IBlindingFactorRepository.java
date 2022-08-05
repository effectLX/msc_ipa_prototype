package de.antoniusstrauch.mpc.core.bridge;

import java.util.UUID;

public interface IBlindingFactorRepository {

  Integer getBlindingFactor(UUID eventBatchId);
}
