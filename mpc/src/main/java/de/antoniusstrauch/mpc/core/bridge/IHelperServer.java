package de.antoniusstrauch.mpc.core.bridge;

import de.antoniusstrauch.mpc.core.entity.EventBatch;

import java.security.PublicKey;

public interface IHelperServer {

  EventBatch decryptBlindShuffle(EventBatch batchOne);

  PublicKey getPublicKey();
}
