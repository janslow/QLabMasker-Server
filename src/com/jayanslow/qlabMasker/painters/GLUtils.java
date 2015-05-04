package com.jayanslow.qlabMasker.painters;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.ReadableColor;

class GLUtils {

  public void applyClearColor(final ReadableColor color) {
    final float red = color.getRed() / 255.0f;
    final float green = color.getGreen() / 255.0f;
    final float blue = color.getBlue() / 255.0f;
    final float alpha = color.getAlpha() / 255.0f;
    GL11.glClearColor(red, green, blue, alpha);
  }

  public void applyColor(final ReadableColor color) {
    final float red = color.getRed() / 255.0f;
    final float green = color.getGreen() / 255.0f;
    final float blue = color.getBlue() / 255.0f;
    final float alpha = color.getAlpha() / 255.0f;
    GL11.glColor4f(red, green, blue, alpha);
  }
}
