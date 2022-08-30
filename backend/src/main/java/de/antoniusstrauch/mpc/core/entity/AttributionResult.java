package de.antoniusstrauch.mpc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributionResult {

  // Count of conversions in the prototype, must be extent to sum/avg of trigger values
  Long result;
}
