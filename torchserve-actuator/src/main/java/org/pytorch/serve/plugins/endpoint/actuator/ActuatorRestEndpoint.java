package org.pytorch.serve.plugins.endpoint.actuator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.pytorch.serve.plugins.endpoint.actuator.health.HealthEndpoint;
import org.pytorch.serve.plugins.endpoint.actuator.info.InfoEndpoint;
import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.ModelServerEndpoint;
import org.pytorch.serve.servingsdk.annotations.Endpoint;
import org.pytorch.serve.servingsdk.annotations.helpers.EndpointTypes;
import org.pytorch.serve.servingsdk.http.Request;
import org.pytorch.serve.servingsdk.http.Response;

@Endpoint(
    urlPattern = "actuator",
    endpointType = EndpointTypes.INFERENCE,
    description = "Actuator health status")
public class ActuatorRestEndpoint extends ModelServerEndpoint {
  // TODO: Refactor to a proper routing based on URI path parsing
  private static final Map<String, ModelServerEndpoint> endpoints =
      Map.of("health", new HealthEndpoint(), "info", new InfoEndpoint());

  @Override
  public void doGet(Request req, Response rsp, Context ctx) throws IOException {
    switch (req.getRequestURI()) {
      case "/actuator" -> doGetActuator(rsp);
      case "/actuator/health" -> getEndpointByName("health").doGet(req, rsp, ctx);
      case "/actuator/info" -> getEndpointByName("info").doGet(req, rsp, ctx);
      default -> {
        rsp.setStatus(404);
        rsp.getOutputStream()
            .write(
                String.format("%s Not found", req.getRequestURI())
                    .getBytes(StandardCharsets.UTF_8));
      }
    }
  }

  private void doGetActuator(Response rsp) throws IOException {
    rsp.setStatus(200);
    rsp.getOutputStream().write("Actuator".getBytes(StandardCharsets.UTF_8));
  }

  private ModelServerEndpoint getEndpointByName(String name) {
    return endpoints.get(name);
  }
}
