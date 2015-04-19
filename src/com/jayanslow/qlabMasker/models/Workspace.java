package com.jayanslow.qlabMasker.models;

import java.util.Optional;

/**
 * Session global object which contains the session state.
 *
 * @copyright
 * @author janslow
 */
public class Workspace {
  private Optional<Point> _selectedPoint = Optional.empty();

  private Screen _screen;

  private Optional<Polygon> _selectedPolygon = Optional.empty();

  public Workspace(final Screen screen) {
    _screen = screen;
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
