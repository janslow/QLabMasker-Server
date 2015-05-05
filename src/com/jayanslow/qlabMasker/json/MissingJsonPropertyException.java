package com.jayanslow.qlabMasker.json;

import java.io.IOException;

class MissingJsonPropertyException extends IOException {

  private static final long serialVersionUID = -1762925763784644706L;

  private final String _name;

  public MissingJsonPropertyException(final String name) {
    super(String.format("Missing JSON property: %s", name));
    _name = name;
  }

  public String getName() {
    return _name;
  }
}
