package com.jayanslow.qlabMasker;

import javax.inject.Inject;

import jsyphon.JSyphonServer;

import org.lwjgl.LWJGLException;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.jayanslow.qlabMasker.models.SampleScreens;
import com.jayanslow.qlabMasker.models.Screen;
import com.jayanslow.qlabMasker.models.Workspace;
import com.jayanslow.qlabMasker.painters.GLPainter;
import com.jayanslow.qlabMasker.painters.PainterMode;
import com.jayanslow.qlabMasker.painters.PaintersModule;
import com.jayanslow.qlabMasker.painters.TargetPainterMode;

public class SampleScreenSyphonTest extends AbstractSyphonTest {

  public static void main(final String[] args) throws LWJGLException {
    final SampleScreenSyphonTest t = Guice.createInjector(new PaintersModule(), new AbstractModule() {
      @Override
      protected void configure() {
        final Workspace workspace = new Workspace(SampleScreens.getScreen());
        bind(Workspace.class).toInstance(workspace);
      }
    }).getInstance(SampleScreenSyphonTest.class);
    t.start();
  }

  private final JSyphonServer _server;

  private final GLPainter<Screen> _screenPainter;

  private final Workspace _workspace;

  /**
   * @throws LWJGLException
   *
   */
  @Inject
  public SampleScreenSyphonTest(@TargetPainterMode(PainterMode.EDIT) final GLPainter<Screen> screenPainter, final Workspace workspace) throws LWJGLException {
    _screenPainter = screenPainter;
    _workspace = workspace;
    _server = new JSyphonServer();
  }

  @Override
  public String getTitle() {
    return "Sample Screen Syphon Test";
  }

  @Override
  protected int getHeight() {
    return _workspace.getScreen().getHeight();
  }

  @Override
  protected int getWidth() {
    return _workspace.getScreen().getWidth();
  }

  @Override
  protected void render() {
    _screenPainter.paint(_workspace.getScreen());
  }
}
