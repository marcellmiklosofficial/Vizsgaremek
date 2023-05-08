# Vizsgaremek

## About

* This project was created as the final exam for the Junior Automation Test Engineer course at [Codecool](https://codecool.com/hu/)
* Project status: Prototype
* Test cases: [Link](https://docs.google.com/spreadsheets/d/1rnPHnXwkC28fUkvBDFhVkE13j3qu2gfbwru6VXaqTQE/edit?usp=sharing)
* Created by: Marcell Miklós

## Table of contents

> * [Vizsgaremek](#vizsgaremek)
>   * [About](#about)
>   * [Table of contents](#table-of-contents)
>   * [Installation](#installation)
>   * [Usage](#usage)
>     * [Features](#features)

## Installation

* Run in your preferred IDE (tested only on IntelliJ Idea) locally after cloning the repository
* Build tool: [Maven](https://maven.apache.org/download.cgi)
* Report dependency: [Allure](https://docs.qameta.io/allure/)

## Usage

* Build and run the tests
* Use `allure serve .\target\allure-results\` to view test result report locally
* See online test results [here](https://marcellmiklosofficial.github.io/Vizsgaremek/)

### Features

* Tests run on multiple threads simultaneously
* Browser mode is environment dependent
* Instantiation of all Page Objects are handled by one generic method lazily
* WebDrivers creation is handled by a Factory
* Test Utilities and Constants are in separate classes
* JSON, YAML and properties types are used
* i18n ready via ResourceBundle integration
