package com.jayanslow.qlabMasker.painters;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Workspace;

public class PolygonEditFillGLPainter extends AbstractPolygonGLPainter {

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
      final Color color = new Color(ReadableColor.GREEN);
      color.setAlpha(ALPHA);
      return color;
    }

    float red = 0.4f;
    final float green = 0.4f;
    float blue = 0.4f;

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

    final List<Polygon> polygons = _workspace.getScreen().getPolygons();

    final int index = polygons.indexOf(polygon);
    float scale;
    if (index < 0) {
      scale = 1;
    }
    else {
      scale = (index + 1.0f) / polygons.size();
      scale = (scale - 0.5f) / 2 + 0.5f;
    }
    return new Color((int) (red * scale * 255), (int) (green * scale * 255), (int) (blue * scale * 255), ALPHA);
  }

}
