package org.pytorch.serve.plugins.endpoint.actuator.info;

import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GitInfoProviderTest {
  @Test
  public void testGetGitInfoProviderNormalJsonSerializable() {
    var json = new GsonBuilder().create().toJson(new GitInfo());
    Assert.assertNotNull(json);
  }
}
