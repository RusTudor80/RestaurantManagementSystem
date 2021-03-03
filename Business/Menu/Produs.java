package Business.Menu;

import java.io.Serializable;

public class Produs extends MenuItem implements Serializable {
	
    
	private static final long serialVersionUID = 1L;
	private Double pret;

    public Produs(Double price) {
        this.pret = price;
    }

    public Double getPrice() {
        return pret;
    }

    public void setPrice(Double price) {
        this.pret = price;
    }

    @Override
    public Double computePrice() {
        return this.pret;
    }

    @Override
    public String toString() {
        return "Un nou produs a fost adaugat cu pretul: " + pret+" lei ";
    }
}
