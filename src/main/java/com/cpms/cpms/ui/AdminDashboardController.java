package com.cpms.cpms.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleObjectProperty;

import com.cpms.cpms.dao.UserDAO;
import com.cpms.cpms.dao.ClientDAO;
import com.cpms.cpms.dao.ProjectDAO;
import com.cpms.cpms.dao.ContractorDAO;
import com.cpms.cpms.dao.InvoiceDAO;
import com.cpms.cpms.dao.FinancialTransactionDAO;


import com.cpms.cpms.entities.Client;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Contractor;
import com.cpms.cpms.entities.User;
import com.cpms.cpms.entities.Invoice; 
import com.cpms.cpms.entities.FinancialTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import  org.hibernate.Transaction;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class AdminDashboardController {

    // TableView for Projects and Clients
    @FXML private TableView<Project> tableProjects;
    @FXML private TableView<Client> tableClients;
    @FXML private TableView<Contractor> tableContractors; 
    @FXML private TableView<Invoice> tableInvoices; 
    @FXML private TableView<FinancialTransaction> tableFinancialtransactions; 


    //----------------------------------------------------------------------------------------------------------------------------
    // Buttons for Projects
    @FXML private Button btnAddProject, btnUpdateProject, btnDeleteProject;

    // Buttons for Clients
    @FXML private Button btnAddClient, btnUpdateClient, btnDeleteClient;

    // Buttons for Contractors
    @FXML private Button btnAddContractor, btnUpdateContractor, btnDeleteContractor; 
    
    // Buttons for Invoices
    @FXML private Button btnAddInvoice, btnUpdateInvoice, btnDeleteInvoice; 

    // Buttons for transactions
    @FXML private Button btnAddTransaction, btnUpdateTransaction, btnDeleteTransaction; 

    // Buttons for exit
    @FXML private Button btnExit;

    //----------------------------------------------------------------------------------------------------------------------------
    // Columns for Project Table
    @FXML private TableColumn<Project, Integer> columnProjectID;
    @FXML private TableColumn<Project, String> columnProjectName;
    @FXML private TableColumn<Project, Double> columnProjectBudget;
    @FXML private TableColumn<Project, java.sql.Date> columnStartDate;
    @FXML private TableColumn<Project, java.sql.Date> columnEndDate;
    @FXML private TableColumn<Project, String> columnStatus;
    @FXML private TableColumn<Project, String> columnDescription;

    // Columns for Client Table
    @FXML private TableColumn<Client, Integer> columnClientID;
    @FXML private TableColumn<Client, String> columnClientName;
    @FXML private TableColumn<Client, String> columnClientContactInfo;
    @FXML private TableColumn<Client, Integer> columnClientUserID;

    // Columns for Contractor Table
    @FXML private TableColumn<Contractor, Integer> columnContractorID;
    @FXML private TableColumn<Contractor, String> columnContractorName;
    @FXML private TableColumn<Contractor, String> columnContactInfo;
    @FXML private TableColumn<Contractor, Integer> columnContractorUserID;

    
    // Columns for Invoice Table
    @FXML private TableColumn<Invoice, Integer> columnInvoiceID;
    @FXML private TableColumn<Invoice, Integer> columnInvoiceProjectID;
    @FXML private TableColumn<Invoice, Double> columnAmount;
    @FXML private TableColumn<Invoice, LocalDateTime> columnDate;
    @FXML private TableColumn<Invoice, String> columnPaymentMethod;
    @FXML private TableColumn<Invoice, String> columnPaymentDetails;
    @FXML private TableColumn<Invoice, Double> columnOutstandingBalance;
    
    // Columns for transactions Table
    @FXML private TableColumn<FinancialTransaction, Integer> columnTransactionID;
    @FXML private TableColumn<FinancialTransaction, Integer> columnTransactionProjectID;
    @FXML private TableColumn<FinancialTransaction, Double> columnTransactionAmount;
    @FXML private TableColumn<FinancialTransaction, LocalDateTime> columnTransactionDate;
    @FXML private TableColumn<FinancialTransaction, String> columnTransactionType;

    //----------------------------------------------------------------------------------------------------------------------------
    
    // DAO Objects
    private ProjectDAO projectDAO;
    private ClientDAO clientDAO;
    private ContractorDAO contractorDAO;
    private InvoiceDAO invoiceDAO;
    private FinancialTransactionDAO transactionDAO;

    //-------------------------------admin info tab---------------------------------------------------------------------------------------------

        private Label adminNameLabel;
        private Label adminEmailLabel;

        public BorderPane createAdminDashboard(String adminName, String adminEmail) {
            BorderPane borderPane = new BorderPane();
            
            VBox vBox = new VBox(10);
            vBox.setStyle("-fx-padding: 10;");
            
            Label titleLabel = new Label("Admin Information: ");
            titleLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #1A3D7C;");
            
            adminNameLabel = new Label("Name: " + adminName);
            adminNameLabel.setStyle("-fx-font-size: 16px;");
            
            adminEmailLabel = new Label("Email: " + adminEmail);
            adminEmailLabel.setStyle("-fx-font-size: 16px;");
            
            vBox.getChildren().addAll(titleLabel, adminNameLabel, adminEmailLabel);
            borderPane.setCenter(vBox);
            
            return borderPane;
        }

        public void updateAdminInfo(String newName, String newEmail) {
            adminNameLabel.setText("Name: " + newName);
            adminEmailLabel.setText("Email: " + newEmail);
        }
    
    
    //----------------------------------------------------------------------------------------------------------------------------    
    @FXML
    public void initialize() {
        // Initialize DAOs
        projectDAO = new ProjectDAO();
        clientDAO = new ClientDAO();
        contractorDAO = new ContractorDAO();
        invoiceDAO = new InvoiceDAO();


        try {
            // Set cell value factories for Project columns
            columnProjectID.setCellValueFactory(new PropertyValueFactory<>("projectID"));
            columnProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
            columnProjectBudget.setCellValueFactory(new PropertyValueFactory<>("projectBudget"));
            columnStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            columnEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            // Set cell value factories for Client columns
            columnClientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
            columnClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
            columnClientContactInfo.setCellValueFactory(new PropertyValueFactory<>("clientContactInfo"));
            columnClientUserID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                    cellData.getValue().getClientUserID().getUserID()));

         // Set cell value factories for Contractor columns
            columnContractorID.setCellValueFactory(new PropertyValueFactory<>("contractorID"));
            columnContractorName.setCellValueFactory(new PropertyValueFactory<>("contractorName"));
            columnContactInfo.setCellValueFactory(new PropertyValueFactory<>("contactInfo")); 
            columnContractorUserID.setCellValueFactory(new PropertyValueFactory<>("contractorUserID")); 
            
            
         // Set cell value factories for Invoice columns
            columnInvoiceID.setCellValueFactory(new PropertyValueFactory<>("invoiceID"));
            columnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
            columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            columnPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
            columnPaymentDetails.setCellValueFactory(new PropertyValueFactory<>("paymentDetails"));
            columnOutstandingBalance.setCellValueFactory(new PropertyValueFactory<>("outstandingBalance"));
            columnInvoiceProjectID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                    cellData.getValue().getInvoiceProjectID().getProjectID()));

         // Set cell value factories for FinancialTransaction columns
            columnTransactionID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
          //  columnTransactionProjectID.setCellValueFactory(new PropertyValueFactory<>("transactionProjectID"));
            columnTransactionAmount.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
            columnTransactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
            columnTransactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
            columnTransactionProjectID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
                    cellData.getValue().getTransactionProjectID().getProjectID()));

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Refresh tables at initialization
        handleRefreshProjects();
        handleRefreshClients();
        handleRefreshContractors();
        handleRefreshInvoices();
      
        handleRefreshFinancialTransactions();

    }
   
    //-------------------------------function adding-------------------------------------------------------------------------------
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
    
   
    
  //handle add client function

  @FXML
  private void handleAddClient() {
      Dialog<Client> dialog = new Dialog<>();
      dialog.setTitle("Add New Client");

      // Set the button types
      ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

      // Create the form fields
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));

      TextField userIdField = new TextField();
      userIdField.setPromptText("User ID (matching user table)");
      TextField clientNameField = new TextField();
      clientNameField.setPromptText("Client Name");
      TextField clientInfoField = new TextField();
      clientInfoField.setPromptText("Client Info");

      grid.add(new Label("User ID:"), 0, 0);
      grid.add(userIdField, 1, 0);
      grid.add(new Label("Client Name:"), 0, 1);
      grid.add(clientNameField, 1, 1);
      grid.add(new Label("Client Info:"), 0, 2);
      grid.add(clientInfoField, 1, 2);

      dialog.getDialogPane().setContent(grid);

      // Convert the result to a Client object when the "Add" button is clicked
      dialog.setResultConverter(dialogButton -> {
          if (dialogButton == addButtonType) {
              try {
                  Client client = new Client();

                  // Retrieve the User object based on the entered user ID
                  UserDAO userDAO = new UserDAO(); // Create a User DAO instance
                  User user = userDAO.getUser(Integer.parseInt(userIdField.getText())); // Fetch the User object

                  // Check if the User object is valid
                  if (user != null) {
                      client.setClientUserID(user); // Set the User object
                  } else {
                      showAlert("Error", "User ID not found. Please enter a valid user ID.");
                      return null; // Stop processing if the User is not found
                  }

                  client.setClientName(clientNameField.getText());
                  client.setClientContactInfo(clientInfoField.getText());

                  return client;
              } catch (NumberFormatException e) {
                  showAlert("Error", "Invalid User ID format. Please enter a numeric value.");
                  return null;
              }
          }
          return null;
      });

      // Handle the dialog result
      dialog.showAndWait().ifPresent(client -> {
          try {
              ClientDAO clientDAO = new ClientDAO(); // Create DAO instance
              clientDAO.addClient(client); // Save the client
              handleRefreshClients(); // Refresh the table
              System.out.println("Client added successfully: " + client.getClientName());
          } catch (Exception e) {
              e.printStackTrace(); // Log the error
              showAlert("Error", "Failed to add the client. Please try again.");
          }
      });
  }

  public void addClient(Client client) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
          transaction = session.beginTransaction();
          session.save(client); // Hibernate generates the ID upon saving
          transaction.commit();
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          e.printStackTrace();
      }
  }
 
    
    //handle add contractor button 
  @FXML
  private void handleAddContractor() {
      Dialog<Contractor> dialog = new Dialog<>();
      dialog.setTitle("Add New Contracor");

      // Set the button types
      ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

      // Create the form fields
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));

      TextField userIdField = new TextField();
      userIdField.setPromptText("User ID");
      TextField contractorNameField = new TextField();
      contractorNameField.setPromptText("Contractor Name");
      TextField contractorInfoField = new TextField();
      contractorInfoField.setPromptText("Contractor Info");

      grid.add(new Label("User ID:"), 0, 0);
      grid.add(userIdField, 1, 0);
      grid.add(new Label("Contractor Name:"), 0, 1);
      grid.add(contractorNameField, 1, 1);
      grid.add(new Label("Contractor Info:"), 0, 2);
      grid.add(contractorInfoField, 1, 2);

      dialog.getDialogPane().setContent(grid);

      // Convert the result to a Client object when the "Add" button is clicked
      dialog.setResultConverter(dialogButton -> {
          if (dialogButton == addButtonType) {
              try {
            	  Contractor contractor = new Contractor();

                  // Retrieve the User object based on the entered user ID
                  UserDAO userDAO = new UserDAO(); // Create a User DAO instance
                  User user = userDAO.getUser(Integer.parseInt(userIdField.getText())); // Fetch the User object

                  // Check if the User object is valid
                  if (user != null) {
                	  contractor.setContractorUserID(user); // Set the User object
                  } else {
                      showAlert("Error", "User ID not found. Please enter a valid user ID.");
                      return null; // Stop processing if the User is not found
                  }

                  contractor.setContractorName(contractorNameField.getText());
                  contractor.setContactInfo(contractorInfoField.getText());

                  return contractor;
              } catch (NumberFormatException e) {
                  showAlert("Error", "Invalid User ID format. Please enter a numeric value.");
                  return null;
              }
          }
          return null;
      });

      // Handle the dialog result
      dialog.showAndWait().ifPresent(contractor -> {
          try {
              ContractorDAO contractorDAO = new ContractorDAO(); // Create DAO instance
              contractorDAO.addContractor(contractor); // Save the client
              handleRefreshContractors(); // Refresh the table
              System.out.println("Contractor added successfully: " + contractor.getContractorName());
          } catch (Exception e) {
              e.printStackTrace(); // Log the error
              showAlert("Error", "Failed to add the contractor. Please try again.");
          }
      });
  }

  public void addContractor(Contractor contractor) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
          transaction = session.beginTransaction();
          session.save(contractor); // Hibernate generates the ID upon saving
          transaction.commit();
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          e.printStackTrace();
      }
  }
  
//add invoice function
  @FXML
  private void handleAddInvoice() {
      Dialog<Invoice> dialog = new Dialog<>();
      dialog.setTitle("Add New Invoice");
      // Set the button types
      ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
      // Create the form fields
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));
      ComboBox<Integer> projectIDComboBox = new ComboBox<>();
      TextField amountField = new TextField();
      amountField.setPromptText("Amount");
      DatePicker datePicker = new DatePicker();
      ComboBox<Invoice.Status> statusComboBox = new ComboBox<>();
      statusComboBox.getItems().addAll(Invoice.Status.UNPAID, Invoice.Status.PAID, Invoice.Status.PARTIALLY_PAID, Invoice.Status.CANCELLED);
      ComboBox<Invoice.PaymentMethod> paymentMethodComboBox = new ComboBox<>();
      paymentMethodComboBox.getItems().addAll(Invoice.PaymentMethod.CASH, Invoice.PaymentMethod.CREDIT_CARD, Invoice.PaymentMethod.BANK_TRANSFER, Invoice.PaymentMethod.OTHER);
      TextArea paymentDetailsArea = new TextArea();
      paymentDetailsArea.setPromptText("Payment Details");
      TextField outstandingBalanceField = new TextField();
      outstandingBalanceField.setPromptText("Outstanding Balance");
      grid.add(new Label("Project ID:"), 0, 0);
      grid.add(projectIDComboBox, 1, 0);
      grid.add(new Label("Amount:"), 0, 1);
      grid.add(amountField, 1, 1);
      grid.add(new Label("Date:"), 0, 2);
      grid.add(datePicker, 1, 2);
      grid.add(new Label("Status:"), 0, 3);
      grid.add(statusComboBox, 1, 3);
      grid.add(new Label("Payment Method:"), 0, 4);
      grid.add(paymentMethodComboBox, 1, 4);
      grid.add(new Label("Payment Details:"), 0, 5);
      grid.add(paymentDetailsArea, 1, 5);
      grid.add(new Label("Outstanding Balance:"), 0, 6);
      grid.add(outstandingBalanceField, 1, 6);
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
      // Convert the result to an Invoice object when the "Add" button is clicked
      dialog.setResultConverter(dialogButton -> {
          if (dialogButton == addButtonType) {
              Invoice invoice = new Invoice();
              // Validate and set Project ID
              if (projectIDComboBox.getValue() == null) {
                  showAlert("Error", "Project ID must be selected.");
                  return null;
              }
              Project assignedProject = new Project();
              assignedProject.setProjectID(projectIDComboBox.getValue());
              invoice.setInvoiceProjectID(assignedProject);
              // Validate and set Amount
              try {
                  invoice.setAmount(Double.parseDouble(amountField.getText()));
              } catch (NumberFormatException e) {
                  showAlert("Error", "Amount must be a valid number.");
                  return null;
              }
              // Validate and set Date
              LocalDate selectedDate = datePicker.getValue();
              if (selectedDate != null) {
                  LocalDateTime startOfDay = selectedDate.atStartOfDay(); // Combine date with time (00:00:00)
                  invoice.setDate(startOfDay); // Set the LocalDateTime directly
              } else {
                  showAlert("Error", "Please select a valid date.");
                  return null;
              }
              // Validate and set Status
              if (statusComboBox.getValue() == null) {
                  showAlert("Error", "Invoice status must be selected.");
                  return null;
              }
              invoice.setStatus(statusComboBox.getValue());
              // Set Payment Method
              invoice.setPaymentMethod(paymentMethodComboBox.getValue());
              // Set Payment Details
              invoice.setPaymentDetails(paymentDetailsArea.getText());
              // Validate and set Outstanding Balance
              try {
                  invoice.setOutstandingBalance(Double.parseDouble(outstandingBalanceField.getText()));
              } catch (NumberFormatException e) {
                  showAlert("Error", "Outstanding balance must be a valid number.");
                  return null;
              }
              return invoice;
          }
          return null;
      });
      // Handle the dialog result
      dialog.showAndWait().ifPresent(invoice -> {
          try {
              InvoiceDAO invoiceDAO = new InvoiceDAO();
              invoiceDAO.addInvoice(invoice);
              handleRefreshInvoices();
              System.out.println("Invoice added successfully: " + invoice.getAmount());
          } catch (Exception e) {
              e.printStackTrace();
              showAlert("Error", "Failed to add the invoice. Please try again.");
          }
      });
  }
  // Save the invoice to the database
  public void addInvoice(Invoice invoice) {
      Transaction transaction = null;
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
          transaction = session.beginTransaction();
          session.save(invoice); // Hibernate should handle LocalDateTime mapping
          transaction.commit();
      } catch (Exception e) {
          if (transaction != null) transaction.rollback();
          e.printStackTrace();
      }
  }



  @FXML
  private void handleAddFinancialTransaction() {
      Dialog<FinancialTransaction> dialog = new Dialog<>();
      dialog.setTitle("Add Financial Transaction");
      // Set the dialog buttons
      ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
      // Create the form fields
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));
      ComboBox<Integer> projectIDComboBox = new ComboBox<>(); // Dropdown for selecting ProjectID
      TextField transactionAmountField = new TextField();
      transactionAmountField.setPromptText("Transaction Amount");
      DatePicker transactionDatePicker = new DatePicker();
      ComboBox<FinancialTransaction.TransactionType> transactionTypeComboBox = new ComboBox<>();
      transactionTypeComboBox.getItems().addAll(FinancialTransaction.TransactionType.EXPENSE, FinancialTransaction.TransactionType.PAYMENT, FinancialTransaction.TransactionType.REFUND, FinancialTransaction.TransactionType.OTHER);
      grid.add(new Label("Select Project ID:"), 0, 0);
      grid.add(projectIDComboBox, 1, 0);
      grid.add(new Label("Transaction Amount:"), 0, 1);
      grid.add(transactionAmountField, 1, 1);
      grid.add(new Label("Transaction Date:"), 0, 2);
      grid.add(transactionDatePicker, 1, 2);
      grid.add(new Label("Transaction Type:"), 0, 3);
      grid.add(transactionTypeComboBox, 1, 3);
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
      // Convert user input into a FinancialTransaction object when the "Add" button is clicked
      dialog.setResultConverter(dialogButton -> {
          if (dialogButton == addButtonType) {
              try {
                  FinancialTransaction financialTransaction = new FinancialTransaction();
                  // Validate and set Project ID
                  if (projectIDComboBox.getValue() == null) {
                      showAlert("Error", "Project ID must be selected.");
                      return null;
                  }
                  Project associatedProject = new Project();
                  associatedProject.setProjectID(projectIDComboBox.getValue());
                  financialTransaction.setTrasactionProject(associatedProject);
                  // Validate and set Transaction Amount
                  try {
                      financialTransaction.setTransactionAmount(Double.parseDouble(transactionAmountField.getText()));
                  } catch (NumberFormatException e) {
                      showAlert("Error", "Transaction amount must be a valid number.");
                      return null;
                  }
                  // Validate and set Transaction Date
                  LocalDate selectedDate = transactionDatePicker.getValue();
                  if (selectedDate != null) {
                      LocalDateTime transactionDateTime = selectedDate.atStartOfDay();
                      financialTransaction.setTransactionDate(transactionDateTime); // Use LocalDateTime
                  } else {
                      showAlert("Error", "Please select a valid transaction date.");
                      return null;
                  }
                  // Validate and set Transaction Type
                  if (transactionTypeComboBox.getValue() == null) {
                      showAlert("Error", "Transaction type must be selected.");
                      return null;
                  }
                  financialTransaction.setType(transactionTypeComboBox.getValue());
                  return financialTransaction;
              } catch (Exception e) {
                  showAlert("Error", "Invalid input. Please check the fields and try again.");
                  return null;
              }
          }
          return null;
      });
      // Handle the dialog result
      dialog.showAndWait().ifPresent(financialTransaction -> {
          try {
              FinancialTransactionDAO financialTransactionDAO = new FinancialTransactionDAO();
              financialTransactionDAO.addTransaction(financialTransaction); // Save to the database
              handleRefreshFinancialTransactions(); // Refresh the TableView
              System.out.println("Financial Transaction added successfully: ID = " + financialTransaction.getTransactionID());
          } catch (Exception e) {
              e.printStackTrace();
              showAlert("Error", "Failed to add the financial transaction. Please try again.");
          }
      });
  }


    
    //-----------------------------------------delete functions-------------------------------------------------------------------

    @FXML
    private void handleDeleteProject() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Project");

        // Set the button types
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the form field for Project ID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField projectIdField = new TextField();
        projectIdField.setPromptText("Project ID");

        grid.add(new Label("Project ID:"), 0, 0);
        grid.add(projectIdField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a Project ID (Integer) when the "Delete" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                try {
                    return Integer.parseInt(projectIdField.getText()); // Validate and convert Project ID
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Project ID format. Please enter a numeric value.");
                    return null; // Stop the process if the ID is invalid
                }
            }
            return null; // No action on other buttons
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(projectId -> {
            try {
                ProjectDAO projectDAO = new ProjectDAO(); // Create DAO instance
                projectDAO.deleteProject(projectId); // Delete the project
                handleRefreshProjects(); // Refresh the table
                System.out.println("Project deleted successfully: ID " + projectId);
            } catch (IllegalArgumentException e) {
                showAlert("Error", e.getMessage()); // Show alert if the project is not found
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
                showAlert("Error", "Failed to delete the project. Please try again.");
            }
        });
    }

    public void deleteProject(int projectId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Project project = session.get(Project.class, projectId); // Retrieve the project
            if (project != null) {
                session.delete(project); // Delete the project
                transaction.commit();
            } else {
                throw new IllegalArgumentException("Project not found with ID: " + projectId);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if there's an error
            e.printStackTrace();
        }
    }
    
    
    
    //handle delete client
    @FXML
    private void handleDeleteClient() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Client");

        // Set the button types
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the form field for Project ID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField clientIdField = new TextField();
        clientIdField.setPromptText("Client ID");

        grid.add(new Label("Client ID:"), 0, 0);
        grid.add(clientIdField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a Project ID (Integer) when the "Delete" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                try {
                    return Integer.parseInt(clientIdField.getText()); // Validate and convert Project ID
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Client ID format. Please enter a numeric value.");
                    return null; // Stop the process if the ID is invalid
                }
            }
            return null; // No action on other buttons
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(clientId -> {
            try {
                ClientDAO clienttDAO = new ClientDAO(); // Create DAO instance
                clienttDAO.deleteClient(clientId); // Delete the project
                handleRefreshClients(); // Refresh the table
                System.out.println("Client deleted successfully: ID " + clientId);
            } catch (IllegalArgumentException e) {
                showAlert("Error", e.getMessage()); // Show alert if the project is not found
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
                showAlert("Error", "Failed to delete the client. Please try again.");
            }
        });
    }

    public void deleteClient(int clientId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientId); // Retrieve the project
            if (client != null) {
                session.delete(client); // Delete the project
                transaction.commit();
            } else {
                throw new IllegalArgumentException("Client not found with ID: " + clientId);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if there's an error
            e.printStackTrace();
        }
    }

    
    
  //handle delete contractor
    @FXML
    private void handleDeleteContractor() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Contractor");

        // Set the button types
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the form field for Project ID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField contractorIdField = new TextField();
        contractorIdField.setPromptText("Contractor ID");

        grid.add(new Label("Contractor ID:"), 0, 0);
        grid.add( contractorIdField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a Project ID (Integer) when the "Delete" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                try {
                    return Integer.parseInt( contractorIdField.getText()); // Validate and convert Project ID
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Contractor ID format. Please enter a numeric value.");
                    return null; // Stop the process if the ID is invalid
                }
            }
            return null; // No action on other buttons
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(contractorId -> {
            try {
                ContractorDAO contractorDAO = new ContractorDAO(); // Create DAO instance
                contractorDAO.deleteContractor(contractorId); // Delete the project
                handleRefreshContractors(); // Refresh the table
                System.out.println("Contractor deleted successfully: ID " + contractorId);
            } catch (IllegalArgumentException e) {
                showAlert("Error", e.getMessage()); // Show alert if the project is not found
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
                showAlert("Error", "Failed to delete the contractor. Please try again.");
            }
        });
    }

    public void deleteContractor(int contractorId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Client contractor= session.get(Client.class, contractorId); // Retrieve the project
            if (contractor != null) {
                session.delete(contractor); // Delete the project
                transaction.commit();
            } else {
                throw new IllegalArgumentException("Contractor not found with ID: " + contractorId);
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if there's an error
            e.printStackTrace();
        }
    }

    
  //delete function for invoice
    @FXML
    private void handleDeleteInvoice() {
        // Get the selected invoice from the TableView
        Invoice selectedInvoice = tableInvoices.getSelectionModel().getSelectedItem();
        if (selectedInvoice == null) {
            showAlert("Error", "No invoice selected. Please select an invoice to delete.");
            return;
        }
        // Confirm deletion
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to delete the selected invoice?");
        confirmationAlert.setContentText("Invoice ID: " + selectedInvoice.getInvoiceID());
        // Wait for the user's response
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    // Call the DAO to delete the invoice
                    InvoiceDAO invoiceDAO = new InvoiceDAO();
                    invoiceDAO.deleteInvoice(selectedInvoice.getInvoiceID());
                    // Refresh the TableView after deletion
                    handleRefreshInvoices();
                    // Notify the user
                    System.out.println("Invoice deleted successfully: ID = " + selectedInvoice.getInvoiceID());
                    showAlert("Success", "Invoice deleted successfully.");
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to delete the invoice. Please try again.");
                }
            }
        });
    }

    
    
    
 // handle delete financial transaction
    @FXML
    private void handleDeleteFinancialTransaction() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Delete Financial Transaction");

        // Set the button types
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        // Create the form field for Transaction ID
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField transactionIdField = new TextField();
        transactionIdField.setPromptText("Transaction ID");

        grid.add(new Label("Transaction ID:"), 0, 0);
        grid.add(transactionIdField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a Transaction ID (Integer) when the "Delete" button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                try {
                    return Integer.parseInt(transactionIdField.getText()); // Validate and convert Transaction ID
                } catch (NumberFormatException e) {
                    showAlert("Error", "Invalid Transaction ID format. Please enter a numeric value.");
                    return null; // Stop the process if the ID is invalid
                }
            }
            return null; // No action on other buttons
        });

        // Handle the dialog result
        dialog.showAndWait().ifPresent(transactionId -> {
            try {
                FinancialTransactionDAO transactionDAO = new FinancialTransactionDAO(); // Create DAO instance
                transactionDAO.deleteTransaction(transactionId); // Delete the transaction
                handleRefreshFinancialTransactions(); // Refresh the table
                System.out.println("Financial Transaction deleted successfully: ID " + transactionId);
            } catch (IllegalArgumentException e) {
                showAlert("Error", e.getMessage()); // Show alert if transaction not found
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
                showAlert("Error", "Failed to delete the financial transaction. Please try again.");
            }
        });
    }

    
    //---------------------------------update functions-------------------------------------------------------------------------------------------
    @FXML
    private void handleUpdateProject() {
        Project selectedProject = tableProjects.getSelectionModel().getSelectedItem();

        if (selectedProject == null) {
            showAlert("Warning", "Please select a project to update.");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Project");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField projectNameField = new TextField(selectedProject.getProjectName());
        TextField budgetField = new TextField(String.valueOf(selectedProject.getProjectBudget()));
        DatePicker startDatePicker = new DatePicker(selectedProject.getStartDate().toLocalDate());
        DatePicker endDatePicker = new DatePicker(selectedProject.getEndDate().toLocalDate());
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Planned", "In Progress", "Completed", "Cancelled");
        statusComboBox.setValue(selectedProject.getStatus().toString());
        TextArea descriptionArea = new TextArea(selectedProject.getDescription());

        grid.add(new Label("Project Name:"), 0, 0);
        grid.add(projectNameField, 1, 0);
        grid.add(new Label("Budget:"), 0, 1);
        grid.add(budgetField, 1, 1);
        grid.add(new Label("Start Date:"), 0, 2);
        grid.add(startDatePicker, 1, 2);
        grid.add(new Label("End Date:"), 0, 3);
        grid.add(endDatePicker, 1, 3);
        grid.add(new Label("Status:"), 0, 4);
        grid.add(statusComboBox, 1, 4);
        grid.add(new Label("Description:"), 0, 5);
        grid.add(descriptionArea, 1, 5);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(result -> {
            if (result == updateButtonType) {
                try {
                    // Update selected project with new values
                    selectedProject.setProjectName(projectNameField.getText());
                    selectedProject.setProjectBudget(Double.parseDouble(budgetField.getText()));
                    selectedProject.setStartDate(java.sql.Date.valueOf(startDatePicker.getValue()));
                    selectedProject.setEndDate(java.sql.Date.valueOf(endDatePicker.getValue()));
                    selectedProject.setStatus(Project.ProjectStatus.valueOf(statusComboBox.getValue()));                    selectedProject.setDescription(descriptionArea.getText());

                    ProjectDAO projectDAO = new ProjectDAO();
                    projectDAO.updateProject(selectedProject);
                    handleRefreshProjects();

                    System.out.println("Project updated successfully: " + selectedProject.getProjectName());
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to update the project. Please try again.");
                }
            }
        });
    }

    
    //update client
    @FXML
    private void handleUpdateClient() {
        Client selectedClient = tableClients.getSelectionModel().getSelectedItem();

        if (selectedClient == null) {
            showAlert("Warning", "Please select a client to update.");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Client");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField clientNameField = new TextField(selectedClient.getClientName());
        TextField contactField = new TextField(selectedClient.getClientContactInfo());

        grid.add(new Label("Client Name:"), 0, 0);
        grid.add(clientNameField, 1, 0);
        grid.add(new Label("Contact Info:"), 0, 1);
        grid.add(contactField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(result -> {
            if (result == updateButtonType) {
                try {
                    // Update selected client with new values
                    selectedClient.setClientName(clientNameField.getText());
                    selectedClient.setClientContactInfo(contactField.getText());
 
                    ClientDAO clientDAO = new ClientDAO();
                    clientDAO.updateClient(selectedClient);
                    handleRefreshClients();

                    System.out.println("Client updated successfully: " + selectedClient.getClientName());
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to update the client. Please try again.");
                }
            }
        });
    }
    
    //update contractor
    @FXML
    private void handleUpdateContractor() {
        Contractor selectedContractor = tableContractors.getSelectionModel().getSelectedItem();

        if (selectedContractor == null) {
            showAlert("Warning", "Please select a contractor to update.");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Contractor");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField contractorNameField = new TextField(selectedContractor.getContractorName());
        TextField contactInfoField = new TextField(selectedContractor.getContactInfo());

        grid.add(new Label("Contractor Name:"), 0, 0);
        grid.add(contractorNameField, 1, 0);
        grid.add(new Label("Contact Info:"), 0, 1);
        grid.add(contactInfoField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(result -> {
            if (result == updateButtonType) {
                try {
                    // Update selected contractor with new values
                    selectedContractor.setContractorName(contractorNameField.getText());
                    selectedContractor.setContactInfo(contactInfoField.getText());

                    ContractorDAO contractorDAO = new ContractorDAO();
                    contractorDAO.updateContractor(selectedContractor);
                    handleRefreshContractors();

                    System.out.println("Contractor updated successfully: " + selectedContractor.getContractorName());
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to update the contractor. Please try again.");
                }
            }
        });
    }
    

  //update function for invoice
  @FXML
  private void handleUpdateInvoice() {
      // Get the selected invoice from the TableView
      Invoice selectedInvoice = tableInvoices.getSelectionModel().getSelectedItem();
      if (selectedInvoice == null) {
          showAlert("Error", "No invoice selected. Please select an invoice to update.");
          return;
      }
      // Create a dialog for updating the invoice
      Dialog<Invoice> dialog = new Dialog<>();
      dialog.setTitle("Update Invoice");
      // Set the button types
      ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);
      // Create the form fields
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));
      ComboBox<Integer> projectIDComboBox = new ComboBox<>();
      TextField amountField = new TextField();
      amountField.setText(String.valueOf(selectedInvoice.getAmount())); // Prepopulate with current value
      DatePicker datePicker = new DatePicker();
      datePicker.setValue(selectedInvoice.getDate().toLocalDate()); // Prepopulate with current value
      ComboBox<Invoice.Status> statusComboBox = new ComboBox<>();
      statusComboBox.getItems().addAll(Invoice.Status.UNPAID, Invoice.Status.PAID, Invoice.Status.PARTIALLY_PAID, Invoice.Status.CANCELLED);
      statusComboBox.setValue(selectedInvoice.getStatus()); // Prepopulate with current value
      ComboBox<Invoice.PaymentMethod> paymentMethodComboBox = new ComboBox<>();
      paymentMethodComboBox.getItems().addAll(Invoice.PaymentMethod.CASH, Invoice.PaymentMethod.CREDIT_CARD, Invoice.PaymentMethod.BANK_TRANSFER, Invoice.PaymentMethod.OTHER);
      paymentMethodComboBox.setValue(selectedInvoice.getPaymentMethod()); // Prepopulate with current value
      TextArea paymentDetailsArea = new TextArea();
      paymentDetailsArea.setText(selectedInvoice.getPaymentDetails()); // Prepopulate with current value
      TextField outstandingBalanceField = new TextField();
      outstandingBalanceField.setText(String.valueOf(selectedInvoice.getOutstandingBalance())); // Prepopulate with current value
      grid.add(new Label("Project ID:"), 0, 0);
      grid.add(projectIDComboBox, 1, 0);
      grid.add(new Label("Amount:"), 0, 1);
      grid.add(amountField, 1, 1);
      grid.add(new Label("Date:"), 0, 2);
      grid.add(datePicker, 1, 2);
      grid.add(new Label("Status:"), 0, 3);
      grid.add(statusComboBox, 1, 3);
      grid.add(new Label("Payment Method:"), 0, 4);
      grid.add(paymentMethodComboBox, 1, 4);
      grid.add(new Label("Payment Details:"), 0, 5);
      grid.add(paymentDetailsArea, 1, 5);
      grid.add(new Label("Outstanding Balance:"), 0, 6);
      grid.add(outstandingBalanceField, 1, 6);
      dialog.getDialogPane().setContent(grid);
      // Populate the ComboBox with available project IDs
      try {
          ProjectDAO projectDAO = new ProjectDAO();
          List<Project> projects = projectDAO.getAllProjects();
          for (Project project : projects) {
              projectIDComboBox.getItems().add(project.getProjectID());
          }
          projectIDComboBox.setValue(selectedInvoice.getInvoiceProjectID().getProjectID()); // Prepopulate with current project ID
      } catch (Exception e) {
          e.printStackTrace();
          showAlert("Error", "Failed to load project IDs. Please try again.");
          return;
      }
      // Convert dialog results into an updated Invoice object
      dialog.setResultConverter(dialogButton -> {
          if (dialogButton == updateButtonType) {
              try {
                  // Validate and update Project ID
                  if (projectIDComboBox.getValue() == null) {
                      showAlert("Error", "Project ID must be selected.");
                      return null;
                  }
                  Project updatedProject = new Project();
                  updatedProject.setProjectID(projectIDComboBox.getValue());
                  selectedInvoice.setInvoiceProjectID(updatedProject);
                  // Validate and update Amount
                  selectedInvoice.setAmount(Double.parseDouble(amountField.getText()));
                  // Validate and update Date
                  LocalDate updatedDate = datePicker.getValue();
                  if (updatedDate != null) {
                      LocalDateTime startOfDay = updatedDate.atStartOfDay();
                      selectedInvoice.setDate(startOfDay); // Updated to use LocalDateTime
                  } else {
                      showAlert("Error", "Please select a valid date.");
                      return null;
                  }
                  // Validate and update Status
                  if (statusComboBox.getValue() == null) {
                      showAlert("Error", "Invoice status must be selected.");
                      return null;
                  }
                  selectedInvoice.setStatus(statusComboBox.getValue());
                  // Update Payment Method
                  selectedInvoice.setPaymentMethod(paymentMethodComboBox.getValue());
                  // Update Payment Details
                  selectedInvoice.setPaymentDetails(paymentDetailsArea.getText());
                  // Validate and update Outstanding Balance
                  selectedInvoice.setOutstandingBalance(Double.parseDouble(outstandingBalanceField.getText()));
                  return selectedInvoice;
              } catch (NumberFormatException e) {
                  showAlert("Error", "Invalid input. Please check the fields and try again.");
                  return null;
              }
          }
          return null;
      });
      // Handle the dialog result
      dialog.showAndWait().ifPresent(updatedInvoice -> {
          try {
              InvoiceDAO invoiceDAO = new InvoiceDAO();
              invoiceDAO.updateInvoice(updatedInvoice); // Save updated invoice to the database
              handleRefreshInvoices(); // Refresh the TableView
              System.out.println("Invoice updated successfully: " + updatedInvoice.getInvoiceID());
          } catch (Exception e) {
              e.printStackTrace();
              showAlert("Error", "Failed to update the invoice. Please try again.");
          }
      });
  }
  
  
  @FXML
  private void handleUpdateFinancialTransaction() {
      // Get the selected transaction from the TableView
      FinancialTransaction selectedTransaction = tableFinancialtransactions.getSelectionModel().getSelectedItem();
      if (selectedTransaction == null) {
          showAlert("Error", "No financial transaction selected. Please select a transaction to update.");
          return;
      }
      // Create a dialog for updating the financial transaction
      Dialog<FinancialTransaction> dialog = new Dialog<>();
      dialog.setTitle("Update Financial Transaction");
      // Set the dialog buttons
      ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);
      // Create the form fields
      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));
      ComboBox<Integer> projectIDComboBox = new ComboBox<>();
      TextField transactionAmountField = new TextField();
      transactionAmountField.setText(String.valueOf(selectedTransaction.getTransactionAmount())); // Prepopulate current value
      DatePicker transactionDatePicker = new DatePicker();
      transactionDatePicker.setValue(selectedTransaction.getTransactionDate().toLocalDate()); // Prepopulate current value
      ComboBox<FinancialTransaction.TransactionType> transactionTypeComboBox = new ComboBox<>();
      transactionTypeComboBox.getItems().addAll(FinancialTransaction.TransactionType.EXPENSE, FinancialTransaction.TransactionType.PAYMENT, FinancialTransaction.TransactionType.REFUND, FinancialTransaction.TransactionType.OTHER);
      transactionTypeComboBox.setValue(selectedTransaction.getTransactionType()); // Prepopulate current value
      grid.add(new Label("Select Project ID:"), 0, 0);
      grid.add(projectIDComboBox, 1, 0);
      grid.add(new Label("Transaction Amount:"), 0, 1);
      grid.add(transactionAmountField, 1, 1);
      grid.add(new Label("Transaction Date:"), 0, 2);
      grid.add(transactionDatePicker, 1, 2);
      grid.add(new Label("Transaction Type:"), 0, 3);
      grid.add(transactionTypeComboBox, 1, 3);
      dialog.getDialogPane().setContent(grid);
      // Populate the ComboBox with available Project IDs
      try {
          ProjectDAO projectDAO = new ProjectDAO();
          List<Project> projects = projectDAO.getAllProjects();
          for (Project project : projects) {
              projectIDComboBox.getItems().add(project.getProjectID());
          }
          projectIDComboBox.setValue(selectedTransaction.getTransactionProjectID().getProjectID()); // Prepopulate current project ID
      } catch (Exception e) {
          e.printStackTrace();
          showAlert("Error", "Failed to load project IDs. Please try again.");
          return;
      }
      // Convert the user input into an updated FinancialTransaction object
      dialog.setResultConverter(dialogButton -> {
          if (dialogButton == updateButtonType) {
              try {
                  // Update Project ID
                  if (projectIDComboBox.getValue() == null) {
                      showAlert("Error", "Project ID must be selected.");
                      return null;
                  }
                  Project updatedProject = new Project();
                  updatedProject.setProjectID(projectIDComboBox.getValue());
                  selectedTransaction.setTrasactionProject(updatedProject);
                  // Update Transaction Amount
                  try {
                      selectedTransaction.setTransactionAmount(Double.parseDouble(transactionAmountField.getText()));
                  } catch (NumberFormatException e) {
                      showAlert("Error", "Transaction amount must be a valid number.");
                      return null;
                  }
                  // Update Transaction Date
                  LocalDate updatedDate = transactionDatePicker.getValue();
                  if (updatedDate != null) {
                      LocalDateTime transactionDateTime = updatedDate.atStartOfDay();
                      selectedTransaction.setTransactionDate(transactionDateTime);
                  } else {
                      showAlert("Error", "Please select a valid transaction date.");
                      return null;
                  }
                  // Update Transaction Type
                  if (transactionTypeComboBox.getValue() == null) {
                      showAlert("Error", "Transaction type must be selected.");
                      return null;
                  }
                  selectedTransaction.setType(transactionTypeComboBox.getValue());
                  return selectedTransaction;
              } catch (Exception e) {
                  showAlert("Error", "Invalid input. Please check the fields and try again.");
                  return null;
              }
          }
          return null;
      });
      // Handle the dialog result
      dialog.showAndWait().ifPresent(updatedTransaction -> {
          try {
              FinancialTransactionDAO financialTransactionDAO = new FinancialTransactionDAO();
              financialTransactionDAO.updateTransaction(updatedTransaction); // Save the changes to the database
              handleRefreshFinancialTransactions(); // Refresh the TableView
              System.out.println("Financial Transaction updated successfully: ID = " + updatedTransaction.getTransactionID());
          } catch (Exception e) {
              e.printStackTrace();
              showAlert("Error", "Failed to update the financial transaction. Please try again.");
          }
      });
  }
  
  


    //----------------------------------------------------------------------------------------------------------------------------

    // Refresh Project Table
    @FXML
    private void handleRefreshProjects() {
        try {
            List<Project> projects = projectDAO.getAllProjects();
            if (projects != null) {
                ObservableList<Project> projectData = FXCollections.observableArrayList(projects);
                tableProjects.setItems(projectData);
            } else {
                tableProjects.setItems(FXCollections.observableArrayList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the Projects table.");
        }
    }

 // Refresh Client Table
    @FXML
    private void handleRefreshClients() {
        try {
            List<Client> clients = clientDAO.getAllClients();
            if (clients != null) {
                ObservableList<Client> clientData = FXCollections.observableArrayList(clients);
                tableClients.setItems(clientData);
            } else {
                tableClients.setItems(FXCollections.observableArrayList()); // Empty table fallback
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the Clients table.");
        }
    }

    // Refresh Contractor Table
    @FXML
    private void handleRefreshContractors() {
        try {
            List<Contractor> contractors = contractorDAO.getAllContractors(); // Fetching contractors
            if (contractors != null) {
                ObservableList<Contractor> contractorData = FXCollections.observableArrayList(contractors);
                tableContractors.setItems(contractorData); // Corrected table name
            } else {
                tableContractors.setItems(FXCollections.observableArrayList()); // Empty table fallback
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the Contractors table."); // Corrected alert message
        }
    }
        
 
    // Refresh Invoices Table
    @FXML
    private void handleRefreshInvoices() {
        try {
            List<Invoice> invoices = invoiceDAO.getAllInvoices(); // Fetching contractors
            if (invoices != null) {
                ObservableList<Invoice> invoiceData = FXCollections.observableArrayList(invoices);
                tableInvoices.setItems(invoiceData); // Corrected table name
            } else {
                tableInvoices.setItems(FXCollections.observableArrayList()); // Empty table fallback
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the Invoices table."); // Corrected alert message
        }
    }
    
    
 
 // Handle Transactions
 // Refresh Financial Transactions Table
    @FXML
    private void handleRefreshFinancialTransactions() {
        try {
        	
        	 // Create an instance of FinancialTransactionDAO
        	FinancialTransactionDAO financialTransactionDAO = new FinancialTransactionDAO();
            
            // Retrieve all financial transactions from the DAO class
        	List<FinancialTransaction> financialTransactions = financialTransactionDAO.getAllTransactions();
            
            if (financialTransactions != null) {
                // Convert the transactions list into an ObservableList and set it to the table
                ObservableList<FinancialTransaction> transactionData = FXCollections.observableArrayList(financialTransactions);
                tableFinancialtransactions.setItems(transactionData);
            } else {
                // Set an empty ObservableList if no transactions are found
                tableFinancialtransactions.setItems(FXCollections.observableArrayList());
            }
        } catch (Exception e) {
            // Handle errors and display an alert
            e.printStackTrace();
            showAlert("Error", "Failed to refresh the Financial Transactions table.");
        }
    }


    // Utility method to display alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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
            stage.setMaximized(true);
            stage.setTitle("Login Page");
            // Print confirmation
            System.out.println("Navigating back to Login page...");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Login page.");
        }
    }
}
