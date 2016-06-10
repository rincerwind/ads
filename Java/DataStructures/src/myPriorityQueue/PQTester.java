package myPriorityQueue;
import java.util.Scanner;

import myPriorityQueue.IPriorityQueue.POrder;

/*
 * Find the largest M entries in a data stream
 * */

public class PQTester {

	public static void main(String[] args) {
		Scanner line_reader = new Scanner(System.in);
		int M = 5;
		IPriorityQueue<MyDate> pq = new UnorderedPQ<MyDate>(M, POrder.LESSER);

		while(line_reader.hasNextLine()){
			String line = line_reader.nextLine();
			MyDate d = new MyDate(line);
			pq.insert(d);
			if(pq.size() > M){
				MyDate first = pq.removeFirst();
				System.out.println("Removing: " + first);
			}
		}
	}

}
