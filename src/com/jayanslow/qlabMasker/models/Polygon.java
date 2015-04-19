package com.jayanslow.qlabMasker.models;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class Polygon {
  private final String _name;

  private final RenderMode _renderMode;

  private final List<Point> _points;

  public Polygon(final String name, final RenderMode renderMode) {
    this(name, renderMode, ImmutableList.of());
  }

  public Polygon(final String name, final RenderMode renderMode, final List<Point> points) {
    _name = name;
    _renderMode = renderMode;
    _points = points;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Polygon other = (Polygon) obj;
    return _name.equals(other._name);
  }

  public String getName() {
    return _name;
  }

  public List<Point> getPoints() {
    return _points;
  }

  public RenderMode getRenderMode() {
    return _renderMode;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _name.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return String.format("Polygon(%s)", _name);
  }
}
