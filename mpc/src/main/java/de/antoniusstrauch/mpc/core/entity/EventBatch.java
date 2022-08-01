package de.antoniusstrauch.mpc.core.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EventBatch {
  List<Event> events;
}
