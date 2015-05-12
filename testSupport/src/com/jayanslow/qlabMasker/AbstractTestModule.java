package com.jayanslow.qlabMasker;

import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;

@RunWith(Parameterized.class)
public abstract class AbstractTestModule {
  protected static interface GuiceTestConfig<T> {
    Key<T> getKey();

    Matcher<? super T> getMatcher();
  }

  protected static class SimpleGuiceTestConfig<T> implements GuiceTestConfig<T> {
    private final Key<T> _key;

    private final Matcher<? super T> _matcher;

    private String _name;

    public SimpleGuiceTestConfig(final Key<T> key, final Class<? extends T> expectedClazz) {
      this(expectedClazz.getSimpleName(), key, CoreMatchers.instanceOf(expectedClazz));
    }

    public SimpleGuiceTestConfig(final String name, final Key<T> key, final Matcher<? super T> matcher) {
      _name = name;
      _key = key;
      _matcher = matcher;
    }

    @Override
    public Key<T> getKey() {
      return _key;
    }

    @Override
    public Matcher<? super T> getMatcher() {
      return _matcher;
    }

    @Override
    public String toString() {
      return _name;
    }
  }

  protected static <T> GuiceTestConfig<T> createSimpleConfig(final Class<T> clazz) {
    return createSimpleConfig(clazz, clazz);
  }

  protected static <T> GuiceTestConfig<T> createSimpleConfig(final Class<T> interfaceClazz, final Class<? extends T> implClazz) {
    return new SimpleGuiceTestConfig<>(Key.<T> get(interfaceClazz), implClazz);
  }

  protected static Collection<Object[]> wrapParameters(final Collection<Object> configs) {
    return configs.stream().map(x -> new Object[] { x }).collect(Collectors.toSet());
  }

  private final GuiceTestConfig<Object> _config;

  public AbstractTestModule(final GuiceTestConfig<Object> config) {
    _config = config;
  }

  @Test
  public void testInject() {
    final Object instance = createInjector().getInstance(_config.getKey());
    assertThat(instance, CoreMatchers.notNullValue());
    assertThat(instance, _config.getMatcher());
  }

  protected Injector createInjector() {
    return Guice.createInjector(createModule());
  }

  protected abstract Module createModule();
}
