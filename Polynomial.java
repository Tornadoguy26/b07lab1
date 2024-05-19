public class Polynomial{
	
	public double Poly[];

	public Polynomial(){
		Poly = new double[]{0};
	}

	public Polynomial(double Coef[]){
		
		Poly = new double[Coef.length];

		for(int i=0; i<Coef.length; i++){
			Poly[i] = Coef[i];
		}
	}

	public Polynomial add(Polynomial Poly2){
		Polynomial npoly = new Polynomial(Poly);
		if(Poly.length >= Poly2.Poly.length){
			npoly = new Polynomial(Poly);
			for(int i=0; i<Poly2.Poly.length; i++){
				npoly.Poly[i] += Poly2.Poly[i];
			}
		} else {
			npoly = new Polynomial(Poly2.Poly);
			for(int i=0; i<Poly.length; i++){
				npoly.Poly[i] += Poly[i];
			}
		} 

		return npoly;
	}

	public double evaluate(double input){
		double value = 0;
		double power = 1;
		for(int i=0; i<Poly.length; i++){
			value += Poly[i] * power;
			power *= input;
		}
		return value;
	}

	public boolean hasRoot(double input){
		double output = evaluate(input);
		if(output == 0){
			return true;
		} else {
			return false;
		}
	}
}

	
			