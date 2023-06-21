package org.pytorch.serve.plugins.endpoint.actuator.info;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InfoImplementationTest {
  @Test
  public void testGetGitInfoProvider() {
    var json = InfoImplementation.getInfoJson();
    Assert.assertNotNull(json);
  }
}
