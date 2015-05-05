package com.jayanslow.qlabMasker.json;

import static com.jayanslow.qlabMasker.json.JsonMatcher.isJson;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jayanslow.qlabMasker.models.Point;
import com.jayanslow.qlabMasker.models.Polygon;
import com.jayanslow.qlabMasker.models.RenderMode;

public class TestPolygonJsonAdapter extends AbstractTestTypeAdapter<Polygon> {

  private static IteratorTypeAdapter mockIteratorTypeAdapter(final TypeAdapter<Point> pointTypeAdapter, final List<Point> points) throws IOException {
    final IteratorTypeAdapter iteratorTypeAdapter = mock(IteratorTypeAdapter.class);
    when(iteratorTypeAdapter.read(Matchers.refEq(pointTypeAdapter), any())).thenAnswer(invocation -> {
      assertThat(invocation.getArgumentAt(1, JsonReader.class).nextString(), is("foobarPoints"));
      return points;
    });
    Mockito.doAnswer(invocation -> {
      invocation.getArgumentAt(1, JsonWriter.class).value("foobarPoints");
      return null;
    }).when(iteratorTypeAdapter).write(refEq(pointTypeAdapter), any(), refEq(points));
    return iteratorTypeAdapter;
  }

  private static TypeAdapter<RenderMode> mockRenderModeTypeAdapter() throws IOException {
    @SuppressWarnings("unchecked")
    final TypeAdapter<RenderMode> renderModeTypeAdapter = mock(TypeAdapter.class);
    when(renderModeTypeAdapter.read(any())).then(invocation -> {
      final JsonReader reader = invocation.getArgumentAt(0, JsonReader.class);
      assertThat(reader.nextString(), is("bar"));
      return RenderMode.MASK;
    });
    Mockito.doAnswer(invocation -> {
      invocation.getArgumentAt(0, JsonWriter.class).value("bar");
      return null;
    }).when(renderModeTypeAdapter).write(any(), eq(RenderMode.MASK));
    return renderModeTypeAdapter;
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private TypeAdapter<RenderMode> _renderModeTypeAdapter;

  private TypeAdapter<Point> _pointTypeAdapter;

  private IteratorTypeAdapter _iteratorTypeAdapter;

  private List<Point> _points;

  @SuppressWarnings("unchecked")
  @Before
  public void setUp() throws IOException {
    _renderModeTypeAdapter = mockRenderModeTypeAdapter();
    _pointTypeAdapter = mock(TypeAdapter.class);
    _points = mock(List.class);
    _iteratorTypeAdapter = mockIteratorTypeAdapter(_pointTypeAdapter, _points);
  }

  @Test
  public void testDeserialize() throws IOException {
    @SuppressWarnings("unchecked")
    final Polygon expected = new Polygon("foo", RenderMode.MASK, _points);
    final String json = "{\"name\": \"foo\", \"renderMode\":\"bar\", \"points\": \"foobarPoints\"}";
    assertThat(deserialize(json), is(expected));
  }

  @Test
  public void testDeserializeExtraProperty() throws IOException {
    assertThat(deserialize("{\"name\": \"foo\", \"renderMode\":\"bar\", \"points\": \"foobarPoints\", \"x\": 1}"), is(new Polygon("foo", RenderMode.MASK, _points)));
  }

  @Test
  public void testMissingNameProperty() throws IOException {
    thrown.expect(new CustomTypeSafeMatcher<MissingJsonPropertyException>("missing name") {
      @Override
      protected boolean matchesSafely(final MissingJsonPropertyException item) {
        return item.getName().equals("name");
      }
    });

    createTypeAdaptor().fromJson("{\"renderMode\":\"bar\", \"points\": \"foobarPoints\"}");
  }

  @Test
  public void testMissingPointsProperty() throws IOException {
    thrown.expect(new CustomTypeSafeMatcher<MissingJsonPropertyException>("missing points") {
      @Override
      protected boolean matchesSafely(final MissingJsonPropertyException item) {
        return item.getName().equals("points");
      }
    });

    createTypeAdaptor().fromJson("{\"name\": \"foo\", \"renderMode\":\"bar\"}");
  }

  @Test
  public void testMissingRenderModeProperty() throws IOException {
    thrown.expect(new CustomTypeSafeMatcher<MissingJsonPropertyException>("missing renderMode") {
      @Override
      protected boolean matchesSafely(final MissingJsonPropertyException item) {
        return item.getName().equals("renderMode");
      }
    });

    createTypeAdaptor().fromJson("{\"name\": \"foo\", \"points\": \"foobarPoints\"}");
  }

  @Test
  public void testSerialize() throws IOException {
    @SuppressWarnings("unchecked")
    final List<Point> points = mock(List.class);

    Mockito.doAnswer(invocation -> {
      invocation.getArgumentAt(0, JsonWriter.class).value("bar");
      return null;
    }).when(_renderModeTypeAdapter).write(any(), eq(RenderMode.MASK));
    Mockito.doAnswer(invocation -> {
      invocation.getArgumentAt(1, JsonWriter.class).value("foobarPoints");
      return null;
    }).when(_iteratorTypeAdapter).write(refEq(_pointTypeAdapter), any(), refEq(points));

    final Polygon object = new Polygon("foo", RenderMode.MASK, points);
    final String expected = "{\"name\": \"foo\", \"renderMode\":\"bar\", \"points\": \"foobarPoints\"}";
    assertThat(serialize(object), isJson(expected));
  }

  @Override
  protected TypeAdapter<Polygon> createTypeAdaptor() {
    return new PolygonTypeAdapter(mock(Logger.class), _renderModeTypeAdapter, _pointTypeAdapter, _iteratorTypeAdapter);
  }
}
