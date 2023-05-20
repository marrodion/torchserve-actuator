package org.pytorch.serve.plugins.endpoint.actuator.health;

import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.Model;
import org.pytorch.serve.servingsdk.Worker;

import java.util.Map;
import java.util.stream.Collectors;

public class Health {

    public HealthResponse getHealthResponse(Context ctx) {
        var modelDetails = ctx.getModels().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> new ModelDetails(entry.getValue())));
        return new HealthResponse(getHealth(modelDetails), modelDetails);
    }

    private HealthStatus getHealth(Map<String, ModelDetails> modelsDetails) {
        var anyLoaded = modelsDetails.values().stream().anyMatch(model -> model.getWorkers().stream().anyMatch(WorkerDetails::isRunning));
        return anyLoaded ? HealthStatus.UP : HealthStatus.DOWN;
    }
}
