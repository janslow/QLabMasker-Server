package com.jayanslow.qlabMasker.json;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jayanslow.qlabMasker.models.Point;
import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.RenderMode;

class PolygonTypeAdapter extends AbstractTypeAdapter<Polygon> {
  private static final String KEY_NAME = "name";

  private static final String KEY_RENDER_MODE = "renderMode";

  private static final String KEY_POINTS = "points";

  private final TypeAdapter<Point> _pointTypeAdapter;

  private final TypeAdapter<RenderMode> _renderModeTypeAdapter;

  private final IteratorTypeAdapter _iteratorTypeAdapter;

  @Inject
  public PolygonTypeAdapter(final Logger logger, final TypeAdapter<RenderMode> renderModeTypeAdapter, final TypeAdapter<Point> pointTypeAdapter, final IteratorTypeAdapter iteratorTypeAdapter) {
    super(logger);
    _renderModeTypeAdapter = renderModeTypeAdapter;
    _pointTypeAdapter = pointTypeAdapter;
    _iteratorTypeAdapter = iteratorTypeAdapter;
  }

  @Override
  public Polygon read(final JsonReader in) throws IOException {
    in.beginObject();

    Optional<String> name = Optional.empty();
    Optional<RenderMode> renderMode = Optional.empty();
    Optional<List<Point>> points = Optional.empty();

    while (in.hasNext()) {
      final String property = in.nextName();
      switch (property) {
        case KEY_NAME:
          name = Optional.of(in.nextString());
          break;
        case KEY_RENDER_MODE:
          renderMode = Optional.of(_renderModeTypeAdapter.read(in));
          break;
        case KEY_POINTS:
          points = Optional.of(_iteratorTypeAdapter.read(_pointTypeAdapter, in));
          break;
        default:
          logUnknownProperty(property);
          in.skipValue();
          break;
      }
    }

    in.endObject();

    assertPropertyIsPresent(KEY_NAME, name);
    assertPropertyIsPresent(KEY_RENDER_MODE, renderMode);
    assertPropertyIsPresent(KEY_POINTS, points);

    return new Polygon(name.get(), renderMode.get(), points.get());
  }

  @Override
  public void write(final JsonWriter out, final Polygon value) throws IOException {
    out.beginObject();

    out.name(KEY_NAME);
    out.value(value.getName());

    out.name(KEY_RENDER_MODE);
    _renderModeTypeAdapter.write(out, value.getRenderMode());

    out.name(KEY_POINTS);
    _iteratorTypeAdapter.write(_pointTypeAdapter, out, value.getPoints());

    out.endObject();
  }
}
