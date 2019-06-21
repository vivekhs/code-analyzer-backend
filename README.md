# Deployment Instructions
- Prerequisites
    -	 JDK 1.8
    - [infer](https://fbinfer.com/docs/getting-started.html)
    - python 2.7
    - gcc compliler
    - maven 3.x
    - MongoDB 4.x
    
- Setup
    - mongodb uri is in applicatio.properties, by default it is mongodb://localhost:27017/codeanalyser
    - For code analysis fb infer is used, please update the infer path in codeanalyser.properties file. By default it is infer.path=/usr/local/bin/infer
    - mvn install
    - java -jar target/code-analyzer-0.1.0.jar 
    - By default port is 5000
    
 
   
   
