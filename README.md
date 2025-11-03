## **Introduction to BlackJack**

**BlackJack**, also know as Twenty One due to the special role of the number in the game, is arguably the oldest known casino game. The game played with 1 to 8 deck of cards allows the player to directly play against the casino and remains **one of the only mathematically beatable casino games.**

A small introduction of the rules can be found on the link: [Intro Video](https://www.youtube.com/watch?v=DwwaJGStuP0)

As seen a total of 5 actions can be taken by the player:


*   **Hit:** Draws a card
*   **Stand:** No more card to draw
*   **Double:** Doubles the bet and draws one card and stands
*   **Split:** Splits a value pair into two individual hands to play separately
*   **Surrender:** Forfiets the game by splitting the bet amount equally between the Casino and the Player.

# 🂡 Blackjack Strategy Lab

**Blackjack Strategy Lab** is a full-stack simulation and analysis platform designed to evaluate and optimize Blackjack betting strategies through large-scale Monte Carlo simulations. It combines a scalable Spring Boot backend with a responsive React frontend to deliver real-time insights and performance metrics.

---

## 🚀 Features

* **Simulation Engine**
  Run millions of Blackjack rounds asynchronously using a custom simulation engine with configurable rules, decks, and strategies.

* **User Authentication & Security**
  JWT-based login and registration system built with Spring Security, featuring role-based access and secure session handling.

* **Real-Time Analytics Dashboard**
  Interactive UI built with React + TypeScript, TailwindCSS, and Recharts for visualizing performance metrics, EV, and ROI.

* **Scalable System Architecture**
  Ready for distributed execution using Kafka event streaming and Redis caching for repeated simulations.

* **Persistence & Storage**
  PostgreSQL database integrated with Flyway migrations for structured simulation result storage.

---

## 🧩 Tech Stack

| Layer                             | Technologies                                   |
| --------------------------------- | ---------------------------------------------- |
| **Frontend**                      | React, TypeScript, Vite, TailwindCSS, Recharts |
| **Backend**                       | Java 21, Spring Boot 3, Spring Security, JWT   |
| **Database**                      | PostgreSQL, Flyway                             |
| **Messaging / Caching (planned)** | Kafka, Redis                                   |
| **Build Tools**                   | Maven, Docker (optional)                       |

---

## ⚙️ Setup & Installation

### Backend

1. Clone the repository

   ```bash
   git clone https://github.com/your-username/blackjack-strategy-lab.git
   cd blackjack-strategy-lab/backend
   ```

2. Configure PostgreSQL in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/blackjack
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

The backend runs at `http://localhost:8080`.

---

### Frontend

1. Navigate to frontend:

   ```bash
   cd ../frontend
   npm install
   npm run dev
   ```
2. Open the app at [http://localhost:5173](http://localhost:5173)

---

## 🔒 Authentication API

| Endpoint             | Method | Description              |
| -------------------- | ------ | ------------------------ |
| `/api/auth/register` | `POST` | Register a new user      |
| `/api/auth/login`    | `POST` | Authenticate and get JWT |
| `/api/test/secure`   | `GET`  | Example secured endpoint |

Attach the JWT token in your headers:

```http
Authorization: Bearer <your_token>
```

---

## 📊 Simulation API (WIP)

| Endpoint        | Method | Description                          |
| --------------- | ------ | ------------------------------------ |
| `/api/simulate` | `POST` | Run a Blackjack strategy simulation  |
| `/api/results`  | `GET`  | Retrieve previous simulation results |

---

## 🧠 System Design (Planned Extensions)

* **Event-driven architecture:** Kafka-based simulation request and completion events.
* **Redis caching:** Store previously computed configurations to reduce redundant simulations.
* **Horizontal scaling:** Worker nodes consuming from Kafka for high-throughput batch simulations.
* **Progress tracking:** WebSocket updates for long-running simulation jobs.

---

## 📸 UI Preview (coming soon)

*(Insert screenshots of the dashboard and results page here once ready.)*

---

## 🧑‍💻 Author

**Ashmit Garg**
[LinkedIn](https://linkedin.com/in/your-profile) · [GitHub](https://github.com/your-username)

---

> “Built for scale, tested with probability, designed with precision — a lab for Blackjack intelligence.”
