# Vehicle Rental Service

## 1. Introduction

### 1.1 Project Overview

The **Vehicle Rental Service** is a Java-based console application designed to simulate the main operations of a vehicle rental system. The system allows users to view available vehicles, rent a vehicle, return a rented vehicle, and view active bookings.

The project was developed as a team project with the main purpose of demonstrating the practical use of **six design patterns** from three different categories: Creational, Behavioral, and Structural patterns.

The following design patterns were implemented:

- **Factory Method** — for creating different types of vehicles.
- **Builder** — for constructing booking objects.
- **Strategy** — for calculating rental prices using different pricing methods.
- **Observer** — for notifying customers and administrators about vehicle availability changes.
- **Adapter** — for integrating the bank payment API with the application's payment interface.
- **Facade** — for providing a simplified interface for the complete vehicle rental process.

The project follows the requirement that each team member implements two design patterns, while all six patterns are integrated into one cohesive Vehicle Rental Service.

### 1.2 Project Objectives

The main objectives of the project are:

1. To develop a functional Vehicle Rental Service using Java.
2. To demonstrate the practical implementation of six design patterns.
3. To separate different responsibilities between classes and components.
4. To demonstrate how different design patterns can work together in one system.
5. To provide a simple interface for vehicle rental operations.
6. To improve the maintainability, flexibility, and organization of the application.

### 1.3 Team Responsibilities

The project was developed collaboratively by three team members. Each team member was responsible for implementing two design patterns.

| Team Member | Design Patterns | Responsibility |
|---|---|---|
| Alnur | Strategy, Observer | Rental pricing and vehicle availability notifications |
| Alisher | Factory Method, Builder | Vehicle creation and booking construction |
| Arsen | Adapter, Facade | Payment integration and simplified rental workflow |

The six patterns were integrated into the same Vehicle Rental Service application rather than being implemented as separate projects.

---

# 2. Body

## 2.1 System Structure

The project is organized into several main parts:

```text
src/main/java/
├── Main.java
├── model/
│   ├── Vehicle.java
│   ├── Car.java
│   ├── Motorcycle.java
│   ├── Van.java
│   └── Booking.java
│
└── patterns/
    ├── creational/
    │   ├── factory/
    │   └── builder/
    │
    ├── behavioral/
    │   ├── strategy/
    │   └── observer/
    │
    └── structural/
        ├── adapter/
        └── facade/
```

The `model` package contains the main domain objects of the application. `Vehicle` is the abstract base class for different vehicle types, while `Car`, `Motorcycle`, and `Van` represent concrete vehicle types. The `Booking` class represents a rental booking.

The `patterns` package contains the six implemented design patterns.

---

## 2.2 Factory Method Pattern

The **Factory Method** pattern is used to create different types of vehicles.

The system contains the abstract `VehicleFactory` class and three concrete factories:

- `CarFactory`
- `MotorcycleFactory`
- `VanFactory`

Each factory creates its corresponding vehicle object.

For example:

```text
VehicleFactory
      |
      +-- CarFactory
      +-- MotorcycleFactory
      +-- VanFactory
```

The main advantage of this approach is that the code responsible for creating vehicles is separated from the main application logic.

When the application initializes the system, it can create different vehicles using their corresponding factories without directly creating every concrete vehicle object in the main workflow.

---

## 2.3 Builder Pattern

The **Builder** pattern is used to construct `Booking` objects.

The `Booking` class contains several properties, including:

- booking ID;
- customer name;
- vehicle;
- rental days;
- rental hours.

The `BookingBuilder` class provides methods such as:

```text
setBookingId()
setCustomerName()
setVehicle()
setRentalDays()
setRentalHours()
build()
```

This makes the creation of a booking more readable and allows different booking configurations to be created step by step.

The Builder pattern is especially useful because a booking may use either hourly or daily rental duration.

---

## 2.4 Strategy Pattern

The **Strategy** pattern is one of the behavioral patterns implemented in the project.

It is used to support different rental pricing methods. The Vehicle Rental Service supports:

- hourly pricing;
- daily pricing.

The main components are:

```text
PricingStrategy
       |
       +-- HourlyPricingStrategy
       |
       +-- DailyPricingStrategy
       
PricingCalculator
```

`PricingStrategy` is an interface that defines the common `calculatePrice()` method.

`HourlyPricingStrategy` calculates the price based on the number of rental hours:

```text
hourly price × number of hours
```

`DailyPricingStrategy` calculates the price based on the number of rental days:

```text
hourly price × 24 × number of days
```

`PricingCalculator` acts as the context and uses the selected pricing strategy.

The Strategy pattern allows the pricing algorithm to be changed without changing the main rental logic.

For example, when the user selects hourly rental, the system uses `HourlyPricingStrategy`. When daily rental is selected, it uses `DailyPricingStrategy`.

---

## 2.5 Observer Pattern

The **Observer** pattern is used to notify users when the availability of a vehicle changes.

The main components are:

```text
Subject
   |
   +-- RentalAvailability
       
Observer
   |
   +-- CustomerObserver
   +-- AdminObserver
```

`Observer` defines the `update()` method.

`CustomerObserver` receives notifications related to the customer's rental.

`AdminObserver` receives notifications related to the administrator.

`RentalAvailability` implements the `Subject` interface and maintains a list of observers.

When a vehicle changes its availability status, `RentalAvailability` calls `notifyObservers()`. Each registered observer then receives the update.

For example:

```text
Vehicle rented
      ↓
RentalAvailability
      ↓
notifyObservers()
      ↓
CustomerObserver
AdminObserver
```

When a vehicle is rented, the system changes its status to unavailable and sends a notification. When the vehicle is returned, the availability changes back to available and the observers are notified again.

This corresponds directly to the intended use of Observer in the Vehicle Rental Service topic: rental availability notifications.

---

## 2.6 Adapter Pattern

The **Adapter** pattern is used to connect the Vehicle Rental Service with a bank payment API.

The project contains:

- `PaymentMethod` — the interface expected by the rental system;
- `BankApi` — an external payment service;
- `BankPaymentAdapter` — the adapter between the two interfaces.

The structure is:

```text
PaymentMethod
      ↑
      |
BankPaymentAdapter
      |
      ↓
   BankApi
```

The rental system does not need to know the internal interface of `BankApi`. Instead, it uses the `PaymentMethod` interface through the adapter.

This makes it easier to integrate the external payment service without changing the main rental system.

---

## 2.7 Facade Pattern

The **Facade** pattern provides a simplified interface for the main rental operations.

The main class is `RentalFacade`.

It combines several subsystems:

```text
RentalFacade
    |
    +-- PricingCalculator
    +-- PaymentMethod
    +-- RentalAvailability
```

The `rentVehicle()` method coordinates several operations:

1. Checks whether the vehicle is available.
2. Determines the rental type.
3. Calculates the rental price using Strategy.
4. Processes the payment using the Adapter.
5. Updates vehicle availability using Observer.
6. Completes the rental.

The `returnVehicle()` method also simplifies the return process and updates vehicle availability.

Therefore, the main application does not need to directly coordinate all these subsystems. It can use the `RentalFacade` as a single simplified interface.

---

## 2.8 Integration of Design Patterns

One of the main goals of the project is not only to implement six patterns, but also to integrate them into one working system.

The rental process demonstrates this integration.

For example, when a customer rents a vehicle:

```text
User
 ↓
Main
 ↓
BookingBuilder
 ↓
Booking
 ↓
RentalFacade
 ├── PricingCalculator
 │      └── PricingStrategy
 │            ├── HourlyPricingStrategy
 │            └── DailyPricingStrategy
 │
 ├── PaymentMethod
 │      └── BankPaymentAdapter
 │            └── BankApi
 │
 └── RentalAvailability
        ↓
     Observers
```

The Factory Method is used when vehicles are created during system initialization, while Builder is used when a customer creates a booking.

The Strategy pattern calculates the rental price, Adapter handles payment processing, Facade coordinates the complete rental process, and Observer updates interested users when vehicle availability changes.

This demonstrates that the six patterns are part of one cohesive system rather than independent examples.

---

## 2.9 Application Functionality

The application provides a console menu with the following operations:

1. **Show all vehicles**
2. **Rent a vehicle**
3. **Return a vehicle**
4. **View active bookings**
5. **Exit**

### Vehicle Listing

The user can view the available vehicles together with their IDs, brands, models, hourly prices, and availability status.

### Vehicle Rental

During the rental process, the user:

1. selects an available vehicle;
2. enters the customer name;
3. selects hourly or daily rental;
4. enters the rental duration;
5. creates a booking using Builder;
6. calculates the rental price using Strategy;
7. processes the payment using Adapter;
8. updates vehicle availability using Observer.

### Vehicle Return

The user can select an active booking and return its vehicle.

The system changes the vehicle status back to available and notifies registered observers.

### Active Bookings

The application stores active bookings and allows the user to view their information.

---

## 2.10 UML Diagrams

The UML diagrams demonstrate the relationships between the classes and design patterns used in the project.

The project should include:

- overall system/class diagram;
- Strategy pattern class diagram;
- Observer pattern class diagram;
- Factory Method class diagram;
- Builder class diagram;
- Adapter class diagram;
- Facade class diagram.

The UML diagrams are included in the `docs/uml` directory.

---

## 2.11 Screenshots

Screenshots demonstrate the application running in the console.

The screenshots should include examples of:

- the main menu;
- available vehicles;
- hourly rental;
- daily rental;
- successful payment;
- rental availability notification;
- successful vehicle return;
- active bookings.

The screenshots are stored in the `docs/screenshots` directory.

---

# 3. Conclusion

The Vehicle Rental Service project demonstrates how design patterns can be applied to solve common software design problems in a practical application.

Six design patterns were implemented:

- Factory Method;
- Builder;
- Strategy;
- Observer;
- Adapter;
- Facade.

Each pattern has a specific responsibility. Factory Method handles vehicle creation, Builder constructs bookings, Strategy handles pricing algorithms, Observer manages availability notifications, Adapter integrates the payment API, and Facade coordinates the main rental operations.

The integration of these patterns makes the application more organized and demonstrates how different design patterns can work together within one system.

The project also helped the team gain practical experience with object-oriented programming, interfaces, inheritance, composition, and software architecture.

---

# 4. Further Work

The current Vehicle Rental Service can be extended with additional functionality.

Possible future improvements include:

1. **Database integration** — store vehicles, customers, bookings, and payment records permanently.
2. **Graphical user interface** — replace the console interface with a desktop or web interface.
3. **Authentication** — add customer and administrator accounts.
4. **More vehicle types** — support additional vehicle categories.
5. **More pricing strategies** — add weekly pricing, seasonal pricing, discounts, or special offers.
6. **Real payment integration** — replace the simulated bank API with a real payment service.
7. **Improved notifications** — support email or other notification services.
8. **Booking history** — allow customers and administrators to view previous rentals.
9. **Automated testing** — expand unit and integration tests for all six design patterns.
10. **Database-based vehicle availability** — synchronize availability between multiple users.

These improvements could make the system closer to a real-world vehicle rental platform while keeping the existing design pattern structure.

---

# 5. Project Resources

The project source code is provided as a Java project and includes the implementation of all six design patterns.

The project also contains UML diagrams and application screenshots required for the final project report.

The final submission consists of the project report in PDF format and the source code in a ZIP archive.
