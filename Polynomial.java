
public class Polynomial{

	public double []Poly;
	
	public Polynomial(){
		Poly = new double[]{0};
	}

	public Polynomial(double []Cof){
		
		Poly = new double[Cof.length];

		for(int i=0; i<Cof.length; i++){
			Poly[i] = Cof[i];
		}
	}

	public Polynomial add(Polynomial b){
		if(Poly.length >= b.Poly.length){
			Polynomial newPoly = new Polynomial(Poly);
			for(int i=0; i < b.Poly.length; i++){
				newPoly.Poly[i] += b.Poly[i];
			}
			return newPoly;
		} else {
			Polynomial newPoly = new Polynomial(b.Poly);
			for(int i=0; i < Poly.length; i++){
				newPoly.Poly[i] += Poly[i];
			}
			return newPoly;
		}
			
			
	}

	public double evaluate(double x){
		
		double value = 0;
		double a = 1;

		for(int i=0; i<Poly.length; i++){
			value += Poly[i] * a;
			a *= x;
		}

		return value;

	}

	public boolean hasRoot(double x){

		double value = 0;
		double a = 1;
		boolean root = true;
		
		for(int i=0; i<Poly.length; i++){
			value += Poly[i] * a;
			a *= x;
		}

		if(value != 0){
			root = false;
		}

		return root;
	}

}