package com.cpms.cpms.ui;

import com.cpms.cpms.entities.Project;
import com.cpms.cpms.config.HibernateUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProjectDashboardController {

    @FXML
    private TableView<Project> projectsTable;

    @FXML
    private TableColumn<Project, String> projectNameColumn;

    @FXML
    private TableColumn<Project, Double> projectBudgetColumn;

    @FXML
    private TableColumn<Project, String> projectStatusColumn;

    @FXML
    private void initialize() {
        // Initialize the columns for the TableView
        initializeTableColumns();
        // Load the projects from the database and display in the TableView
        loadProjects();
    }

    private void initializeTableColumns() {
        // Bind project name to projectNameColumn
        projectNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProjectName()));

        // Bind project budget to projectBudgetColumn
        projectBudgetColumn.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getProjectBudget()).asObject());

        // Bind project status to projectStatusColumn
        projectStatusColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatus().toString()));
    }

    @FXML
    private void handleAddProject() {
        // Open a form to add a new project
        System.out.println("Add project button clicked.");
    }

    @FXML
    private void handleViewProject() {
        // View selected project details
        Project selectedProject = projectsTable.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            // Display details of the selected project
            System.out.println("View project: " + selectedProject.getProjectName());
        }
    }

    private void loadProjects() {
        // Fetch projects from the database and load them into the TableView
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession(); // Use openSession instead of getCurrentSession

        try {
            session.beginTransaction();
            // Query all projects from the database
            List<Project> projects = session.createQuery("from Project", Project.class).getResultList();
            // Set the projects list to the TableView
            projectsTable.getItems().setAll(projects);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback(); // Rollback transaction on failure
        } finally {
            session.close();
        }
    }
}