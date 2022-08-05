package de.antoniusstrauch.mpc.impl.controller;

import com.google.gson.Gson;
import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventBatch;
import de.antoniusstrauch.mpc.impl.config.AppConfig;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AdTechController {

  private final Gson gson;
  private final RestTemplate template;
  private final AppConfig config;
  BlockingQueue<Event> queue;

  @Autowired
  public AdTechController(Gson gson, RestTemplate template, AppConfig config) {
    this.queue = new LinkedBlockingDeque<>();
    this.gson = gson;
    this.template = template;
    this.config = config;

  }

  @PostMapping("/createEvent")
  void createEvent(@RequestBody Event event) throws InterruptedException {
    System.out.println(event.getType().toString());
    queue.put(event);
  }

  @Scheduled(fixedDelay = 5000)
  void checkAndSendEvents() throws InterruptedException {
    if (queue.size() < 10) {
      return;
    }

    LinkedList<Event> events = new LinkedList<>();
    int capacity = queue.size();
    for (int i = 0; i < capacity; i++) {
      Event e = queue.take();
      events.add(e);
    }
    EventBatch batch = EventBatch.builder().events(events).build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<>(gson.toJson(batch), headers);
    AttributionResult attributionResult = template.postForObject(
        config.getMpc().getServerURL() + "/requestAttribution", request,
        AttributionResult.class);
    if (attributionResult != null) {
      log.info("Attribution result: " + attributionResult.getResult());
    } else {
      log.warn("Attribution result null");
    }
  }
}
