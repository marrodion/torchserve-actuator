package org.pytorch.serve.plugins.endpoint.actuator.health;

import org.pytorch.serve.servingsdk.Model;

import java.util.Map;

public class HealthResponse {
    private final HealthStatus status;
    private final Map<String, ModelDetails> details;

    public HealthResponse(HealthStatus status, Map<String, ModelDetails> details) {
        this.status = status;
        this.details = details;
    }

    public HealthStatus getStatus() {
        return status;
    }

    public Map<String, ModelDetails> getDetails() {
        return details;
    }
}
