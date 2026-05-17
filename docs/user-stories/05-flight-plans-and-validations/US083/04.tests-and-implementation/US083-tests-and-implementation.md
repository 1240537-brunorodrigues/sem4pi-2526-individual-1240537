# US083 - Core Flight DSL

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the Core Flight DSL specification and validation pipeline.

The implementation should include:

* definition of the Core Flight DSL grammar;
* lexical analysis of DSL content;
* syntactic analysis of token streams;
* parse tree or AST creation;
* mapping of the parse tree/AST into a flight plan DSL model;
* semantic analysis of the DSL model;
* meaningful error reporting;
* distinction between lexical, syntactic and semantic errors;
* no persistence of flight plans during DSL validation.

This user story provides the DSL validation foundation used by US081.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `CoreFlightDslSpecification`
* `CoreFlightDslValidatorUI`
* `CoreFlightDslValidatorController`
* `CoreFlightDslValidatorService`
* `DslValidationRequest`
* `DslValidationResultDTO`
* `FlightPlanDslModelDTO`
* `ValidationErrorDTO`
* `CoreFlightDslLexer`
* `CoreFlightDslParser`
* `FlightPlanAstMapper`
* `CoreFlightDslSemanticValidator`
* `LexerResult`
* `ParserResult`
* `SemanticValidationResult`
* `TokenStream`
* `Token`
* `TokenType`
* `ParseTree`
* `ParseNode`
* `FlightPlanDslModel`
* `ValidationError`
* `ValidationErrorType`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that valid DSL content is tokenized successfully.
* Verify that invalid characters produce lexical errors.
* Verify that lexical errors include line and column information.
* Verify that valid token streams are parsed successfully.
* Verify that invalid DSL structure produces syntactic errors.
* Verify that syntactic errors include line and column information.
* Verify that a valid parse tree can be mapped into a flight plan DSL model.
* Verify that missing required flight plan fields produce semantic errors.
* Verify that invalid domain references produce semantic errors.
* Verify that invalid values produce semantic errors.
* Verify that semantic errors include meaningful messages.
* Verify that a syntactically valid but semantically invalid DSL file is rejected.
* Verify that a valid DSL file produces a valid internal flight plan representation.
* Verify that DSL validation does not persist flight plans.
* Verify that lexical, syntactic and semantic errors are distinguishable.

---

### 4.4. Acceptance Tests

**Test 1:** Valid Core Flight DSL file

* **Given** a file written according to the Core Flight DSL grammar
* **And** the file contains semantically valid flight plan data
* **When** the DSL file is validated
* **Then** lexical analysis should succeed
* **And** syntactic analysis should succeed
* **And** semantic analysis should succeed
* **And** a valid internal flight plan representation should be produced

**Test 2:** Lexical error

* **Given** DSL content with invalid characters or tokens
* **When** the DSL content is validated
* **Then** lexical analysis should fail
* **And** the system should return a meaningful lexical error

**Test 3:** Syntactic error

* **Given** DSL content with valid tokens but invalid structure
* **When** the DSL content is validated
* **Then** syntactic analysis should fail
* **And** the system should return a meaningful syntactic error

**Test 4:** Semantic error

* **Given** DSL content with valid syntax
* **And** invalid domain meaning or references
* **When** the DSL content is validated
* **Then** semantic analysis should fail
* **And** the system should return a meaningful semantic error

**Test 5:** Missing required field

* **Given** DSL content missing a required flight plan field
* **When** the DSL content is validated
* **Then** semantic analysis should fail
* **And** the missing field should be identified

**Test 6:** Valid DSL does not create a flight plan by itself

* **Given** valid DSL content
* **When** the DSL content is validated through this user story
* **Then** an internal representation should be produced
* **And** no flight plan should be persisted by the DSL validator itself

**Test 7:** Error location reporting

* **Given** invalid DSL content
* **When** validation fails
* **Then** the returned error should include the error type
* **And** line and column information when available
* **And** a meaningful message

---

### 4.5. Implementation Status

Not implemented yet.