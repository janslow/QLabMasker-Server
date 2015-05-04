package com.jayanslow.qlabMasker.painters;

import javax.inject.Inject;
import javax.inject.Named;

import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Screen;

class ScreenInvertedMaskGLPainter extends AbstractScreenGLPainter implements GLPainter<Screen> {

  @Inject
  public ScreenInvertedMaskGLPainter(final GLUtils glUtils, @TargetPainterMode(PainterMode.INVERTED_MASK) final GLPainter<Polygon> polygonPainter, @Named(PaintersModule.NAME_MASK_COLOR) final ReadableColor clearColor) {
    super(glUtils, polygonPainter, clearColor);
  }

}
