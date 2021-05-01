/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;

/**
 *
 * @author jigna
 */
public class Actualizar extends HttpServlet {
    
    //variables globales
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    //constructor del servlet
    //nos va a ayudar a inicializar la conexion con la base de datos
    
    public void init(ServletConfig cfg) throws ServletException{
        //lo primero que necesitamos es trazar la ruta al servidor de la bd
        String URL = "jdbc:mysql://localhost/registro4iv8";
        //driver:gestor:puerto//ip/nombreBD
        String userName = "root";
        String password = "vilchisc2611";
                
        try{
            //tipo de driver
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, userName, password);
            set = con.createStatement();
            System.out.println("Conexión exitosa");
        }catch(Exception e){
            System.out.println("Conexión no exitosa");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //variables
            String nom, appat, apmat, correo;
            int id, edad;
            id=Integer.parseInt(request.getParameter("idactualizar"));
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Actualizar</title>");
            out.println("<link rel=\"stylesheet\" href=\"./CSS/style.css\">");
                            out.println("<link href=\'https://fonts.googleapis.com/css2?family=Lemonada:wght@300&family=Merriweather:ital@1&family=Montserrat:wght@500&display=swap\" rel=\"stylesheet\'>");
            out.println("<script src=\"./JS/validacion.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class = 'contenedora'>");
            out.println("<h1>Actualizar usuario</h1>");
            out.println("</div");
            out.println("<br>"
                    + "<hr>");
            out.println("<table align = 'center' border='2'>"
                    + "<thead>"
                        + "<tr><th>ID</th>"
                        + "<th>Nombre Completo</th>"
                        + "<th>Edad</th>"
                        + "<th>Email</th></tr>"
                    + "</thead>");
            try{
                //mostramos los datos de el id del usuario ingresado en el index
                String q="select * from mregistro where id_usu="+id;
                set=con.createStatement();
                rs=set.executeQuery(q);
                while(rs.next()){
                    id = rs.getInt("id_usu");
                    nom = rs.getString("nom_usu");
                    appat = rs.getString("appat_usu");
                    apmat = rs.getString("apmat_usu");
                    edad = rs.getInt("edad_usu");
                    correo = rs.getString("correo_usu");
                    out.println("<tbody>"
                            + "<tr><td>"+id+"</td>"
                            + "<td>"+nom+" "+appat+" "+apmat+"</td>"
                            + "<td>"+edad+"</td>"
                            + "<td>"+correo+"</td></tr>"
                            + "</tbody>");
                }  
            }catch(Exception e){
                System.out.println("Consulta no exitosa");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            out.println("</table>");
            out.println("<hr>");
            out.println("<div class=\"containerRegistro\">");
            out.println("<form name=\"datos_act\" method=\"get\" action=\"editarUsuario\" onsubmit=\"return validarDatosA(this)\">"
                    + "<div class=\"input-box\">"
                    + "<span class=\"details\">Nombre(s)</span>"
                    + "<input type=\"text\" name=\"nombrea\" placeholder=\"Ingrese su nombre\" required>"
                    + "</div>"
                    + "<div class=\"input-box\">"
                    + "<span class=\"details\">Apellido Paterno</span>"
                    + "<input type=\"text\" name=\"appata\" placeholder=\"Ingrese su apellido paterno\" required>"
                    + "</div>"
                    + "<div class=\"input-box\">"
                    + "<span class=\"details\">Apellido Materno</span>"
                    + "<input type=\"text\" name=\"apmata\" placeholder=\"Ingrese su apellido materno\" required>"
                    + "</div>"
                    + "<div class=\"input-box\">"
                    + "<span class=\"details\">Correo</span>"
                    + "<input type=\"email\" name=\"correoa\" placeholder=\"Ingrese su correo\" required>"
                    + "</div>"
                    + "<div class=\"input-box\">"
                    + "<span class=\"details\">Edad</span>"
                    + "<input type=\"number\" name=\"edada\" placeholder=\"Ingrese su edad\" required>"
                    + "</div>"
                    + "<div class=\"input-box\">"
                    + "<span class=\"details\">ID</span>"
                    + "<input type=\"number\" name=\"ida\" placeholder=\"Ingrese su id\" required>"
                    + "</div>"
                    + "<div class=\"button\">"
                    + "<input type=\"submit\" value=\"Actualizar\">"
                    + "</div>"
                    + "<div class=\"button\">"
                    + "<input type=\"reset\" value=\"Eliminar Datos\">"
                    + "</div>"
                    + "</form>"
                    + "</div>"
                    + "<br>"
                    + "<hr>");
            out.println("<a href='index.html'>Regresar</a>"
                    + "<hr>");
            out.println("<div class = 'contenedorb'>");
            out.print("</div");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    public void destroy(){
        try{
            con.close();
        }catch(Exception e){
            super.destroy();
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}