import re
from random import randint
from socket import *
import sys
String = "Server of Caleb J. Badour"
x = (randint(1, 100))
server_ip = "127.0.0.1"
server_port = 8000
#Create the server socket, using TCP
server_socket = socket(AF_INET, SOCK_STREAM)
#Bind the server ip and port to the server socket
server_socket.bind((server_ip, server_port))
#Listen for at most 1 incoming connection
server_socket.listen(1)
print("The server is ready to receive...")
#Once a connection has be established, create the connection socket for thespecific client.
while True:
    #Return value of accept is a pair where the first elements is a new socket and the second is the client's address
    connection_socket, client_address = server_socket.accept()
    #Receive the client's message
    client_message = connection_socket.recv(2048).decode()
    #Capitalize the client's message
    print(client_message)
    client_num = int(re.search(r'\d+', client_message).group())
    print(String)
    print(client_num)
    print(x)
    print(client_num + x)

    server_message = String + " " + str(x)
    #Send the new message back to the client via the connection socket
    connection_socket.send(server_message.encode())
    #Close the connection socket
    connection_socket.close()