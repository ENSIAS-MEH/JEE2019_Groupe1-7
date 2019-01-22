
package Controlleur;

import Beans.Produit;
import Model.PanierDAO;
import Model.PanierProduitDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class enregistrer_panier extends HttpServlet {


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
        PanierDAO pand=null;
        PanierProduitDAO ppd=null;
        System.out.println("1");
        try {
            pand=new PanierDAO();
            ppd=new PanierProduitDAO();
        } 
        catch (SQLException ex) { }
        catch (ClassNotFoundException ex) {}
        @SuppressWarnings("unchecked")
		HashMap<Produit,Integer> panier=(HashMap<Produit,Integer>) ses.getAttribute("panier");
        String login=(String) ses.getAttribute("login");
        int total=Integer.parseInt(request.getQueryString());
        System.out.println("2");
        try {
            if(!pand.hasPanier(login))
            	System.out.println("3");
                pand.ajouterPanier(login, total);
                System.out.println("4");
           
           ppd.SupprimerAllByLogin(login);
           System.out.println("5");
            
            for (Entry<Produit, Integer> entry : panier.entrySet()) {
                    ppd.ajouterProduitPanier(entry.getKey().getModel(), pand.getPanier(login).getIdPanier(), entry.getValue());
                    System.out.println("6");
            }
            pand.modifierPanier(pand.getPanier(login).getIdPanier(),total); 
            System.out.println("7");
     this.getServletContext().getRequestDispatcher("/consulter_panier.jsp?enregistrer_panier").forward(request, response);
     System.out.println("8");

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
