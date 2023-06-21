package org.pytorch.serve.plugins.endpoint.actuator.info;

public class InfoSingleton {
  private static final InfoSingleton INSTANCE = new InfoSingleton();

  private final BuildInfoProvider build;
  private final GitInfoProvider git;

  private InfoSingleton() {
    build = BuildInfoProvider.getInfoProvider();
    git = GitInfoProvider.getGitInfoProvider();
  }

  public static InfoSingleton getInstance() {
    return INSTANCE;
  }
}
