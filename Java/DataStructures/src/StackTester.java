import java.util.Scanner;

public class StackTester {

	public static void main(String[] args) {
		iStackOfStrings s = new StackOfStrings_LList();
		Scanner line_reader = new Scanner(System.in);
		
		while(line_reader.hasNextLine()){
			String line = line_reader.nextLine();
			
			if(line.equals("-"))
				System.out.println(s.pop());
			else
				s.push(line);
		}
		line_reader.close();
	}

}
