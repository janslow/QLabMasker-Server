package com.jayanslow.qlabMasker.models;

public class Point {
  private final int _x;

  private final int _y;

  public Point(final int x, final int y) {
    _x = x;
    _y = y;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Point other = (Point) obj;
    return _x == other._x && _y == other._y;
  }

  public int getX() {
    return _x;
  }

  public int getY() {
    return _y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _x;
    result = prime * result + _y;
    return result;
  }

  @Override
  public String toString() {
    return String.format("Point(%d, %d)", _x, _y);
  }

}
