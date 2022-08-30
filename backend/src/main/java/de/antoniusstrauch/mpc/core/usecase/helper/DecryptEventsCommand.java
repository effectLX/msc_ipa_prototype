package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.MPCException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DecryptEventsCommand {

  Long inputMatchKey;
  Long clientKey;

  @Builder
  public DecryptEventsCommand(Long inputMatchKey, Long clientKey) throws MPCException {
    if (inputMatchKey == null) {
      throw new MPCException("inputMatchKey cannot be null");
    }
    if (clientKey == null) {
      throw new MPCException("clientKey cannot be null");
    }
    this.inputMatchKey = inputMatchKey;
    this.clientKey = clientKey;
  }

}
