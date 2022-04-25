[![Java CI](https://github.com/marcelmalewski/allegro-summer-experience-2022/actions/workflows/gradle.yml/badge.svg)](https://github.com/marcelmalewski/allegro-summer-experience-2022/actions/workflows/gradle.yml)

# allegro-summer-experience-2022
My email in recruitment process: mmalewski9@gmail.com
---
### My comments:
I used Java 17 with Spring.

* **First endpoint:**  
  (Repositories list)  
  I can get max 100 repositories from github api in one request.
  First i check number of public repostories and when it is higher than 100 then i use loop to get all of them.  
  
* **Second endpoint:**  
  (User data)  
  I used here method from first endpoint to get all repositories of user so i have to write less code.  

Both endpoints are using class "GithubService".  
Object of this class is created in two endpoints not extended so it is easier to test.  
In this class are implemented two class variables and one method that are used in both end points.  
Thanks to that i have less code.  

* **Testing:**  
  I created github account with some repostirories.  
  Thanks to that i can test my application on github user who will never change.  
  Also i used github actions to test my application in cloud.  

### How to launch:
First enter application folder, run the command "./gradlew build".  
In this folder will be created folder "build".  
Enter folder "build" and then enter folder "libs".  
Run the command "java -jar githubApi-0.0.1-SNAPSHOT.jar"  
