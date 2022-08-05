package de.antoniusstrauch.mpc.impl.config;

import lombok.Data;

@Data
public class HelperConfig {

  String serverURL;
  Integer decryptionFactor;
  Integer blindingFactor;
  Integer publicKey;
}
