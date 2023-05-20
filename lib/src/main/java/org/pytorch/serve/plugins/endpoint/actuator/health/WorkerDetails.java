package org.pytorch.serve.plugins.endpoint.actuator.health;

import org.pytorch.serve.servingsdk.Worker;

public class WorkerDetails {
    private final boolean isRunning;
    private final long workerMemory;

    public WorkerDetails(Worker worker) {
        this.isRunning = worker.isRunning();
        this.workerMemory = worker.getWorkerMemory();
    }

    public long getWorkerMemory() {
        return workerMemory;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
