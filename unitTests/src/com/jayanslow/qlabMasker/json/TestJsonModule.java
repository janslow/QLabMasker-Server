package com.jayanslow.qlabMasker.json;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Collection;

import org.hamcrest.Matcher;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.gson.TypeAdapter;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteralUtils;
import com.jayanslow.qlabMasker.AbstractTestModule;
import com.jayanslow.qlabMasker.models.Point;
import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.RenderMode;

public class TestJsonModule extends AbstractTestModule {
  @Parameters(name = "{index} = {0}")
  public static Collection<Object[]> data() {
    final Builder<Object> builder = ImmutableSet.builder();
    builder.add(createSimpleConfig(IteratorTypeAdapter.class, IteratorTypeAdapterImpl.class));
    builder.add(createTypeAdapterConfig(Point.class, PointTypeAdapter.class));
    builder.add(createTypeAdapterConfig(Polygon.class, PolygonTypeAdapter.class));
    builder.add(createTypeAdapterConfig(RenderMode.class, RenderModeTypeAdapter.class));
    return wrapParameters(builder.build());
  }

  private static <T> GuiceTestConfig<TypeAdapter<T>> createTypeAdapterConfig(final Class<T> targetClazz, final Class<? extends TypeAdapter<T>> adapterClazz) {
    return new GuiceTestConfig<TypeAdapter<T>>() {

      @Override
      public Key<TypeAdapter<T>> getKey() {
        return Key.get(TypeLiteralUtils.typeAdapterOf(targetClazz));
      }

      @Override
      public Matcher<? super TypeAdapter<T>> getMatcher() {
        return instanceOf(adapterClazz);
      }

      @Override
      public String toString() {
        return String.format("TypeAdapter<%s>", targetClazz.getSimpleName());
      }
    };
  }

  public TestJsonModule(final GuiceTestConfig<Object> config) {
    super(config);
  }

  @Override
  protected Module createModule() {
    return new JsonModule();
  }
}
