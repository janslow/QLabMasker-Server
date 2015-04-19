package com.jayanslow.qlabMasker.models;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class Screen {
  private final int _width;

  private final int _height;

  private final List<Polygon> _polygons;

  public Screen(final int width, final int height) {
    this(width, height, ImmutableList.of());
  }

  public Screen(final int width, final int height, final List<Polygon> polygons) {
    _width = width;
    _height = height;
    _polygons = polygons;
  }

  public int getHeight() {
    return _height;
  }

  public List<Polygon> getPolygons() {
    return _polygons;
  }

  public int getWidth() {
    return _width;
  }

  @Override
  public String toString() {
    return String.format("Screen(%dx%d)", _width, _height);
  }

}
