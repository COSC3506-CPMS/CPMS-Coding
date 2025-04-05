package com.cpms.cpms.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import com.cpms.cpms.dao.ProjectDAO;
import com.cpms.cpms.dao.WorkerDAO;
import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Task;
import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.dao.TaskDAO;

import java.sql.Date;
import java.util.List;


import org.hibernate.Transaction;
import org.hibernate.Session;


import com.cpms.cpms.config.HibernateUtil;
import com.cpms.cpms.dao.MilestoneDAO;

public class ContractorDashboardController {
	//tableView
    @FXML private TabPane tabPane;
    @FXML private TableView<Project> tableProjects;
    @FXML private TableView<Worker> tableWorkers;
    @FXML private TableView<Task> tableTasks;
    @FXML private TableView<Milestone> tableMilestones;
    
    //buttons
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
    @FXML private TableColumn<Project, Integer> columnProjectID;
    @FXML private TableColumn<Project, String> columnProjectName;
    @FXML private TableColumn<Project, Double> columnProjectBudget; 
    @FXML private TableColumn<Project, java.sql.Date> columnStartDate; 
    @FXML private TableColumn<Project, java.sql.Date> columnEndDate;
    @FXML private TableColumn<Project, String> columnStatus;
    @FXML private TableColumn<Project, String> columnDescription;
    //worker table columns
    @FXML private TableColumn<Worker, Integer> columnWorkerID;
    @FXML private TableColumn<Worker, String> columnWorkerName;
    @FXML private TableColumn<Worker, String> columnContactInfo;
    @FXML private TableColumn<Worker, Worker.Availability> columnAvailability;
    @FXML private TableColumn<Worker, String> columnSpecialty;
    @FXML private TableColumn<Worker, Integer> columnAssignedProjectID;
    //task table columns
    @FXML private TableColumn<Task, Integer> columnTaskID;
    @FXML private TableColumn<Task, String> columnTaskName;
    @FXML private TableColumn<Task, java.sql.Date> columnDeadline;
    @FXML private TableColumn<Task, String> columnTaskStatus;
    @FXML private TableColumn<Task, Integer> columnProgressPercentage;
    @FXML private TableColumn<Task, Integer> columnTaskProjectID;
    
    //milestone table columns
    @FXML private TableColumn<Milestone, Integer> columnMilestoneID;
    @FXML private TableColumn<Milestone, String> columnMilestoneName;
    @FXML private TableColumn<Milestone, java.sql.Date> columnTargetDate;
    @FXML private TableColumn<Milestone, java.sql.Date> columnCompletionDate;
    @FXML private TableColumn<Milestone, String> columnMilestoneStatus;
    
    
    //initialize columns
    @FXML
    public void initialize() {
    	
    	//Initialize Project columns with property values
        projectDAO = new ProjectDAO();
        try {
        	columnProjectID.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        	columnProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        	columnProjectBudget.setCellValueFactory(new PropertyValueFactory<>("projectBudget")); 
        	columnStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        	columnEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        	columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        	columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Initialize Worker columns with property values
        try {
            columnWorkerID.setCellValueFactory(new PropertyValueFactory<>("workerID"));
            columnWorkerName.setCellValueFactory(new PropertyValueFactory<>("workerName"));
            columnContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
            columnAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
            columnSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
            columnAssignedProjectID.setCellValueFactory(new PropertyValueFactory<>("assignedProjectID"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Initialize Task columns with property values
        taskDAO = new TaskDAO();
        try {
            columnTaskID.setCellValueFactory(new PropertyValueFactory<>("taskID"));
            columnTaskName.setCellValueFactory(new PropertyValueFactory<>("taskName"));
            columnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
            columnTaskStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            columnProgressPercentage.setCellValueFactory(new PropertyValueFactory<>("progressPercentage"));
            columnTaskProjectID.setCellValueFactory(new PropertyValueFactory<>("taskProjectID"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Initialize Milestone columns with property values
        milestoneDAO = new MilestoneDAO();
        try {
        columnMilestoneID.setCellValueFactory(new PropertyValueFactory<>("milestoneID"));
        columnMilestoneName.setCellValueFactory(new PropertyValueFactory<>("milestoneName"));
        columnTargetDate.setCellValueFactory(new PropertyValueFactory<>("targetDate"));
        columnCompletionDate.setCellValueFactory(new PropertyValueFactory<>("completionDate"));
        columnMilestoneStatus.setCellValueFactory(new PropertyValueFactory<>("milestoneStatus"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //Load data at initialization
        refreshAllTables(); 
    }
    
    //-----------------------------------------------------------------function adding-------------------------------------------------------------------------------
    //handle project add function
    @FXML
    private void handleAddProject() {
        Dialog<Project> dialog = new Dialog<>();
        dialog.setTitle("Add New Project");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField projectNameField = new TextField();
        projectNameField.setPromptText("Project Name");
        TextField budgetField = new TextField();
        budgetField.setPromptText("Project Budget");
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Planned", "In Progress", "Completed", "Cancelled");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Project Description");

        grid.add(new Label("Project Name:"), 0, 0);
        grid.add(projectNameField, 1, 0);
        grid.add(new Label("Budget:"), 0, 4);
        grid.add(budgetField, 1, 4);
        grid.add(new Label("Start Date:"), 0, 1);
        grid.add(startDatePicker, 1, 1);
        grid.add(new Label("End Date:"), 0, 2);
        grid.add(endDatePicker, 1, 2);
        grid.add(new Label("Status:"), 0, 3);
        grid.add(statusComboBox, 1, 3);
        grid.add(new Label("Description:"), 0, 5);
        grid.add(descriptionArea, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a Project object when the "Add" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Project project = new Project();
                project.setProjectName(projectNameField.getText());
                project.setStartDate(java.sql.Date.valueOf(startDatePicker.getValue()));
                project.setEndDate(java.sql.Date.valueOf(endDatePicker.getValue()));
                String statusString = statusComboBox.getValue();
                Project.ProjectStatus statusEnum = Project.ProjectStatus.valueOf(statusString.replace(" ", "_").toUpperCase());
                project.setStatus(statusEnum);
                project.setDescription(descriptionArea.getText());
                try {
                    double budget = Double.parseDouble(budgetField.getText());
                    project.setProjectBudget(budget);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid budget format. Please enter a numeric value.");
                    return null;
                }

                return project;
            }
            return null;
        });
        // Handle the dialog result
        dialog.showAndWait().ifPresent(project -> {
            try {
                ProjectDAO projectDAO = new ProjectDAO(); // Create DAO instance
                projectDAO.addProject(project); // Save the project
                handleRefreshProjects(); // Refresh the table
                System.out.println("Project added successfully: " + project.getProjectName());
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
                showAlert("Error", "Failed to add the project. Please try again.");
            }
        });
    }
    public void addProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(project); // Hibernate generates the ID upon saving
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    //--------------------------------------------------------ending of add functions-------------------------------------------------------------------
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
            ProjectDAO projectDAO = new ProjectDAO();
            List<Project> projects = projectDAO.getAllProjects();

            if (projects != null) {
                ObservableList<Project> projectData = FXCollections.observableArrayList(projects);
                tableProjects.setItems(projectData);
            }
            for (Project project : projects) {
                System.out.println("Project ID: " + project.getProjectID());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the projects table.");
        }
    }
    //handle worker refresh
    @FXML
    private void handleRefreshWorkers() {
        try {
            WorkerDAO workerDAO = new WorkerDAO(); // Create WorkerDAO instance
            List<Worker> workers = workerDAO.getAllWorkers();

            if (workers == null || workers.isEmpty()) {
                showAlert("Error", "No workers found in the database.");
                return;
            }

            ObservableList<Worker> workerData = FXCollections.observableArrayList(workers);
            tableWorkers.setItems(workerData);

            System.out.println("Workers table refreshed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the workers table.");
        }
    }
   
    //handle task refresh
    @FXML
    private void handleRefreshTasks() {
        try {
            TaskDAO taskDAO = new TaskDAO(); // Create an instance of TaskDAO
            List<Task> tasks = taskDAO.getAllTasks();

            if (tasks == null || tasks.isEmpty()) {
                showAlert("Error", "No tasks found in the database.");
                return;
            }

            ObservableList<Task> taskData = FXCollections.observableArrayList(tasks);
            tableTasks.setItems(taskData);

            System.out.println("Tasks table refreshed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the tasks table.");
        }
    }
    //handle milestone refresh
    @FXML
    private void handleRefreshMilestones() {
        try {
            MilestoneDAO milestoneDAO = new MilestoneDAO(); // Create an instance of MilestoneDAO
            List<Milestone> milestones = milestoneDAO.getAllMilestones();

            if (milestones == null || milestones.isEmpty()) {
                showAlert("Error", "No milestones found in the database.");
                return;
            }

            ObservableList<Milestone> milestoneData = FXCollections.observableArrayList(milestones);
            tableMilestones.setItems(milestoneData);

            System.out.println("Milestones table refreshed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the milestones table.");
        }
    }
    
    private void refreshAllTables() {
    	handleRefreshProjects();
    	handleRefreshWorkers();
    	handleRefreshTasks();
    	handleRefreshMilestones();
        // Logic to populate tables with data from DAOs
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
}