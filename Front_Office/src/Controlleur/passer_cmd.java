
package Controlleur;

import Beans.Produit;
import Model.CommandeDAO;
import Model.FactureDAO;
import Model.ProduitCommandeDAO;
import Model.ProduitDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class passer_cmd extends HttpServlet {

	private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter pr=response.getWriter();
        HttpSession ses=request.getSession();
        CommandeDAO cmdd=null;
        ProduitDAO pd=null;
        ProduitCommandeDAO pcd=null;
        FactureDAO fd=null;
        System.out.println("1");
        try {
            cmdd=new CommandeDAO();
            pcd=new ProduitCommandeDAO();
            fd=new FactureDAO();
            pd=new ProduitDAO();
            System.out.println("2");
        } 
        catch (SQLException ex) { }
        catch (ClassNotFoundException ex) {}
        System.out.println("3");
        @SuppressWarnings("unchecked")
		HashMap<Produit,Integer> panier=(HashMap<Produit,Integer>) ses.getAttribute("panier");
        System.out.println("4");
        String login=(String) ses.getAttribute("login");
        System.out.println(login);
        int total=Integer.parseInt(request.getQueryString());
        System.out.println("5");
        try {   
            if(panier.size()>0){
            	System.out.println("5.1");
                cmdd.ajouterCommande(login);
                System.out.println("5.2");
            for (Entry<Produit, Integer> entry : panier.entrySet()) {
            	System.out.println("5.3");
                    pcd.ajouterProduitCommande(entry.getKey().getModel(), cmdd.getLastIdCmd(), entry.getValue());
                    System.out.println("5.4");
pd.modifierQteProduit(entry.getKey().getModel(), pd.getProduit(entry.getKey().getModel()).getQteStock()-entry.getValue());
System.out.println("6");
        } 
            fd.ajouterFacture(cmdd.getLastIdCmd(), total);
            cmdd.modifierCommande(cmdd.getLastIdCmd());
            System.out.println("7");
            ses.setAttribute("panier", null);
       this.getServletContext().getRequestDispatcher("/consulter_panier.jsp?commande_passee").forward(request, response);
            }
            else
       this.getServletContext().getRequestDispatcher("/consulter_panier.jsp?panier_vide").forward(request, response);

        }
        catch (SQLException ex) {}
        catch (ClassNotFoundException ex) {}
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
