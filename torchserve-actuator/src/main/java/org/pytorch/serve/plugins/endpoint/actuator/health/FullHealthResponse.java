package org.pytorch.serve.plugins.endpoint.actuator.health;

import java.util.Map;

public class FullHealthResponse extends HealthResponse {
  private final Map<String, ComponentDetails> components;

  public FullHealthResponse(HealthStatus status, Map<String, ComponentDetails> components) {
    this.status = status;
    this.components = Map.copyOf(components);
  }

  public Map<String, ComponentDetails> getDetails() {
    return Map.copyOf(components);
  }
}
