package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

public class WorkerTest {

    @Test
    public void testSettersAndGetters() {
        Worker worker = new Worker();

        int workerID = 1;
        String name = "David Lee";
        String contact = "david@example.com";
        String specialty = "Electrician";
        Availability availability = Availability.AVAILABLE;

        Project project = new Project();
        project.setProjectID(100);
        project.setProjectName("Mall Expansion");

        worker.setWorkerID(workerID);
        worker.setWorkerName(name);
        worker.setContactInfo(contact);
        worker.setSpecialty(specialty);
        worker.setAvailability(availability);
        worker.setProject(project);

        Assert.assertEquals(workerID, worker.getWorkerID());
        Assert.assertEquals(name, worker.getWorkerName());
        Assert.assertEquals(contact, worker.getContactInfo());
        Assert.assertEquals(specialty, worker.getSpecialty());
        Assert.assertEquals(availability, worker.getAvailability());
        Assert.assertEquals(project, worker.getProject());
    }
}
