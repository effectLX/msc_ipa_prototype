package de.antoniusstrauch.mpc.core.usecase.mpc;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.entity.PublicKey;

public class RequestPublicKey extends AUsecase<Void, PublicKey> {

  private final IHelperServer helperServerOne;
  private final IHelperServer helperServerTwo;

  public RequestPublicKey(IHelperServer helperServerOne, IHelperServer helperServerTwo) {
    this.helperServerOne = helperServerOne;
    this.helperServerTwo = helperServerTwo;
  }

  // Threshold encryption based on pure multiplication of keys, to be improved
  @Override
  public PublicKey runUsecase(Void unused) {
    PublicKey publicKeyOne = helperServerOne.getPublicKey();
    PublicKey publicKeyTwo = helperServerTwo.getPublicKey();

    return PublicKey.builder().publicKey(publicKeyOne.getPublicKey() * publicKeyTwo.getPublicKey())
        .build();
  }
}
