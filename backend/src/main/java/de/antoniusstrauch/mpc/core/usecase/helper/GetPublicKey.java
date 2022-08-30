package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.bridge.IMpcServer;
import de.antoniusstrauch.mpc.core.entity.PublicEncryptionFactor;
import de.antoniusstrauch.mpc.core.entity.PublicKey;

public class GetPublicKey extends AUsecase<Void, PublicKey> {

  private final Long privateKey;
  private final IMpcServer mpcServer;

  public GetPublicKey(Long privateKey, IMpcServer mpcServer) {
    this.privateKey = privateKey;
    this.mpcServer = mpcServer;
  }

  // Simplification, protocol proposes ElGamal encryption
  // Prototype uses simplified encryption based on explanation in project report (see Analysis on IPA Attack Space)
  @Override
  public PublicKey runUsecase(Void unused) {
    PublicEncryptionFactor publicEncryptionFactor = mpcServer.requestPublicEncryptionFactor();
    Long key = (long) Math.pow(publicEncryptionFactor.getPublicEncryptionFactor(), privateKey);
    return PublicKey.builder().publicKey(key).build();
  }
}
