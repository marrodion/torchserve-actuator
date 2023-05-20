package org.pytorch.serve.plugins.endpoint.actuator.health;

public enum HealthStatus {

    UP("UP", 200),
    DOWN("DOWN", 503),
    UNKNOWN("UNKNOWN", 200);

    public final String status;
    public final int statusCode;

    private HealthStatus(String status, int statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }
}
