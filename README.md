# Thesis Project – Development Environment Setup

This document describes how to set up the working development environment for the thesis project. The project uses **IntelliJ IDEA** as the main IDE and **SQL Server Management Studio (SSMS)** for database management.

---

## Prerequisites

Before starting, make sure you have the following installed:

* **Java Development Kit (JDK)**
* **IntelliJ IDEA**
* **Microsoft SQL Server**
* **SQL Server Management Studio (SSMS)**

---

## Step 1: Set Up IntelliJ IDEA

1. Download and install **IntelliJ IDEA**.
2. Launch IntelliJ IDEA.
3. Configure the JDK:

   * Go to **File → Project Structure → SDKs**.
   * Add the JDK if it is not detected automatically.

---

## Step 2: Set Up SQL Server and SSMS

1. Install **Microsoft SQL Server**.
2. Install **SQL Server Management Studio (SSMS)**.
3. Open SSMS and connect to your local SQL Server instance.
4. Create or restore the project database as required by the thesis project.

---

## Step 3: Open the Project in IntelliJ

1. In IntelliJ, select **File → Open** and choose the project root directory.
2. Let IntelliJ import the project and download dependencies.

---

## Step 4: Configure Database Connection

1. In IntelliJ, navigate to:

```
src/main/resources/application.yml
```

2. Locate the database configuration section.
3. Set the **Windows ID** and connection properties for your local SQL Server instance. Example:

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://yourWindowsID\\InstanceName:1433;databaseName=mydb;encrypt=true;trustServerCertificate=true;
    username: sqlcoadmin
    password: 123123123
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
```

4. Save the file.

---

## Step 5: Run the Application

1. In IntelliJ, locate the main application class.
2. Click **Run**.
3. Wait until the application starts successfully and the server is listening on the configured ports.

---

## Step 6: Access the Application

After the application is running, open a web browser and use the following URLs:

### Main Bestiary Page

```
http://localhost:5173/
```

### Entity Adding Templates

```
http://localhost:8080/FillDBMonster
```

---

## Test Data

There is a large amount of dummy data added (Monsters, Items, Spells, and Characters) to highlight the overall view of the application.
To access instances with all possible data filled in, please choose **FullObject** entities so that the full view is available.
The main login for testing is:

* **Email:** [testemail@gmail.com](mailto:testemail@gmail.com)
* **Password:** **82388234**

---

## Troubleshooting

* If the application fails to start, check the console logs in IntelliJ.
* Verify that:

  * SQL Server is running.
  * The database credentials in `application.yml` are correct.
  * Required ports (5173 and 8080) are not blocked or already in use.
