package de.antoniusstrauch.mpc.core.bridge;

import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.PublicKey;

public interface IHelperServer {

  EventBatch decryptBlindShuffle(EventBatch batchOne);

  PublicKey getPublicKey();
}
