# Informational Security - Laboratory Work # 1

## Implemented functionalities:
1. The user is able to send messages through UDP or/and TCP.  
To send message through TCP use:  
`-tp <host_address>:<port> -m "<message_body>"`  
 To send message through UDP use:  
`-up <host_address>:<port> -m "<message_body>"`  

2. The user is able to turn himself into a UDP and/or UDP Server, listening for mesages from other users.  
To start accespting TCP connections and start listening for messages use:  
`-tp <host_address>:<port> -l`  
To start listening for messages, sent through UDP, use:  
`-up <host_address>:<port> -l`  
3. The user is able send a message at fixed intervals (every x seconds send something, with the possibility to indicate the maximum number of messages).  
To send message through TCP, at fixed intervals and indicating the maximum # of messages, use:  
`-tp <host_address>:<port> -m "<message_body>" -i <interval_in_seconds> -max <max_#_of_messages>`    
To send message through UDP, at fixed intervals and indicating the maximum # of messages, use:  
`-up <host_address>:<port> -m "<message_body>"  -i <interval_in_seconds> -max <max_#_of_messages>` 

## System description
  To be able to continously read commands from user, it is used an `while(true)` loop, the program uses the command introduced by the user, in the command line. The command is read as `String`, splited and depending on the command structure, performs different actions. Indifferent of the command, it is used an IpValidator to validate the format of the introduced server address.  

  To forbid the creation of 2 the same listeners (two TCP/UDP), for the same client, the corresponding classes are made Singletone. I.e. if user will call the command for starting listener, an message will be returned, saying that the listener is already started.  
  
All the classes responding for connections acceptance and messages processing are extending Thread class, this way, when calling their methods, the `while(true)` loops from those classes won't interfere with the main one.
  
