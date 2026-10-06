# Souq - Android E-Commerce App 🛒

A modern Android e-commerce application built with Kotlin and Jetpack Compose.

## 🛠 Tech Stack & Architecture
- **Language:** Kotlin
- **UI:** Jetpack Compose

The project is structured using a **Feature-based Architecture** to ensure modularity, scalability, and clean code principles:

```text
dev.aziz.souq/
├── data/
│   ├── network/       # Retrofit Services, ApiService, OkHttp
│   ├── local/         # Room Database, DAOs, DataStore Preferences
│   ├── model/         # Data Models (Product, Category, User, Cart)
│   └── repository/    # Repository implementations (Data Layer)
│
├── domain/            # Clean Models & Business Rules (Domain Layer)
│
├── ui/                # Presentation Layer (Jetpack Compose)
│   ├── theme/         # Colors, Typography, Shapes
│   ├── components/    # Reusable UI Components
│   ├── login/         # Authentication Screens & ViewModel
│   ├── home/          # Home Screen, Categories & ViewModel
│   ├── product_details/ # Product Details Screen & ViewModel
│   ├── cart/          # Cart Management & ViewModel
│   ├── checkout/      # Order Checkout Process
│   ├── profile/       # User Profile & Settings
│   └── drawer/        # Navigation Drawer
│
├── navigation/        # Jetpack Compose Navigation & Routes
├── utils/             # Helpers, Resource State, Extensions, NetworkObserver
├── di/                # Dependency Injection Setup
└── MainActivity.kt    # Main Entry Point