package de.antoniusstrauch.mpc.impl.repository;

import de.antoniusstrauch.mpc.core.bridge.IBlindingFactorRepository;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class BlindingFactorListRepository implements IBlindingFactorRepository {

  private final HashMap<UUID, Integer> blindingFactors;

  public BlindingFactorListRepository() {
    this.blindingFactors = new HashMap<>();
  }

  @Override
  public Integer getBlindingFactor(UUID eventBatchId) {
    Integer blindingFactor = blindingFactors.get(eventBatchId);
    if (blindingFactor == null) {
      blindingFactor = (int) (Math.random() * 1000);
      blindingFactors.put(eventBatchId, blindingFactor);
    }
    return blindingFactor;
  }

}

