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