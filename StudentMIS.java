package assg6_badourc19;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentMIS {
	public static void main(String[] args) {
		
		/**
		 * Declarations
		 */
		String id, name, standing, major;
		String filename = "assg6_data.txt";
		
		/**
		 * loaddata into list 1 and 2
		 * list 2 will be checked later to see if changes are made
		 */
		StudentList list1 = new StudentList();
		list1.loadData(filename);
		StudentList list2 = new StudentList();
		list2.loadData(filename);
		list1.displayRoster();
		
		/**
		 * Menu
		 */
		int option;
		Scanner keyboard = new Scanner(System.in);
		Scanner keyboard1 = new Scanner(System.in);
		Scanner keyboard2 = new Scanner(System.in);
		option = 0;
		while(option!=8) {
			System.out.println("1. Display the roster" + "\n" + 
		"2. Search for a student by id" + "\n" + 
		"3. Add a new student" + "\n" +
		"4. Remove a student" + "\n" + 
		"5. Search for students by major" + "\n" +
		"6. Sort and save to file" + "\n" + 
		"7. Save to file" + "\n" + 
		"8. Exit");
			
			option = keyboard.nextInt();
			switch(option) {
			/**
			 * case 1 = displayRoster
			 */
			case 1 : 
				list1.displayRoster();
				break;
				/**
				 * case 2 = search for student
				 */
			case 2 :
				System.out.println("Enter a students ID: ");
				id = keyboard1.nextLine();
				if(list1.searchForStudent(id) == (null)) {
					System.out.println("Student not found");
				}
				else {
					System.out.println(list1.searchForStudent(id));
				}
				break;
				/**
				 * case 3 = add student
				 */
			case 3:
				System.out.println("Enter a students ID,name, standing, and major : ");
				id = keyboard1.nextLine();
				System.out.println("id");
				name= keyboard1.nextLine();
				System.out.println("name");
				standing = keyboard1.nextLine();
				System.out.println("standing");
				major = keyboard1.nextLine();
				System.out.println("major");
				if(list1.addStudent(id, name, standing, major) == false) {
					System.out.println("Student ID already exists... Student was not added");
				}
				else {
					list1.addStudent(id,name,standing,major);
					System.out.println("Student has been added");
				}
				break;
				/**
				 * case 4 = remove student
				 */
			case 4:
				System.out.println("Enter a students ID to remove: ");
				id = keyboard1.nextLine();
				if(list1.removeStudent(id) == true) {
				System.out.println("Student " + id + " has been removed");
				list1.removeStudent(id);
				}
				else
					System.out.println("Student id " + id + " was not found");
				break;
				/**
				 * case 5 = get students by major
				 */
			case 5:
				System.out.println("Enter a major: ");
				major = keyboard1.nextLine();
				System.out.println(list1.getStudentsByMajor(major));
				break;
				/**
				 * case 6 = sort and save to file
				 */
			case 6:
				list1.sort();
				list1.save();
				System.out.println("the data has been sorted and saved!");
				break;
				/**
				 * case 7 = if data has changed save
				 */
			case 7:
				if(list1.studentList.equals(list2.studentList)) {
					System.out.println("the data has not changed");
				}
				else {
				System.out.println("the data has been saved");
				list1.save();
				}
				break;
			}
			System.out.println("Press Enter to continue");
			String enterkey = keyboard2.nextLine();
			while(enterkey != "");
			continue;
		    }
		/**
		 * after exit is pressed check to see if data has changed
		 * if it has save it.
		 */
		if(list1.studentList.equals(list2.studentList)) {
			System.out.println("the data has not changed");
		}
		else {
		System.out.println("the data has been saved");
		list1.save();
		}
		}
		

		}

