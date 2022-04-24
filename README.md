# allegro-summer-experience-2022
My email in recruitment process: mmalewski9@gmail.com
---
### My comments:
I used Java with Spring.

* **First endpoint:**  
  (Repositories list)  
  I can get max 100 repositories from github api in one request.
  First i check number of public repostories and when it is higher than 100 then i use loop to get them all.
  When i parse json with repository data to my object, only class variable name is filled.  
  Object have variables: name, languages(map(string, int)).  
  Then i manually refill languages.
  
* **Second endpoint:**  
  (User data)
  I use function from first endpoint to get all repositories of user so i have to write less code.
  Then i use on this result another method that will get aggregated languages by bytes so there is less code i one method.

Both end points extends from abstract class "GithubService".
In this class are implemented two class variables and one method that are used in both end points.
Thanks to that i have less code.  
### How to install:
...

### How to launch:
...
