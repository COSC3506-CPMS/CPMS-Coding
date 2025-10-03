# Construction Project Management System (CPMS)
This is a Construction Project Management System that helps project managers, contractors, workers, and clients work together more effectively. It makes it easier to manage projects, track tasks, handle finances, and keep everyone updated.



## Main Features
* Separate dashboards for Admins, Contractors, and Clients
* Project and Task Management – create projects, assign tasks, and track milestones
* Worker Management – add, update, and assign workers
* Financial Management – manage budgets, invoices, and payments
* Client Services – request services, track progress, approve milestones, and make payments
* Secure Login – role-based access, password protection, and multi-factor authentication
* Simple User Interface – clear dashboards, accessible design



## System Architecture
* Presentation Layer (UI): JavaFX dashboards for different user roles
* Logic Layer (Business Rules): Java modules for projects, tasks, workers, finances
* Data Access Layer: JDBC to connect with the database
* Database Layer: MySQL for storing users, projects, invoices, and financial data




## User Roles
* Admin – full access, manages users, projects, and reports
* Contractor – manages assigned projects, workers, and invoices
* Client – requests services, views progress, and makes payments
* Worker – works on assigned tasks and reports progress



## Modules
* User Management – create and manage accounts
* Project Management – handle schedules and milestones
* Task Management – assign and track tasks
* Worker Management – manage worker information and availability
* Financial Management – track budgets, payments, and invoices
* Client Services – handle requests, approvals, and history



## Non-Functional Requirements
* Performance: supports 100+ users without delays
* Scalability: can handle more data and users as projects grow
* Security: role-based access, encryption, multi-factor authentication
* Reliability: 99.9% uptime with backups and disaster recovery
* Usability: simple dashboards, accessible design
* Maintainability: modular code with support for updates and CI/CD



## Requirements

**Hardware**
* Minimum: 2 GHz CPU, 4GB RAM, 10GB storage
* Recommended: 3 GHz CPU, 8GB RAM, SSD

**Software**
* Java 11+
* MySQL 8.0+
* JDBC Driver
* Runs on Windows, Linux, or MacOS
