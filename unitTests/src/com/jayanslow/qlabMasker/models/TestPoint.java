package com.jayanslow.qlabMasker.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestPoint {

  @Test
  public void testConstructor() {
    final int x = 123;
    final int y = 456;
    final Point point = new Point(x, y);

    assertThat(point.getX(), is(x));
    assertThat(point.getY(), is(y));
  }

  @Test
  public void testEquality() {
    final Point point1 = new Point(123, 456);
    final Point point2 = new Point(123, 456);
    final Point point3 = new Point(1234, 456);

    assertThat(point1, is(point2));
    assertThat(point1.hashCode(), is(point2.hashCode()));

    assertThat(point1, is(not(point3)));
  }
}
