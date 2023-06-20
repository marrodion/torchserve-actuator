package org.pytorch.serve.plugins.endpoint.actuator.health;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAVAILABLE;

public enum HealthStatus {
  UP("UP", HTTP_OK),
  DOWN("DOWN", HTTP_UNAVAILABLE),
  UNKNOWN("UNKNOWN", HTTP_OK);

  public final String status;
  public final int statusCode;

  HealthStatus(String status, int statusCode) {
    this.status = status;
    this.statusCode = statusCode;
  }
}
