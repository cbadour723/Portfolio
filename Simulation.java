package assg8_badourc19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Simulation {
	/**
	 * Global variables for calculations
	 * time
	 */
	static int time;
	static int average[] = new int[10];
	static int i = 0;
	
	public static void main(String[] args) {
		/**
		 * declare Queue and list
		 */
		Queue<Event> bankQueue = new LinkedList<>();
		EventList list = new EventList();
		int t;
		
		
		/**
		 * read from file
		 */
		Scanner inputStream = null;
		String filename = "assg8_input.txt";
		
		try {
			inputStream = new Scanner(new File(filename));
			}
			catch (FileNotFoundException e) {
			System.out.println("Error openning the file " + filename);
			System.exit(1);
			}
		/**
		 * insert first item
		 */
		list.insert(new Event('A',  t =inputStream.nextInt(), inputStream.nextInt()));
		
		time = t;
		double total = 0;
		double sum = 0;
		double mean;
		
		/**
		 * while loop that calls arrival event and departure event
		 * ends when list is empty
		 */
		while(!list.empty()){
			Event newEvent = list.retrieve();
			
			if(newEvent.EventType()) {
				arrivalEvent(newEvent,inputStream,list,bankQueue);
				total++;
			}
			else {
				departureEvent(newEvent,list,bankQueue);
			}
		}
		
		
		/**
		 * calculations for mean
		 * then print final statistics
		 */
		for(int i = 0; i < average.length; i++) {
			sum =sum + average[i];
		}
		
		mean = sum/total ;
		System.out.println("Final statistics: " + "\n" + "Total number of people processed: " + total + "\n" + "Average wait time: " + mean );
		
	}
	
	
	/**
	 * processes arrivalEvent
	 * @param newEvent
	 * @param inputStream
	 * @param list
	 * @param bankQueue
	 */
	public static void arrivalEvent(Event newEvent, Scanner inputStream, EventList list, Queue<Event> bankQueue) {
		
		int transactionTime = newEvent.getTransaction();
		int arrivalTime = newEvent.getArrival();
		System.out.println("Processing an arrival event at time:" + arrivalTime);
		if(arrivalTime > time) {
			time = arrivalTime;
		}
		
		Boolean atFront = bankQueue.isEmpty();
		bankQueue.add(newEvent);
		list.remove();
		
		if(atFront) {
			list.insert(new Event('D', (time = time + transactionTime) ));
		}
		
		if(inputStream.hasNextLine()) {
			list.insert(new Event('A', inputStream.nextInt(), inputStream.nextInt()));
		}
		
		//System.out.println(arrivalTime + " " + transactionTime + " " + time);
		average[i] = time - arrivalTime;
		i++;
	}
	
	
	/**
	 * processes departureEvent
	 * @param departureEvent
	 * @param list
	 * @param bankQueue
	 */
	public static void departureEvent(Event departureEvent, EventList list, Queue<Event> bankQueue) {
		bankQueue.poll();
		//if(!list.retrieve().EventType()) {
			System.out.println("Processing a departure event at time:" + time);
		//}
		list.remove();
		if(!bankQueue.isEmpty()) {
			list.insert(new Event('D', time = time + departureEvent.getTransaction()));
		}
	}
}
