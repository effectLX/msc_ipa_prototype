package de.antoniusstrauch.mpc.core.bridge;

import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;

import java.util.List;

public interface ILeaderServer {

  public EventBatchPair seperatedBatch(EventBatch events);
}
