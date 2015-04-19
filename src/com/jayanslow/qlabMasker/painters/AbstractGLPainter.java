package com.jayanslow.qlabMasker.painters;

public abstract class AbstractGLPainter<T> implements GLPainter<T> {

  protected final GLUtils _glUtils;

  public AbstractGLPainter(final GLUtils glUtils) {
    _glUtils = glUtils;
  }

  protected final GLUtils glUtils() {
    return _glUtils;
  }

}