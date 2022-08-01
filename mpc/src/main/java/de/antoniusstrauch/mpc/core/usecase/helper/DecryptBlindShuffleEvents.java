package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventType;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class DecryptBlindShuffleEvents extends AUsecase<EventBatch, EventBatch> {

  private final DecryptEvents decryptEvents;
  private final BlindEvents blindEvents;
  private final ShuffleEvents shuffleEvents;

  public DecryptBlindShuffleEvents(DecryptEvents decryptEvents, BlindEvents blindEvents,
      ShuffleEvents shuffleEvents) {
    this.decryptEvents = decryptEvents;
    this.blindEvents = blindEvents;
    this.shuffleEvents = shuffleEvents;
  }

  @Override
  public EventBatch runUsecase(EventBatch eventBatch) {

    LinkedList<Event> outputEvents = new LinkedList<>();

    for (Event event : eventBatch.getEvents()) {
      // Save event data member
      EventType type = event.getType();
      int matchKey = event.getMatchKey();
      LocalDateTime timestamp = event.getTimestamp();

      // Transform match key (decrypt, blind)
      matchKey = decryptEvents.runUsecase(matchKey);
      matchKey = blindEvents.runUsecase(matchKey);

      // Create new event and write to array of events
      Event newEvent = Event.builder().type(type).matchKey(matchKey).timestamp(timestamp).build();
      outputEvents.add(newEvent);
    }

    // Write new eventCollection and shuffle
    return shuffleEvents.runUsecase(EventBatch.builder().events(outputEvents).build());
  }
}
