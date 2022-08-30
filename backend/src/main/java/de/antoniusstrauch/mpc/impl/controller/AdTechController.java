package de.antoniusstrauch.mpc.impl.controller;

import com.google.gson.Gson;
import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.impl.config.AppConfig;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@CrossOrigin
public class AdTechController {

  private final Gson gson;
  private final RestTemplate template;
  private final AppConfig config;
  private final BlockingQueue<Event> queue;
  private AttributionResult attributionResult;

  @Autowired
  public AdTechController(Gson gson, RestTemplate template, AppConfig config) {
    this.attributionResult = AttributionResult.builder().result(0L).build();
    this.queue = new LinkedBlockingQueue<>();
    this.gson = gson;
    this.template = template;
    this.config = config;

  }

  @PostMapping("/createEvent")
  void createEvent(@RequestBody Event event) throws InterruptedException {
    log.info("Component: {}, Creating event: {}", AdTechController.class.getSimpleName(), event);
    queue.put(event);
  }

  //ONLY FOR DEMONSTRATION PUROPSE
  @GetMapping("/getLastAttributionResult")
  AttributionResult getLastAttributionResult() {
    return this.attributionResult;
  }

  //ONLY FOR DEMONSTRATION PUROPSE
  @GetMapping("/getCurrentQueue")
  BlockingQueue<Event> getCurrentQueue() {
    return queue;
  }

  @GetMapping("/checkAndSendEvents")
  void checkAndSendEvents() throws InterruptedException {
    LinkedList<Event> events = new LinkedList<>();
    int capacity = queue.size();
    for (int i = 0; i < capacity; i++) {
      Event e = queue.take();
      events.add(e);
    }
    EventBatch batch = EventBatch.builder().eventBatchPairId(UUID.randomUUID()).events(events)
        .build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(gson.toJson(batch), headers);
    this.attributionResult = template.postForObject(
        config.getMpc().getServerURL() + "/requestAttribution", request, AttributionResult.class);
    if (attributionResult != null) {
      log.info("Attribution result: " + attributionResult.getResult());
    } else {
      log.warn("Attribution result null");
    }
  }
}
