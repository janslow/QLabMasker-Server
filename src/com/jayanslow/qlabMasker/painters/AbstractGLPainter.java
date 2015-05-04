package com.jayanslow.qlabMasker.painters;


abstract class AbstractGLPainter<T> implements GLPainter<T> {

  private final GLUtils _glUtils;

  public AbstractGLPainter(final GLUtils glUtils) {
    _glUtils = glUtils;
  }

  protected final GLUtils glUtils() {
    return _glUtils;
  }

}