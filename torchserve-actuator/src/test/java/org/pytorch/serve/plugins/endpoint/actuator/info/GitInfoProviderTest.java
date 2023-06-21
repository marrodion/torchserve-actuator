package org.pytorch.serve.plugins.endpoint.actuator.info;

import com.google.gson.GsonBuilder;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GitInfoProviderTest {
  @Test
  public void testGetGitInfoNormalJsonSerializable() {
    var json =
        new GsonBuilder()
            .setDateFormat(DateTimeFormatter.ISO_OFFSET_DATE_TIME.toString())
            .create()
            .toJson(new GitInfo());
    Assert.assertNotNull(json);
  }
}
