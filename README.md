# FTP_Implementation

This is a simple implementation of File Transfer Protocol

It consists of two Client and Server Module

Server Module:
As soon as the server is started it will be waiting for the client to connect.
If the client what to send a file, the server will receive the file and store it in the same directory as of code.
If the client requests for some file, If the file is present in server's directory it will send the file else it will throw an exception.

Client Module:
As soon as the client is started it will connect with the server.
It will have a menu where it can select if it wants to send or receive the files.
If it wants to send a file it should present in the directory where the code is present else it will throw an exception.
If it wants to receive, the received file will be stored in the same directory as the code.
