import socket

HOST = input("Enter server IP address: ")
PORT = int(input("Enter server port number: "))

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    username = input("Enter your username: ")
    while True:
        message = input("Enter your message: ")
        s.sendall(f"{username}: {message}".encode())
        if message.lower() == "end":
            break
        data = s.recv(1024)
        if not data:
            break
        print(data.decode())
