package de.antoniusstrauch.mpc.core.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

  // Secret shares for numerical values to be added
  // Tigger value to be added
  private EventType type;
  private Long matchKey;
  private Long clientKey;
  private LocalDateTime timestamp;

}
