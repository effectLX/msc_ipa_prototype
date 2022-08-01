package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventType;

import java.util.LinkedList;

public class ServerHelperUC {

  private final HelperDecryptionUC decrypting = new HelperDecryptionUC();
  private final HelperBlindingUC blinding = new HelperBlindingUC();
  private final HelperShufflingUC shuffling = new HelperShufflingUC();

  public LinkedList<Event> runUsecase(LinkedList<Event> inputEvents, int decrypt, int blind) {

    LinkedList<Event> outputEvents = new LinkedList<>();

    for(Event event : inputEvents){
      // Save event data member
      EventType thisType = event.getType();
      int thisMatchKey = event.getMatchKey();
      int thisTimestamp = event.getTimestamp();

      // Transform match key (decrypt, blind)
      thisMatchKey = decrypting.runUsecase(thisMatchKey, decrypt);
      thisMatchKey = blinding.runUsecase(thisMatchKey, blind);

      // Create new event and write to array of events
      Event newEvent = new Event(thisType, Integer.toString(thisMatchKey), Integer.toString(thisTimestamp));
      outputEvents.add(newEvent);
    }

    // Write new eventCollection and shuffle
    shuffling.runUsecase(outputEvents);
    return outputEvents;
  }
}
