package com.cpms.cpms.services;

import com.cpms.cpms.dao.WorkerDAO;
import com.cpms.cpms.entities.Worker;
import java.util.List;

public class WorkerService {
    private WorkerDAO workerDAO;

    public WorkerService() {
        this.workerDAO = new WorkerDAO();
    }

    // Adds a new worker to the database
    public void addWorker(Worker worker) {
        workerDAO.addWorker(worker);
    }

    // Retrieves a worker by their ID
    public Worker getWorkerById(int id) {
        return workerDAO.getWorker(id);
    }

    // Retrieves all workers from the database
    public List<Worker> getAllWorkers() {
        return workerDAO.getAllWorkers();
    }

    // Updates an existing worker's details
    public void updateWorker(Worker worker) {
        workerDAO.updateWorker(worker);
    }

    // Deletes a worker by their ID
    public void deleteWorker(int id) {
        workerDAO.deleteWorker(id);
    }
}