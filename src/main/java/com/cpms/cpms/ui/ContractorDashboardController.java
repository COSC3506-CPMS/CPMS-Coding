package com.cpms.cpms.ui;

import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Project.ProjectStatus;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Date;
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
    
    // Buttons for project operations
    @FXML
    private Button btnAddProject;
    @FXML
    private Button btnUpdateProject;
    @FXML
    private Button btnDeleteProject;
    
    // Buttons for task operations
    @FXML
    private Button btnAddTask;
    @FXML
    private Button btnUpdateTask;
    @FXML
    private Button btnDeleteTask;
    
    // Buttons for milestone operations
    @FXML
    private Button btnAddMilestone;
    @FXML
    private Button btnUpdateMilestone;
    @FXML
    private Button btnDeleteMilestone;

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

        // Existing worker buttons
        btnExit.setOnAction(event -> handleExit());
        btnRefresh.setOnAction(event -> refreshAllTables());
        btnAddWorker.setOnAction(event -> handleAddWorker());
        btnUpdateWorker.setOnAction(event -> handleUpdateWorker());
        btnDeleteWorker.setOnAction(event -> handleDeleteWorker());
        // project buttons
        btnAddProject.setOnAction(event -> handleAddProject());
        btnUpdateProject.setOnAction(event -> handleUpdateProject());
        btnDeleteProject.setOnAction(event -> handleDeleteProject());
        // task buttons
        btnAddTask.setOnAction(event -> handleAddTask());
        btnUpdateTask.setOnAction(event -> handleUpdateTask());
        btnDeleteTask.setOnAction(event -> handleDeleteTask());
        // milestone buttons
        btnAddMilestone.setOnAction(event -> handleAddMilestone());
        btnUpdateMilestone.setOnAction(event -> handleUpdateMilestone());
        btnDeleteMilestone.setOnAction(event -> handleDeleteMilestone());
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
        
     // Add milestone testing logic here
        System.out.println("Testing Milestones:");
        List<Milestone> milestones = milestoneService.getAllMilestones();
        if (milestones != null && !milestones.isEmpty()) {
            milestones.forEach(milestone -> System.out.println(
                "Milestone Name: " + milestone.getMilestoneName() +
                ", Target Date: " + (milestone.getTargetDate() != null ? milestone.getTargetDate() : "N/A") +
                ", Completion Date: " + (milestone.getCompletionDate() != null ? milestone.getCompletionDate() : "N/A") +
                ", Status: " + milestone.getStatus()));
        } else {
            System.out.println("No milestones found or milestones list is null.");
        }

    }
//-------------------------------------------handling add, delete and update functions---------------------------------------------------------------
    @FXML
    private void handleAddWorker() {
        Dialog<Worker> workerDialog = createWorkerDialog(null); // Pass null for adding a new Worker
        workerDialog.showAndWait().ifPresent(worker -> {
            workerService.addWorker(worker);
            refreshAllTables();
            showAlert("Success", "Worker added successfully!");
        });
    }

    @FXML
    private void handleUpdateWorker() {
        Worker selectedWorker = tableWorkers.getSelectionModel().getSelectedItem();
        if (selectedWorker == null) {
            showAlert("Error", "Please select a worker to update.");
            return;
        }
        Dialog<Worker> workerDialog = createWorkerDialog(selectedWorker); // Pass selected worker for update
        workerDialog.showAndWait().ifPresent(worker -> {
            workerService.updateWorker(worker);
            refreshAllTables();
            showAlert("Success", "Worker updated successfully!");
        });
    }

    @FXML
    private void handleDeleteWorker() {
        Worker selectedWorker = tableWorkers.getSelectionModel().getSelectedItem();
        if (selectedWorker == null) {
            showAlert("Error", "Please select a worker to delete.");
            return;
        }
        workerService.deleteWorker(selectedWorker.getWorkerID());
        refreshAllTables();
        showAlert("Success", "Worker deleted successfully!");
    }

    @FXML
    private void handleAddProject() {
        Dialog<Project> projectDialog = createProjectDialog(null); // Pass null for adding a new Project
        projectDialog.showAndWait().ifPresent(project -> {
            projectService.addProject(project);
            refreshAllTables();
            showAlert("Success", "Project added successfully!");
        });
    }

    @FXML
    private void handleUpdateProject() {
        Project selectedProject = tableProjects.getSelectionModel().getSelectedItem();
        if (selectedProject == null) {
            showAlert("Error", "Please select a project to update.");
            return;
        }
        Dialog<Project> projectDialog = createProjectDialog(selectedProject); // Pass selected project for update
        projectDialog.showAndWait().ifPresent(project -> {
            projectService.updateProject(project);
            refreshAllTables();
            showAlert("Success", "Project updated successfully!");
        });
    }

    @FXML
    private void handleDeleteProject() {
        Project selectedProject = tableProjects.getSelectionModel().getSelectedItem();
        if (selectedProject == null) {
            showAlert("Error", "Please select a project to delete.");
            return;
        }
        projectService.deleteProject(selectedProject.getProjectID());
        refreshAllTables();
        showAlert("Success", "Project deleted successfully!");
    }

    @FXML
    private void handleAddTask() {
        Dialog<Task> taskDialog = createTaskDialog(null); // Pass null for adding a new Task
        taskDialog.showAndWait().ifPresent(task -> {
            taskService.addTask(task);
            refreshAllTables();
            showAlert("Success", "Task added successfully!");
        });
    }

    @FXML
    private void handleUpdateTask() {
        Task selectedTask = tableTasks.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to update.");
            return;
        }
        Dialog<Task> taskDialog = createTaskDialog(selectedTask); // Pass selected task for update
        taskDialog.showAndWait().ifPresent(task -> {
            taskService.updateTask(task);
            refreshAllTables();
            showAlert("Success", "Task updated successfully!");
        });
    }

    @FXML
    private void handleDeleteTask() {
        Task selectedTask = tableTasks.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            showAlert("Error", "Please select a task to delete.");
            return;
        }
        taskService.deleteTask(selectedTask.getTaskID());
        refreshAllTables();
        showAlert("Success", "Task deleted successfully!");
    }

    @FXML
    private void handleAddMilestone() {
        Dialog<Milestone> milestoneDialog = createMilestoneDialog(null); // Pass null for adding a new Milestone
        milestoneDialog.showAndWait().ifPresent(milestone -> {
            milestoneService.addMilestone(milestone);
            refreshAllTables();
            showAlert("Success", "Milestone added successfully!");
        });
    }

    @FXML
    private void handleUpdateMilestone() {
        Milestone selectedMilestone = tableMilestones.getSelectionModel().getSelectedItem();
        if (selectedMilestone == null) {
            showAlert("Error", "Please select a milestone to update.");
            return;
        }
        Dialog<Milestone> milestoneDialog = createMilestoneDialog(selectedMilestone); // Pass selected milestone for update
        milestoneDialog.showAndWait().ifPresent(milestone -> {
            milestoneService.updateMilestone(milestone);
            refreshAllTables();
            showAlert("Success", "Milestone updated successfully!");
        });
    }

    @FXML
    private void handleDeleteMilestone() {
        Milestone selectedMilestone = tableMilestones.getSelectionModel().getSelectedItem();
        if (selectedMilestone == null) {
            showAlert("Error", "Please select a milestone to delete.");
            return;
        }
        milestoneService.deleteMilestone(selectedMilestone.getMilestoneID());
        refreshAllTables();
        showAlert("Success", "Milestone deleted successfully!");
    }
    
//-------------------------------------------handling finish-----------------------------------------------------------------------------------------
    
//----------------------------------------------dialog create----------------------------------------------------------------------------------
    private Dialog<Project> createProjectDialog(Project project) {
        Dialog<Project> dialog = new Dialog<>();
        dialog.setTitle(project == null ? "Add Project" : "Update Project");
        dialog.setHeaderText(project == null ? "Enter the details for the new project" : "Edit the details for the selected project");

        // Input fields
        TextField nameField = new TextField(project != null ? project.getProjectName() : "");
        nameField.setPromptText("Project Name");

        TextField startDateField = new TextField(project != null && project.getStartDate() != null ? project.getStartDate().toString() : "");
        startDateField.setPromptText("Start Date (YYYY-MM-DD)");

        TextField endDateField = new TextField(project != null && project.getEndDate() != null ? project.getEndDate().toString() : "");
        endDateField.setPromptText("End Date (YYYY-MM-DD)");

        TextField budgetField = new TextField(project != null ? String.valueOf(project.getProjectBudget()) : "");
        budgetField.setPromptText("Budget");

        ComboBox<Project.ProjectStatus> statusBox = new ComboBox<>();
        statusBox.getItems().addAll(Project.ProjectStatus.values());
        statusBox.setValue(project != null ? project.getStatus() : null);

        TextField descriptionField = new TextField(project != null ? project.getDescription() : "");
        descriptionField.setPromptText("Description");

        // Layout input fields in a grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Project Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Start Date (YYYY-MM-DD):"), 0, 1);
        grid.add(startDateField, 1, 1);
        grid.add(new Label("End Date (YYYY-MM-DD):"), 0, 2);
        grid.add(endDateField, 1, 2);
        grid.add(new Label("Budget:"), 0, 3);
        grid.add(budgetField, 1, 3);
        grid.add(new Label("Status:"), 0, 4);
        grid.add(statusBox, 1, 4);
        grid.add(new Label("Description:"), 0, 5);
        grid.add(descriptionField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // Buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Temporary variable to work around final/effectively final constraint
        final Project tempProject = project == null ? new Project() : project;

        // Result handling
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    tempProject.setProjectName(nameField.getText());
                    tempProject.setStartDate(Date.valueOf(startDateField.getText()));
                    tempProject.setEndDate(Date.valueOf(endDateField.getText()));
                    tempProject.setProjectBudget(Double.parseDouble(budgetField.getText()));
                    tempProject.setStatus(statusBox.getValue());
                    tempProject.setDescription(descriptionField.getText());
                    return tempProject;
                } catch (Exception e) {
                    showAlert("Error", "Invalid input! Please check the fields.");
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }
    
    //task
    private Dialog<Task> createTaskDialog(Task task) {
        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle(task == null ? "Add Task" : "Update Task");
        dialog.setHeaderText(task == null ? "Enter the details for the new task" : "Edit the details for the selected task");

        // Input fields
        TextField nameField = new TextField(task != null ? task.getTaskName() : "");
        nameField.setPromptText("Task Name");

        TextField deadlineField = new TextField(task != null && task.getDeadline() != null ? task.getDeadline().toString() : "");
        deadlineField.setPromptText("Deadline (YYYY-MM-DD)");

        TextField statusField = new TextField(task != null ? task.getStatus() : "");
        statusField.setPromptText("Status");

        TextField progressField = new TextField(task != null ? String.valueOf(task.getProgressPercentage()) : "");
        progressField.setPromptText("Progress (%)");

        // Layout input fields in a grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Task Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Deadline (YYYY-MM-DD):"), 0, 1);
        grid.add(deadlineField, 1, 1);
        grid.add(new Label("Status:"), 0, 2);
        grid.add(statusField, 1, 2);
        grid.add(new Label("Progress (%):"), 0, 3);
        grid.add(progressField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Temporary variable to satisfy final/effectively final requirement
        final Task tempTask = task == null ? new Task() : task;

        // Result handling
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    tempTask.setTaskName(nameField.getText());
                    tempTask.setDeadline(Date.valueOf(deadlineField.getText()));
                    tempTask.setStatus(statusField.getText());
                    tempTask.setProgressPercentage(Integer.parseInt(progressField.getText()));
                    return tempTask;
                } catch (Exception e) {
                    showAlert("Error", "Invalid input! Please check the fields.");
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }
    
    //worker
    private Dialog<Worker> createWorkerDialog(Worker worker) {
        Dialog<Worker> dialog = new Dialog<>();
        dialog.setTitle(worker == null ? "Add Worker" : "Update Worker");
        dialog.setHeaderText(worker == null ? "Enter the details for the new worker" : "Edit the details for the selected worker");

        // Input fields
        TextField nameField = new TextField(worker != null ? worker.getWorkerName() : "");
        nameField.setPromptText("Worker Name");

        TextField contactField = new TextField(worker != null ? worker.getContactInfo() : "");
        contactField.setPromptText("Contact Info");

        TextField availabilityField = new TextField(worker != null ? worker.getAvailability().toString() : "");
        availabilityField.setPromptText("Availability (e.g., AVAILABLE)");

        TextField specialtyField = new TextField(worker != null ? worker.getSpecialty() : "");
        specialtyField.setPromptText("Specialty");

        // Layout input fields in a grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Worker Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Contact Info:"), 0, 1);
        grid.add(contactField, 1, 1);
        grid.add(new Label("Availability:"), 0, 2);
        grid.add(availabilityField, 1, 2);
        grid.add(new Label("Specialty:"), 0, 3);
        grid.add(specialtyField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Temporary variable to satisfy final/effectively final requirement
        final Worker tempWorker = worker == null ? new Worker() : worker;

        // Result handling
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    tempWorker.setWorkerName(nameField.getText());
                    tempWorker.setContactInfo(contactField.getText());
                    tempWorker.setAvailability(Worker.Availability.valueOf(availabilityField.getText().toUpperCase()));
                    tempWorker.setSpecialty(specialtyField.getText());
                    return tempWorker;
                } catch (Exception e) {
                    showAlert("Error", "Invalid input! Please check the fields.");
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }
    //milestone
    private Dialog<Milestone> createMilestoneDialog(Milestone milestone) {
        Dialog<Milestone> dialog = new Dialog<>();
        dialog.setTitle(milestone == null ? "Add Milestone" : "Update Milestone");
        dialog.setHeaderText(milestone == null ? "Enter the details for the new milestone" : "Edit the details for the selected milestone");

        // Input fields
        TextField nameField = new TextField(milestone != null ? milestone.getMilestoneName() : "");
        nameField.setPromptText("Milestone Name");

        TextField targetDateField = new TextField(milestone != null && milestone.getTargetDate() != null ? milestone.getTargetDate().toString() : "");
        targetDateField.setPromptText("Target Date (YYYY-MM-DD)");

        TextField completionDateField = new TextField(milestone != null && milestone.getCompletionDate() != null ? milestone.getCompletionDate().toString() : "");
        completionDateField.setPromptText("Completion Date (YYYY-MM-DD)");

        TextField statusField = new TextField(milestone != null ? milestone.getStatus() : "");
        statusField.setPromptText("Status");

        // Layout input fields in a grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Milestone Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Target Date (YYYY-MM-DD):"), 0, 1);
        grid.add(targetDateField, 1, 1);
        grid.add(new Label("Completion Date (YYYY-MM-DD):"), 0, 2);
        grid.add(completionDateField, 1, 2);
        grid.add(new Label("Status:"), 0, 3);
        grid.add(statusField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Temporary variable to satisfy final/effectively final requirement
        final Milestone tempMilestone = milestone == null ? new Milestone() : milestone;

        // Result handling
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    tempMilestone.setMilestoneName(nameField.getText());
                    tempMilestone.setTargetDate(Date.valueOf(targetDateField.getText()));
                    tempMilestone.setCompletionDate(completionDateField.getText().isEmpty() ? null : Date.valueOf(completionDateField.getText()));
                    tempMilestone.setStatus(statusField.getText());
                    return tempMilestone;
                } catch (Exception e) {
                    showAlert("Error", "Invalid input! Please check the fields.");
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }
//------------------------------------------------dialog create done-----------------------------------------------------------------------------------
    //to exit from dashboard
    private void handleExit() {
        try {
            // Load the Login page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cpms/cpms/ui/LoginPage.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) tabPane.getScene().getWindow(); // 'tabPane' is any element in the current scene
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
}