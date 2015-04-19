package com.jayanslow.qlabMasker.models;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class SampleScreens {
  public static Polygon getMaskPolygon1() {
    final List<Point> points = Lists.newArrayList(new Point(200, 200), new Point(400, 340), new Point(400, 500), new Point(150, 450));
    return new Polygon("foo", RenderMode.MASK, points);
  }

  public static Polygon getMaskPolygon2() {
    final List<Point> points = Lists.newArrayList(new Point(520, 420), new Point(520, 200), new Point(840, 200), new Point(840, 420));
    return new Polygon("bar", RenderMode.MASK, points);
  }

  public static Screen getScreen() {
    return new Screen(800, 600, ImmutableList.of(getMaskPolygon1(), getMaskPolygon2(), getUnmaskPolygon()));
  }

  public static Polygon getUnmaskPolygon() {
    final List<Point> points = Lists.newArrayList(new Point(320, 460), new Point(320, 380), new Point(600, 340), new Point(600, 420));
    return new Polygon("unmask", RenderMode.UNMASK, points);
  }

  private SampleScreens() {
  }
}
