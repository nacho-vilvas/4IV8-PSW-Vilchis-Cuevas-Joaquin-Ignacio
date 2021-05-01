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
public class editarUsuario extends HttpServlet {
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //variables
            String nom, appat, apmat, correo;
            int id, edad;
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editarUsuario</title>");
            out.println("<link rel=\"stylesheet\" href=\"./CSS/style.css\">");
                            out.println("<link href=\'https://fonts.googleapis.com/css2?family=Lemonada:wght@300&family=Merriweather:ital@1&family=Montserrat:wght@500&display=swap\" rel=\"stylesheet\'>");

            out.println("</head>");
            out.println("<body>");
            out.println("<div class = 'contenedora'>");
            out.println("<h1 class=\"title\">Editar Datos del Usuario</h1>");
            out.println("</div>");
            out.println("<hr>");
            try{
                //proceso para actualizar datos
                nom=request.getParameter("nombrea");
                appat=request.getParameter("appata");
                apmat=request.getParameter("apmata");
                correo=request.getParameter("correoa");
                edad=Integer.parseInt(request.getParameter("edada"));
                id=Integer.parseInt(request.getParameter("ida"));

                String qN="UPDATE mregistro SET nom_usu = '"+nom+"' WHERE id_usu = "+id;
                String qP="UPDATE mregistro SET appat_usu = '"+appat+"' WHERE id_usu = "+id;
                String qM="UPDATE mregistro SET apmat_usu = '"+apmat+"' WHERE id_usu = "+id;
                String qE="UPDATE mregistro SET edad_usu = "+edad+" WHERE id_usu = "+id;
                String qC="UPDATE mregistro SET correo_usu = '"+correo+"' WHERE id_usu = "+id;
            
            
                //actualizacion del nombre
                //set=con.createStatement();
                set.executeUpdate(qN);
                
                //actualizacion del apellido paterno
                //set=con.createStatement();
                set.executeUpdate(qP);
                
                //actualizacon del apellido materno
                //set=con.createStatement();
                set.executeUpdate(qM);
                
                //actualizacion de la edad
                //set=con.createStatement();
                set.executeUpdate(qE);
                
                //actualizacion del correo
                //set=con.createStatement();
                set.executeUpdate(qC);
                
                System.out.println("Actualización de datos Exitosa");
                
            }catch(Exception e){
                System.out.println("Actualización de datos Fallida");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }
            out.println("<table id = 'tabla' border='2'>"
                    + "<thead>"
                        + "<tr><th>ID</th>"
                        + "<th>Nombre Completo</th>"
                        + "<th>Edad</th>"
                        + "<th>Email</th></tr>"
                    + "</thead>");
            try{
            
                //impresion de los nuevos datos del usuario
                id=Integer.parseInt(request.getParameter("ida"));
                String qA="select * from mregistro where id_usu="+id;
                set=con.createStatement();
                rs=set.executeQuery(qA);
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
            
                System.out.println("Consulta de Datos Exitosa");
            
            }catch(Exception e){
            
                System.out.println("Consulta de Datos Fallida");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            
            }
            out.println("</table>");
            out.println("<hr>");
            out.println("<a class=\"link1\" href='index.html'>Regresar</a>");
            out.println("<br>");
            out.println("<a class=\"link2\" href='Consultar'>Consultar datos de usuarios </a>");
            out.println("<hr>");
            out.println("<div class = 'contenedorb'>");
            out.println("</body>");
            out.println("</html>");
        };
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