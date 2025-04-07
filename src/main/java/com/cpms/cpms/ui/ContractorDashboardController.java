package com.cpms.cpms.ui;

import javafx.application.Platform;
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
import com.cpms.cpms.entities.Contractor;
import com.cpms.cpms.entities.User;
import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Task;
import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.dao.TaskDAO;

import java.sql.Date;
import java.util.List;


import org.hibernate.Transaction;
import org.hibernate.Session;

import com.cpms.cpms.config.CurrentUser;
import com.cpms.cpms.config.HibernateUtil;
import com.cpms.cpms.dao.ContractorDAO;
import com.cpms.cpms.dao.MilestoneDAO;

public class ContractorDashboardController {
	//tableView
    @FXML private TabPane tabPane;
    @FXML private TableView<Project> tableProjects;
    @FXML private TableView<Worker> tableWorkers;
    @FXML private TableView<Task> tableTasks;
    @FXML private TableView<Milestone> tableMilestones;
    
    //buttons
    @FXML private Button btnAddProject, btnUpdateProject, btnDeleteProject;
    @FXML private Button btnAddWorker, btnUpdateWorker, btnDeleteWorker;
    @FXML private Button btnAddTask, btnUpdateTask, btnDeleteTask;
    @FXML private Button btnAddMilestone, btnUpdateMilestone, btnDeleteMilestone;
    @FXML private Button btnExit;

    //object DAO
    private ProjectDAO projectDAO;
    private WorkerDAO workerDAO;
    private TaskDAO taskDAO;
    private MilestoneDAO milestoneDAO;
    
    @FXML private Label contractorNameLabel;
    @FXML private Label contractorEmailLabel;
    
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
    @FXML private TableColumn<Milestone, String> columnMProjectID;
   
    
    //contractor information
    @FXML
    public void initializeContractorInfo(Contractor contractor) {
        Platform.runLater(() -> {
            if (contractor != null) {
                contractorNameLabel.setText("Name: " + contractor.getContractorName());
                contractorEmailLabel.setText("Email: " + contractor.getContactInfo());
            } else {
                contractorNameLabel.setText("Name: Not Available");
                contractorEmailLabel.setText("Email: Not Available");
            }
        });
    }
    @FXML
    public void initializeData() {
        try {
            ContractorDAO contractorDAO = new ContractorDAO();
            User loggedInUser = CurrentUser.getLoggedInUser(); // Get the current user

          if (loggedInUser == null) {
                contractorNameLabel.setText("Name: Not Available");
                contractorEmailLabel.setText("Email: Not Available");
                System.out.println("[DEBUG][INIT_DATA] No logged-in user found!");
                return;
            }
            int testUserId = 5; // Replace this with a valid ID from your database
            Contractor contractor = contractorDAO.getContractorByUserId(testUserId);
            initializeContractorInfo(contractor);

            initializeContractorInfo(contractor);
        } catch (Exception e) {
            contractorNameLabel.setText("Name: Error");
            contractorEmailLabel.setText("Email: Error");
            System.out.println("[DEBUG][INIT_DATA] Error initializing contractor data: " + e.getMessage());
        }
    }

    
    
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
        workerDAO = new WorkerDAO();
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
            columnTaskStatus.setCellValueFactory(new PropertyValueFactory<>("taskStatus"));
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
        columnMProjectID.setCellValueFactory(new PropertyValueFactory<>("mProjectID"));
        
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
    
    //add function for worker
    @FXML
    private void handleAddWorker() {
        Dialog<Worker> dialog = new Dialog<>();
        dialog.setTitle("Add New Worker");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField workerNameField = new TextField();
        workerNameField.setPromptText("Worker Name");
        TextField contactInfoField = new TextField();
        contactInfoField.setPromptText("Contact Info");
        ComboBox<Worker.Availability> availabilityComboBox = new ComboBox<>();
        availabilityComboBox.getItems().addAll(Worker.Availability.AVAILABLE, Worker.Availability.UNAVAILABLE);
        TextField specialtyField = new TextField();
        specialtyField.setPromptText("Specialty");
        ComboBox<Integer> projectIDComboBox = new ComboBox<>(); // Use ComboBox for Project IDs

        grid.add(new Label("Worker Name:"), 0, 0);
        grid.add(workerNameField, 1, 0);
        grid.add(new Label("Contact Info:"), 0, 1);
        grid.add(contactInfoField, 1, 1);
        grid.add(new Label("Availability:"), 0, 2);
        grid.add(availabilityComboBox, 1, 2);
        grid.add(new Label("Specialty:"), 0, 3);
        grid.add(specialtyField, 1, 3);
        grid.add(new Label("Assigned Project ID:"), 0, 4);
        grid.add(projectIDComboBox, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available project IDs
        try {
            ProjectDAO projectDAO = new ProjectDAO(); // Assuming you have a DAO for projects
            List<Project> projects = projectDAO.getAllProjects(); // Get all projects from the database
            for (Project project : projects) {
                projectIDComboBox.getItems().add(project.getProjectID()); // Add project IDs to ComboBox
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load project IDs. Please try again.");
            return;
        }

        // Convert the result to a Worker object when the "Add" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Worker worker = new Worker();

                // Validate Worker Name
                if (workerNameField.getText() == null || workerNameField.getText().isEmpty()) {
                    showAlert("Error", "Worker Name cannot be empty.");
                    return null;
                }
                worker.setWorkerName(workerNameField.getText());

                // Validate Contact Info
                worker.setContactInfo(contactInfoField.getText());

                // Validate Availability
                if (availabilityComboBox.getValue() == null) {
                    showAlert("Error", "Worker availability must be selected.");
                    return null;
                }
                worker.setAvailability(availabilityComboBox.getValue());

                // Validate Specialty
                worker.setSpecialty(specialtyField.getText());

                // Validate and Set Project ID
                if (projectIDComboBox.getValue() == null) {
                    showAlert("Error", "Assigned Project ID must be selected.");
                    return null;
                }
                Project assignedProject = new Project();
                assignedProject.setProjectID(projectIDComboBox.getValue());
                worker.setAssignedProjectID(assignedProject);

                return worker;
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(worker -> {
            try {
                WorkerDAO workerDAO = new WorkerDAO(); // Create DAO instance
                workerDAO.addWorker(worker); // Save the worker
                handleRefreshWorkers(); // Refresh the table
                System.out.println("Worker added successfully: " + worker.getWorkerName());
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
                showAlert("Error", "Failed to add the worker. Please try again.");
            }
        });
    }

    // Save the worker to the database
    public void addWorker(Worker worker) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(worker); // Hibernate generates the ID upon saving
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    //add function for task
    @FXML
    private void handleAddTask() {
        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle("Add New Task");

        // Set the button types
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Task Name");
        DatePicker deadlinePicker = new DatePicker();
        ComboBox<Task.TaskStatus> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll(Task.TaskStatus.values()); // Populate enum values
        TextField progressPercentageField = new TextField();
        progressPercentageField.setPromptText("Progress Percentage");
        ComboBox<Integer> projectIDComboBox = new ComboBox<>();

        grid.add(new Label("Task Name:"), 0, 0);
        grid.add(taskNameField, 1, 0);
        grid.add(new Label("Deadline:"), 0, 1);
        grid.add(deadlinePicker, 1, 1);
        grid.add(new Label("Status:"), 0, 2);
        grid.add(statusComboBox, 1, 2);
        grid.add(new Label("Progress Percentage:"), 0, 3);
        grid.add(progressPercentageField, 1, 3);
        grid.add(new Label("Project ID:"), 0, 4);
        grid.add(projectIDComboBox, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available project IDs
        try {
            ProjectDAO projectDAO = new ProjectDAO();
            List<Project> projects = projectDAO.getAllProjects();
            for (Project project : projects) {
                projectIDComboBox.getItems().add(project.getProjectID());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load project IDs. Please try again.");
            return;
        }

        // Convert the result to a Task object when the "Add" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Task task = new Task();

                // Validate Task Name
                if (taskNameField.getText() == null || taskNameField.getText().isEmpty()) {
                    showAlert("Error", "Task Name cannot be empty.");
                    return null;
                }
                task.setTaskName(taskNameField.getText());

                // Validate Deadline
                if (deadlinePicker.getValue() == null) {
                    showAlert("Error", "Deadline must be selected.");
                    return null;
                }
                task.setDeadline(java.sql.Date.valueOf(deadlinePicker.getValue()));

                // Validate Status
                if (statusComboBox.getValue() == null) {
                    showAlert("Error", "Task Status must be selected.");
                    return null;
                }
                task.setTaskStatus(statusComboBox.getValue());

                // Validate Progress Percentage
                try {
                    int progressPercentage = Integer.parseInt(progressPercentageField.getText());
                    if (progressPercentage < 0 || progressPercentage > 100) {
                        showAlert("Error", "Progress Percentage must be between 0 and 100.");
                        return null;
                    }
                    task.setProgressPercentage(progressPercentage);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Progress Percentage. Please enter a numeric value.");
                    return null;
                }

                // Validate and Set Project ID
                if (projectIDComboBox.getValue() == null) {
                    showAlert("Error", "Project ID must be selected.");
                    return null;
                }
                Project assignedProject = new Project();
                assignedProject.setProjectID(projectIDComboBox.getValue());
                task.setTaskProjectID(assignedProject);

                return task;
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(task -> {
            try {
                TaskDAO taskDAO = new TaskDAO(); // Create DAO instance
                taskDAO.addTask(task); // Save the task
                handleRefreshTasks(); // Refresh the table
                System.out.println("Task added successfully: " + task.getTaskName());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to add the task. Cause: " + e.getMessage());
            }
        });
    }
    
    //handle add function for milestone
    @FXML
    private void handleAddMilestone() {
        Dialog<Milestone> dialog = new Dialog<>();
        dialog.setTitle("Add New Milestone");

        // Set the dialog buttons
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField milestoneNameField = new TextField();
        milestoneNameField.setPromptText("Milestone Name");
        DatePicker targetDatePicker = new DatePicker();
        DatePicker completionDatePicker = new DatePicker();

        // ComboBox for MilestoneStatus (Dropdown for PENDING and COMPLETED)
        ComboBox<String> milestoneStatusComboBox = new ComboBox<>();
        milestoneStatusComboBox.getItems().addAll("PENDING", "COMPLETED");

        // ComboBox for Project IDs (Foreign Key)
        ComboBox<Integer> projectIDComboBox = new ComboBox<>();

        grid.add(new Label("Milestone Name:"), 0, 0);
        grid.add(milestoneNameField, 1, 0);
        grid.add(new Label("Target Date:"), 0, 1);
        grid.add(targetDatePicker, 1, 1);
        grid.add(new Label("Completion Date:"), 0, 2);
        grid.add(completionDatePicker, 1, 2);
        grid.add(new Label("Milestone Status:"), 0, 3);
        grid.add(milestoneStatusComboBox, 1, 3);
        grid.add(new Label("Assigned Project ID:"), 0, 4);
        grid.add(projectIDComboBox, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Populate Project ID ComboBox (Fetching from Database)
        try {
            ProjectDAO projectDAO = new ProjectDAO(); // Assuming a DAO exists for projects
            List<Project> projects = projectDAO.getAllProjects(); // Retrieve all projects
            for (Project project : projects) {
                projectIDComboBox.getItems().add(project.getProjectID()); // Add project IDs to ComboBox
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load project IDs. Please try again.");
            return;
        }
        // Convert user input into a Milestone object when the "Add" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Milestone milestone = new Milestone();

                // Validate Milestone Name
                if (milestoneNameField.getText() == null || milestoneNameField.getText().isEmpty()) {
                    showAlert("Error", "Milestone Name cannot be empty.");
                    return null;
                }
                milestone.setMilestoneName(milestoneNameField.getText());

                // Validate Target Date
                if (targetDatePicker.getValue() == null) {
                    showAlert("Error", "Target Date must be selected.");
                    return null;
                }
                milestone.setTargetDate(java.sql.Date.valueOf(targetDatePicker.getValue()));

                // Optional Completion Date
                if (completionDatePicker.getValue() != null) {
                    milestone.setCompletionDate(java.sql.Date.valueOf(completionDatePicker.getValue()));
                }

                // Validate Milestone Status
                if (milestoneStatusComboBox.getValue() == null) {
                    showAlert("Error", "Milestone Status must be selected.");
                    return null;
                }
                milestone.setMilestoneStatus(milestoneStatusComboBox.getValue());

                // Validate Project ID
                if (projectIDComboBox.getValue() == null) {
                    showAlert("Error", "Assigned Project ID must be selected.");
                    return null;
                }
                Project assignedProject = new Project();
                assignedProject.setProjectID(projectIDComboBox.getValue());
                milestone.setMProjectID(assignedProject);

                return milestone;
            }
            return null;
        });
        // Handle dialog result
        dialog.showAndWait().ifPresent(milestone -> {
            try {
                MilestoneDAO milestoneDAO = new MilestoneDAO(); // Create DAO instance
                milestoneDAO.addMilestone(milestone); // Save milestone to the database
                handleRefreshMilestones(); // Refresh the milestones TableView
                System.out.println("Milestone added successfully: " + milestone.getMilestoneName());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to add the milestone. Please try again.");
            }
        });
    } 
    //--------------------------------------------------------ending of add functions-------------------------------------------------------------------
    
    //-------------------------------------------------------delete function----------------------------------------------------------------------------
    //project delete function
    @FXML
    private void handleDeleteProject() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Project");

        // Set the dialog buttons
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the ComboBox for selecting ProjectID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> projectIDComboBox = new ComboBox<>();
        grid.add(new Label("Select Project ID:"), 0, 0);
        grid.add(projectIDComboBox, 1, 0);
        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Project IDs
        try {
            ProjectDAO projectDAO = new ProjectDAO(); // Assuming a DAO exists for projects
            List<Project> projects = projectDAO.getAllProjects(); // Fetch all projects from the database
            for (Project project : projects) {
                projectIDComboBox.getItems().add(project.getProjectID()); // Add Project IDs to the dropdown
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load project IDs. Please try again.");
            return;
        }
        // Convert the result to the selected ProjectID
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                return projectIDComboBox.getValue(); // Return the selected ProjectID
            }
            return null;
        });
        // Handle the dialog result
        dialog.showAndWait().ifPresent(selectedProjectID -> {
            if (selectedProjectID == null) {
                showAlert("Error", "No Project ID selected.");
                return;
            }
            try {
                ProjectDAO projectDAO = new ProjectDAO(); // Create DAO instance
                projectDAO.deleteProject(selectedProjectID); // Delete the project with the selected ID
                handleRefreshProjects(); // Refresh the projects TableView
                System.out.println("Project deleted successfully: ID = " + selectedProjectID);
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to delete the project. Please try again.");
            }
        });
    }
    
    //delete function for worker
    @FXML
    private void handleDeleteWorker() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Worker");

        // Set the dialog buttons
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the ComboBox for selecting WorkerID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> workerIDComboBox = new ComboBox<>();
        grid.add(new Label("Select Worker ID:"), 0, 0);
        grid.add(workerIDComboBox, 1, 0);
        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Worker IDs
        try {
            WorkerDAO workerDAO = new WorkerDAO(); // Assuming a DAO exists for workers
            List<Worker> workers = workerDAO.getAllWorkers(); // Fetch all workers from the database
            for (Worker worker : workers) {
                workerIDComboBox.getItems().add(worker.getWorkerID()); // Add Worker IDs to the dropdown
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load worker IDs. Please try again.");
            return;
        }

        // Convert the result to the selected WorkerID
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                return workerIDComboBox.getValue(); // Return the selected WorkerID
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(selectedWorkerID -> {
            if (selectedWorkerID == null) {
                showAlert("Error", "No Worker ID selected.");
                return;
            }

            try {
                WorkerDAO workerDAO = new WorkerDAO(); // Create DAO instance
                workerDAO.deleteWorker(selectedWorkerID); // Delete the worker with the selected ID
                handleRefreshWorkers(); // Refresh the workers TableView
                System.out.println("Worker deleted successfully: ID = " + selectedWorkerID);
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to delete the worker. Please try again.");
            }
        });
    }

    //delete function for task
    @FXML
    private void handleDeleteTask() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Task");

        // Set the dialog buttons
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the ComboBox for selecting TaskID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> taskIDComboBox = new ComboBox<>();
        grid.add(new Label("Select Task ID:"), 0, 0);
        grid.add(taskIDComboBox, 1, 0);
        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Task IDs
        try {
            TaskDAO taskDAO = new TaskDAO(); // Assuming a DAO exists for tasks
            List<Task> tasks = taskDAO.getAllTasks(); // Fetch all tasks from the database
            for (Task task : tasks) {
                taskIDComboBox.getItems().add(task.getTaskID()); // Add Task IDs to the dropdown
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load task IDs. Please try again.");
            return;
        }

        // Convert the result to the selected TaskID
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                return taskIDComboBox.getValue(); // Return the selected TaskID
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(selectedTaskID -> {
            if (selectedTaskID == null) {
                showAlert("Error", "No Task ID selected.");
                return;
            }

            try {
                TaskDAO taskDAO = new TaskDAO(); // Create DAO instance
                taskDAO.deleteTask(selectedTaskID); // Delete the task with the selected ID
                handleRefreshTasks(); // Refresh the tasks TableView
                System.out.println("Task deleted successfully: ID = " + selectedTaskID);
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to delete the task. Please try again.");
            }
        });
    }
    //delete function for milestone
    @FXML
    private void handleDeleteMilestone() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Milestone");

        // Set the dialog buttons
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the ComboBox for selecting MilestoneID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> milestoneIDComboBox = new ComboBox<>();
        grid.add(new Label("Select Milestone ID:"), 0, 0);
        grid.add(milestoneIDComboBox, 1, 0);
        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Milestone IDs
        try {
            MilestoneDAO milestoneDAO = new MilestoneDAO(); // Assuming a DAO exists for milestones
            List<Milestone> milestones = milestoneDAO.getAllMilestones(); // Fetch all milestones from the database
            for (Milestone milestone : milestones) {
                milestoneIDComboBox.getItems().add(milestone.getMilestoneID()); // Add Milestone IDs to the dropdown
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load milestone IDs. Please try again.");
            return;
        }

        // Convert the result to the selected MilestoneID
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                return milestoneIDComboBox.getValue(); // Return the selected MilestoneID
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(selectedMilestoneID -> {
            if (selectedMilestoneID == null) {
                showAlert("Error", "No Milestone ID selected.");
                return;
            }

            try {
                MilestoneDAO milestoneDAO = new MilestoneDAO(); // Create DAO instance
                milestoneDAO.deleteMilestone(selectedMilestoneID); // Delete the milestone with the selected ID
                handleRefreshMilestones(); // Refresh the milestones TableView
                System.out.println("Milestone deleted successfully: ID = " + selectedMilestoneID);
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to delete the milestone. Please try again.");
            }
        });
    }

    //-------------------------------------------------------ending of delete function------------------------------------------------------------------
    
    //--------------------------------------------------------update functions-------------------------------------------------------------------------
    @FXML
    private void handleUpdateProject() {
        Dialog<Project> dialog = new Dialog<>();
        dialog.setTitle("Update Project");

        // Set the dialog buttons
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> projectIDComboBox = new ComboBox<>(); // Dropdown for selecting ProjectID
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

        grid.add(new Label("Select Project ID:"), 0, 0);
        grid.add(projectIDComboBox, 1, 0);
        grid.add(new Label("Project Name:"), 0, 1);
        grid.add(projectNameField, 1, 1);
        grid.add(new Label("Budget:"), 0, 2);
        grid.add(budgetField, 1, 2);
        grid.add(new Label("Start Date:"), 0, 3);
        grid.add(startDatePicker, 1, 3);
        grid.add(new Label("End Date:"), 0, 4);
        grid.add(endDatePicker, 1, 4);
        grid.add(new Label("Status:"), 0, 5);
        grid.add(statusComboBox, 1, 5);
        grid.add(new Label("Description:"), 0, 6);
        grid.add(descriptionArea, 1, 6);

        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Project IDs
        try {
            ProjectDAO projectDAO = new ProjectDAO(); // Assuming a DAO exists for projects
            List<Project> projects = projectDAO.getAllProjects(); // Fetch all projects
            for (Project project : projects) {
                projectIDComboBox.getItems().add(project.getProjectID()); // Add Project IDs to ComboBox
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load project IDs. Please try again.");
            return;
        }

        // Pre-fill the form with the selected project details when a ProjectID is chosen
        projectIDComboBox.setOnAction(event -> {
            try {
                int selectedProjectID = projectIDComboBox.getValue();
                ProjectDAO projectDAO = new ProjectDAO();
                Project selectedProject = projectDAO.getProject(selectedProjectID);
                if (selectedProject != null) {
                    projectNameField.setText(selectedProject.getProjectName());
                    budgetField.setText(String.valueOf(selectedProject.getProjectBudget()));
                    startDatePicker.setValue(selectedProject.getStartDate().toLocalDate());
                    endDatePicker.setValue(selectedProject.getEndDate().toLocalDate());
                    statusComboBox.setValue(selectedProject.getStatus().toString().replace("_", " "));
                    descriptionArea.setText(selectedProject.getDescription());
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load project details.");
            }
        });

        // Convert the user input into an updated Project object when the "Update" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                Project project = new Project();
                int selectedProjectID = projectIDComboBox.getValue();
                if (selectedProjectID == 0) {
                    showAlert("Error", "No Project ID selected.");
                    return null;
                }

                project.setProjectID(selectedProjectID);

                // Validate Project Name
                project.setProjectName(projectNameField.getText());

                // Validate Budget
                try {
                    double budget = Double.parseDouble(budgetField.getText());
                    project.setProjectBudget(budget);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid budget format. Please enter a numeric value.");
                    return null;
                }

                // Validate Dates
                if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
                    showAlert("Error", "Start Date and End Date must be selected.");
                    return null;
                }
                project.setStartDate(java.sql.Date.valueOf(startDatePicker.getValue()));
                project.setEndDate(java.sql.Date.valueOf(endDatePicker.getValue()));

                // Validate and Set Status
                String statusString = statusComboBox.getValue();
                Project.ProjectStatus statusEnum = Project.ProjectStatus.valueOf(statusString.replace(" ", "_").toUpperCase());
                project.setStatus(statusEnum);

                // Validate Description
                project.setDescription(descriptionArea.getText());

                return project;
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(updatedProject -> {
            try {
                ProjectDAO projectDAO = new ProjectDAO();
                projectDAO.updateProject(updatedProject); // Update the project in the database
                handleRefreshProjects(); // Refresh the projects TableView
                System.out.println("Project updated successfully: " + updatedProject.getProjectName());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to update the project. Please try again.");
            }
        });
    }
    
    //update function for worker
    @FXML
    private void handleUpdateWorker() {
        Dialog<Worker> dialog = new Dialog<>();
        dialog.setTitle("Update Worker");

        // Set the dialog buttons
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> workerIDComboBox = new ComboBox<>(); // Dropdown for selecting WorkerID
        TextField workerNameField = new TextField();
        workerNameField.setPromptText("Worker Name");
        TextField contactInfoField = new TextField();
        contactInfoField.setPromptText("Contact Info");
        ComboBox<String> availabilityComboBox = new ComboBox<>(); // Dropdown for Availability
        availabilityComboBox.getItems().addAll("AVAILABLE", "UNAVAILABLE");
        TextField specialtyField = new TextField();
        specialtyField.setPromptText("Specialty");

        grid.add(new Label("Select Worker ID:"), 0, 0);
        grid.add(workerIDComboBox, 1, 0);
        grid.add(new Label("Worker Name:"), 0, 1);
        grid.add(workerNameField, 1, 1);
        grid.add(new Label("Contact Info:"), 0, 2);
        grid.add(contactInfoField, 1, 2);
        grid.add(new Label("Availability:"), 0, 3);
        grid.add(availabilityComboBox, 1, 3);
        grid.add(new Label("Specialty:"), 0, 4);
        grid.add(specialtyField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Worker IDs
        try {
            WorkerDAO workerDAO = new WorkerDAO(); // Assuming a DAO exists for workers
            List<Worker> workers = workerDAO.getAllWorkers(); // Fetch all workers
            for (Worker worker : workers) {
                workerIDComboBox.getItems().add(worker.getWorkerID()); // Add Worker IDs to ComboBox
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load worker IDs. Please try again.");
            return;
        }

        // Pre-fill the form with the selected worker's details when a Worker ID is chosen
        workerIDComboBox.setOnAction(event -> {
            try {
                int selectedWorkerID = workerIDComboBox.getValue();
                WorkerDAO workerDAO = new WorkerDAO();
                Worker selectedWorker = workerDAO.getWorker(selectedWorkerID);
                if (selectedWorker != null) {
                    workerNameField.setText(selectedWorker.getWorkerName());
                    contactInfoField.setText(selectedWorker.getContactInfo());
                    availabilityComboBox.setValue(selectedWorker.getAvailability().toString());
                    specialtyField.setText(selectedWorker.getSpecialty());
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load worker details.");
            }
        });

        // Convert the user input into an updated Worker object when the "Update" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                Worker worker = new Worker();
                worker.setWorkerID(workerIDComboBox.getValue());
                worker.setWorkerName(workerNameField.getText());
                worker.setContactInfo(contactInfoField.getText());
                worker.setAvailability(Worker.Availability.valueOf(availabilityComboBox.getValue()));
                worker.setSpecialty(specialtyField.getText());
                return worker;
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(updatedWorker -> {
            try {
                WorkerDAO workerDAO = new WorkerDAO();
                workerDAO.updateWorker(updatedWorker); // Save the changes to the database
                handleRefreshWorkers(); // Refresh the TableView
                System.out.println("Worker updated successfully: ID = " + updatedWorker.getWorkerID());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to update the worker. Please try again.");
            }
        });
    }
    
    //update function for task
    @FXML
    private void handleUpdateTask() {
        Dialog<Task> dialog = new Dialog<>();
        dialog.setTitle("Update Task");

        // Set the dialog buttons
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> taskIDComboBox = new ComboBox<>(); // Dropdown for selecting TaskID
        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Task Name");
        DatePicker deadlinePicker = new DatePicker();
        ComboBox<String> statusComboBox = new ComboBox<>(); // Dropdown for Task Status
        statusComboBox.getItems().addAll("PENDING", "IN_PROGRESS", "COMPLETED");
        TextField progressPercentageField = new TextField();
        progressPercentageField.setPromptText("Progress Percentage");
        ComboBox<Integer> projectIDComboBox = new ComboBox<>(); // Dropdown for selecting ProjectID

        grid.add(new Label("Select Task ID:"), 0, 0);
        grid.add(taskIDComboBox, 1, 0);
        grid.add(new Label("Task Name:"), 0, 1);
        grid.add(taskNameField, 1, 1);
        grid.add(new Label("Deadline:"), 0, 2);
        grid.add(deadlinePicker, 1, 2);
        grid.add(new Label("Status:"), 0, 3);
        grid.add(statusComboBox, 1, 3);
        grid.add(new Label("Progress Percentage:"), 0, 4);
        grid.add(progressPercentageField, 1, 4);
        grid.add(new Label("Assigned Project ID:"), 0, 5);
        grid.add(projectIDComboBox, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Task IDs and Project IDs
        try {
            TaskDAO taskDAO = new TaskDAO(); // Assuming a DAO exists for tasks
            List<Task> tasks = taskDAO.getAllTasks(); // Fetch all tasks
            for (Task task : tasks) {
                taskIDComboBox.getItems().add(task.getTaskID()); // Add Task IDs to ComboBox
            }

            ProjectDAO projectDAO = new ProjectDAO(); // Assuming a DAO exists for projects
            List<Project> projects = projectDAO.getAllProjects(); // Fetch all projects
            for (Project project : projects) {
                projectIDComboBox.getItems().add(project.getProjectID()); // Add Project IDs to ComboBox
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load task or project data. Please try again.");
            return;
        }

        // Pre-fill the form with the selected task's details
        taskIDComboBox.setOnAction(event -> {
            try {
                int selectedTaskID = taskIDComboBox.getValue();
                TaskDAO taskDAO = new TaskDAO();
                Task selectedTask = taskDAO.getTask(selectedTaskID);
                if (selectedTask != null) {
                    taskNameField.setText(selectedTask.getTaskName());
                    deadlinePicker.setValue(selectedTask.getDeadline().toLocalDate());
                    statusComboBox.setValue(selectedTask.getTaskStatus().toString());
                    progressPercentageField.setText(String.valueOf(selectedTask.getProgressPercentage()));
                    projectIDComboBox.setValue(selectedTask.getTaskProjectID());
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load task details.");
            }
        });

        // Convert the user input into an updated Task object when the "Update" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                Task task = new Task();
                int selectedTaskID = taskIDComboBox.getValue();
                if (selectedTaskID == 0) {
                    showAlert("Error", "No Task ID selected.");
                    return null;
                }

                task.setTaskID(selectedTaskID);

                // Validate Task Name
                if (taskNameField.getText() == null || taskNameField.getText().isEmpty()) {
                    showAlert("Error", "Task Name cannot be empty.");
                    return null;
                }
                task.setTaskName(taskNameField.getText());

                // Validate Deadline
                if (deadlinePicker.getValue() == null) {
                    showAlert("Error", "Deadline must be selected.");
                    return null;
                }
                task.setDeadline(java.sql.Date.valueOf(deadlinePicker.getValue()));

                // Validate Task Status
                if (statusComboBox.getValue() == null || statusComboBox.getValue().isEmpty()) {
                    showAlert("Error", "Task Status must be selected.");
                    return null;
                }
                task.setTaskStatus(Task.TaskStatus.valueOf(statusComboBox.getValue()));

                // Validate Progress Percentage
                try {
                    int progressPercentage = Integer.parseInt(progressPercentageField.getText());
                    task.setProgressPercentage(progressPercentage);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Progress Percentage format. Please enter a numeric value.");
                    return null;
                }

                // Validate and Set Assigned Project ID
                if (projectIDComboBox.getValue() == null) {
                    showAlert("Error", "Assigned Project ID must be selected.");
                    return null;
                }
                Project assignedProject = new Project();
                assignedProject.setProjectID(projectIDComboBox.getValue());
                task.setTaskProjectID(assignedProject);

                return task;
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(updatedTask -> {
            try {
                TaskDAO taskDAO = new TaskDAO();
                taskDAO.updateTask(updatedTask); // Update the task in the database
                handleRefreshTasks(); // Refresh the tasks TableView
                System.out.println("Task updated successfully: ID = " + updatedTask.getTaskID());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to update the task. Please try again.");
            }
        });
    }

    //update function for milestone
    @FXML
    private void handleUpdateMilestone() {
        Dialog<Milestone> dialog = new Dialog<>();
        dialog.setTitle("Update Milestone");

        // Set the dialog buttons
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Integer> milestoneIDComboBox = new ComboBox<>(); // Dropdown for selecting Milestone ID
        TextField milestoneNameField = new TextField();
        milestoneNameField.setPromptText("Milestone Name");
        DatePicker targetDatePicker = new DatePicker();
        DatePicker completionDatePicker = new DatePicker();
        ComboBox<String> milestoneStatusComboBox = new ComboBox<>();
        milestoneStatusComboBox.getItems().addAll("PENDING", "COMPLETED"); // Only allow PENDING or COMPLETED
        ComboBox<Integer> projectIDComboBox = new ComboBox<>();

        grid.add(new Label("Select Milestone ID:"), 0, 0);
        grid.add(milestoneIDComboBox, 1, 0);
        grid.add(new Label("Milestone Name:"), 0, 1);
        grid.add(milestoneNameField, 1, 1);
        grid.add(new Label("Target Date:"), 0, 2);
        grid.add(targetDatePicker, 1, 2);
        grid.add(new Label("Completion Date:"), 0, 3);
        grid.add(completionDatePicker, 1, 3);
        grid.add(new Label("Milestone Status:"), 0, 4);
        grid.add(milestoneStatusComboBox, 1, 4);
        grid.add(new Label("Assigned Project ID:"), 0, 5);
        grid.add(projectIDComboBox, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // Populate the ComboBox with available Milestone IDs
        try {
            MilestoneDAO milestoneDAO = new MilestoneDAO(); // Assuming a DAO exists for milestones
            List<Milestone> milestones = milestoneDAO.getAllMilestones(); // Fetch all milestones
            for (Milestone milestone : milestones) {
                milestoneIDComboBox.getItems().add(milestone.getMilestoneID()); // Add Milestone IDs to ComboBox
            }

            ProjectDAO projectDAO = new ProjectDAO(); // Assuming a DAO exists for projects
            List<Project> projects = projectDAO.getAllProjects(); // Fetch all projects
            for (Project project : projects) {
                projectIDComboBox.getItems().add(project.getProjectID()); // Add Project IDs to ComboBox
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load milestone or project data. Please try again.");
            return;
        }

        // Pre-fill the form with the selected milestone details when a Milestone ID is chosen
        milestoneIDComboBox.setOnAction(event -> {
            try {
                int selectedMilestoneID = milestoneIDComboBox.getValue();
                MilestoneDAO milestoneDAO = new MilestoneDAO();
                Milestone selectedMilestone = milestoneDAO.getMilestone(selectedMilestoneID);
                if (selectedMilestone != null) {
                    milestoneNameField.setText(selectedMilestone.getMilestoneName());
                    targetDatePicker.setValue(selectedMilestone.getTargetDate().toLocalDate());
                    if (selectedMilestone.getCompletionDate() != null) {
                        completionDatePicker.setValue(selectedMilestone.getCompletionDate().toLocalDate());
                    }
                    milestoneStatusComboBox.setValue(selectedMilestone.getMilestoneStatus());
                    projectIDComboBox.setValue(selectedMilestone.getMProjectID());
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load milestone details.");
            }
        });

        // Convert the user input into an updated Milestone object when the "Update" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                Milestone milestone = new Milestone();
                int selectedMilestoneID = milestoneIDComboBox.getValue();
                if (selectedMilestoneID == 0) {
                    showAlert("Error", "No Milestone ID selected.");
                    return null;
                }

                milestone.setMilestoneID(selectedMilestoneID);

                // Validate and set Milestone Name
                if (milestoneNameField.getText() == null || milestoneNameField.getText().isEmpty()) {
                    showAlert("Error", "Milestone Name cannot be empty.");
                    return null;
                }
                milestone.setMilestoneName(milestoneNameField.getText());

                // Validate and set Target Date
                if (targetDatePicker.getValue() == null) {
                    showAlert("Error", "Target Date must be selected.");
                    return null;
                }
                milestone.setTargetDate(java.sql.Date.valueOf(targetDatePicker.getValue()));

                // Validate and set Completion Date (optional)
                if (completionDatePicker.getValue() != null) {
                    milestone.setCompletionDate(java.sql.Date.valueOf(completionDatePicker.getValue()));
                }

                // Validate and set Milestone Status
                if (milestoneStatusComboBox.getValue() == null) {
                    showAlert("Error", "Milestone Status must be selected.");
                    return null;
                }
                milestone.setMilestoneStatus(milestoneStatusComboBox.getValue());

                // Validate and set Assigned Project ID
                if (projectIDComboBox.getValue() == null) {
                    showAlert("Error", "Assigned Project ID must be selected.");
                    return null;
                }
                Project assignedProject = new Project();
                assignedProject.setProjectID(projectIDComboBox.getValue());
                milestone.setMProjectID(assignedProject);

                return milestone;
            }
            return null;
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(updatedMilestone -> {
            try {
                MilestoneDAO milestoneDAO = new MilestoneDAO();
                milestoneDAO.updateMilestone(updatedMilestone); // Update the milestone in the database
                handleRefreshMilestones(); // Refresh the milestones TableView
                System.out.println("Milestone updated successfully: ID = " + updatedMilestone.getMilestoneID());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to update the milestone. Please try again.");
            }
        });
    }
    
    //-------------------------------------------------------ending of update function------------------------------------------------------------------
    
    //---------------------------------------------------------Refresh Handling-------------------------------------------------------------------------
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
            WorkerDAO workerDAO = new WorkerDAO(); //Instance of WorkerDAO
            List<Worker> workers = workerDAO.getAllWorkers(); //Fetch all workers

            if (workers != null) { 
                ObservableList<Worker> workerData = FXCollections.observableArrayList(workers); //Convert to ObservableList
                tableWorkers.setItems(workerData); //Update the TableView with the workers
            }

            for (Worker worker : workers) { 
                System.out.println("Worker ID: " + worker.getWorkerID() + ", Worker Name: " + worker.getWorkerName()); //Log worker details
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            showAlert("Error", "Failed to refresh the workers table."); //Show error message
        }
    }
   
    //handleR task refresh
    @FXML
    private void handleRefreshTasks() {
        try {
            TaskDAO taskDAO = new TaskDAO(); // Create an instance of TaskDAO
            List<Task> tasks = taskDAO.getAllTasks();
            if (tasks == null || tasks.isEmpty()) {
                tableTasks.setItems(FXCollections.observableArrayList()); // Clear the table
                showAlert("Info", "No tasks found.");
                System.out.println("No tasks found in the database.");
                return;
            }
            ObservableList<Task> taskData = FXCollections.observableArrayList(tasks);
            tableTasks.setItems(taskData);

            System.out.println("Tasks table refreshed successfully. Total tasks: " + tasks.size());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the tasks table. Cause: " + e.getMessage());
        }
    }
    
    //handle milestone refresh
    @FXML
    private void handleRefreshMilestones() {
        try {
            MilestoneDAO milestoneDAO = new MilestoneDAO(); // Create an instance of MilestoneDAO
            List<Milestone> milestones = milestoneDAO.getAllMilestones();

            if (milestones == null || milestones.isEmpty()) {
                tableMilestones.setItems(FXCollections.observableArrayList()); // Clear the table
                showAlert("Info", "No milestones found."); // Inform the user
                System.out.println("No milestones found in the database."); // Log the result
                return;
            }

            ObservableList<Milestone> milestoneData = FXCollections.observableArrayList(milestones);
            tableMilestones.setItems(milestoneData); // Populate the TableView

            System.out.println("Milestones table refreshed successfully. Total milestones: " + milestones.size()); // Log success
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the milestones table. Cause: " + e.getMessage()); // Provide detailed error
        }
    }
    
    private void refreshAllTables() {
    	handleRefreshProjects();
    	handleRefreshWorkers();
    	handleRefreshTasks();
    	handleRefreshMilestones();
        // Logic to populate tables with data from DAOs
    }
    //--------------------------------------------------------handle refresh end----------------------------------------------------------------------------
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
            stage.setMaximized(true);
            stage.setWidth(1700); // Example of overriding width
            stage.setHeight(900); // Example of overriding height
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