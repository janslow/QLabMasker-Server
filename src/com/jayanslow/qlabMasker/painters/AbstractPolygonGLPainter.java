package com.jayanslow.qlabMasker.painters;

import java.util.List;
import java.util.Optional;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Point;
import com.jayanslow.qlabMasker.models.Polygon;

public abstract class AbstractPolygonGLPainter extends AbstractGLPainterWithChildren<Polygon, Point> {

  public AbstractPolygonGLPainter(final GLUtils glUtils, final Optional<GLPainter<Point>> pointPainter) {
    super(glUtils, pointPainter);
  }

  protected void applyStyle(final Polygon polygon) {
    glUtils().applyColor(getFillColor(polygon));
  }

  @Override
  protected List<Point> getChildren(final Polygon polygon) {
    return polygon.getPoints();
  }

  protected abstract ReadableColor getFillColor(final Polygon polygon);

  @Override
  protected void paintSelf(final Polygon polygon) {
    applyStyle(polygon);

    GL11.glBegin(GL11.GL_POLYGON);
    for (final Point point : polygon.getPoints()) {
      GL11.glVertex3f(point.getX() + 0.5f, point.getY() + 0.5f, 0.0f);
    }
    GL11.glEnd();
  }

}