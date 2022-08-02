package de.antoniusstrauch.mpc.core.bridge;

import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;

public interface ILeaderServer {

  EventBatchPair seperatedBatch(EventBatch events);
}
