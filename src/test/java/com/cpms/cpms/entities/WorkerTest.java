package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class WorkerTest {

    @Test
    public void testWorkerSettersAndGetters() {
        // Arrange
        Worker worker = new Worker();
        int workerID = 1;
        String name = "David Lee";
        String contact = "david@example.com";
        String specialty = "Electrician";
        Worker.Availability availability = Worker.Availability.AVAILABLE;

        Project project = new Project(); // Create a Project object
        project.setProjectID(1);
        project.setProjectName("Mall Expansion");

        // Act
        worker.setWorkerID(workerID);
        worker.setWorkerName(name);
        worker.setContactInfo(contact);
        worker.setSpecialty(specialty);
        worker.setAvailability(availability);
        worker.setAssignedProjectID(project); // Assign project

        // Assert
        Assertions.assertEquals(workerID, worker.getWorkerID(), "Worker ID mismatch");
        Assertions.assertEquals(name, worker.getWorkerName(), "Worker Name mismatch");
        Assertions.assertEquals(contact, worker.getContactInfo(), "Contact Info mismatch");
        Assertions.assertEquals(specialty, worker.getSpecialty(), "Specialty mismatch");
        Assertions.assertEquals(availability, worker.getAvailability(), "Availability mismatch");
        Assertions.assertEquals(project.getProjectID(), worker.getAssignedProjectID(), "Assigned Project ID mismatch");
    }
}