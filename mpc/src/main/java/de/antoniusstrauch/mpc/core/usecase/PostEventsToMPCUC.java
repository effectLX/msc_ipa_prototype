package de.antoniusstrauch.mpc.core.usecase;

import org.jetbrains.annotations.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

//TODO: Das müssen wir umbauen, der Usecase ist richtig, aber die Details der implementierung gehören in interfaces
// also ungefähr so
// runUsecase(Event event) {
//  checkEvent(event)
//  mpcucRepository.sendRequest(event)
// }
// Das mpcuRepository macht dann deinen call mit HTTP Headers und setConentType usw.
public class PostEventsToMPCUC {

  private final RestTemplate restTemplate;

  public PostEventsToMPCUC() {
    this.restTemplate = new RestTemplateBuilder().build();
  }

  public @Nullable String runUsecase(String attributionRequest) {

    String url = "http://localhost:3000/attribution";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    // build the request
    HttpEntity<String> entity = new HttpEntity<>(attributionRequest, headers);

    // send POST request
    ResponseEntity<String> result = this.restTemplate.postForEntity(url, entity, String.class);

    // check response status code
    if (result.getStatusCode() == HttpStatus.CREATED) {
      return result.getBody();
    } else {
      return null;
    }
  }
}
