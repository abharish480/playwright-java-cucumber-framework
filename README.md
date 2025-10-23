---

# 🚀 Playwright Java Cucumber Framework  

[![Build Status](https://github.com/abharish480/playwright-java-cucumber-framework/actions/workflows/maven.yml/badge.svg)](https://github.com/abharish480/playwright-java-cucumber-framework/actions)  
[![Build Status](https://github.com/abharish480/playwright-java-cucumber-framework/actions/workflows/manual-trigger.yml/badge.svg?branch=main)](https://github.com/abharish480/playwright-java-cucumber-framework/actions/workflows/manual-trigger.yml)
[![Allure Report](https://img.shields.io/badge/Allure-Report-ff69b4)](https://abharish480.github.io/playwright-java-cucumber-framework/)  
![Playwright](https://img.shields.io/badge/Playwright-Automation-brightgreen)  
![Java](https://img.shields.io/badge/Java-17-blue)  
![Maven](https://img.shields.io/badge/Maven-Build-orange)  
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-green)  
![TestNG](https://img.shields.io/badge/TestNG-Runner-lightgrey)  
![License](https://img.shields.io/badge/License-MIT-green)  

A modern **UI automation framework** built with **Playwright**, **Java**, **Cucumber**, and **Maven**, designed for scalability, maintainability, and CI/CD integration.  
This framework follows the **Page Object Model (POM)** pattern, supports **parallel execution** with `ThreadLocal`, integrates with **Allure Reports**, and runs seamlessly on **GitHub Actions**.

---

## ✨ Features

- 🎭 **Playwright (Java)** – Fast, reliable browser automation across Chromium, Firefox, and WebKit.  
- 🥒 **Cucumber (BDD)** – Behavior‑Driven Development with Gherkin syntax for human‑readable test scenarios.  
- 🏗 **Page Object Model (POM)** – Clean separation of test logic and UI interactions.  
- 🧵 **ThreadLocal Driver Management** – Safe parallel execution without session conflicts.  
- 🧪 **TestNG Runner** – Flexible test execution, grouping, and parallelism.  
- 📊 **Allure Reports** – Rich, interactive test reports with screenshots, environment info, and CI metadata.  
- ⚙️ **Config Reader** – Centralized configuration via `config.properties` and Maven `-D` overrides.  
- 📂 **JSON Data Reader** – Externalized test data for data‑driven testing.  
- 🧷 **Soft Assertions Utility** – Collect multiple assertion results in a single test run.  
- ☁️ **GitHub Actions CI/CD** – Automated test execution and report publishing.  
- 📦 **Maven Build Tool** – Dependency management, build lifecycle, and integration with CI/CD pipelines.  

---

## 🏗 Project Structure

```
src
 ├── main
 │    ├── java
 │    │    ├── pages/          # Page Object classes
 │    │    ├── utils/          # Utilities (ConfigReader, JSON Reader, SoftAssert)
 │    │    └── drivers/        # ThreadLocal Playwright driver manager
 │    └── resources
 │         └── config.properties
 └── test
      ├── java
      │    ├── tests/stepdefs          # Test classes
      │    └── runners/                # TestNG runners
      └── resources
            ├── features/  
           └── testdata.json
```

---

## ⚙️ Configuration

- **config.properties** holds default values:
  ```properties
  env=QA
  browser=chrome
  headless=true
  threads=3
  ```
- Override at runtime with Maven:
  ```bash
  mvn clean test -Dbrowser=firefox -Dheadless=false -Denv=staging -Dthreads=2 -Dtags=@regression
  ```

---

## ▶️ Running Tests

- **Local run (headed):**
  ```bash
  mvn clean test -Dheadless=false
  ```

- **CI run (GitHub Actions):**
  ```bash
  mvn clean test -Dheadless=true
  ```

---

## 📊 Allure Reports

- Generate report locally:
  ```bash
  allure serve
  ```
- In CI, reports are published to **GitHub Pages** with:
  - ✅ Screenshots on failure  
  - ✅ Environment details (`env`, `browser`, `headless`, `threads`)  
  - ✅ Executor metadata (GitHub Actions run info)  

---

## 🔗 GitHub Actions Integration

- Workflow runs on push/PR and manual dispatch.  
- Executes tests in headless mode.  
- Publishes Allure reports to GitHub Pages.  
- Adds run metadata (branch, commit, run ID) to the **Executors** section in Allure.  

---

## 📚 Utilities

- **ConfigReader** → Reads from `config.properties` or Maven `-D` flags.  
- **JSON Reader** → Loads test data from JSON files.  
- **SoftAssert Utility** → Collects multiple assertion failures before failing a test.  

---

## ✅ Benefits

- Clean, modular, and maintainable test code.  
- Parallel execution with isolated browser contexts.  
- Rich reporting with screenshots, logs, and environment info.  
- Seamless local and CI/CD execution.  

---

## 🌟 Future Enhancements

- 🔄 Add API testing module.  
- 📦 Dockerized test execution.  
- ☁️ Integration with cloud providers (BrowserStack, Sauce Labs).  

```markdown
## ⚡ Quick Start

1. **Clone the repository**  
   ```bash
   git clone https://github.com/abharish480/playwright-java-cucumber-framework.git
   cd playwright-java-cucumber-framework
   ```

2. **Install dependencies**  
   Make sure you have installed:
   - Java 17+  
   - Maven 3.8+  
   - Node.js (required by Playwright)  

   Then install Playwright browsers:  
   ```bash
   mvn exec:java -e -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
   ```

3. **Configure environment**  
   Update `src/main/resources/config.properties` with your defaults (browser, env, headless, threads, etc.).

4. **Run tests**  
   ```bash
   mvn clean test -Dbrowser=chrome -Dheadless=false
   ```

5. **View reports**  
   ```bash
   allure serve allure-results
   ```
```
