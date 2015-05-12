package com.google.inject;

import java.lang.reflect.Type;

import com.google.gson.TypeAdapter;
import com.google.inject.internal.MoreTypes.ParameterizedTypeImpl;
import com.jayanslow.qlabMasker.painters.GLPainter;

public final class TypeLiteralUtils {

  public static <T> TypeLiteral<GLPainter<T>> painterOf(final Class<T> clazz) {
    return typeLiteralOf(parameterize(GLPainter.class, clazz));
  }

  public static <T> TypeLiteral<TypeAdapter<T>> typeAdapterOf(final Class<T> clazz) {
    return typeLiteralOf(parameterize(TypeAdapter.class, clazz));
  }

  @Deprecated
  private static Type parameterize(final Class<?> clazz, final Type parameter) {
    return new ParameterizedTypeImpl(null, clazz, parameter);
  }

  @Deprecated
  private static <T> TypeLiteral<T> typeLiteralOf(final Type type) {
    return new TypeLiteral<T>(type) {
    };
  }

  private TypeLiteralUtils() {
  }
}
