package org.pytorch.serve.plugins.endpoint.actuator.health;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.Map;
import org.pytorch.serve.servingsdk.Model;
import org.pytorch.serve.servingsdk.Worker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HealthResponseTest {
  Model model = mock(Model.class);

  @BeforeMethod
  public void setUp() {
    when(model.getModelWorkers()).thenReturn(List.of(mock(Worker.class)));
  }

  @Test
  public void testJsonSerializable() {
    HealthStatus healthStatus = HealthStatus.UP;
    HealthResponse healthResponse =
        new HealthResponse(healthStatus, Map.of("model", ComponentDetails.fromModel(model)));
    var json = new GsonBuilder().create().toJson(healthResponse);
    Assert.assertNotNull(json);
  }
}
