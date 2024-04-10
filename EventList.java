package assg8_badourc19;

import java.util.ArrayList;
import java.util.Collections;

public class EventList {
	
	/**
	 * declare empty list
	 */
	ArrayList<Event> list = new ArrayList<Event>(); 
	
	/**
	 * insert event into list
	 * @param event
	 */
	public void insert(Event event) {
		list.add(event);
	}
	
	/**
	 * remove first item in list
	 */
	public void remove() {
		list.remove(0);
	}
	
	/**
	 * gets first item in list
	 * @return first item
	 */
	public Event retrieve() {
		return list.get(0);
	}
	
	/**
	 * checks if list is empty
	 * @return true or false
	 */
	public Boolean empty() {
		if (list.isEmpty()) {
			return true;
		}
		else 
			return false;
	}
	
}
