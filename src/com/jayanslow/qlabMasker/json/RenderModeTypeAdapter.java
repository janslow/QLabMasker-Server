package com.jayanslow.qlabMasker.json;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jayanslow.qlabMasker.models.RenderMode;

class RenderModeTypeAdapter extends AbstractTypeAdapter<RenderMode> {

  @Inject
  public RenderModeTypeAdapter(final Logger logger) {
    super(logger);
  }

  @Override
  public RenderMode read(final JsonReader in) throws IOException {
    final String s = in.nextString();
    try {
      return Enum.valueOf(RenderMode.class, s);
    }
    catch (final IllegalArgumentException e) {
      throw new RuntimeException(String.format("Unable to parse render mode: %s", s), e);
    }
  }

  @Override
  public void write(final JsonWriter out, final RenderMode value) throws IOException {
    switch (value) {
      case MASK:
        out.value("MASK");
        break;
      case UNMASK:
        out.value("UNMASK");
        break;
      default:
        getLogger().warning(String.format("Unknown render mode to serialize: %s", value));
        out.value(value.toString().toUpperCase());
        break;
    }
  }
}
