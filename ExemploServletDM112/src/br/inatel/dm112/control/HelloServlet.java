package br.inatel.dm112.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloService")
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public HelloServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Served at: " + request.getContextPath());
		
		response.getWriter()
			.append("Olá de doGet. Hora do servidor: " + new java.util.Date());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Served at: " + request.getContextPath());
		
		response.getWriter()
			.append("Olá de doPost. Hora do servidor: " + new java.util.Date());
	}

}
