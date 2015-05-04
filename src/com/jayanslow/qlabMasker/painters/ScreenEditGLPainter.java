package com.jayanslow.qlabMasker.painters;

import javax.inject.Inject;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.ReadableColor;

import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.Screen;

class ScreenEditGLPainter extends AbstractScreenGLPainter {

  private static final ReadableColor CLEAR_COLOR = ReadableColor.BLACK;

  private static final ReadableColor BORDER_COLOR = ReadableColor.YELLOW;

  @Inject
  public ScreenEditGLPainter(final GLUtils glUtils, @TargetPainterMode(PainterMode.EDIT) final GLPainter<Polygon> polygonFillPainter) {
    super(glUtils, polygonFillPainter, CLEAR_COLOR);
  }

  @Override
  protected void paintSelf(final Screen screen) {
    super.paintSelf(screen);
    paintScreenStroke(screen);
  }

  private void paintScreenStroke(final Screen screen) {
    glUtils().applyColor(BORDER_COLOR);
    GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
    GL11.glLineWidth(2.0f);
    GL11.glRectf(0.5f, 0.5f, screen.getWidth() - 0.5f, screen.getHeight() - 0.5f);
    GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
  }
}
