/**
 * Caleb Badour
 * Student
 */

package assg6_badourc19;

public class Student implements Comparable<Student>{
	String name, standing, major, id;
	
	/**
	 * Student constructor
	 * @param id
	 * @param name
	 * @param standing
	 * @param major
	 */
	public Student(String id, String name, String standing, String major) {
		this.id = id;
		this.name = name;
		this.standing = standing;
		this.major = major;
	}
	
	/**
	 * toString 
	 */
	public String toString() {

		 return id + " " + name + " " + standing + " " + major;
	 }
	/**
	 * getname
	 * @return name
	 */
	public String getname() {
		return name;
	}
	/**
	 * getid
	 * @return id
	 */
	public String getid() {
		return id;
	}
	/**
	 * getstanding
	 * @return standing
	 */
	public String getstanding() {
		return standing;
	}
	/**
	 * getmajor
	 * @return major
	 */
	public String getmajor() {
		return major;
	}
	
	/**
	 * setmajor
	 * @param major
	 */
	public void setmajor(String major) {
		this.major= major;
	}
	
	/**
	 * Equals method
	 * @param Object student
	 * @return false or true
	 */
	public boolean equals(Object student) {
		if (student == null)
		{
			return false;
		}
		if(student instanceof Student)
		{
			Student temp = (Student)student;
			return this.getid().equals(temp.getid());
			
		}
		else 
			return false;
	}
	
	/**
	 * compareTo
	 * @param Student student
	 * @return 0,1,-1
	 */
	public int compareTo(Student student) {
		if(Integer.parseInt(this.getid()) == Integer.parseInt(student.getid())) {
			return 0;
		}
		else if (Integer.parseInt(this.getid()) < Integer.parseInt(student.getid())) {
			return -1;
		}
		else
			return 1;
	}
}
