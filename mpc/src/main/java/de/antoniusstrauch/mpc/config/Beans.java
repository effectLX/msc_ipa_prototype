package de.antoniusstrauch.mpc.config;

import de.antoniusstrauch.mpc.core.entity.BlindingFactor;
import de.antoniusstrauch.mpc.core.entity.Encryption;
import de.antoniusstrauch.mpc.core.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public HelperAttributionUC getHelperAttribution() {
    return new HelperAttributionUC();
  }

  @Bean
  public HelperBlindingUC getHelperBinding() {
    return new HelperBlindingUC();
  }

  @Bean
  public BlindingFactor getBlindingFactor() {
    return new BlindingFactor();
  }

  @Bean
  public HelperDecryptionUC getHelperDecryptionUC() {
    return new HelperDecryptionUC();
  }

  @Bean
  public HelperShufflingUC getHelperShufflingUC() {
    return new HelperShufflingUC();
  }

  @Bean
  public ServerCollectorUC getServerCollectorUC() {
    return new ServerCollectorUC();
  }

  @Bean(name = "ServerHelperOne")
  public ServerHelperUC getServerHelperUC1() {
    return new ServerHelperUC();
  }

  @Bean(name = "ServerHelperTwo")
  public ServerHelperUC getServerHelperUC2() {
    return new ServerHelperUC();
  }

  @Bean
  public ServerLeaderUC getServerLeaderUC() {
    return new ServerLeaderUC();
  }

  @Bean
  public Encryption getEncryption() {
    return new Encryption();
  }

  @Bean
  public PrepareJSONAttributionUC getJSON() { return new PrepareJSONAttributionUC(); }

  @Bean
  @Autowired
  public RunMPCUC runMPC(ServerLeaderUC leader,
      @Qualifier("ServerHelperOne") ServerHelperUC helperOne,
      @Qualifier("ServerHelperTwo") ServerHelperUC helperTwo,
      ServerCollectorUC collector, Encryption encrypt, BlindingFactor blind) {

    return new RunMPCUC(leader, helperOne, helperTwo, collector, encrypt, blind);
  }

  @Bean
  @Autowired
  public GetPublicKeyUC getPublicKey(Encryption encrypt) {
    return new GetPublicKeyUC(encrypt);
  }

  @Bean
  public CheckReportTriggerUC checkReport() {return new CheckReportTriggerUC();}

  @Bean
  public PrepareJSONDictionaryUC prepareDict() {return new PrepareJSONDictionaryUC();}

  @Bean
  public PostEventsToMPCUC postEvents() {return new PostEventsToMPCUC();}

  @Bean
  public AddEventToAdtechUC addEvent(CheckReportTriggerUC checkReport) {return new AddEventToAdtechUC(checkReport);}
}
