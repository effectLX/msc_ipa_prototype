package de.antoniusstrauch.mpc.core.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventBatchPair {

  EventBatch batchOne;
  EventBatch batchTwo;
}
