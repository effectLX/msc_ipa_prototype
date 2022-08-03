package de.antoniusstrauch.mpc.impl.util;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>,
    JsonDeserializer<LocalDateTime> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd'T'HH:mm:ss.SSS");

  @Override
  public @NotNull JsonElement serialize(@NotNull LocalDateTime localDateTime, Type type,
      JsonSerializationContext jsonSerializationContext) {
    return new JsonPrimitive(localDateTime.format(FORMATTER));
  }

  @Override
  public @NotNull LocalDateTime deserialize(@NotNull JsonElement jsonElement, Type type,
      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    return LocalDateTime.parse(jsonElement.getAsString(), FORMATTER);
  }
}
