package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.services.WorkerService;
import java.util.List;

// Controller class for managing worker-related actions
public class WorkerController {
    private final WorkerService workerService;

    // Constructor initializes the WorkerService
    public WorkerController() {
        this.workerService = new WorkerService();
    }

    // Adds a new worker
    public void addWorker(Worker worker) {
        workerService.addWorker(worker);
    }

    // Retrieves a worker by their ID
    public Worker getWorkerById(int id) {
        return workerService.getWorkerById(id);
    }

    // Retrieves all workers
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    // Updates an existing worker
    public void updateWorker(Worker worker) {
        workerService.updateWorker(worker);
    }

    // Deletes a worker by their ID
    public void deleteWorker(int id) {
        workerService.deleteWorker(id);
    }
}