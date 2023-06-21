package org.pytorch.serve.plugins.endpoint.actuator.info;

import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InfoImplementationTest {
  @Test
  public void testGetGitInfoProvider() {
    var json = new GsonBuilder().create().toJson(InfoImplementation.getInfo());
    Assert.assertNotNull(json);
  }
}
