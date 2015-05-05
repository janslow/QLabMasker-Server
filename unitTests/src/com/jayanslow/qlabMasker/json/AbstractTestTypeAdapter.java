package com.jayanslow.qlabMasker.json;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;

public abstract class AbstractTestTypeAdapter<T> {
  protected void abstractTestAdapter(final T object, final String json) {
    assertThat(serialize(object), JsonMatcher.isJson(json));
    assertThat(deserialize(json), is(object));
  }

  protected abstract TypeAdapter<T> createTypeAdaptor();

  protected T deserialize(final String json) {
    final TypeAdapter<T> toTest = createTypeAdaptor();
    try {
      return toTest.fromJson(json);
    }
    catch (final IOException e) {
      throw new RuntimeException("Error serializing object.", e);
    }
  }

  protected JsonElement serialize(final T object) {
    final TypeAdapter<T> toTest = createTypeAdaptor();
    return toTest.toJsonTree(object);
  }
}
