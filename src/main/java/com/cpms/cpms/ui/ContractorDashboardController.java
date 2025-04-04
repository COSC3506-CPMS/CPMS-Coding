package com.cpms.cpms.ui;

import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.entities.Task;
import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.services.ProjectService;
import com.cpms.cpms.services.WorkerService;
import com.cpms.cpms.services.TaskService;
import com.cpms.cpms.services.MilestoneService;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.text.SimpleDateFormat;
import java.util.List;

public class ContractorDashboardController {

    // Tabs and Tables
    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Project> tableProjects;
    @FXML
    private TableColumn<Project, String> colProjectName;
    @FXML
    private TableColumn<Project, String> colStartDate;
    @FXML
    private TableColumn<Project, String> colEndDate;
    @FXML
    private TableColumn<Project, Double> colProjectBudget;
    @FXML
    private TableColumn<Project, String> colStatus;
    @FXML
    private TableColumn<Project, String> colDescription;

    @FXML
    private TableView<Worker> tableWorkers;
    @FXML
    private TableColumn<Worker, String> colWorkerName;
    @FXML
    private TableColumn<Worker, String> colContactInfo;
    @FXML
    private TableColumn<Worker, String> colAvailability;
    @FXML
    private TableColumn<Worker, String> colSpecialty;

    @FXML
    private TableView<Task> tableTasks;
    @FXML
    private TableColumn<Task, String> colTaskName;
    @FXML
    private TableColumn<Task, String> colDeadline;
    @FXML
    private TableColumn<Task, String> colTaskStatus;
    @FXML
    private TableColumn<Task, Integer> colProgressPercentage;

    @FXML
    private TableView<Milestone> tableMilestones;
    @FXML
    private TableColumn<Milestone, String> colMilestoneName;
    @FXML
    private TableColumn<Milestone, String> colTargetDate;
    @FXML
    private TableColumn<Milestone, String> colCompletionDate;
    @FXML
    private TableColumn<Milestone, String> colMilestoneStatus;

    // Buttons for worker operations
    @FXML
    private Button btnAddWorker;
    @FXML
    private Button btnUpdateWorker;
    @FXML
    private Button btnDeleteWorker;

    // Buttons for exit and refresh
    @FXML
    private Button btnExit;
    @FXML
    private Button btnRefresh;

    // Services for fetching data
    private ProjectService projectService = new ProjectService(new com.cpms.cpms.dao.ProjectDAO());
    private WorkerService workerService = new WorkerService();
    private TaskService taskService = new TaskService();
    private MilestoneService milestoneService = new MilestoneService();

    // Date formatter for display purposes
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    @FXML
    public void initialize() {
        setupProjectTable();
        setupWorkerTable();
        setupTaskTable();
        setupMilestoneTable();
        refreshAllTables();

        btnExit.setOnAction(event -> handleExit());
        btnRefresh.setOnAction(event -> refreshAllTables());

        btnAddWorker.setOnAction(event -> handleAddWorker());
        btnUpdateWorker.setOnAction(event -> handleUpdateWorker());
        btnDeleteWorker.setOnAction(event -> handleDeleteWorker());
    }

    private void setupProjectTable() {
        colProjectName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProjectName()));
        colStartDate.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getStartDate() != null ? dateFormatter.format(cellData.getValue().getStartDate()) : "N/A"));
        colEndDate.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getEndDate() != null ? dateFormatter.format(cellData.getValue().getEndDate()) : "N/A"));
        colProjectBudget.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProjectBudget()).asObject());
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));
        colDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
    }

    private void setupWorkerTable() {
        colWorkerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWorkerName()));
        colContactInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactInfo()));
        colAvailability.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvailability().toString()));
        colSpecialty.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpecialty()));
    }

    private void setupTaskTable() {
        colTaskName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTaskName()));
        colDeadline.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getDeadline() != null ? dateFormatter.format(cellData.getValue().getDeadline()) : "N/A"));
        colTaskStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));
        colProgressPercentage.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProgressPercentage()).asObject());
    }

    private void setupMilestoneTable() {
        colMilestoneName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMilestoneName()));
        colTargetDate.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getTargetDate() != null ? dateFormatter.format(cellData.getValue().getTargetDate()) : "N/A"));
        colCompletionDate.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getCompletionDate() != null ? dateFormatter.format(cellData.getValue().getCompletionDate()) : "N/A"));
        colMilestoneStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));
    }

    private void refreshAllTables() {
        tableProjects.getItems().setAll(projectService.getAllProjects());
        tableWorkers.getItems().setAll(workerService.getAllWorkers());
        tableTasks.getItems().setAll(taskService.getAllTasks());
        tableMilestones.getItems().setAll(milestoneService.getAllMilestones());
        System.out.println("Testing Projects:");
        List<Project> projects = projectService.getAllProjects();
        if (projects != null && !projects.isEmpty()) {
            projects.forEach(project -> System.out.println("Project Name: " + project.getProjectName()));
        } else {
            System.out.println("No projects found or projects list is null.");
        }

        System.out.println("Testing Workers:");
        List<Worker> workers = workerService.getAllWorkers();
        if (workers != null && !workers.isEmpty()) {
            workers.forEach(worker -> System.out.println("Worker Name: " + worker.getWorkerName()));
        } else {
            System.out.println("No workers found or workers list is null.");
        }
        // Add task testing logic here
        System.out.println("Testing Tasks:");
        List<Task> tasks = taskService.getAllTasks();
        if (tasks != null && !tasks.isEmpty()) {
            tasks.forEach(task -> System.out.println("Task Name: " + task.getTaskName() + 
                ", Status: " + task.getStatus() + 
                ", Progress: " + task.getProgressPercentage() + "%"));
        } else {
            System.out.println("No tasks found or tasks list is null.");
        }

    }

    private void handleAddWorker() {
        Worker newWorker = new Worker();
        newWorker.setWorkerName("New Worker");
        newWorker.setContactInfo("example@email.com");
        newWorker.setAvailability(Worker.Availability.AVAILABLE);
        newWorker.setSpecialty("General Specialty");
        workerService.addWorker(newWorker);
        refreshAllTables();
        showAlert("Success", "New worker added successfully!");
    }

    private void handleUpdateWorker() {
        Worker selectedWorker = tableWorkers.getSelectionModel().getSelectedItem();
        if (selectedWorker != null) {
            selectedWorker.setWorkerName("Updated Worker Name");
            selectedWorker.setContactInfo("updated@email.com");
            selectedWorker.setSpecialty("Updated Specialty");
            workerService.updateWorker(selectedWorker);
            refreshAllTables();
            showAlert("Success", "Worker updated successfully!");
        } else {
            showAlert("Error", "Please select a worker to update.");
        }
    }

    private void handleDeleteWorker() {
        Worker selectedWorker = tableWorkers.getSelectionModel().getSelectedItem();
        if (selectedWorker != null) {
            workerService.deleteWorker(selectedWorker.getWorkerID());
            refreshAllTables();
            showAlert("Success", "Worker deleted successfully!");
        } else {
            showAlert("Error", "Please select a worker to delete.");
        }
    }

    private void handleExit() {
        System.out.println("Exiting dashboard and returning to login page...");
        // Implement navigation logic here
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}