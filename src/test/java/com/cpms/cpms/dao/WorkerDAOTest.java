package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Worker.Availability;

import java.sql.Date;

public class WorkerDAOTest {
    public static void main(String[] args) {
        WorkerDAO workerDAO = new WorkerDAO();
        ProjectDAO projectDAO = new ProjectDAO();

        // Add a project for the worker
        Project project = new Project();
        project.setProjectName("New Building Construction");
        project.setStartDate(Date.valueOf("2025-06-01"));
        project.setEndDate(Date.valueOf("2025-12-31"));
        project.setProjectBudget(750000.00);
        project.setStatus(Project.ProjectStatus.PLANNED);
        project.setDescription("Construction of a commercial building.");
        projectDAO.addProject(project); // Add project to be associated with the worker

        // Test adding a worker
        System.out.println("---- Testing Add Worker ----");
        Worker worker = new Worker();
        worker.setWorkerName("John Doe");
        worker.setContactInfo("john.doe@example.com");
        worker.setAvailability(Availability.AVAILABLE);
        worker.setSpecialty("Electrician");
        worker.setProject(project);
        workerDAO.addWorker(worker);
        System.out.println("Worker added: " + worker);

        // Test getting a worker by ID
        System.out.println("---- Testing Get Worker by ID ----");
        Worker retrievedWorker = workerDAO.getWorker(worker.getWorkerID());
        if (retrievedWorker != null) {
            System.out.println("Retrieved Worker: " + retrievedWorker);
        }

        // Test updating a worker
        System.out.println("---- Testing Update Worker ----");
        if (retrievedWorker != null) {
            retrievedWorker.setContactInfo("new.john.doe@example.com");
            workerDAO.updateWorker(retrievedWorker);
            System.out.println("Worker updated: " + retrievedWorker);
        }

        // Test retrieving all workers
        System.out.println("---- Testing Get All Workers ----");
        System.out.println("All Workers: " + workerDAO.getAllWorkers());

        // Test deleting a worker
        System.out.println("---- Testing Delete Worker ----");
        if (retrievedWorker != null) {
            workerDAO.deleteWorker(retrievedWorker.getWorkerID());
            System.out.println("Worker deleted successfully!");
        }
    }
}