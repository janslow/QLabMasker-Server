package com.jayanslow.qlabMasker.json;

import static com.google.inject.TypeLiteralUtils.typeAdapterOf;

import com.google.inject.AbstractModule;
import com.jayanslow.qlabMasker.models.Point;
import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.RenderMode;

public class JsonModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(IteratorTypeAdapter.class).to(IteratorTypeAdapterImpl.class);

    bind(typeAdapterOf(RenderMode.class)).to(RenderModeTypeAdapter.class);
    bind(typeAdapterOf(Point.class)).to(PointTypeAdapter.class);
    bind(typeAdapterOf(Polygon.class)).to(PolygonTypeAdapter.class);
  }

}
