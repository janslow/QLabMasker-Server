package com.jayanslow.qlabMasker.json;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jayanslow.qlabMasker.models.Point;

class PointTypeAdapter extends AbstractTypeAdapter<Point> {
  private static final String KEY_X = "x";

  private static final String KEY_Y = "y";

  @Inject
  public PointTypeAdapter(final Logger logger) {
    super(logger);
  }

  @Override
  public Point read(final JsonReader in) throws IOException {
    in.beginObject();

    Optional<Integer> x = Optional.empty();
    Optional<Integer> y = Optional.empty();

    while (in.hasNext()) {
      final String name = in.nextName();
      switch (name) {
        case KEY_X:
          x = Optional.of(in.nextInt());
          break;
        case KEY_Y:
          y = Optional.of(in.nextInt());
          break;
        default:
          logUnknownProperty(name);
          in.skipValue();
          break;
      }
    }

    in.endObject();

    assertPropertyIsPresent(KEY_X, x);
    assertPropertyIsPresent(KEY_Y, y);

    return new Point(x.get(), y.get());
  }

  @Override
  public void write(final JsonWriter out, final Point value) throws IOException {
    out.beginObject();

    out.name(KEY_X);
    out.value(value.getX());

    out.name(KEY_Y);
    out.value(value.getY());

    out.endObject();
  }
}
