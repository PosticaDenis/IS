# Informational Security - Laboratory Work # 1

## Implemented functionalities:
1. The user is able to send messages through UDP or/and TCP.  
To send message through TCP use:  
`-tp <host_address>:<port> -m "<message_body>"`  
 To send message through UDP use:  
`-up <host_address>:<port> -m "<message_body>"`  

2. The user is able to turn himself into a UDP and/or UDP Server, listening for mesages from other users.  
To start accepting TCP connections and start listening for messages use:  
`-tp <host_address>:<port> -l`  
To start listening for messages, sent through UDP, use:  
`-up <host_address>:<port> -l`  
3. The user is able send a message at fixed intervals (every x seconds send something, with the possibility to indicate the maximum number of messages).  
To send message through TCP, at fixed intervals and indicating the maximum # of messages, use:  
`-tp <host_address>:<port> -m "<message_body>" -i <interval_in_seconds> -max <max_#_of_messages>`    
To send message through UDP, at fixed intervals and indicating the maximum # of messages, use:  
`-up <host_address>:<port> -m "<message_body>"  -i <interval_in_seconds> -max <max_#_of_messages>` 
4. The user can scan for free port, on a given IP.  
To scan all the ports use:  
`scan <ip_address>`  
To scan for free port, indicating a range, use:  
`scan <ip_address> -r <from_port-to_port>`

##  Project Structure
`java-----tcp-------TCPConnectionAccepter.java`  
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|--TCPServer.java`  
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|--udp------UDPServer.java`  
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|--utils----IpCheckerUtil.java`  
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|--timer-----ITimerTask.java`   
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|--TimerTCPTask.java`  
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|` &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|--TimerUDPTask.java`  
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;`|--MainSocket.java`   
 
`TCPConnectionAccepter.java` - this class is used to accept client connections for TCP communication.  
`TCPServer.java` - it is used to read messages sent through TCP, by the client.  
`UDPServer.java` - it is used to read messages sent through UDP, by the client.  
`IpCheckerUtil.java` - is is used to validate the server address introduced by the user. Is is used when user wants to user the program as TCP/UDP server or client.   
`ITimerTask.java` - interface class for timer.  
`TimerTCPTask.java` - it is used to sent TCP messages at fixed intervals.        
`TimerUDPTask.java` - it is used to sent UDP messages at fixed intervals.  
`MainSocket.java` - main class of the program, used to read user commands and call corresponding methods.  


## System description
  To be able to continuously read commands from user, it is used an `while(true)` loop, the program uses the command introduced by the user, in the command line. The command is read as `String`, splited and depending on the command structure, performs different actions. Indifferent of the command, it is used an IpValidator to validate the format of the introduced server address.

  To forbid the creation of 2 the same listeners (two TCP/UDP), for the same client, I use use 2 static variables, which are checked for `null` value, before creating the corresponding classes. I.e. if user will call the command for starting listener, an message will be returned, saying that the listener is already started.
  
All the classes responding for connections acceptance and messages processing are extending Thread class, this way, when calling their methods, the `while(true)` loops from those classes won't interfere with the main one.
  
