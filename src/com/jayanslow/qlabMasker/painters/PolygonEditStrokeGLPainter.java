package com.jayanslow.qlabMasker.painters;

import java.util.Optional;

import javax.inject.Inject;

import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Workspace;

class PolygonEditStrokeGLPainter extends AbstractPolygonGLPainter {

  private final Workspace _workspace;

  @Inject
  public PolygonEditStrokeGLPainter(final GLUtils glUtils, final Workspace workspace) {
    super(glUtils, Optional.empty());
    _workspace = workspace;

  }

  @Override
  protected ReadableColor getFillColor(final Polygon polygon) {
    final Optional<Polygon> selectedPolygon = _workspace.getSelectedPolygon();
    if (selectedPolygon.map(p -> p.equals(polygon)).orElse(false)) {
      return ReadableColor.GREEN;
    }
    switch (polygon.getRenderMode()) {
      case MASK:
        return ReadableColor.RED;
      case UNMASK:
        return ReadableColor.BLUE;
      default:
        throw new RuntimeException(String.format("Unsupported render mode: %s", polygon.getRenderMode()));
    }
  }

}
