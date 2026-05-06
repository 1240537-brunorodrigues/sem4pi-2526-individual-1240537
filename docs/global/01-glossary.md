# 📘 Glossary

---

## 📑 Index

| Concept | Description |
|--------|------------|
| [Aircraft Model](#-aircraft-model) | Aircraft specifications |
| [Air Transport Company](#-air-transport-company) | Airline entity |
| [Flight Plan](#-flight-plan) | Flight configuration |
| [User](#-user) | System user |

---

## ✈️ Aircraft Model

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Commercial aircraft model characterized by its physical and performance properties.

**Attributes:**
- `modelName`
- `weight`
- `MTOW` (Maximum Take-Off Weight)
- `fuelCapacity`
- `cruiseSpeed`
- `aerodynamicCoefficients`

**Constraints:**
- `MTOW > 0`
- `fuelCapacity > 0`
- `cruiseSpeed > 0`

**Relationships:**
- Used in a [Flight Plan](#-flight-plan)

---

## 🏢 Air Transport Company

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
An airline that uses the system to manage its operations.

**Attributes:**
- `name`
- `airlineCode`
- `fleet`
- `registeredRoutes`

**Constraints:**
- `airlineCode` must be unique

**Relationships:**
- Owns multiple [Aircraft Models](#-aircraft-model)
- Employs pilots who create [Flight Plans](#-flight-plan)

---

## ✈️ Flight Plan

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
A structured plan created by a pilot describing a flight.

**Attributes:**
- `route`
- `aircraft`
- `dateTime`
- `fuel`
- `pilot`

**Constraints:**
- `dateTime` must be in the future
- `fuel >= minimum required fuel`

**Relationships:**
- Uses an [Aircraft Model](#-aircraft-model)
- Created by a [User](#-user)

**Notes:**
- May be imported via a DSL (Domain-Specific Language)

---

## 👤 User

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
A person with access to the system.

**Attributes:**
- `email` (unique)
- `name`
- `phoneNumber`
- `status` (active/inactive)
- `roles`
- `securityClearance`
- `skillsAssessment`

**Constraints:**
- `email` must be unique
- `status` ∈ {active, inactive}

**Relationships:**
- Can create [Flight Plans](#-flight-plan)

---