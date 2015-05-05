package com.jayanslow.qlabMasker.json;

import static com.jayanslow.qlabMasker.json.JsonMatcher.isJson;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class TestIteratorTypeAdaptorImpl {
  private IteratorTypeAdaptorImpl _toTest;

  @Before
  public void setUp() {
    _toTest = new IteratorTypeAdaptorImpl();

  }

  @Test
  public void testRead() throws IOException {
    final String json = "[1,2,3]";
    final JsonReader reader = new JsonReader(new StringReader(json));

    @SuppressWarnings("unchecked")
    final TypeAdapter<Object> typeAdapter = mock(TypeAdapter.class);
    when(typeAdapter.read(reader)).thenAnswer(invocation -> invocation.getArgumentAt(0, JsonReader.class).nextInt());

    final List<Object> values = _toTest.read(typeAdapter, reader);
    assertThat(values, is(ImmutableList.of(1, 2, 3)));
  }

  @Test
  public void testWrite() throws IOException {
    final List<Integer> values = ImmutableList.of(3, 2, 1);

    final StringWriter stringWriter = new StringWriter();
    final JsonWriter writer = new JsonWriter(stringWriter);

    @SuppressWarnings("unchecked")
    final TypeAdapter<Integer> typeAdapter = mock(TypeAdapter.class);
    Mockito.doAnswer(invocation -> invocation.getArgumentAt(0, JsonWriter.class).value(invocation.getArgumentAt(1, Integer.class).intValue())).when(typeAdapter).write(refEq(writer), any());

    _toTest.write(typeAdapter, writer, values);
    final JsonElement json = new Gson().fromJson(stringWriter.toString(), JsonElement.class);
    assertThat(json, isJson("[3,2,1]"));
  }
}
