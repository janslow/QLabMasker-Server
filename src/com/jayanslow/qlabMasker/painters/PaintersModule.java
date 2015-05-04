package com.jayanslow.qlabMasker.painters;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteralUtils;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.name.Names;
import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Screen;

public class PaintersModule extends AbstractModule {

  public static final String NAME_MASK_COLOR = "MASK_COLOR";

  public static final String NAME_UNMASK_COLOR = "UNMASK_COLOR";

  @Override
  protected void configure() {
    bindPainter(Screen.class, PainterMode.MASK).to(ScreenMaskGLPainter.class);
    bindPainter(Polygon.class, PainterMode.MASK).to(PolygonMaskGLPainter.class);

    bindPainter(Screen.class, PainterMode.INVERTED_MASK).to(ScreenInvertedMaskGLPainter.class);
    bindPainter(Polygon.class, PainterMode.INVERTED_MASK).to(PolygonInvertedMaskGLPainter.class);

    bindPainter(Screen.class, PainterMode.EDIT).to(ScreenEditGLPainter.class);
    bindPainter(Polygon.class, PainterMode.EDIT).to(PolygonEditFillGLPainter.class);

    bind(ReadableColor.class).annotatedWith(Names.named(NAME_MASK_COLOR)).toInstance(new Color(0, 0, 0));
    bind(ReadableColor.class).annotatedWith(Names.named(NAME_UNMASK_COLOR)).toInstance(new Color(255, 255, 255));
  }

  private <T> LinkedBindingBuilder<GLPainter<T>> bindPainter(final Class<T> targetClass, final PainterMode targetMode) {
    return bind(TypeLiteralUtils.painterOf(targetClass)).annotatedWith(TargetPainterModes.target(targetMode));
  }
}
