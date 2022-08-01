package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.BlindingFactor;
import de.antoniusstrauch.mpc.core.entity.Encryption;
import de.antoniusstrauch.mpc.core.entity.Event;

import java.util.LinkedList;

public class RunMPCUC {

  private final ServerLeaderUC leader;// = new ServerLeaderUC();
  private final ServerHelperUC helperOne;// = new ServerLeaderUC(); = new ServerHelperUC();
  private final ServerHelperUC helperTwo;// = new ServerLeaderUC(); = new ServerHelperUC();
  private final ServerCollectorUC collector;// = new ServerLeaderUC(); = new ServerCollectorUC();
  private final Encryption decrypt;// = new ServerLeaderUC(); = new BlindingFactor();
  private final BlindingFactor blind;// = new ServerLeaderUC(); = new BlindingFactor();

  public RunMPCUC(ServerLeaderUC leader, ServerHelperUC helperOne, ServerHelperUC helperTwo, ServerCollectorUC collector,
                  Encryption encrypt, BlindingFactor blind) {
    this.leader = leader;
    this.helperOne = helperOne;
    this.helperTwo = helperTwo;
    this.collector = collector;
    this.decrypt = encrypt;
    this.blind = blind;
  }

  public Integer runUsecase(LinkedList<Event> eventsInput) {

    // Split events in two batches
    LinkedList<LinkedList<Event>> batches = leader.runUsecase(eventsInput);

    // Pass batches through helper servers with handover between servers
    batches.set(0, helperOne.runUsecase(batches.get(0), decrypt.getDecrypt1(), blind.getBlind1()));
    batches.set(1, helperTwo.runUsecase(batches.get(1), decrypt.getDecrypt2(), blind.getBlind2()));
    batches.set(1, helperOne.runUsecase(batches.get(1), decrypt.getDecrypt1(), blind.getBlind1()));
    batches.set(0, helperTwo.runUsecase(batches.get(0), decrypt.getDecrypt2(), blind.getBlind2()));

    // Combine batches and calculate attribution
    return collector.runUsecase(batches.get(0), batches.get(1));
  }
}
