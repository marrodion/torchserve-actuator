package org.pytorch.serve.plugins.endpoint.actuator;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class PathSegmentsTest {
  @Test
  public void testMainPath() {
    PathSegments segments = PathSegments.fromPath("/actuator");
    assertEquals(segments.getPrefix(), "actuator");
    assertEquals(segments.getEndpointName(), "");
    assertEquals(segments.getRemainder(), "");
  }

  @Test
  public void testEndpointNoParams() {
    PathSegments segments = PathSegments.fromPath("/actuator/health");
    assertEquals(segments.getPrefix(), "actuator");
    assertEquals(segments.getEndpointName(), "health");
    assertEquals(segments.getRemainder(), "");
  }

  @Test
  public void testEndpointWithParam() {
    PathSegments segments = PathSegments.fromPath("/actuator/health/model");
    assertEquals(segments.getPrefix(), "actuator");
    assertEquals(segments.getEndpointName(), "health");
    assertEquals(segments.getRemainder(), "model");
  }

  @Test
  public void testMainWithMultipleParams() {
    PathSegments segments = PathSegments.fromPath("/actuator/health/model/worker");
    assertEquals(segments.getPrefix(), "actuator");
    assertEquals(segments.getEndpointName(), "health");
    assertEquals(segments.getRemainder(), "model/worker");
  }

  @Test
  public void testEmptyPath() {
    PathSegments segments = PathSegments.fromPath("/");
    assertEquals(segments.getPrefix(), "");
    assertEquals(segments.getEndpointName(), "");
    assertEquals(segments.getRemainder(), "");
  }
}
