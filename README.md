# netflix-backend

This project is about to create an application that stores videos with recommendations and you can update video or add new recommendation for it.<br>
This application first of all is a practice for me to use micro services.<br>

In this state of the project,<br>
  -there is an eureka server where video-services and recommendation-services can be registered: hhttp://localhost:8761/ <br>
  -it can list all videos throught zuul-api at: http://localhost:8762/app/videos/ <br>
  -it can get info about a video with all its recommendations at: http://localhost:8762/app/videos/{videoId} <br>
  
# technologies

This project has been written in Java8 using Spring technologies.<br>
-springboot-devtools<br>
-springboot-security<br>
-swagger<br>
-eureka<br>
-lombok<br>
-h2<br>
-jpa<br>

# config

Run all pom.xml to setup configurations.
