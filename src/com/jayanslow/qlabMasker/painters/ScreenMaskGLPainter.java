package com.jayanslow.qlabMasker.painters;

import javax.inject.Inject;
import javax.inject.Named;

import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Screen;

public class ScreenMaskGLPainter extends AbstractScreenGLPainter implements GLPainter<Screen> {

  @Inject
  public ScreenMaskGLPainter(final GLUtils glUtils, @TargetPainterMode(PainterMode.MASK) final GLPainter<Polygon> polygonPainter, @Named(PaintersModule.NAME_UNMASK_COLOR) final ReadableColor clearColor) {
    super(glUtils, polygonPainter, clearColor);
  }

}
