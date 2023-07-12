package org.pytorch.serve.plugins.endpoint.actuator;

import java.nio.file.Path;

public class PathSegments {
  private static final String SEPARATOR = "/";
  private final String prefix;
  private final String endpointName;
  private final String remainder;


  public PathSegments(String path) {
    String[] segments = path.split(SEPARATOR, 3);
    prefix = segments[0];
    if (segments.length  > 1) {
      endpointName = segments[1];
    } else {
      endpointName = "";
    }
    if (segments.length > 2) {
      remainder = segments[2];
    } else {
      remainder = "";
    }
  }

  public String getPrefix() {
    return prefix;
  }

  public String getEndpointName() {
    return endpointName;
  }

  public String getRemainder() {
    return remainder;
  }
}
