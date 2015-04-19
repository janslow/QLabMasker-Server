package com.jayanslow.qlabMasker.painters;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.lang.annotation.Annotation;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class TestTargetPainterModes {

  @TargetPainterMode(PainterMode.EDIT)
  public TargetPainterMode getAnnotation() {
    try {
      final Annotation[] annotations = TestTargetPainterModes.class.getMethod("getAnnotation").getAnnotations();
      for (final Annotation annotation : annotations) {
        if (annotation instanceof TargetPainterMode) {
          return (TargetPainterMode) annotation;
        }
      }
      throw new RuntimeException("Couldn't find annotation.");
    }
    catch (NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testEquality() {
    final TargetPainterMode target1 = TargetPainterModes.target(PainterMode.EDIT);
    final TargetPainterMode target2 = TargetPainterModes.target(PainterMode.EDIT);
    final TargetPainterMode target3 = TargetPainterModes.target(PainterMode.MASK);
    final TargetPainterMode target4 = getAnnotation();

    assertThat(target1, is(target2));
    assertThat(target1.hashCode(), is(target2.hashCode()));

    assertThat(target1, is(not(target3)));

    assertThat(target1, is(target4));
    assertThat(target1.hashCode(), is(target4.hashCode()));

    assertThat(target3, is(not(target4)));
  }

  @Test
  public void testFactory() {
    final PainterMode painterMode = PainterMode.MASK;

    final TargetPainterMode target = TargetPainterModes.target(painterMode);

    assertThat(target.annotationType(), CoreMatchers.<Class<?>> is(TargetPainterMode.class));
    assertThat(target.value(), is(painterMode));
  }
}
