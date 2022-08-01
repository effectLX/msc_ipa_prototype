package de.antoniusstrauch.mpc.core.bridge;

import de.antoniusstrauch.mpc.core.entity.EventBatch;

public interface IHelperServer {

  EventBatch decryptBlindShuffle(EventBatch batchOne);
}
