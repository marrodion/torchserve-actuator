package org.pytorch.serve.plugins.endpoint.actuator.health;

import java.util.Map;

public class HealthResponse {
  private final HealthStatus status;
  private final Map<String, ComponentDetails> components;

  public HealthResponse(HealthStatus status, Map<String, ComponentDetails> components) {
    this.status = status;
    this.components = Map.copyOf(components);
  }

  public HealthStatus getStatus() {
    return status;
  }

  public Map<String, ComponentDetails> getDetails() {
    return Map.copyOf(components);
  }
}
