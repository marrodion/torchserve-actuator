package org.pytorch.serve.plugins.endpoint.actuator.health;

public abstract class HealthResponse {
  protected HealthStatus status;

  public HealthStatus getStatus() {
    return status;
  }
}
