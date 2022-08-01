package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Encryption;

public class GetPublicKey {

  private final Encryption decrypt;

  public GetPublicKey(Encryption encrypt) {
    this.decrypt = encrypt;
  }

  public String runUsecase() {
    return Integer.toString(decrypt.getPublicKey());
  }
}
