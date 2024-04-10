/**
 * Caleb Badour

 * Interface
 */


package assg6_badourc19;

public interface StudentListInterface {
	
	/**
	 * loadData loads the file 
	 * @param filename
	 */
	public void loadData(String filename);
	
	/**
	 * displayRoster will display the roster
	 */
	public void displayRoster();
	
	/**
	 * search for the student and return student object or null if student is not found
	 * @param string
	 * @return student or null
	 */
	public Object searchForStudent(String string);
	
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
	public Boolean addStudent(String id, String name, String standing, String major);
	
	/**
	 * removeStudent will remove a student
	 * will return true if student is removed
	 * will return false if student is not removed
	 * @param id
	 * @return
	 */
	public Boolean removeStudent(String id);
	
	/**
	 * getStudentsByMajor will get all the students taking a particular major
	 * will return an arraylist with the students
	 * if there are no students with the major will return arraylist = 0
	 * @param major
	 * @return
	 */
	public Object getStudentsByMajor(String major);
	
	/**
	 * sorts students by id
	 */
	public void sort();
	
	/**
	 * save writes the data back to the file if the data has been changed
	 */
	public void save();
	
}
