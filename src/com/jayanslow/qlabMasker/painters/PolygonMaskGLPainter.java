package com.jayanslow.qlabMasker.painters;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;

class PolygonMaskGLPainter extends AbstractPolygonGLPainter implements GLPainter<Polygon> {
  private final ReadableColor _maskColor;

  private final ReadableColor _unmaskColor;

  @Inject
  public PolygonMaskGLPainter(final GLUtils glUtils, @Named(PaintersModule.NAME_MASK_COLOR) final ReadableColor maskColor, @Named(PaintersModule.NAME_UNMASK_COLOR) final ReadableColor unmaskColor) {
    super(glUtils, Optional.empty());
    _maskColor = maskColor;
    _unmaskColor = unmaskColor;
  }

  @Override
  protected ReadableColor getFillColor(final Polygon polygon) {
    switch (polygon.getRenderMode()) {
      case MASK:
        return _maskColor;
      case UNMASK:
        return _unmaskColor;
      default:
        throw new RuntimeException(String.format("Unknown render mode: %s", polygon.getRenderMode()));
    }
  }
}
