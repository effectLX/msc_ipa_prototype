package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.MPCException;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlindEventsCommand {

  Long matchKey;
  UUID eventBatchPairId;

  @Builder
  public BlindEventsCommand(Long matchKey, UUID eventBatchPairId) throws MPCException {
    if (matchKey == null) {
      throw new MPCException("matchKey cannot be null");
    }
    if (eventBatchPairId == null) {
      throw new MPCException("eventBatchPairId cannot be null");
    }
    this.matchKey = matchKey;
    this.eventBatchPairId = eventBatchPairId;
  }

}
