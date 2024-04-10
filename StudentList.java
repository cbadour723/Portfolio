/**
 * Caleb Badour
 * StudentList
 */


package assg6_badourc19;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentList implements StudentListInterface{
	
	/**
	 * ArrayList declaration
	 */
	ArrayList<Student> studentList = new ArrayList<Student>();
	
	/**
	 * loadData loads the file 
	 * @param filename
	 */
	@Override
	public void loadData(String filename) {
		// TODO Auto-generated method stub
		StringTokenizer myTokens,myTokens1;
		Scanner inputStream = null;
		PrintWriter outputStream = null;
		String linef1, linef2, line;
		try {
		inputStream = new Scanner(new File(filename));
		}
		catch (FileNotFoundException e) {
		System.out.println("Error openning the file " + filename);
		System.exit(1);
		}
		while (inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			myTokens = new StringTokenizer(line,",");
			if(line.replaceAll(" ", "") != "") {
			studentList.add(new Student(myTokens.nextToken(),myTokens.nextToken(),myTokens.nextToken(),myTokens.nextToken()));
			}
		}
	}

	/**
	 * displayRoster will display the roster
	 */
	@Override
	public void displayRoster() {
		for (int i = 0; i < studentList.size(); i++) {
		     System.out.println(studentList.get(i));
		}
	}
	
	/**
	 * search for the student and return student object or null if student is not found
	 * @param string
	 * @return student or null
	 */
	@Override
	public Object searchForStudent(String string) {
		for(int i = 0; i< studentList.size(); i++) {
		 if (studentList.get(i).getid().equals(string)) {
			 return studentList.get(i);
		 }
		}
		return null;
		}
	
	/**
	 * addStudent will add a student
	 * will return true if student was added
	 * will return false if student was not added
	 * @param id
	 * @param name
	 * @param standing
	 * @param major
	 * @return
	 */
	@Override
	public Boolean addStudent(String id, String name, String standing, String major) {
		for(int i = 0; i< studentList.size(); i++) {
			 if (studentList.get(i).getid().equals(id)) {
				 return false;
			 }
		}
		studentList.add(new Student(id,name,standing,major));
		return true; 
	}
	public Boolean removeStudent(String id) {
		for(int i = 0; i< studentList.size(); i++) {
			 if (studentList.get(i).getid().equals(id)) {
				 studentList.remove(i);
				 return true;
			 }
		 }
		return false;
	}
	
	/**
	 * removeStudent will remove a student
	 * will return true if student is removed
	 * will return false if student is not removed
	 * @param id
	 * @return
	 */
	@Override
	public Object getStudentsByMajor(String major) {
		ArrayList<Student> majorList = new ArrayList<Student>();
	
		for(int i = 0; i< studentList.size(); i++) {
			 if (studentList.get(i).getmajor().equals(major)) {
				 majorList.add(new Student(studentList.get(i).getid(),studentList.get(i).getname(),"",""));
			 }
		}
		if(majorList.isEmpty()) {
			System.out.println("No students found with this major");
		}
		return majorList;
		
	}
	
	/**
	 * sorts students by id
	 */
	@Override
	public void sort() {
		Collections.sort(studentList);
	}
	
	/**
	 * save writes the data back to the file if the data has been changed
	 */
	@Override
	public void save() {
		PrintWriter outputStream = null;
		String filename = "assg6_data.txt";
		try
        {
            outputStream = new PrintWriter(filename);
        }
        catch(IOException e)
        {
            System.out.println("Error opening the file " + filename);
            System.exit(0);
        }
		for(int i = 0; i < studentList.size(); i++) {
		outputStream.println(studentList.get(i).getid() + "," + studentList.get(i).getname() + "," + studentList.get(i).getstanding() + "," + studentList.get(i).getmajor());
		}
		outputStream.close();
		}
	}

