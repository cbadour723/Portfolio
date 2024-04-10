#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    int n;

    // Check for positive integer 
    if (argc != 2) {
        printf("Usage: %s <starting number>\n", argv[0]);
        return 1;
    }

    n = atoi(argv[1]);
    if (n <= 0) {
        printf("Error: Starting number must be a positive integer\n");
        return 1;
    }

    // child process
    pid_t pid = fork();

    if (pid < 0) {
        printf("Error: Failed to fork process\n");
        return 1;
    } else if (pid == 0) {
        // Child process
        printf("%d", n);
        while (n > 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            printf(", %d", n);
        }
        printf("\n");
    } else {
        // Parent process
        wait(NULL);
    }

    return 0;
}