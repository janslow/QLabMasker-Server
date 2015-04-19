package com.jayanslow.qlabMasker;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import jsyphon.JSyphonServer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public abstract class AbstractSyphonTest {

  private final JSyphonServer _server;

  public AbstractSyphonTest() throws LWJGLException {
    _server = new JSyphonServer();
  }

  public abstract String getTitle();

  public void start() {
    try {
      Display.setDisplayMode(new DisplayMode(getWidth(), getHeight()));
      Display.create();
      Display.setTitle(getTitle());
    }
    catch (final LWJGLException e) {
      e.printStackTrace();
      System.exit(0);
    }

    _server.initWithName("Server Test");

    final IntBuffer intBuff = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();

    while (!Display.isCloseRequested()) {
      final int width = getWidth();
      final int height = getHeight();

      GL11.glEnable(GL11.GL_LINE_SMOOTH);
      GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
      GL11.glEnable(GL11.GL_BLEND);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
      GL11.glLoadIdentity();

      GL11.glTranslatef(-1.0f, 1.0f, 0.0f);
      final float scaleX = 2 / (float) width;
      final float scaleY = 2 / (float) height;
      GL11.glScalef(scaleX, -scaleY, 1.0f);

      GL11.glColor3f(0.0f, 0.0f, 1.0f); // blue color

      render();

      publishFrame(intBuff, width, height);

      intBuff.clear();
      intBuff.rewind();

      // Always call this so LWJGL can swap buffers, etc.
      Display.update();
    }

    _server.stop();
    Display.destroy();
  }

  protected abstract int getHeight();

  protected abstract int getWidth();

  protected abstract void render();

  private void publishFrame(final IntBuffer intBuff, final int width, final int height) {
    final int target = GL11.GL_TEXTURE_2D;

    GL11.glEnable(target);

    GL11.glGenTextures(intBuff);

    GL11.glBindTexture(target, intBuff.get(0));

    final ByteBuffer pixels = null;
    GL11.glTexImage2D(target, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);

    GL11.glCopyTexSubImage2D(target, 0, 0, 0, 0, 0, width, height);

    _server.publishFrameTexture(intBuff.get(0), target, 0, 0, width, height, width, height, false);

    GL11.glDeleteTextures(intBuff.get(0));
  }
}
