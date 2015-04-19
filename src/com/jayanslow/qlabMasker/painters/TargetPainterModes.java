package com.jayanslow.qlabMasker.painters;

import java.lang.annotation.Annotation;

public class TargetPainterModes {
  private static class TargetPainterModeImpl implements TargetPainterMode {

    private final PainterMode _mode;

    public TargetPainterModeImpl(final PainterMode mode) {
      _mode = mode;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
      return TargetPainterMode.class;
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof TargetPainterMode)) {
        return false;
      }
      final TargetPainterMode other = (TargetPainterMode) obj;
      return value() == other.value();
    }

    @Override
    public int hashCode() {
      // This is specified in java.lang.Annotation.
      return 127 * "value".hashCode() ^ value().hashCode();
    }

    @Override
    public PainterMode value() {
      return _mode;
    }
  }

  public static TargetPainterMode target(final PainterMode mode) {
    return new TargetPainterModeImpl(mode);
  }
}
