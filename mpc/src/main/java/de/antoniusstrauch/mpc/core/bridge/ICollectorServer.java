package de.antoniusstrauch.mpc.core.bridge;

import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;

public interface ICollectorServer {

  AttributionResult collect(EventBatchPair pair);
}
