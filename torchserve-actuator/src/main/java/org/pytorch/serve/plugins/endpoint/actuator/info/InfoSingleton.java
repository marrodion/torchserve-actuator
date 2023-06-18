package org.pytorch.serve.plugins.endpoint.actuator.info;

public enum InfoSingleton {
    INSTANCE;

    private final BuildInfoProvider buildInfoProvider;
    private final GitInfoProvider gitInfoProvider;

    InfoSingleton() {
        buildInfoProvider = new BuildInfoProvider();
        gitInfoProvider = new GitInfoProvider();
    }

    public BuildInfoProvider getBuildInfoProvider() {
        return buildInfoProvider;
    }

    public GitInfoProvider getGitInfoProvider() {
        return gitInfoProvider;
    }
}
