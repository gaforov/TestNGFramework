# TestNG Framework
_This is a Test Automation Framework demo that I have developed myself from the ground up._

I've incorporated both **Test-Driven** (TDD) and **Data-Driven** Testing (DDT) into this TestNG Framework
- based on Page Object Model (POM) Pattern Design. 
- _... and some tools I've implemented are:_
- **Java** as main language,
- **Selenium** as automation,
- **TestNG** for assertion and running tests,
- **XML files** for running tests such as **regression** and **smoke** tests,
- **Extent Reports** for generating **_beautiful reports_** after each test,
- **Maven** as an automation tool management used for dependency management using **Project Object Model** (pom.xml),
- **.properties files** for reading and retrieving test data from the config package,
- **Excel utility** function for reading and writing data to and from spreadsheets,
- **CommonFunctions** class added for reuse to avoid **DRY** (_Don't repeat yourself_) principle,
- and many more...

<p>&nbsp;</p>
1. Organized, minimalistic, and clean coding is the key!
<p>&nbsp;</p>

When someone pulls/opens your framework, you don't want this happen to them? 

- ![](screenshots/tupperware-fail_clean_coding.gif)

2. Follow this simple formula to achieve number 1. 
````mermaid
graph LR
A[Write Code] --> B{Does it work?} 
B -- Yes --> C[Great!]
B -- No --> D[Google/StackOverFlow]
D -- Research/Refactor --> A
C --- E{{is it clean code?}}
E -- Yes --> F[Great!]
E -- No -->D

````

- `System.out.println("Happy Coding!");`
