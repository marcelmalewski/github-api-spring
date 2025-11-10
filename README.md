[![Java CI](https://github.com/marcelmalewski/allegro-summer-experience-2022/actions/workflows/gradle.yml/badge.svg)](https://github.com/marcelmalewski/allegro-summer-experience-2022/actions/workflows/gradle.yml)

# Github API

## Endpoints
### 1 endpoint
Return list of repositories of a given user along with information about programming languages used in repository
(language name, number of bytes of code in a given language).

### 2 endpoint
Return user data (login, name, bio) with aggregated information about languages
programming used in its repositories (language name, number of bytes of code in a given language).

## Technologies
* Java 17
* Spring Boot 2
* JUnit 5
* Gradle

## Local development
First enter application folder, run the command "./gradlew build".  
In this folder will be created folder "build".  
Enter folder "build" and then enter folder "libs".  
Run the command "java -jar githubApi-0.0.1-SNAPSHOT.jar"  
