import re
from socket import *
#The client needs to know the server's ip address and the port at which the process is running.
server_ip = '127.0.0.1'
server_port = 8000
#Create the client socket using TCP type.
client_socket = socket(AF_INET, SOCK_STREAM)
#Connect to server socket
client_socket.connect((server_ip, server_port))
#Get user input of message to send to server
client_message = "Client of Caleb J. Badour " + input("Enter an integer: ")
#Send the message to the server
client_socket.send(client_message.encode())
#Receive the server's message
server_message = client_socket.recv(2048)
print(client_message)
print(server_message.decode())
client_num = int(re.search(r'\d+', client_message).group())
server_num = int(re.search(r'\d+', server_message.decode()).group())
print(client_num + server_num)
#Close client socket.
client_socket.close()