import socket

HOST = input("Enter IP address (press Enter for localhost): ") or '127.0.0.1'
PORT = int(input("Enter port number (press Enter for default port): ") or 12345)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    print(f"Server started on {HOST}:{PORT}")
    s.listen()
    conn, addr = s.accept()
    with conn:
        username = input("Enter your username: ")
        print(f"Connected by {addr} as {username}")
        while True:
            data = conn.recv(1024)
            if not data:
                break
            print(f"{username}: {data.decode()}")
            message = input("Enter your message: ")
            conn.sendall(f"{username}: {message}".encode())
            if message.lower() == "end":
                break
