package com.cyberslex.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;//LAS LIBRERÍAS DE JAVAX.SERVLET O JAKARTA INDICAN QUE ESTAMOS USANDO JAVA EE. JAVA EE ES UNA ESPECIFICACION (DEFINICION DE INTERFACES) DE JAVA PARA EL DESARROLLO DE APLICACIONES WEB MEDIANTE EL USO DE SERVLETES. PRACTICAMENTE ES UNA ALTERNATIVA DE SPRING.
import javax.servlet.ServletException;//LAS ESPECIFICACIONES DE JAVA EE MAS IMPORTATES SON: GLASSFISH, WILDFLY, PAYARA, WEBSPHERE,WEBLOGIC. A  PESAR DE QUE SON SERVIDORES DE APLICACIONES, SON IMPLEMETACIONES PORQUE TRAEN  TODO EL CÓDIGO  NECESARIO  PARA QUE JAVA EE SE PUEDA EJECUTAR. LOS JARS VIENEN EN UN PATH DETERMINADO  EN EL SERVIDOR.
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MyServlet")//Sin esta anotacion tendriamos que colocar todos los servlets en un archivo web.xml. En versiones anteriores a Java EE 6, sólo se podía usar el archivo web.xml; en versiones posteriores puedes optar por cualquiera de las 2 opciones. En nuestro caso podemos identificar qué versión estamos usando por la version del servidor, que es Tomcat 9.La  version la podemos ver en el archivo org.eclipse.wst.common.project.facet.core.xml  con el atributo "jst.web".
						// El mapeo de url (que en este caso es /MyServlet) también se realizaría en el web.xml, con la etiqueta <servlet-mapping>.
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//ESTOS METODOS HACEN LAS VECES DE METODOS CONTROLLERS. @GetMapping @PostMapping de Spring.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Ingresamos al metodo GET de mi servlet");
		System.out.println(request.getParameter("var1"));
		System.out.println(request.getParameter("var2"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Ingresamos al metodo POST de mi servlet");
		System.out.println(request.getParameter("nombre"));//HAY QUE RECORDAR QUE LOS OBJETOS REQUEST Y RESPONSE TAMBIEN SE USAN EN LOS METODOS CONTROLADORES Y MUCHO. // AQUI ESTAMOS RECUPERANDO EL VALOR INGRESADO EN UN FORMULARIO QUE REDIRIGIÒ A ESTE METODO.
		//doGet(request, response);
		
		request.setAttribute("name1", request.getParameter("nombre"));//HAY QUE RECORDAR QUE, EN SPRING, ESTA LINEA SE SUSTITUYE POR UN model.addAttribute
		
		request.getSession().setAttribute("nameSession", request.getParameter("nombre"));
		
		RequestDispatcher rd;
		rd=request.getRequestDispatcher("/presentacion.jsp");//Estamos retornando una pagina jsp, tal y como se hace en un metodo controller.
		rd.forward(request, response);
	}

}
