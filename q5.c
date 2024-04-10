#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <ctype.h>

int main() {
    int fd1[2], fd2[2];
    pid_t pid;

    if (pipe(fd1) == -1 || pipe(fd2) == -1) {
        perror("pipe");
        exit(1);
    }

    pid = fork();

    if (pid < 0) {
        perror("fork");
        exit(1);
    }

    if (pid > 0) {  // parent process
        close(fd1[0]);  // close unused read end of pipe 1
        close(fd2[1]);  // close unused write end of pipe 2

        char message[] = "Hi There";
        write(fd1[1], message, strlen(message) + 1);  // send message to child process

        char reversed[strlen(message) + 1];
        read(fd2[0], reversed, strlen(message) + 1);  // read reversed message from child process
        printf("Reversed message: %s\n", reversed);

        close(fd1[1]);  // close write end of pipe 1
        close(fd2[0]);  // close read end of pipe 2
    } else {  // child process
        close(fd1[1]);  // close unused write end of pipe 1
        close(fd2[0]);  // close unused read end of pipe 2

        char message[strlen("Hi There") + 1];
        read(fd1[0], message, sizeof(message));  // read message from parent process

        for (int i = 0; i < strlen(message); i++) {
            if (islower(message[i])) {
                message[i] = toupper(message[i]);
            } else if (isupper(message[i])) {
                message[i] = tolower(message[i]);
            }
        }

        write(fd2[1], message, strlen(message) + 1);  // send reversed message to parent process

        close(fd1[0]);  // close read end of pipe 1
        close(fd2[1]);  // close write end of pipe 2
    }

    return 0;
}