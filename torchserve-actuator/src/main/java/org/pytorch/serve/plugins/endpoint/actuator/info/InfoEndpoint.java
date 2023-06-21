package org.pytorch.serve.plugins.endpoint.actuator.info;

import static java.net.HttpURLConnection.HTTP_OK;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import org.pytorch.serve.servingsdk.Context;
import org.pytorch.serve.servingsdk.ModelServerEndpoint;
import org.pytorch.serve.servingsdk.ModelServerEndpointException;
import org.pytorch.serve.servingsdk.http.Request;
import org.pytorch.serve.servingsdk.http.Response;

public class InfoEndpoint extends ModelServerEndpoint {
  private static final InfoResponse INFO_RESPONSE = InfoImplementation.getInfo();
  private static final String JSON_HEADER = "application/json";

  @Override
  public void doGet(Request req, Response res, Context ctx)
      throws ModelServerEndpointException, IOException {
    res.setStatus(HTTP_OK);
    res.setContentType(JSON_HEADER);
    res.getOutputStream()
        .write(
            new GsonBuilder()
                .setDateFormat(DateTimeFormatter.ISO_OFFSET_DATE_TIME.toString())
                .create()
                .toJson(INFO_RESPONSE)
                .getBytes(StandardCharsets.UTF_8));
  }
}
