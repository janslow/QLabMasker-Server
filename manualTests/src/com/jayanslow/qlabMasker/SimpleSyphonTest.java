package com.jayanslow.qlabMasker;

import jsyphon.JSyphonServer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

/**
 * @author Skye Book
 *
 */
public class SimpleSyphonTest extends AbstractSyphonTest {

  /**
   * @param args
   * @throws LWJGLException
   */
  public static void main(final String[] args) throws LWJGLException {
    final SimpleSyphonTest t = new SimpleSyphonTest();
    t.start();
  }

  private final JSyphonServer _server;

  /**
   * @throws LWJGLException
   *
   */
  public SimpleSyphonTest() throws LWJGLException {
    _server = new JSyphonServer();
  }

  @Override
  public String getTitle() {
    return "Simple Syphon Test";
  }

  @Override
  protected int getHeight() {
    return 600;
  }

  @Override
  protected int getWidth() {
    return 800;
  }

  @Override
  protected void render() {
    GL11.glBegin(GL11.GL_POLYGON);// begin drawing of polygon
    GL11.glVertex3f(200f, 400f, 0.0f);// first vertex
    GL11.glVertex3f(600f, 400f, 0.0f);// second vertex
    GL11.glVertex3f(800f, 300f, 0.0f);// third vertex
    GL11.glVertex3f(600f, 200.0f, 0.0f);// fourth vertex
    GL11.glVertex3f(200f, 200.0f, 0.0f);// fifth vertex
    GL11.glVertex3f(0f, 300.0f, 0.0f);// sixth vertex
    GL11.glEnd();// end drawing of polygon
  }
}
