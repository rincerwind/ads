package myStack;
import java.util.Scanner;

import myGenerics.IStack;
import myGenerics.Stack_Resizing;

public class StackTester {

	public static void main(String[] args) {
		IStack<Integer> s = new Stack_Resizing<Integer>();
		Scanner line_reader = new Scanner(System.in);
		
		while(line_reader.hasNextLine()){
			String line = line_reader.nextLine();
			
			if(line.equals("-"))
				System.out.println(s.pop());
			else
				s.push(Integer.parseInt(line));
		}
		line_reader.close();
	}

}
