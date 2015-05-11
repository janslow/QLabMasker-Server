package com.jayanslow.qlabMasker.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

class IteratorTypeAdaptorImpl implements IteratorTypeAdapter {

  @Override
  public <T> List<T> read(final TypeAdapter<T> typeAdapter, final JsonReader in) throws IOException {
    in.beginArray();
    final List<T> values = new LinkedList<>();
    while (in.peek() != JsonToken.END_ARRAY) {
      values.add(typeAdapter.read(in));
    }
    in.endArray();
    return values;
  }

  @Override
  public <T> void write(final TypeAdapter<? super T> typeAdapter, final JsonWriter out, final Iterable<T> values) throws IOException {
    write(typeAdapter, out, values.iterator());
  }

  @Override
  public <T> void write(final TypeAdapter<? super T> typeAdapter, final JsonWriter out, final Iterator<T> values) throws IOException {
    out.beginArray();
    while (values.hasNext()) {
      typeAdapter.write(out, values.next());
    }
    out.endArray();
  }

}
