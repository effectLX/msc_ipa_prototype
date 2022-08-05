package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.PublicKey;

public class GetPublicKey extends AUsecase<Void, PublicKey> {

  private final int publicKey;

  public GetPublicKey(int publicKey) {
    this.publicKey = publicKey;
  }

  @Override
  public PublicKey runUsecase(Void unused) {
    return PublicKey.builder().publicKey(publicKey).build();
  }
}
