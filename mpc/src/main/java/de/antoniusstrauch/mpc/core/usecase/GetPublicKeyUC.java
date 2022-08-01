package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Encryption;

public class GetPublicKeyUC {

  private final Encryption decrypt;

  public GetPublicKeyUC(Encryption encrypt) {
    this.decrypt = encrypt;
  }

  public String runUsecase() {
    return Integer.toString(decrypt.getPublicKey());
  }
}
