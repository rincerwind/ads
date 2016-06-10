package myPriorityQueue;

public class MyDate implements Comparable<MyDate> {
	int dd, mm, yyyy;
	public MyDate(String d){
		String[] splitted = d.split("/");
		
		dd = Integer.parseInt(splitted[0]);
		mm = Integer.parseInt(splitted[1]);
		yyyy = Integer.parseInt(splitted[2]);
	}

	@Override
	public int compareTo(MyDate o) {		
		int diff = this.yyyy - o.yyyy;
		if(diff != 0)
			return diff;
		
		diff = this.mm - o.mm;
		if(diff != 0)
			return diff;
		
		return this.dd - o.dd;
	}
	
	@Override
	public String toString(){
		return "" + dd + "/" + mm + "/" + yyyy;
	}
}
