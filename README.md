# AI Resume Analyzer

![Application Screenshot](assets/ui-screenshot.png)
*Caption: The AI Resume Analyzer web interface.*

An intelligent web application that evaluates resumes using natural language processing. Users submit their resume text, and the system leverages Google's Gemini AI to provide an overall quality score (out of 100) along with targeted, actionable feedback. 

## Architecture Style

![Architecture Diagram](assets/architecture-diagram.png)
*Caption: High-level client-server data flow and system architecture.*

This application follows a **Client-Server Architecture** utilizing a **Layered (N-Tier) Monolithic Backend**. 

### Backend Layers (Spring Boot)
1. **Controller Layer (`controller.java`):** Exposes RESTful HTTP endpoints (`/api/resume/analyze`) and handles incoming web requests and outgoing JSON responses.
2. **Service Layer (`service.java`):** Encapsulates the core business logic. It constructs the specific prompting instructions, manages the synchronous HTTP connection to the Google Gemini API using Spring AI, and parses the LLM's text output into structured data fields.
3. **Repository Layer (`repository.java`):** An abstraction layer over the database utilizing Spring Data JPA. It translates Java object operations into SQL queries.
4. **Data Layer (`model.java` & MySQL):** The `model` entity maps directly to the `resume_records` MySQL table, defining the schema (e.g., storing long-form text as `TEXT` data types) to persist user submissions and AI feedback.

### Frontend
A lightweight, static **Single Page Application (SPA)** built with Vanilla JavaScript, HTML, and CSS. It operates asynchronously, using the Fetch API to communicate with the backend without requiring page reloads.

## Tech Stack

*   **Backend:** Java 17, Spring Boot, Spring AI, Spring Data JPA, Hibernate
*   **AI Integration:** Google Gemini API (`ChatClient`)
*   **Database:** MySQL 8.0
*   **Frontend:** HTML5, CSS3, Vanilla JavaScript
*   **Build Tool:** Maven

## How It Works (Data Flow)

1. **User Input:** The user pastes their candidate name and resume text into the frontend HTML form and clicks "Analyze".
2. **API Call:** The JavaScript intercepts the form submission, prevents the default page reload, and sends a `POST` request containing the text to the backend at `http://localhost:8080/api/resume/analyze`.
3. **Prompt Engineering:** The Spring Boot `controller` routes the request to the `service`. The service dynamically injects the resume text into a strict instruction prompt, demanding a numeric score on the first line and text feedback on the subsequent lines.
4. **AI Processing:** The `service` transmits the prompt to the Google Gemini API. Gemini processes the text against standard IT recruiter benchmarks and returns a tailored response.
5. **Data Parsing & Persistence:** The `service` splits the returned string to isolate the integer score and the string feedback. It instantiates a new `model` object with these values and calls the `repository` to execute an `INSERT` statement into the MySQL database.
6. **UI Update:** The backend returns the saved record as a JSON object to the frontend. The JavaScript reads the `matchScore` and `aiFeedback` attributes and dynamically updates the DOM to display the results to the user.

## Prerequisites

*   Java 17 or higher
*   Maven installed
*   MySQL Server installed and running on default port `3306`
*   A valid Google Gemini API Key

## Setup & Installation

**1. Database Configuration**
Create a new database in your local MySQL instance:
```sql
CREATE DATABASE studentdb;
