**Clean Compose Architecture Android Project**

## **Overview**
This Android project demonstrates the implementation of Clean Architecture with Jetpack Compose for building modern and maintainable 
Android applications. Clean Architecture separates the concerns of an application into distinct layers, 
promoting testability, maintainability, and scalability. Jetpack Compose is used for building the user interface, 
providing a declarative and reactive approach to UI development.

## **Methodologies and Tools**
Clean Architecture: Divide the app into distinct layers (presentation, domain, data) for easier maintenance and testing.
Jetpack Compose: Employ modern UI toolkit for building beautiful and dynamic user interfaces.
Single Activity Pattern: Enhance performance and navigation by adopting a single activity with multiple composables.
MVVM Architecture: Ensure separation of concerns and streamline data flow using Model-View-ViewModel architecture.
Hilt/Dagger2 for Dependency Injection: Manage dependencies efficiently and promote modularity.
Modularization: Break down the app into smaller, independent modules for enhanced scalability and parallel development.
Version Catalog: Centralize dependency versions for easier management and updates.

## **Dependencies**
Jetpack Compose: Modern UI toolkit for building native Android apps.
StateFlow: Architecture components for managing UI-related data and lifecycle awareness.
Hilt: Dependency injection library for managing dependencies and providing dependencies to components.
Retrofit: Type-safe HTTP client for making network requests.
Room: Persistence library for managing local databases and data caching.