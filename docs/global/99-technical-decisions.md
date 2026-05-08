## TD-001 - Implement US056 before US055

### Context

According to the specification, US055 requires the registration of an aircraft model. However, an aircraft model must have at least one certified engine model associated with it.

US056 defines the registration of aircraft engine models.

### Decision

US056 will be analysed, designed and implemented before US055.

### Rationale

Although US055 appears before US056 in the specification, US055 depends on the existence of at least one aircraft engine model. Therefore, implementing US056 first reduces artificial assumptions and allows US055 to validate its business rules properly.

### Consequences

* Aircraft engine models can be registered before aircraft models.
* US055 can require and validate at least one certified engine model.
* The implementation order differs from the numerical order of the user stories.
* This decision should be referenced in both US055 and US056 documentation.