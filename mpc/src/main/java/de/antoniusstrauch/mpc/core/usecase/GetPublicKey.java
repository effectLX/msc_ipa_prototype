package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.MPCExecption;
import de.antoniusstrauch.mpc.core.entity.Encryption;

import java.security.PublicKey;

public class GetPublicKey extends AUsecase<Void, PublicKey> {

  private final Encryption decrypt;

  public GetPublicKey(Encryption encrypt) {
    this.decrypt = encrypt;
  }

  @Override
  public PublicKey runUsecase(Void unused) throws MPCExecption {
    return Integer.toString(decrypt.getPublicKey());
  }
}
