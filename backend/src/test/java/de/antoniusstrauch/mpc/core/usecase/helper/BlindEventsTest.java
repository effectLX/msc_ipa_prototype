package de.antoniusstrauch.mpc.core.usecase.helper;

import static org.mockito.Mockito.mock;

import de.antoniusstrauch.mpc.core.MPCException;
import de.antoniusstrauch.mpc.core.bridge.IBlindingFactorRepository;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BlindEventsTest {

  // Exemplary test, to be extended for every use case

  private BlindEvents blindEvents;
  private UUID eventBatchPairId;

  @BeforeEach
  void setUp() {
    this.eventBatchPairId = UUID.randomUUID();
    IBlindingFactorRepository blindingFactorRepository = mock(IBlindingFactorRepository.class);
    this.blindEvents = new BlindEvents(blindingFactorRepository);
    Mockito.when(blindingFactorRepository.getBlindingFactor(eventBatchPairId)).thenReturn(5L);
  }

  @Test
  void testUseCase() throws MPCException {
    BlindEventsCommand command = BlindEventsCommand.builder().matchKey(5L)
        .eventBatchPairId(eventBatchPairId)
        .build();
    Long result = blindEvents.runUsecase(command);
    Assertions.assertEquals(25L, result);
  }
}