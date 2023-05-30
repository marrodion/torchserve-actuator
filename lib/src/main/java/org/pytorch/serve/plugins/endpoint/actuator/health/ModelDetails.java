package org.pytorch.serve.plugins.endpoint.actuator.health;

import java.util.List;
import org.pytorch.serve.servingsdk.Model;

public class ModelDetails {
  private final String name;
  private final String uri;
  private final String handler;

  private final List<WorkerDetails> workers;

  public ModelDetails(Model model) {
    this.name = model.getModelName();
    this.uri = model.getModelUrl();
    this.handler = model.getModelHandler();
    this.workers = model.getModelWorkers().stream().map(WorkerDetails::new).toList();
  }

  public String getName() {
    return name;
  }

  public String getUri() {
    return uri;
  }

  public String getHandler() {
    return handler;
  }

  public List<WorkerDetails> getWorkers() {
    return List.copyOf(workers);
  }
}
