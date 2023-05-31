package org.pytorch.serve.plugins.endpoint.actuator.health;

import java.util.Map;
import java.util.stream.Collectors;
import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.Model;

public class Health {

  public HealthResponse getHealthResponse(Context ctx) {
    var components =
        ctx.getModels().values().stream()
            .collect(Collectors.toMap(Model::getModelName, ComponentDetails::fromModel));
    return new HealthResponse(getHealth(components), components);
  }

  private HealthStatus getHealth(Map<String, ComponentDetails> components) {
    var anyLoaded =
        components.values().stream()
            .anyMatch(componentDetails -> componentDetails.getStatus() == HealthStatus.UP);
    return anyLoaded ? HealthStatus.UP : HealthStatus.DOWN;
  }
}
