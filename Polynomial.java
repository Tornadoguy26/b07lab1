import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Polynomial{

	public double []Coff;
	public int []Deg;
	
	public Polynomial(){
		Coff = new double[]{0};
		Deg = new int[]{0};
	}

	public Polynomial(double []Cof, int []Deg){
		
		Coff = new double[Cof.length];
		this.Deg = new int[Deg.length];

		for(int i=0; i< Cof.length; i++){
			Coff[i] = Cof[i];
		}
		
		for(int j=0; j<Deg.length; j++) {
			this.Deg[j] = Deg[j];
		}
	}
	
	public Polynomial(File file) throws IOException{
		
		BufferedReader b = new BufferedReader(new FileReader(file));
		String j = b.readLine();
		b.close();
		
		int count = 1;
		
		for(int i=0; i<j.length()-1; i++) {
			if(j.substring(i, i+1).equals("+") || j.substring(i, i+1).equals("-")){
				count++;
			}
		}
		
		Coff = new double[count];
		Deg = new int[count];
		
		count = 0;
		int count2 = 0;
		int val = 0;
		double sign = 1;
		double sign2 = 1;
		boolean flag = true;
		
		for(int i=0; i<j.length()-1; i++) {
			if(j.substring(i, i+1).equals("+") || j.substring(i, i+1).equals("-")){
				if(flag) {
					if(j.substring(0,1).equals("-")) {
						sign2 = -1;
						val = 1;
					} else {
						sign2 = 1;
					}
					Deg[count] = 0;
					Coff[count2] = sign2*Double.parseDouble(j.substring(val, i));
					val = i+1;
					count ++;
					count2 ++;
					flag = false;
					if(j.substring(i, i+1).equals("-")) {
						sign = -1;
					}
				} else {
					Deg[count] = Integer.parseInt(j.substring(val,i));
					count++;
					val = i+1;
					if(j.substring(i, i+1).equals("-")) {
						sign = -1;
					}
				}
			} else if(j.substring(i, i+1).equals("x")) {
				flag = false;
				Coff[count2] = sign*Double.parseDouble(j.substring(val, i));
				sign = 1;
				count2++;
				val = i+1;
			}
		}
		
		Deg[count] = Integer.parseInt(j.substring(val, j.length()));
		
	}
	
	public Polynomial add(Polynomial b){
		
		double []a = new double[Arrays.stream(Deg).max().getAsInt()+1];
		double []a2 = new double[Arrays.stream(b.Deg).max().getAsInt()+1];
		
		for(int i=0; i<Deg.length; i++) {
			a[Deg[i]] = Coff[i];
		}
		
		for(int i=0; i<b.Deg.length; i++) {
			a2[b.Deg[i]] = b.Coff[i];
		}
		
		// a = [
		
		double []c = null;
		
		if(a.length >= a2.length){
			for(int i=0; i < a2.length; i++){
				a[i] += a2[i];
			}
			c = a.clone();
		} else {
			for(int i=0; i < a.length; i++){
				a2[i] += a[i];
			}
			c = a2.clone();
		}
		
		int count = 0;
		
		for(int i=0; i<c.length; i++){
			if(c[i] == 0 ) {
				count++;
			}
		}
		
		int []x = new int[c.length-count];
		double []y = new double[c.length-count];
		int j = 0;
		
		for(int i=0; i<c.length; i++) {
			if(c[i] != 0 || i==0) {
				x[j] = i;
				y[j] = c[i];
				j++;
			}
		}
		
		Polynomial Poly = new Polynomial(y, x);
		return Poly;
			
			
	}

	public double evaluate(double x){
		
		double value = 0;

		for(int i=0; i<Deg.length; i++){
			value += Coff[i] * Math.pow(x, Deg[i]);
		}

		return value;

	}

	public boolean hasRoot(double x){


		boolean root = false;

		Polynomial a = new Polynomial(Coff, Deg);
		if(a.evaluate(x) <= 0.0001){
			root = true;
		}

		return root;
	}
	
	public Polynomial multiply(Polynomial b) {
		
		double []a2 = new double[Arrays.stream(Deg).max().getAsInt()+Arrays.stream(b.Deg).max().getAsInt()+1];
		int count = 0;
		boolean flag = true;
		
		for(int i = 0; i<Deg.length; i++) {
			for(int j=0; j<b.Deg.length; j++) {
				if(a2[Deg[i]+b.Deg[j]] == 0 ) {
					count += 1;
					flag = false;
				}
				
				a2[Deg[i]+b.Deg[j]] += Coff[i]*b.Coff[j];
				
				if(a2[Deg[i]+b.Deg[j]] == 0 && flag) {
					count -= 1;
				}
				flag = true;
			}
		}
		
		double []c = new double[count];
		int []d = new int[count];
		int j = 0;
		
		for(int i=0; i<a2.length; i++) {
			if(a2[i]!=0) {
				c[j] = a2[i];
				d[j] = i;
				j++;
			}
		}
		
		
		Polynomial Poly = new Polynomial(c, d);
		return Poly;
		
	}

	public void saveToFile(String file) throws IOException {
		
		BufferedWriter b = new BufferedWriter(new FileWriter(file));
		
		for(int i=0; i<Deg.length; i++) {

			b.write(Double.toString(Coff[i]));
			if(Deg[i]!=0) {
				b.write("x");
				b.write(Integer.toString(Deg[i]));
			}
			
			if(i<Deg.length-1) {
				if(Coff[i+1]>=0) {
					b.write("+");
				} 
			}	
			
		}
		
		b.close();
	}
}