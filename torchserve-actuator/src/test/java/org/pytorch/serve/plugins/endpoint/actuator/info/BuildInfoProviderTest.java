package org.pytorch.serve.plugins.endpoint.actuator.info;

import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuildInfoProviderTest {

  @Test
  public void testGetInfoProvider() {
    BuildInfo infoProvider = new BuildInfo();
    var json = new GsonBuilder().create().toJson(infoProvider);
    Assert.assertEquals(json, "{\"artifact\":\"\",\"version\":\"\",\"group\":\"\"}");
  }
}
