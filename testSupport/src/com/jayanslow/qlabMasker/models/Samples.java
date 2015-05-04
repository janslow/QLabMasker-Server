package com.jayanslow.qlabMasker.models;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class Samples {
  public static Polygon getMaskPolygon1() {
    final List<Point> points = Lists.newArrayList(new Point(200, 200), new Point(400, 340), new Point(400, 500), new Point(150, 450));
    return new Polygon("foo", RenderMode.MASK, points);
  }

  public static Polygon getMaskPolygon2() {
    final List<Point> points = Lists.newArrayList(new Point(520, 420), new Point(520, 200), new Point(840, 200), new Point(840, 420));
    return new Polygon("bar", RenderMode.MASK, points);
  }

  public static Screen getScreen() {
    return new Screen(800, 600, ImmutableList.of(getMaskPolygon1(), getMaskPolygon2(), getUnmaskPolygon1(), getUnmaskPolygon2()));
  }

  public static Polygon getUnmaskPolygon1() {
    final List<Point> points = Lists.newArrayList(new Point(320, 460), new Point(320, 380), new Point(600, 340), new Point(600, 420));
    return new Polygon("unmask", RenderMode.UNMASK, points);
  }

  public static Polygon getUnmaskPolygon2() {
    final List<Point> points = Lists.newArrayList(new Point(50, 50), new Point(300, 50), new Point(250, 300), new Point(100, 300));
    return new Polygon("unmask2", RenderMode.UNMASK, points);
  }

  public static Workspace getWorkspace() {
    final Polygon unmaskPolygon1 = getUnmaskPolygon1();
    return new Workspace(getScreen(), Optional.of(unmaskPolygon1), Optional.of(unmaskPolygon1.getPoints().get(0)));
  }

  private Samples() {
  }
}
