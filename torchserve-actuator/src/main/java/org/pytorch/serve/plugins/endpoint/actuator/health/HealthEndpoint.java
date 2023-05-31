package org.pytorch.serve.plugins.endpoint.actuator.health;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.ModelServerEndpoint;
import org.pytorch.serve.servingsdk.http.Request;
import org.pytorch.serve.servingsdk.http.Response;

public class HealthEndpoint extends ModelServerEndpoint {
  private static final Health healthImpl = new Health();

  private static final String JSON_HEADER = "application/json";

  @Override
  public void doGet(Request req, Response rsp, Context ctx) throws IOException {
    var healthResponse = healthImpl.getHealthResponse(ctx);
    rsp.setStatus(healthResponse.getStatus().statusCode);
    rsp.setContentType(JSON_HEADER);
    rsp.getOutputStream()
        .write(new GsonBuilder().create().toJson(healthResponse).getBytes(StandardCharsets.UTF_8));
  }
}
