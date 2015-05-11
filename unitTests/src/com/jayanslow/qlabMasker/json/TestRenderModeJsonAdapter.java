package com.jayanslow.qlabMasker.json;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.jayanslow.qlabMasker.models.RenderMode;

public class TestRenderModeJsonAdapter extends AbstractTestTypeAdapter<RenderMode> {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testAdapter() {
    abstractTestAdapter(RenderMode.MASK, "\"MASK\"");
    abstractTestAdapter(RenderMode.UNMASK, "\"UNMASK\"");
  }

  @Test
  public void testDeserializeUnknown() {
    thrown.expect(RuntimeException.class);
    thrown.expectMessage("mAsK");

    deserialize("\"mAsK\"");
  }

  @Override
  protected TypeAdapter<RenderMode> createTypeAdaptor() {
    return new RenderModeTypeAdapter(mock(Logger.class));
  }

  @Override
  protected RenderMode deserialize(final String json) {
    final TypeAdapter<RenderMode> toTest = createTypeAdaptor();
    try {
      final JsonReader reader = new JsonReader(new StringReader(json));
      reader.setLenient(true);
      return toTest.read(reader);
    }
    catch (final IOException e) {
      throw new RuntimeException("Error serializing object.", e);
    }
  }
}
