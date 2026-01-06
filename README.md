# Amazon Selenium Automation Project

## Overview

This project demonstrates a real-world Selenium automation framework for an e-commerce workflow using **Amazon** as the target application.

It showcases best practices in:
- Page Object Model (POM)
- Explicit waits and synchronization
- Window and tab handling
- Screenshot capture
- Logging and reporting
- Handling dynamic and unstable UI behavior

The focus of this project is **framework design, stability, and professional automation decision-making**, not bypassing security mechanisms.

---

## Tech Stack

- **Java**: 17  
- **Selenium WebDriver**: 4.x  
- **Test Framework**: TestNG  
- **Build Tool**: Maven  
- **Driver Management**: WebDriverManager  
- **Logging**: Log4j2  
- **Reporting**: TestNG default reports (Extent optional)

---

## Automated Scenario

1. Navigate to Amazon homepage  
2. Search for a product (e.g., *Laptop*)  
3. Click the first valid product from search results  
4. Handle new tab or window if opened  
5. Validate presence of **Add to Cart** button  
6. Capture screenshot of the product details page  

---

## Project Structure


amazon-selenium-automation
│
├── pom.xml
├── README.md
│
├── src
│ ├── main
│ │ └── java
│ │ ├── driver // WebDriver setup
│ │ ├── pages // Page Object classes
│ │ └── utils // Wait & Screenshot utilities
│ │
│ └── test
│ ├── java
│ │ ├── base // Base test setup
│ │ ├── listeners // Test listeners
│ │ └── tests // Test cases
│ └── resources
│ └── testng.xml
│
├── screenshots // Test screenshots
└── logs // Execution logs


---

## How to Run

### Using Maven

```bash
mvn clean test

Using TestNG

Open testng.xml

Right-click

Run As → TestNG Suite

Screenshots & Logging

Screenshots are captured after successful validation

Stored under the screenshots/ directory

Timestamped filenames prevent overwriting

Execution logs are written to the logs/ directory using Log4j2

Real-World Challenges Addressed
1. Dynamic & Frequently Changing DOM

Amazon pages are highly dynamic and change frequently due to:

Sponsored ads

A/B experiments

Dynamic rendering

Mitigation

Stable and scoped locators

Explicit waits instead of static delays

2. Sponsored Products in Search Results (Amazon-Specific)

Amazon search results frequently include Sponsored Products that behave differently from organic results.

Observed Behavior

Sponsored items often appear at the top

They may open in new tabs

DOM structure and position change between page loads

Impact

Clicking the absolute first visible result leads to flaky tests

Design Decision

Automation targets the first valid, stable product link

In some runs, this may be the second or third visible product

This prioritizes test reliability over positional assumptions, reflecting real-world automation strategy.

3. Window / Tab Handling

Clicking a product may open in a new browser tab or window

Window-switching logic ensures validation occurs on the correct page

4. Cloudflare & Bot Detection

Amazon uses advanced bot-detection mechanisms.

Automated browsers may encounter:

“Verify you are human” challenges

Temporary blocking

Important

Security mechanisms are intentionally not bypassed

Test flows avoid protected endpoints while still validating core functionality

5. Stale Element Reference Handling

DOM refresh after search can cause stale element references.

Mitigation

Elements are re-located after navigation

No caching of WebElement instances

Wait utilities always return fresh elements

6. Add to Cart Button Variability

The Add to Cart button structure varies based on:

Product type

Availability

Region

Validation is done using presence and visibility checks, not fixed IDs.

Design Decisions

Page Object Model for maintainability and scalability

Explicit waits preferred over implicit waits

No CAPTCHA or security bypass attempts

Framework designed to be reusable across e-commerce platforms

Known Limitations

Amazon search may occasionally trigger bot detection

CAPTCHA / Cloudflare scenarios are intentionally excluded

Focus is on UI automation best practices, not security testing

Why This Project

This repository demonstrates:

Real-world Selenium framework design

Handling of dynamic e-commerce UI behavior

Professional automation judgment


Note

In enterprise environments, automation is executed in QA/UAT environments where:

Bot protection is disabled, or

Automation IPs are whitelisted

This project reflects industry-standard practices, not shortcuts.