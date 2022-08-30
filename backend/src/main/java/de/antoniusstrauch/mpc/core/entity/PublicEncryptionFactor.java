package de.antoniusstrauch.mpc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicEncryptionFactor {

  // Simplified assumption, protocol proposes ElGamal encryption using Elliptic Curves
  Long publicEncryptionFactor;

}
