
package Controlleur;

import Beans.Produit;
import Model.ProduitDAO;
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


public class supprimer_produit_panier extends HttpServlet {

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
      
       HashMap<Produit,Integer> panier=(HashMap<Produit,Integer>) ses.getAttribute("panier");
       HashMap<Produit,Integer> panier_new=new HashMap<Produit,Integer>();
        String model=request.getQueryString(); 
        for (Entry<Produit, Integer> entry : panier.entrySet()) {
                                   if(!entry.getKey().getModel().equals(model))
                                     panier_new.put(entry.getKey(), entry.getValue());
          } 
            ses.setAttribute("panier", panier_new);
                         this.getServletContext().getRequestDispatcher("/consulter_panier.jsp?supprimer_produit_panier").forward(request, response);

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
