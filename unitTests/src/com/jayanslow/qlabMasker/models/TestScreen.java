package com.jayanslow.qlabMasker.models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestScreen {

  @Test
  public void testConstructor() {
    final int width = 123;
    final int height = 456;
    final Screen screen = new Screen(width, height);

    assertThat(screen.getWidth(), is(width));
    assertThat(screen.getHeight(), is(height));
  }
}
