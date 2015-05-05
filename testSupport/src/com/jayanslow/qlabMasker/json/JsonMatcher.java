package com.jayanslow.qlabMasker.json;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JsonMatcher extends TypeSafeMatcher<JsonElement> {

  public static JsonMatcher isJson(final JsonElement json) {
    return new JsonMatcher(json);
  }

  public static JsonMatcher isJson(final String json) {
    final Gson gson = new Gson();
    final JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
    return isJson(jsonElement);
  }

  private final JsonElement _json;

  public JsonMatcher(final JsonElement json) {
    _json = json;
  }

  @Override
  public void describeTo(final Description description) {
    description.appendText("is ").appendValue(_json);
  }

  @Override
  protected void describeMismatchSafely(final JsonElement item, final Description mismatchDescription) {
    mismatchDescription.appendText("was ").appendValue(item);
  }

  protected boolean matchesSafely(final JsonArray expected, final JsonArray actual) {
    if (expected.size() != actual.size()) {
      return false;
    }
    final Iterator<JsonElement> expectedValues = expected.iterator();
    final Iterator<JsonElement> actualValues = actual.iterator();
    while (expectedValues.hasNext()) {
      if (!matchesSafely(expectedValues.next(), actualValues.next())) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected boolean matchesSafely(final JsonElement item) {
    return matchesSafely(_json, item);
  }

  protected boolean matchesSafely(final JsonObject expected, final JsonObject actual) {
    final Set<Entry<String, JsonElement>> expectedProperties = expected.entrySet();
    if (expectedProperties.size() != actual.entrySet().size()) {
      return false;
    }
    for (final Entry<String, JsonElement> expectedProperty : expectedProperties) {
      final String key = expectedProperty.getKey();
      if (actual.has(key)) {
        if (!matchesSafely(expectedProperty.getValue(), actual.get(key))) {
          return false;
        }
      }
      else {
        return false;
      }
    }
    return true;
  }

  protected boolean matchesSafely(final JsonPrimitive expected, final JsonPrimitive actual) {
    if (expected.isNumber() && actual.isNumber()) {
      return expected.getAsBigDecimal().equals(actual.getAsBigDecimal());
    }
    else if (expected.isBoolean() && actual.isBoolean()) {
      return expected.getAsBoolean() == actual.getAsBoolean();
    }
    else if (expected.isString() && actual.isString()) {
      return expected.getAsString().equals(actual.getAsBoolean());
    }
    return false;
  }

  private boolean matchesSafely(final JsonElement expected, final JsonElement actual) {
    if (expected.isJsonArray() && actual.isJsonArray()) {
      return matchesSafely(expected.getAsJsonArray(), actual.getAsJsonArray());
    }
    else if (expected.isJsonNull() && actual.isJsonNull()) {
      return true;
    }
    else if (expected.isJsonObject() && actual.isJsonObject()) {
      return matchesSafely(expected.getAsJsonObject(), actual.getAsJsonObject());
    }
    else if (expected.isJsonPrimitive() && actual.isJsonPrimitive()) {
      return matchesSafely(expected.getAsJsonPrimitive(), actual.getAsJsonPrimitive());
    }
    return false;
  }
}
