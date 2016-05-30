package myQueue;

import java.util.Scanner;

public class QueueTester {

	public static void main(String[] args) {
		iQueueOfStrings s = new QueueOfStrings_Array(4);
		Scanner line_reader = new Scanner(System.in);
		
		while(line_reader.hasNextLine()){
			String line = line_reader.nextLine();
			
			if(line.equals("-"))
				System.out.println(s.dequeue());
			else
				s.enqueue(line);
		}
		line_reader.close();
	}

}
