package de.antoniusstrauch.mpc.core.usecase.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecryptEventsCommand {

  Long inputMatchKey;
  Long clientKey;

}
