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
import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.dao.TaskDAO;

import java.sql.Date;
import java.util.List;

import com.cpms.cpms.dao.MilestoneDAO;

public class ContractorDashboardController {
    @FXML private TabPane tabPane;
    @FXML private TableView<Project> tableProjects;
    @FXML private TableView<Worker> tableWorkers, tableTasks, tableMilestones;
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
   //worker table columns
    @FXML private TableColumn<Worker, Integer> columnWorkerId;
    @FXML private TableColumn<Worker, String> columnWorkerName;
    @FXML private TableColumn<Worker, String>columnContactInfo;
    @FXML private TableColumn<Worker, String>columnAvailablility;
    @FXML private TableColumn<Worker, String>columnSpecialty; 
    @FXML
    public void initialize() {
    	
    	// Initialize Project columns with property values
        projectDAO = new ProjectDAO();
        try {
            columnProjectId.setCellValueFactory(new PropertyValueFactory<>("projectID"));
            columnProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
            columnStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            columnEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Initialize Project columns with property values
        workerDAO = new WorkerDAO();
        try {
        	columnWorkerId.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        	columnWorkerName.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        	columnContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
        	columnAvailablility.setCellValueFactory(new PropertyValueFactory<>("availability"));
        	columnSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        
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

    //Refresh Handling
    //refresh project
    @FXML
    private void handleRefreshProjects() {
        try {
            // Fetch the list of projects from the ProjectDAO
            List<Project> projects = projectDAO.getAllProjects();

            // Check if projects is not null
            if (projects != null) {
                // Convert the list to an ObservableList for the TableView
                ObservableList<Project> projectData = FXCollections.observableArrayList(projects);

                // Use setItems to directly set the ObservableList to the TableView
                tableProjects.setItems(projectData);

                System.out.println("Projects table refreshed successfully.");
            } else {
                // Handle the case when no projects are found
                showAlert("Error", "No projects found in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Show an alert if refreshing fails
            showAlert("Error", "Failed to refresh the projects table.");
        }
    }
    @FXML
    private void handleRefreshWorkers() {
        try {
            // Fetch the list of projects from the ProjectDAO
            List<Worker> workers = workerDAO.getAllWorkers();

            // Check if projects is not null
            if (workers != null) {
                // Convert the list to an ObservableList for the TableView
                ObservableList<Worker> workerData = FXCollections.observableArrayList(workers);

                // Use setItems to directly set the ObservableList to the TableView
                tableWorkers.setItems(workerData);

                System.out.println("Workers table refreshed successfully.");
            } else {
                // Handle the case when no projects are found
                showAlert("Error", "No workers found in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Show an alert if refreshing fails
            showAlert("Error", "Failed to refresh the workers table.");
        }
    }
    
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
            //stage.setMaximized(true);
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
    	handleRefreshProjects();
    	handleRefreshWorkers();
        // Logic to populate tables with data from DAOs
    }
}