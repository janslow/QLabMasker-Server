package com.jayanslow.qlabMasker.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class TestPolygon {
  @Test
  public void testConstructor() {
    final String name = "foo";
    final RenderMode renderMode = RenderMode.MASK;
    final List<Point> points = Lists.newArrayList(new Point(1, 2));

    final Polygon polygon = new Polygon(name, renderMode, points);

    assertThat(polygon.getName(), is(name));
    assertThat(polygon.getRenderMode(), is(renderMode));
    assertThat(polygon.getPoints(), is(points));
  }

  @Test
  public void testConstructorWithDefaultPoints() {
    final String name = "foo";
    final RenderMode renderMode = RenderMode.MASK;

    final Polygon polygon = new Polygon(name, renderMode);

    assertThat(polygon.getName(), is(name));
    assertThat(polygon.getRenderMode(), is(renderMode));
    assertThat(polygon.getPoints(), is(Lists.newArrayList()));
  }

  @Test
  public void testEquality() {
    final Polygon polygon1 = new Polygon("foo", RenderMode.MASK, ImmutableList.of(new Point(13, 14)));
    final Polygon polygon2 = new Polygon("foo", RenderMode.MASK, Lists.newArrayList(new Point(13, 14)));

    final Polygon polygon3 = new Polygon("bar", RenderMode.MASK, ImmutableList.of(new Point(13, 14)));
    final Polygon polygon4 = new Polygon("foo", RenderMode.UNMASK, ImmutableList.of(new Point(13, 14)));
    final Polygon polygon5 = new Polygon("foo", RenderMode.MASK, ImmutableList.of(new Point(13, 15)));
    final Polygon polygon6 = new Polygon("foo", RenderMode.MASK, ImmutableList.of(new Point(13, 15), new Point(15, 13)));
    final Polygon polygon7 = new Polygon("foo", RenderMode.MASK);

    assertThat(polygon1, is(polygon2));
    assertThat(polygon1.hashCode(), is(polygon2.hashCode()));

    assertThat(polygon1, is(not(polygon3)));

    // Equality only cares about name.
    assertThat(polygon1, is(polygon4));
    assertThat(polygon1.hashCode(), is(polygon4.hashCode()));
    assertThat(polygon1, is(polygon5));
    assertThat(polygon1.hashCode(), is(polygon5.hashCode()));
    assertThat(polygon1, is(polygon6));
    assertThat(polygon1.hashCode(), is(polygon6.hashCode()));
    assertThat(polygon1, is(polygon7));
    assertThat(polygon1.hashCode(), is(polygon7.hashCode()));
  }
}
