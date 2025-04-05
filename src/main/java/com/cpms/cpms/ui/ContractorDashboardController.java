package com.cpms.cpms.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import com.cpms.cpms.dao.ProjectDAO;
import com.cpms.cpms.dao.WorkerDAO;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.dao.TaskDAO;

import java.util.List;

import com.cpms.cpms.dao.MilestoneDAO;

public class ContractorDashboardController {
    @FXML private TabPane tabPane;
    @FXML private TableView<?> tableProjects, tableWorkers, tableTasks, tableMilestones;
    @FXML private Button btnAddProject, btnUpdateProject, btnDeleteProject, btnRefreshProjects;
    @FXML private Button btnAddWorker, btnUpdateWorker, btnDeleteWorker, btnRefreshWorkers;
    @FXML private Button btnAddTask, btnUpdateTask, btnDeleteTask, btnRefreshTasks;
    @FXML private Button btnAddMilestone, btnUpdateMilestone, btnDeleteMilestone, btnRefreshMilestones;
    @FXML private Button btnExit;

    //object DAO
    private ProjectDAO projectDAO;
    private WorkerDAO workerDAO;
    private TaskDAO taskDAO;
    private MilestoneDAO milestoneDAO;
    
    //table columns
    //project table columns
    @FXML private TableColumn<Project, Integer> columnProjectId;
    @FXML private TableColumn<Project, String> columnProjectName;
    @FXML private TableColumn<Project, String> columnStartDate;
    @FXML private TableColumn<Project, String> columnEndDate;
    @FXML private TableColumn<Project, String> columnStatus;

    @FXML
    public void initialize() {
    	
    	// Initialize Project columns with property values
        projectDAO = new ProjectDAO();
        columnProjectId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
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


    
   /* @FXML
    private void handleRefreshProjects() {
        try {
            // Fetch the list of projects from the ProjectDAO
            List<Project> projects = projectDAO.getAllProjects();

            // Check if projects is not null
            if (projects != null) {
                // Convert the list to an ObservableList for the TableView
                ObservableList<Project> projectData = FXCollections.observableArrayList(projects);

                // Clear and update the table with fresh data
                tableProjects.getItems().clear();
                tableProjects.getItems().addAll(projectData);

                System.out.println("Projects table refreshed successfully.");
            } else {
                showAlert("Error", "No projects found in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the projects table.");
        }
    }*/
    
    //to exit from dashboard
    @FXML
    private void handleExit() {
        try {
            // Load the Login page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cpms/cpms/ui/LoginPage.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) btnExit.getScene().getWindow(); 
            stage.setScene(new Scene(root));
            stage.setTitle("Login Page");

            // Print confirmation
            System.out.println("Navigating back to Login page...");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Login page.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void refreshAllTables() {
        // Logic to populate tables with data from DAOs
    }
}