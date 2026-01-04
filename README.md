# PlanEats_2025: AI-Powered Healthy Meal Planner
**Sustainable Development Goals (SDG) Alignment:** Goal 3 - Good Health and Well-being.


## 👥 Group Information
**Naufal Apta Rivarizq Ghathfaan** - 23523262
**Muhammad Bagas Raya** - 23523223
**Ahsani Taufiq Khawarizmi** - 23523279
**Brilliant Hard** - 23523272


### 🔑 App Login Credentials
**Email:** joseph@gmail.com
**Password:** joseph123


## 🚀 Mandatory Components
This application adheres to all mandatory project requirements:
**Platform:** Mobile-based application.
**Tech Stack:** Built using **Kotlin** and **Jetpack Compose**.
**CRUD Functionalities:** Full implementation of Create, Read, Update, and Delete for meal entries.
**Architecture:** Follows the **MVVM (Model-View-ViewModel)** design pattern.
**Database:** Integrated with **Firebase Firestore** for dynamic data storage and retrieval.
**Interface:** Features more than four distinct screens/interfaces.


## 📱 Interface Relation Design
The application follows a structured user flow across multiple screens:
**1. Splash Screen:** The initial branding screen upon opening the app.
**2. Login/Register Screen:** User authentication gateway; leads directly to the **Home Screen** upon success.
**3. Home Screen:** The main dashboard displaying daily calorie summaries and meal history (Read).
**4. Add Meal Screen:** A dynamic input form where users add new meal entries (Create) assisted by AI.
**5. Detail & Edit Screen:** View comprehensive nutritional data, modify existing records, or remove entries (Update & Delete).


## 📊 Database Design
The application utilizes a multi-table database structure (minimum 2 tables):

### 1. Table: `Users`
Stores user profiles and health targets.
**Attributes:** `uid` (Primary Key), `fullName`, `email`, `calorieGoal`.

### 2. Table: `Meals`
Records daily nutritional intake.
**Attributes:** `mealId` (Primary Key), `userId` (Foreign Key), `title`, `calories`, `category`, `healthRating`, `aiFeedback`, `date`, `time`.


## 💡 AI & Gamification
To enhance user engagement and utility, the following advanced features are implemented:
**AI Integration:** Leverages **Gemini AI** to automatically estimate calories and provide tailored nutritional feedback based on meal titles.
**Gamification:** Using the **Achievement** technique to increase user engagement.
