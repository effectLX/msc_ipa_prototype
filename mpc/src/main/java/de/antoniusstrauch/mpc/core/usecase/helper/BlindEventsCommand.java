package de.antoniusstrauch.mpc.core.usecase.helper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlindEventsCommand {

  Integer inputMatchKey;
  Integer blindingFactor;
}
