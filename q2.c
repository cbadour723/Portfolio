#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork(); // create a child process
    if (pid == -1) {
        // error while forking
        perror("fork");
        exit(1);
    }
    else if (pid == 0) {
        // child process
        exit(0); // immediately exit to become a zombie process
    }
    else {
        // parent process
        sleep(15); // wait for the child to become a zombie process
        printf("Child process is now a zombie\n");
        system("ps -l"); // check the process state
    }
    return 0;
}