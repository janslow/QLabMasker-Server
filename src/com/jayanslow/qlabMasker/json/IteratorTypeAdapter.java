package com.jayanslow.qlabMasker.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

interface IteratorTypeAdapter {
  <T> List<T> read(TypeAdapter<T> typeAdapter, JsonReader in) throws IOException;

  <T> void write(TypeAdapter<? super T> typeAdapter, JsonWriter out, Iterable<T> values) throws IOException;

  <T> void write(TypeAdapter<? super T> typeAdapter, JsonWriter out, Iterator<T> values) throws IOException;
}
