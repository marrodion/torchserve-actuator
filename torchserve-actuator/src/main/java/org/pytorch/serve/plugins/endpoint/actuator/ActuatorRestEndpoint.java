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
  private static final String PREFIX = "actuator";
  private static final Map<String, ModelServerEndpoint> endpoints =
      Map.of("health", new HealthEndpoint(), "info", new InfoEndpoint());

  @Override
  public void doGet(Request req, Response rsp, Context ctx) throws IOException {
    PathSegments segments = PathSegments.fromPath(req.getRequestURI());
    if (PREFIX.equals(segments.getPrefix())) {
      if ("".equals(segments.getEndpointName())) {
        doGetActuator(rsp);
      } else if (endpoints.containsKey(segments.getEndpointName())) {
        endpoints.get(segments.getEndpointName()).doGet(req, rsp, ctx);
      } else {
        notFoundResponse(req, rsp);
      }
    } else {
      notFoundResponse(req, rsp);
    }
  }

  private void doGetActuator(Response rsp) throws IOException {
    rsp.setStatus(200);
    rsp.getOutputStream().write("Actuator".getBytes(StandardCharsets.UTF_8));
  }

  private void notFoundResponse(Request req, Response rsp) throws IOException {
    rsp.setStatus(404);
    rsp.getOutputStream()
        .write(String.format("%s Not found", req.getRequestURI()).getBytes(StandardCharsets.UTF_8));
  }
}
