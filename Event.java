/**
 * Caleb Badour
 */


package assg8_badourc19;

import assg6_badourc19.Student;

public class Event {
	int arrival, transaction, departure, time;
	char type;
	
	/**
	 * Arrival Event Constructor
	 * @param type
	 * @param arrival
	 * @param transaction
	 */
	public Event(char type, int arrival, int transaction) {
		this.arrival = arrival;
		this.transaction = transaction;
		this.type = type;

	}
	
	/**
	 * Departure Event Constructor
	 * @param type
	 * @param departure
	 */
	public Event(char type, int departure) {
		this.type = type;
		this.departure = departure;

	}
	
	/**
	 * get Arrival
	 * @return arrival
	 */
	public int getArrival() {
		return this.arrival;
	}
	

	/**
	 * getTransaction
	 * @return transaction
	 */
	public int getTransaction() {
		return this.transaction;
	}
	
	/**
	 * getDeparture
	 * @return departure
	 */
	public int getDeparture() {
		return this.departure;
	}
	
	/**
	 * toString
	 */
	public String toString() {
		return this.type + " " + this.arrival + " " + this.transaction;
	}
	
	/**
	 * compareTo method
	 * @param event
	 * @return 0/-1/1 
	 */
	public int compareTo(Event event) {
		if(this.getArrival() == event.getArrival()) {
			return 0;
		}
		else if (this.getArrival() < event.getArrival() ) {
			return -1;
		}
		else
			return 1;
	}
	
	/**
	 * Checks what type the event is
	 * @return true or false
	 */
	public Boolean EventType() {
		if(this.type == 'A') {
			return true;
		}
		else return false;
	}
	
}
