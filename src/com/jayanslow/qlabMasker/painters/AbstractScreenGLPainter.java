package com.jayanslow.qlabMasker.painters;

import java.util.List;
import java.util.Optional;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Screen;

public class AbstractScreenGLPainter extends AbstractGLPainterWithChildren<Screen, Polygon> implements GLPainter<Screen> {

  private final GLPainter<Polygon> _polygonPainter;

  private final ReadableColor _clearColor;

  public AbstractScreenGLPainter(final GLUtils glUtils, final GLPainter<Polygon> polygonPainter, final ReadableColor clearColor) {
    super(glUtils, Optional.of(polygonPainter));
    _polygonPainter = polygonPainter;
    _clearColor = clearColor;
  }

  @Override
  protected List<Polygon> getChildren(final Screen screen) {
    return screen.getPolygons();
  }

  @Override
  protected void paintSelf(final Screen screen) {
    glUtils().applyClearColor(_clearColor);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
  }
}