package de.antoniusstrauch.mpc.core.usecase.collector;

import de.antoniusstrauch.mpc.core.entity.Event;
import lombok.Data;

import java.util.List;

@Data
public class AttributeEventsCommand {

  List<Event> events;
}
