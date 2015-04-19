package com.jayanslow.qlabMasker.painters;

import java.util.List;
import java.util.Optional;

public abstract class AbstractGLPainterWithChildren<T, S> extends AbstractGLPainter<T> {

  private final Optional<GLPainter<S>> _childPainter;

  public AbstractGLPainterWithChildren(final GLUtils glUtils, final Optional<GLPainter<S>> childPainter) {
    super(glUtils);
    _childPainter = childPainter;
  }

  @Override
  public void paint(final T t) {
    paintSelf(t);

    if (_childPainter.isPresent()) {
      for (final S child : getChildren(t)) {
        _childPainter.get().paint(child);
      }
    }
  }

  protected abstract List<S> getChildren(final T t);

  protected abstract void paintSelf(final T t);

}