package de.antoniusstrauch.mpc.core.usecase.mpc;

import de.antoniusstrauch.mpc.core.AUsecase;
import de.antoniusstrauch.mpc.core.bridge.IHelperServer;
import de.antoniusstrauch.mpc.core.bridge.ILeaderServer;
import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.core.entity.EventBatchPair;

public class RequestAttribution extends AUsecase<EventBatch, AttributionResult> {

  private final ILeaderServer leader;
  private final IHelperServer helperOne;
  private final IHelperServer helperTwo;

  public RequestAttribution(ILeaderServer leader, IHelperServer helperOne,
      IHelperServer helperTwo) {
    this.leader = leader;
    this.helperOne = helperOne;
    this.helperTwo = helperTwo;
  }

  @Override
  public AttributionResult runUsecase(EventBatch eventBatch) {
    EventBatchPair batches = leader.seperatedBatch(eventBatch);
    EventBatch shuffledBatchOne = helperOne.decryptBlindShuffle(batches.getBatchOne());
    EventBatch shuffledBatchTwo = helperTwo.decryptBlindShuffle(batches.getBatchTwo());
    EventBatch secondTimeShuffledBatchOne = helperOne.decryptBlindShuffle(shuffledBatchTwo);
    EventBatch secondTimeShuffledBatchTwo = helperTwo.decryptBlindShuffle(shuffledBatchOne);

    EventBatchPair pair = EventBatchPair.builder().batchOne(secondTimeShuffledBatchOne)
        .batchTwo(secondTimeShuffledBatchTwo).build();

    return leader.collect(pair);
  }
}
