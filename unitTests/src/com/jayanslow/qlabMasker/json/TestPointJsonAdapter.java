package com.jayanslow.qlabMasker.json;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.logging.Logger;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.TypeAdapter;
import com.jayanslow.qlabMasker.models.Point;

public class TestPointJsonAdapter extends AbstractTestTypeAdapter<Point> {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testAdapter() {
    abstractTestAdapter(new Point(12, 34), "{\"x\":12, \"y\":34}");
  }

  @Test
  public void testDeserializeExtraProperty() {
    assertThat(deserialize("{\"x\":13, \"y\":57, \"z\":56}"), is(new Point(13, 57)));
  }

  @Test
  public void testMissingXProperty() throws IOException {
    thrown.expect(new CustomTypeSafeMatcher<MissingJsonPropertyException>("missing x") {
      @Override
      protected boolean matchesSafely(final MissingJsonPropertyException item) {
        return item.getName().equals("x");
      }
    });

    createTypeAdaptor().fromJson("{\"y\":34}");
  }

  @Test
  public void testMissingYProperty() throws IOException {
    thrown.expect(new CustomTypeSafeMatcher<MissingJsonPropertyException>("missing y") {
      @Override
      protected boolean matchesSafely(final MissingJsonPropertyException item) {
        return item.getName().equals("y");
      }
    });

    createTypeAdaptor().fromJson("{\"x\":34}");
  }

  @Override
  protected TypeAdapter<Point> createTypeAdaptor() {
    return new PointTypeAdapter(mock(Logger.class));
  }
}
