# spider
Solution to Interview Question
==============================

Assumptions
-----------
 - No validation
 - Not checking if URL has been crawled already
 - Not checking if a node has it's parent as child
 - Limited testing
 - Docker is available

Run the following command to run the unit tests:

    $ ./gradlew clean test
    
Running The Crawler
===================

    $ ./gradlew clean execute -Ddomain=<the_domain_to_crawl>
    
    e.g. ./gradlew clean execute -Ddomain=http://localhost
    
Below are instructions for setting up an nginx docker container for a test run

1. Setup nginx, this is a one time only command

    $ docker run --name nginx-container -v <project-to-project>/spider/site:/usr/share/nginx/html:ro -p <http-port>:80 -d nginx
    
Please set <project-to-project> and <http-port> per your environment
 
 2. Subsequently, do
 
    $ docker start nginx-container
    
    
What I would do differently given more time
===========================================

 - I don't like the fact that Crawler#findChildren is private on Crawler thus only tested indirectly.
As this method holds the core of the logic, I would have loved for it to have better testing coverage around it.

- The application runs as a web application in test mode in order to make use of the gretty plugin's capability of starting Jetty before tests and shutting it down after the tests.

- The crawler cannot quite process more than one URL at a time.

- See assumptions above for other areas for improvement.