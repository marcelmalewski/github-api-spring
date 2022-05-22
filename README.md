[![Java CI](https://github.com/marcelmalewski/allegro-summer-experience-2022/actions/workflows/gradle.yml/badge.svg)](https://github.com/marcelmalewski/allegro-summer-experience-2022/actions/workflows/gradle.yml)

# Github api

## Table of contents
* [Technologies](#technologies)
* [Endpoints](#endpoints)
* [How to launch](#how-to-launch)
	
## Technologies
Project is created with:
* Java version: 17.0.1
* Spring version: 2.6.7
* JUnit 5
* Gradle

## Endpoints
### 1 endpoint
Return list of repositories of a given user along with information about programming languages used in repository
(language name, number of bytes of code in a given language).

### 2 endpoint
Return user data (login, name, bio) with aggregated information about languages
programming used in its repositories (language name, number of bytes of code in a given language).

## How to launch:
First enter application folder, run the command "./gradlew build".  
In this folder will be created folder "build".  
Enter folder "build" and then enter folder "libs".  
Run the command "java -jar githubApi-0.0.1-SNAPSHOT.jar"  
