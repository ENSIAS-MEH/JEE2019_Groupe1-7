
package Controlleur;

import Model.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class cloturer_compte extends HttpServlet {

   
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
         ClientDAO cd=null;
               try {
            cd = new ClientDAO();
                 } 
               catch (SQLException ex) {}
           catch (ClassNotFoundException ex) {}
              String login=(String) ses.getAttribute("login");
              
              
                try {
           cd.supprimerClient(login); 
       this.getServletContext().getRequestDispatcher("/deconnexion").forward(request, response);
            
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
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
