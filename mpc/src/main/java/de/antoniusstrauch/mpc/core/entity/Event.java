package de.antoniusstrauch.mpc.core.entity;

import lombok.Getter;

@Getter
public class Event {

  private final EventType type;
  private final int matchKey;
  private final int timestamp; // secrete shared value

  public Event(EventType eventType, String matchKey, String timestamp) {
    this.type = eventType;
    this.matchKey = checkInputString(matchKey);
    this.timestamp = checkInputString(timestamp);
  }

  private int checkInputString(String strValue) {
    if (strValue == null || strValue.length() == 0) {
      throw new IllegalArgumentException("Value is null or empty.");
    }
    int value;
    try {
      value = Integer.parseInt(strValue);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Value is not an integer.");
    }
    return value;
  }
}
