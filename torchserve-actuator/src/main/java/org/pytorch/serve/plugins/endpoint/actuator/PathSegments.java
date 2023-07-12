package org.pytorch.serve.plugins.endpoint.actuator;

public class PathSegments {
  private static final String SEPARATOR = "/";
  private final String prefix;
  private final String endpointName;
  private final String remainder;

  private PathSegments(String prefix, String endpointName, String remainder) {
    this.prefix = prefix;
    this.endpointName = endpointName;
    this.remainder = remainder;
  }

  public static PathSegments fromPath(String path) {
    String[] segments = path.split(SEPARATOR, 4);
    return switch (segments.length) {
      case 2 -> new PathSegments(segments[1], "", "");
      case 3 -> new PathSegments(segments[1], segments[2], "");
      case 4 -> new PathSegments(segments[1], segments[2], segments[3]);
      default -> new PathSegments("", "", "");
    };
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
