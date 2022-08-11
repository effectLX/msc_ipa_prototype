package de.antoniusstrauch.mpc.core.usecase.helper;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
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
      // Transform match key (decrypt, blind)
      Long matchKey = decryptEvents.runUsecase(
          DecryptEventsCommand.builder().clientKey(event.getClientKey())
              .inputMatchKey(event.getMatchKey()).build());

      matchKey = blindEvents.runUsecase(BlindEventsCommand.builder().matchKey(matchKey)
          .eventBatchPairId(eventBatch.getEventBatchPairId()).build());

      // Create new event and write to array of events
      Event newEvent = Event.builder().type(event.getType()).matchKey(matchKey)
          .clientKey(event.getClientKey()).timestamp(event.getTimestamp()).build();
      outputEvents.add(newEvent);
    }

    // Write new eventCollection and shuffle
    return shuffleEvents.runUsecase(EventBatch.builder().events(outputEvents).build());
  }
}
