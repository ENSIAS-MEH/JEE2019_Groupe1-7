
package Controlleur;

import Beans.Produit;
import Model.ProduitDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ajouter_panier extends HttpServlet {


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
        ProduitDAO pd=null;
        boolean test=false;
        try {
            pd=new ProduitDAO();
        } 
        catch (SQLException ex) { }
        catch (ClassNotFoundException ex) {}
        HashMap<Produit,Integer> panier=(HashMap<Produit,Integer>) ses.getAttribute("panier");
        String model=request.getQueryString();
        try {
            for (Map.Entry<Produit, Integer> entry : panier.entrySet()) {
                                   if(entry.getKey().getModel().equals(model))
                                     test=true;
          }
            if(test==false)
            {
            panier.put(pd.getProduit(model), 1);
           ses.setAttribute("panier", panier);
         this.getServletContext().getRequestDispatcher("/consulter_panier.jsp?ajouter_panier").forward(request, response);

            }
            else
        this.getServletContext().getRequestDispatcher("/consulter_panier.jsp?produit_existe").forward(request, response);

           
        } catch (SQLException ex) {
            Logger.getLogger(ajouter_panier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
