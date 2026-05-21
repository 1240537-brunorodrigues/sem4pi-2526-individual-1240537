# SEM4PI 2025/2026 — AlSafe Back Office Management

## Important Notice

This repository was originally created for a group academic project at ISEP.

However, the reality is simple:

Most of the work currently present in this repository was developed individually by me.

Due to mandatory attendance rules and institutional group constraints, students who work and cannot physically attend classes regularly are effectively prevented from properly integrating into groups, regardless of the actual work they are capable of producing.

So instead of pretending this project never existed, or leaving months of work buried in a private repository because of bureaucratic nonsense, I decided to make it public.

Not because I have to.

Because I can.

And because the work exists whether the system acknowledges it or not.

This repository therefore serves simultaneously as:

- a software engineering project;
- a technical portfolio piece;
- a public authorship record;
- and a reminder that competence and attendance are not the same thing.

Every commit, architectural decision, document, implementation detail, UML diagram, grammar definition, and code contribution is permanently associated with this repository history.

If someone chooses to plagiarize, redistribute, submit, or academically misuse this work as their own, that is entirely on them — but plagiarism is still plagiarism, whether it happens inside or outside a university.

---

## Project Overview

This project is being developed for the SEM4PI curricular unit at ISEP during the 2025/2026 academic year.

The objective is to develop part of the back office management system for **AlSafe**, a startup focused on aircraft flight control and flight safety support.

The system aims to manage several business areas, including:

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

The project follows a Domain-Driven Design (DDD) approach in order to better handle the complexity of the domain.

---

## Current Project Structure

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
```

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

## Final Note

Universities love talking about merit.

Sometimes merit is attendance sheets.

Sometimes merit is bureaucracy.

Sometimes merit is who was physically present in a classroom at 8AM.

And sometimes merit is simply sitting down after work, exhausted, and still building the damn thing anyway.

This repository exists because I chose the last option.

---

## Author

Bruno Rodrigues

ISEP — Instituto Superior de Engenharia do Porto

SEM4PI — 2025/2026

Independent development and maintenance.
