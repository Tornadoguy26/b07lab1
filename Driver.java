import java.io.File;
import java.io.IOException;

public class Driver {
	
	public static void main(String [] args) throws IOException {
		
		Polynomial p = new Polynomial();
		//File file = new File("C:\\Users\\torna\\eclipse-workspace\\Polynomial\\src\\Test.txt");
		//Polynomial ft = new Polynomial(file);
		
		int []d1 = {0,3};
		double []c1 = {6,5};
		
		int []d2 = {1, 4};
		double []c2 = {-2, -9};
		
		int []d3 = {2, 3, 5};
		double []c3 = {3.9, 6, -1};
		
		Polynomial p1 = new Polynomial(c1, d1);
		Polynomial p2 = new Polynomial(c2, d2);
		Polynomial p3 = new Polynomial(c3, d3);		
		
		System.out.println(p.evaluate(3));
		Polynomial s = p1.add(p2);
		Polynomial m = s.multiply(p3);
		
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		
		System.out.println(m.evaluate(2));
		
		if(s.hasRoot(1)){
			System.out.println("1 is a root of s");
		}else {
			System.out.println("1 is not a root of s");
		}
		//System.out.println(ft.evaluate(1));
		
		//s.saveToFile("C:\\Users\\torna\\eclipse-workspace\\Polynomial\\src\\Test2.txt");
	}
	
}