package de.antoniusstrauch.mpc.impl.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.jetbrains.annotations.NotNull;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>,
    JsonDeserializer<LocalDateTime> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd'T'HH:mm:ss.SSS");

  @Override
  public JsonElement serialize(LocalDateTime localDateTime, Type type,
      JsonSerializationContext jsonSerializationContext) {
    return new JsonPrimitive(localDateTime.format(FORMATTER));
  }

  @Override
  public LocalDateTime deserialize(JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    return LocalDateTime.parse(jsonElement.getAsString(), FORMATTER);
  }
}
