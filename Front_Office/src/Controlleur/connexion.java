
package Controlleur;

import Beans.Client;
import Model.ClientDAO;
import Model.PanierDAO;
import Model.PanierProduitDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class connexion extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client c=new Client();
             ClientDAO ch=null;
             PanierProduitDAO ppd=null;
             PanierDAO pand=null;
             HttpSession ses=request.getSession();
             PrintWriter pr=response.getWriter();
             String login=request.getParameter("login");
              String password=request.getParameter("password");
        try {
            ch = new ClientDAO();
            pand=new PanierDAO();
            ppd=new PanierProduitDAO();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
        
        try {
            if(ch.connexion(login,password)==true)
            { ses.setAttribute("login", login);
                try {
                    if(pand.hasPanier(login))
                    ses.setAttribute("panier",ppd.getAllProduitsPanier(pand.getPanier(login).getIdPanier()) );
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(connexion.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else
            { 
        this.getServletContext().getRequestDispatcher("/connexion.jsp?erreur_connexion").forward(request, response);
}
        }
        catch (SQLException ex) {
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
