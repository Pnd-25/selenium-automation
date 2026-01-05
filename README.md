Amazon Selenium Automation Project
Overview

This project demonstrates a real-world Selenium automation framework for an e-commerce workflow using Amazon as the target application.
It showcases best practices in Page Object Model (POM) design, synchronization, window handling, screenshots, logging, and reporting.

The focus of this project is framework design, stability, and professional decision-making, not bypassing security mechanisms.

Tech Stack

Java: 17

Selenium WebDriver: 4.x

Test Framework: TestNG

Build Tool: Maven

Driver Management: WebDriverManager

Logging: Log4j2

Reporting: TestNG default reports (Extent optional)

Automated Scenario

Navigate to Amazon homepage

Search for a product (e.g., Laptop)

Click the first valid product from search results

Handle new tab/window if opened

Validate presence of Add to Cart button

Capture screenshot of the product details page

Project Structure
amazon-selenium-automation
│
├── pom.xml
├── README.md
│
├── src
│   ├── main
│   │   └── java
│   │       ├── driver        → WebDriver setup
│   │       ├── pages         → Page Object classes
│   │       └── utils         → Wait & Screenshot utilities
│   │
│   └── test
│       ├── java
│       │   ├── base          → Base test setup
│       │   ├── listeners     → Test listeners
│       │   └── tests         → Test cases
│       └── resources
│           └── testng.xml
│
├── screenshots               → Test screenshots
└── logs                      → Execution logs

How to Run
Using Maven
mvn clean test

Using TestNG

Open testng.xml

Right-click → Run As → TestNG Suite

Screenshot & Logging

Screenshots are captured after successful validation

Stored under the screenshots/ directory

Timestamped filenames prevent overwriting

Execution logs are written to the logs/ directory using Log4j2

Real-World Challenges Addressed
1. Dynamic & Frequently Changing DOM

Amazon pages are highly dynamic and change frequently.

Sponsored ads, A/B testing, and dynamic rendering affect element locators.

Mitigation:

Stable, scoped locators

Explicit waits instead of static delays

2. Sponsored Products in Search Results (Amazon-Specific)

Amazon search results frequently include Sponsored Products that behave differently from organic results.

Observed behavior:

Sponsored products often appear at the top of search results

They may open in new tabs

DOM structure and position can change between page loads

Impact on automation:

Clicking the absolute first visible result can lead to inconsistent behavior

Sponsored items may cause flaky execution due to non-deterministic UI changes

Design decision:

The automation targets the first valid, stable product link

In some executions, this may result in selecting the second or third visible product

This approach prioritizes test reliability over positional assumptions

This reflects real-world automation strategy where:

Sponsored content is treated as non-deterministic

Stability is preferred over marketing placement

3. Window / Tab Handling

Clicking a product may open in a new browser tab or window

Window-switching logic ensures validation occurs on the correct page

4. Cloudflare & Bot Detection

Amazon uses advanced bot-detection mechanisms

Automated browsers may encounter:

“Verify you are human” challenges

Temporary blocking

Security mechanisms are intentionally not bypassed

Test flows are adjusted to avoid protected endpoints while still validating core functionality

5. Stale Element Reference Handling

DOM refresh after search can cause stale element references

Mitigation:

Elements are re-located after navigation

No caching of WebElement instances

Wait utilities return fresh elements

6. Add to Cart Button Variability

The “Add to Cart” button structure varies based on:

Product type

Availability

Region

Validation is performed using presence and visibility checks rather than fixed IDs

Design Decisions

Page Object Model for maintainability and scalability

Explicit waits preferred over implicit waits

No CAPTCHA or security bypass attempts

Framework designed to be reusable across e-commerce platforms

Known Limitations

Amazon search may occasionally trigger bot-detection

CAPTCHA / Cloudflare scenarios are intentionally excluded

This project focuses on UI automation best practices, not security testing

Why This Project

This repository demonstrates:

Real-world Selenium framework design

Handling of dynamic e-commerce UI behavior

Professional automation judgment

Interview-ready coding standards

Note

In enterprise environments, automation is executed in QA/UAT environments where bot protection is disabled or automation IPs are whitelisted.