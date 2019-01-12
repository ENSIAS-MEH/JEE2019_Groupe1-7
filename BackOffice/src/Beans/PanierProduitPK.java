
package Beans;

import java.io.Serializable;



public class PanierProduitPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String model;
    private int idPanier;

    public PanierProduitPK() {
    }

    public PanierProduitPK(String model, int idPanier) {
        this.model = model;
        this.idPanier = idPanier;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }


}
