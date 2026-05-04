# SEM4PI 2025/2026 — AlSafe Back Office Management

## Project Overview

This project is being developed for the SEM4PI curricular unit at ISEP, during the 2025/2026 academic year.

The goal of the project is to develop part of the back office management system for **AlSafe**, a startup focused on aircraft flight control and flight safety support.

The system is intended to manage several areas of the business domain, including:

- user authentication and authorization;
- backoffice user management;
- aircraft and engine manufacturers;
- aircraft engine models;
- aircraft models;
- air transport companies;
- collaborators and pilots;
- airports and air control areas;
- flight routes and flight plans;
- weather data;
- flight plan validation through a DSL;
- flight simulation and air control simulation.

The project will follow a Domain-Driven Design approach in order to better handle the complexity of the business domain.

---

## Technical Stack

The main technologies planned for this project are:

- **Java** — main programming language;
- **Maven** — build automation and dependency management;
- **JUnit 5** — unit testing;
- **GitHub** — source control and project repository;
- **GitHub Actions** — continuous integration;
- **Markdown** — technical documentation;
- **PlantUML** — UML diagrams;
- **ANTLR** — DSL grammar and parsing;
- **C** — flight simulation components;
- **Relational Database** — final persistent storage;
- **In-memory persistence** — development and testing support.

---

## Project Structure

Current initial structure:

```text
.
├── docs/
│   ├── 00-project-overview.md
│   ├── 01-glossary.md
│   └── 02-domain-model.md
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
├── .gitignore
├── pom.xml
└── README.md
````

This structure will evolve as the project progresses.

---

## Documentation

Project documentation is available in the `docs/` folder.

The current documentation includes:

* `00-project-overview.md` — general description of the project;
* `01-glossary.md` — domain terminology and definitions;
* `02-domain-model.md` — initial domain model;

Documentation will be updated throughout the development process.

---

## Requirements

To build and run this project, the following tools are required:

* Java 21 or higher;
* Maven 3.9 or higher;
* Git;
* GCC, later required for the C simulation components.

To check the installed Java version:

```bash
java -version
```

To check the installed Maven version:

```bash
mvn -v
```

---

## Build

To compile the project:

```bash
mvn clean compile
```

---

## Test

To run the automated tests:

```bash
mvn clean test
```

At this stage, the project only contains the initial structure and testing setup.

---

## Package

To package the project:

```bash
mvn clean package
```

The generated files will be placed inside the `target/` directory.

The `target/` directory is generated automatically by Maven and must not be committed to the repository.

---

## Development Process

The project will follow an incremental development process.

For each relevant user story, the expected workflow is:

1. analysis;
2. domain design;
3. implementation;
4. testing;
5. documentation update.

Whenever possible, the project will follow a test-driven or test-oriented development approach.

---

## Source Control Rules

The repository should remain in a valid state after every commit.

Before committing, the following command should be executed:

```bash
mvn clean test
```

If the build or tests fail, the changes should not be committed until the issue is fixed.

Recommended commit message format:

```text
Short description of the change
```

Examples:

```text
Set up Maven project structure and initial documentation
Add initial user domain model
Implement user activation and deactivation rules
Add unit tests for user security clearance validation
```

---

## Author

Bruno Rodrigues
ISEP — Instituto Superior de Engenharia do Porto
SEM4PI — 2025/2026
