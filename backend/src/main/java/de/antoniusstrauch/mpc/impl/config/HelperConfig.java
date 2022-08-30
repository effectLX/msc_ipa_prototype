package de.antoniusstrauch.mpc.impl.config;

import lombok.Data;

@Data
public class HelperConfig {

  String serverURL;
  Long decryptionFactor;
  Long privateKey;
}
