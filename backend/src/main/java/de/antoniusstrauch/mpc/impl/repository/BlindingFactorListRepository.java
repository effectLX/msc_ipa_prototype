package de.antoniusstrauch.mpc.impl.repository;

import de.antoniusstrauch.mpc.core.bridge.IBlindingFactorRepository;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class BlindingFactorListRepository implements IBlindingFactorRepository {

  private final HashMap<UUID, Long> blindingFactors;

  public BlindingFactorListRepository() {
    this.blindingFactors = new HashMap<>();
  }

  // Assumption of blinding factor between 0-1000
  @Override
  public Long getBlindingFactor(UUID eventBatchId) {
    Long blindingFactor = blindingFactors.get(eventBatchId);
    if (blindingFactor == null) {
      blindingFactor = (long) (Math.random() * 1000);
      blindingFactors.put(eventBatchId, blindingFactor);
    }
    return blindingFactor;
  }

}

