package Business.Menu;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {
	
 
	private static final long serialVersionUID = 1L;
	private ArrayList<MenuItem> composition;

    public CompositeProduct(ArrayList<MenuItem> composition) {
        this.composition = composition;
    }

    public ArrayList<MenuItem> getComposition() {
        return composition;
    }

    public void setComposition(ArrayList<MenuItem> composition) {
        this.composition = composition;
    }
    
    @Override
    public Double computePrice() {
        Double price = 0d;
        for(MenuItem i : composition) {
            price += i.computePrice();
        }
        return price;
    }

    @Override
    public String toString() {
        return "Un nou fel de mancare a fost adaugat :" + " compus din " + composition.size() + " alimente";
    }
}
