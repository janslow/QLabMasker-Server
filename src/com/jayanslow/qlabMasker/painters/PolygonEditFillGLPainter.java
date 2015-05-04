package com.jayanslow.qlabMasker.painters;

import java.util.Optional;

import javax.inject.Inject;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Workspace;

class PolygonEditFillGLPainter extends AbstractPolygonGLPainter {

  private static final int ALPHA = 200;

  private final Workspace _workspace;

  @Inject
  public PolygonEditFillGLPainter(final GLUtils glUtils, final Workspace workspace) {
    super(glUtils, Optional.empty());
    _workspace = workspace;

  }

  @Override
  protected ReadableColor getFillColor(final Polygon polygon) {
    final Optional<Polygon> selectedPolygon = _workspace.getSelectedPolygon();
    if (selectedPolygon.map(p -> p.equals(polygon)).orElse(false)) {
      final Color color = new Color(0, 200, 0, ALPHA);
      return color;
    }

    float red = 0.3f;
    final float green = 0.3f;
    float blue = 0.3f;

    switch (polygon.getRenderMode()) {
      case MASK:
        red = 1.0f;
        break;
      case UNMASK:
        blue = 1.0f;
        break;
      default:
        throw new RuntimeException(String.format("Unsupported render mode: %s", polygon.getRenderMode()));
    }

    return new Color((int) (red * 255), (int) (green * 255), (int) (blue * 255), ALPHA);
  }

}
