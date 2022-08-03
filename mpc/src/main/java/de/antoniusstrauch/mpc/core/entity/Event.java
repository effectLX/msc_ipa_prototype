package de.antoniusstrauch.mpc.core.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class Event {

  private EventType type;
  private Integer matchKey;

  private LocalDateTime timestamp;

  public Event(EventType eventType, Integer matchKey, LocalDateTime timestamp) {
    this.type = eventType;
    this.matchKey = checkMatchKey(matchKey);
    this.timestamp = checkTimestamp(timestamp);
  }

  private LocalDateTime checkTimestamp(LocalDateTime timestamp) {
    return timestamp; // TODO Check timestamp
  }

  private Integer checkMatchKey(Integer matchKey) {
    return matchKey; //TODO Check MAtch key
  }

}
