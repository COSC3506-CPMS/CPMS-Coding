package com.cpms.cpms.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.cpms.cpms.dao.ProjectDAO;
import com.cpms.cpms.dao.WorkerDAO;
import com.cpms.cpms.dao.TaskDAO;
import com.cpms.cpms.dao.MilestoneDAO;

public class ContractorDashboardController {
    @FXML private TabPane tabPane;
    @FXML private TableView<?> tableProjects, tableWorkers, tableTasks, tableMilestones;
    @FXML private Button btnAddProject, btnUpdateProject, btnDeleteProject, btnRefreshProjects;
    @FXML private Button btnAddWorker, btnUpdateWorker, btnDeleteWorker, btnRefreshWorkers;
    @FXML private Button btnAddTask, btnUpdateTask, btnDeleteTask, btnRefreshTasks;
    @FXML private Button btnAddMilestone, btnUpdateMilestone, btnDeleteMilestone, btnRefreshMilestones;
    @FXML private Button btnExit;

    private ProjectDAO projectDAO;
    private WorkerDAO workerDAO;
    private TaskDAO taskDAO;
    private MilestoneDAO milestoneDAO;

    @FXML
    public void initialize() {
        projectDAO = new ProjectDAO();
        workerDAO = new WorkerDAO();
        taskDAO = new TaskDAO();
        milestoneDAO = new MilestoneDAO();

        refreshAllTables(); // Load data at initialization
    }

    @FXML
    private void handleAddProject() {
        // Add project logic
    }

    @FXML
    private void handleUpdateProject() {
        // Update project logic
    }

    @FXML
    private void handleDeleteProject() {
        // Delete project logic
    }

    @FXML
    private void handleRefreshProjects() {
        // Refresh projects table
    }

    @FXML
    private void handleExit() {
        // Logic to log out and navigate back to login
    }

    private void refreshAllTables() {
        // Logic to populate tables with data from DAOs
    }
}