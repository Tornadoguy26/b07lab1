package test3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Polynomial{
	
	public double Coef[];
	public int Exp[];

	public Polynomial(){
		Coef = new double[]{0};
		Exp = new int[]{0};
	}

	public Polynomial(double Coef[], int Exp[]){
		
		this.Coef = new double[Coef.length];
		this.Exp = new int[Exp.length];

		for(int i=0; i<Coef.length; i++){
			this.Coef[i] = Coef[i];
		}
		
		for(int i=0; i<Exp.length; i++){
			this.Exp[i] = Exp[i];
		}
		
	}
	
public Polynomial(File file) throws IOException{
		
		BufferedReader text = new BufferedReader(new FileReader(file));
		String line = text.readLine();
		text.close();
		
		int count = 1;
		
		for(int i=0; i<line.length()-1; i++) {
			if(line.substring(i, i+1).equals("+") || line.substring(i, i+1).equals("-")){
				count++;
			}
		}
		
		this.Coef = new double[count];
		this.Exp = new int[count];
		
		count = 0;
		int count2 = 0;
		int val = 0;
		double sign = 1;
		double sign2 = 1;
		boolean flag = true;
		
		for(int i=0; i<line.length()-1; i++) {
			if(line.substring(i, i+1).equals("+") || line.substring(i, i+1).equals("-")){
				if(flag) {
					if(line.substring(0,1).equals("-")) {
						sign2 = -1;
						val = 1;
					} else {
						sign2 = 1;
					}
					this.Exp[count] = 0;
					this.Coef[count2] = sign2*Double.parseDouble(line.substring(val, i));
					val = i+1;
					count ++;
					count2 ++;
					flag = false;
					if(line.substring(i, i+1).equals("-")) {
						sign = -1;
					}
				} else {
					this.Exp[count] = Integer.parseInt(line.substring(val,i));
					count++;
					val = i+1;
					if(line.substring(i, i+1).equals("-")) {
						sign = -1;
					}
				}
			} else if(line.substring(i, i+1).equals("x")) {
				flag = false;
				this.Coef[count2] = sign*Double.parseDouble(line.substring(val, i));
				sign = 1;
				count2++;
				val = i+1;
			}
		}
		
		this.Exp[count] = Integer.parseInt(line.substring(val, line.length()));
		
	}

	public Polynomial add(Polynomial Poly2){

		double ncoef1[] = new double[Arrays.stream(this.Exp).max().getAsInt()+1];
		double ncoef2[] = new double[Arrays.stream(Poly2.Exp).max().getAsInt()+1];
	
		int x = ncoef1.length;
		int y = ncoef2.length;
		
		for(int i=0; i<this.Exp.length; i++) {
			ncoef1[this.Exp[i]] = this.Coef[i];
		}
		
		for(int i=0; i<Poly2.Exp.length; i++) {
			ncoef2[Poly2.Exp[i]] = Poly2.Coef[i];
		}
		
		double nrcoef[];
		
		if(Math.max(x, y) == x){
			for(int i=0; i<y; i++) {
				ncoef1[i] += ncoef2[i];
			}
			nrcoef = ncoef1.clone();
		} else {
			for(int i=0; i<x; i++) {
				ncoef2[i] += ncoef1[i];
			}
			nrcoef = ncoef2.clone();
		}
		
		int count = 0;
		int count2 = 0;
		
		for(int i=0; i<nrcoef.length; i++) {
			if(nrcoef[i] == 0) {
				count++;
			}
		}
		
		double nCoef[] = new double[nrcoef.length - count];
		int nExp[] = new int[nrcoef.length - count];
		
		for(int i=0; i<nrcoef.length; i++) {
			if(nrcoef[i] != 0 || i == 0) {
				nExp[count2] = i;
				nCoef[count2] = nrcoef[i];
				count2++;
			}
		}
		
		Polynomial nPoly = new Polynomial(nCoef, nExp);
		
		
		return nPoly;
		

		
	}

	public double evaluate(double input){
		
		double output = 0;

		for(int i=0; i<this.Exp.length; i++){
			output += this.Coef[i] * Math.pow(input, this.Exp[i]);
		}

		return output;

	}
	
	public boolean hasRoot(double input){
		
		double output = evaluate(input);
		
		if(output == 0){
			return true;
		} else {
			return false;
		}
		
	}
	
	public Polynomial multiply(Polynomial Poly2) {
		
		double ncoef1[] = new double[Arrays.stream(this.Exp).max().getAsInt()+Arrays.stream(Poly2.Exp).max().getAsInt()+1];
		int count = 0;
		boolean flag = true;
		
		for(int i = 0; i<this.Exp.length; i++) {
			for(int j=0; j<Poly2.Exp.length; j++) {
				if(ncoef1[Exp[i]+Poly2.Exp[j]] == 0 ) {
					count += 1;
					flag = false;
				}
				
				ncoef1[Exp[i]+Poly2.Exp[j]] += this.Coef[i]*Poly2.Coef[j];
				
				if(ncoef1[Exp[i]+Poly2.Exp[j]] == 0 && flag) {
					count -= 1;
				}
				flag = true;
			}
		}
		
		double []nCoef = new double[count];
		int []nExp = new int[count];
		count = 0;
		
		for(int i=0; i<ncoef1.length; i++) {
			if(ncoef1[i]!=0) {
				nCoef[count] = ncoef1[i];
				nExp[count] = i;
				count++;
			}
		}
		
		Polynomial Poly = new Polynomial(nCoef, nExp);
		return Poly;
		
	}
	
	public void saveToFile(String file) throws IOException {
		
		BufferedWriter text = new BufferedWriter(new FileWriter(file));
		
		for(int i=0; i<this.Exp.length; i++) {

			text.write(Double.toString(this.Coef[i]));
			if(this.Exp[i]!=0) {
				text.write("x");
				text.write(Integer.toString(this.Exp[i]));
			}
			
			if(i<this.Exp.length-1) {
				if(this.Coef[i+1]>=0) {
					text.write("+");
				} 
			}	
			
		}
		
		text.close();
	}
}

	
			