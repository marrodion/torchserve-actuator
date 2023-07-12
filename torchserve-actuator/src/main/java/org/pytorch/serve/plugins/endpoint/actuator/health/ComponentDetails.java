package org.pytorch.serve.plugins.endpoint.actuator.health;

import org.pytorch.serve.servingsdk.Model;

public final class ComponentDetails extends HealthResponse {
  private final ModelDetails details;

  private ComponentDetails(HealthStatus status, ModelDetails details) {
    this.status = status;
    this.details = details;
  }

  public static ComponentDetails fromModel(Model model) {
    var modelDetails = new ModelDetails(model);
    var status =
        modelDetails.getWorkers().stream().anyMatch(WorkerDetails::isRunning)
            ? HealthStatus.UP
            : HealthStatus.DOWN;
    return new ComponentDetails(status, modelDetails);
  }

  public ModelDetails getDetails() {
    return details;
  }
}
