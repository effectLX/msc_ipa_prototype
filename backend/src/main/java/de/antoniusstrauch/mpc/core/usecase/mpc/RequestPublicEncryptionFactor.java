package de.antoniusstrauch.mpc.core.usecase.mpc;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.PublicEncryptionFactor;

public class RequestPublicEncryptionFactor extends AUsecase<Void, PublicEncryptionFactor> {

  private final Long publicEncryptionFactor;

  // Assumption, that encryption factor is between 2-5
  public RequestPublicEncryptionFactor() {
    this.publicEncryptionFactor = (long) (Math.random() * 3 + 2);
  }

  @Override
  public PublicEncryptionFactor runUsecase(Void unused) {
    return PublicEncryptionFactor.builder().publicEncryptionFactor(publicEncryptionFactor).build();
  }
}
