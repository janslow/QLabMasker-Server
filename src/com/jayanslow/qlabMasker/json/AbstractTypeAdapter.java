package com.jayanslow.qlabMasker.json;

import java.util.Optional;
import java.util.logging.Logger;

import com.google.gson.TypeAdapter;

abstract class AbstractTypeAdapter<T> extends TypeAdapter<T> {

  private final Logger _logger;

  public AbstractTypeAdapter(final Logger logger) {
    _logger = logger;
  }

  protected void assertPropertyIsPresent(final String property, final Optional<?> value) throws MissingJsonPropertyException {
    if (!value.isPresent()) {
      throw new MissingJsonPropertyException(property);
    }
  }

  protected Logger getLogger() {
    return _logger;
  }

  protected void logUnknownProperty(final String name) {
    _logger.warning(String.format("Unknown JSON property: %s", name));
  }
}
