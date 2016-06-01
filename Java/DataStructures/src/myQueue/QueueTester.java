package myQueue;

import java.util.Scanner;

import myGenerics.Queue_LList;
import myGenerics.IQueue;

public class QueueTester {

	public static void main(String[] args) {
		IQueue<Integer> s = new Queue_LList<Integer>();
		Scanner line_reader = new Scanner(System.in);
		
		while(line_reader.hasNextLine()){
			String line = line_reader.nextLine();
			
			if(line.equals("-"))
				System.out.println(s.dequeue());
			else
				s.enqueue(Integer.parseInt(line));
		}
		line_reader.close();
	}

}
