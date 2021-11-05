package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pagina;
import service.BuscadorService;
import service.BuscadorServiceFactory;

/**
 * Servlet implementation class Buscador
 */
@WebServlet("/Buscador")
public class Buscador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(var out=response.getWriter();){
			response.setContentType("text/html");
			var clave=request.getParameter("clave");
			BuscadorService service = BuscadorServiceFactory.getBuscadorService(); 
			out.println("<html><body><center>");
			List<Pagina> paginas = service.buscar(clave);
			for (Pagina p : paginas) {
				out.println("<div style=\"border: 1px solid black\"><a href='" + p.getUrl() + "'>" + p.getTitulo()  + "</a><br>");
				out.println("<a href='" + p.getUrl() + "'>" + p.getDescripcion()  + "</a></div>");
				out.println("<br><br>");
				
			}
			out.println("<a href='InicioBuscador.html'>Volver</a>");
			out.println("</center></body></html>");
		}
	}
}
