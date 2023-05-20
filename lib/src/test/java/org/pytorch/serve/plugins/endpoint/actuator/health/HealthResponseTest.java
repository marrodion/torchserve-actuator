package org.pytorch.serve.plugins.endpoint.actuator.health;

import com.google.gson.GsonBuilder;
import org.pytorch.serve.servingsdk.Model;
import org.pytorch.serve.servingsdk.Worker;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public class HealthResponseTest {
    @Test
    public void testGetStatus() {
        HealthStatus healthStatus = HealthStatus.UP;
        HealthResponse healthResponse = new HealthResponse(healthStatus, Map.of("model", new ModelDetails(new Model() {
            @Override
            public String getModelName() {
                return "test";
            }

            @Override
            public String getModelUrl() {
                return "predictions/test";
            }

            @Override
            public String getModelHandler() {
                return "handler.py";
            }

            @Override
            public List<Worker> getModelWorkers() {
                return List.of(new Worker() {
                    @Override
                    public boolean isRunning() {
                        return true;
                    }

                    @Override
                    public long getWorkerMemory() {
                        return 100;
                    }
                });
            }
        })));
        var json = new GsonBuilder().create().toJson(healthResponse);
        assert json != null;
    }
}