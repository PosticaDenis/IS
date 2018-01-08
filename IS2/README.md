# Informational Security - Laboratory Work #3: Slow HTTP POST vulnerability

## Overview

&nbsp; &nbsp; The web application is possibly vulnerable to a "slow HTTP POST" Denial of Service (DoS) attack. This is an application-level DoS that consumes server resources by maintaining open connections for an extended period of time by slowly sending traffic to the server. If the server maintains too many connections open at once, then it may not be able to respond to new, legitimate connections. Unlike bandwidth-consumption DoS attacks, the "slow" attack does not require a large amount of traffic to be sent to the server -- only that the client is able to maintain open connections for several minutes at a time. The attack holds server connections open by sending properly crafted HTTP POST headers that contain a Content-Length header with a large value to inform the web server how much of data to expect. After the HTTP POST headers are fully sent, the HTTP POST message body is sent at slow speeds to prolong the completion of the connection and lock up server resources. By waiting for the complete request body, the server is helping clients with slow or intermittent connections to complete requests, but is also exposing itself to abuse.

## Slow HTTP POST Demo

&nbsp; &nbsp; In order to show the possible effect of an slow HTTP Post attack on a web application, I created a simple CRUD web-app, in Groovy Grails, and used SwitchBlade to perform the slow HTTP POST attacks.   

SwitchBlade UI:
![Alt text](1.png?raw=true)  

Performance of the web-app before attack:
![Alt text](2.png?raw=true) 

Performance of the web-app with 150 connections:
![Alt text](3.png?raw=true) 

&nbsp; &nbsp; Untill 199 connections the web-app was able to load, slower, but it was able. Starting with 200 connections, web-app was no more available.

&nbsp; &nbsp; P.S. Tried this app on a random site (mostly probable not access by anyone in the last 10 years ...) I found (`http://www.genochoice.com/`), at 200 connections `This site can’t be reached` appeared, when tried to access the site.
