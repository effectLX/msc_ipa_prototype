package de.antoniusstrauch.mpc.core.usecase.helper;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlindEventsCommand {

  Integer matchKey;
  UUID eventBatchPairId;
}
