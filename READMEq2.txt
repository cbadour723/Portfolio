README 

The program starts by calling fork() to create a child process. 
The fork() function creates a copy of the current process, and returns the process ID of the child to the parent, and 0 to the child.
If fork() returns -1, an error occurred while creating the child process, and the program exits with an error message.
If fork() returns 0, the current process is the child process. 
The child process immediately exits using the exit() function, which turns it into a zombie process.
 A zombie process is a process that has completed its execution, but its parent process has not yet called wait() or waitpid() to collect its exit status.
If fork() returns a positive value, the current process is the parent process.
 The parent process waits for 15 seconds using the sleep() function to give the child process time to become a zombie process. 
Then, it checks the process state using the ps -l command, which lists all processes and their states.
 If the child process is a zombie process, it will be listed with a state of Z.
Finally, the parent process terminates the child process using the kill() function, which sends a signal to the specified process.
 In this case, we send the SIGTERM signal to the child process, which terminates it.
To compile and run the program on a UNIX or Linux system, save the code above into a file called zombie.
c, and run the following commands in a terminal:
