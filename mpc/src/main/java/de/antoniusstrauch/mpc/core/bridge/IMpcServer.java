package de.antoniusstrauch.mpc.core.bridge;

import de.antoniusstrauch.mpc.core.entity.PublicEncryptionFactor;

public interface IMpcServer {

  PublicEncryptionFactor requestPublicEncryptionFactor();
}
