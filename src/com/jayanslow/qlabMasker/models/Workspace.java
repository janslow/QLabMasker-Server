package com.jayanslow.qlabMasker.models;

import java.util.Optional;

/**
 * Session global object which contains the session state.
 *
 * @copyright
 * @author janslow
 */
public class Workspace {
  private Optional<Point> _selectedPoint;

  private Screen _screen;

  private Optional<Polygon> _selectedPolygon;

  public Workspace(final Screen screen) {
    this(screen, Optional.empty(), Optional.empty());
  }

  public Workspace(final Screen screen, final Optional<Polygon> selectedPolygon, final Optional<Point> selectedPoint) {
    _screen = screen;
    _selectedPolygon = selectedPolygon;
    _selectedPoint = selectedPoint;
  }

  public Screen getScreen() {
    return _screen;
  }

  public Optional<Point> getSelectedPoint() {
    return _selectedPoint;
  }

  public Optional<Polygon> getSelectedPolygon() {
    return _selectedPolygon;
  }

  public void setScreen(final Screen screen) {
    _screen = screen;
  }

  public void setSelectedPoint(final Optional<Point> selectedPoint) {
    _selectedPoint = selectedPoint;
  }

  public void setSelectedPolygon(final Optional<Polygon> selectedPolygon) {
    _selectedPolygon = selectedPolygon;
  }
}
