package de.antoniusstrauch.mpc.core.usecase.collector;

import de.antoniusstrauch.mpc.core.entity.Event;
import lombok.Data;

import java.util.LinkedList;

@Data
public class MergeBatchesCommand {

  LinkedList<Event> batchOne;
  LinkedList<Event> batchTwo;
}
