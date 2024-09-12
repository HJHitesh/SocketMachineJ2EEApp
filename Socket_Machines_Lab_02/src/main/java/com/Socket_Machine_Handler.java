package com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;



@WebServlet("/SocketMachine")
public class Socket_Machine_Handler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static double TYPE_A_PRICE = 20;
	private static double TYPE_B_PRICE = 30;
	private static double TYPE_C_PRICE = 40;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Socket_Machine_Handler() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/socket_machine.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
    	 // do the validation of the request
        if(StringUtils.isEmpty(request.getParameter("quantity"))) {
        	response.setContentType("text/html");
        	response.getWriter().println("<h1>Quantity should be empty</h1>");
        	return;
        }
    	
        String socketType = request.getParameter("socketType");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        // do the validation of the request
        if(quantity <= 0) {
        	response.setContentType("text/html");
        	response.getWriter().println("<h1>Quantity should be more than zero</h1>");
        	return;
        }
        
        if(StringUtils.isEmpty(name)) {
        	response.setContentType("text/html");
        	response.getWriter().println("<h1>Name not be empty</h1>");
        	return;
        }
        
        if(StringUtils.isEmpty(email)) {
        	response.setContentType("text/html");
        	response.getWriter().println("<h1>Email not be empty</h1>");
        	return;
        }
        
        //Calculate the Price of Quote
        double totalQuoteValue = 0.0;
        double totalQuoteTypeSingleValue = 0.0;
        if("Type A".equalsIgnoreCase(socketType)) {
        	totalQuoteValue = quantity * TYPE_A_PRICE;
        	totalQuoteTypeSingleValue = TYPE_A_PRICE;
        }else if("Type B".equalsIgnoreCase(socketType)) {
        	totalQuoteValue = quantity * TYPE_B_PRICE;
        	totalQuoteTypeSingleValue = TYPE_B_PRICE;
        }else if("Type C".equalsIgnoreCase(socketType)) {
        	totalQuoteValue = quantity * TYPE_C_PRICE;
        	totalQuoteTypeSingleValue = TYPE_C_PRICE;
        }else {
        	response.setContentType("text/html");
            response.getWriter().println("<h1>No Type is selected</h1>");
            return;
        }
        

        // Process the form data (for now, just send it back in the response)
        response.setContentType("text/html");
        response.getWriter().println("<h1>Order Summary</h1>");
        response.getWriter().println("<p>Socket Type: " + socketType + "</p>");
        response.getWriter().println("<p>Quantity: " + quantity + "</p>");
        response.getWriter().println("<p>Customer Name: " + name + "</p>");
        response.getWriter().println("<p>Customer Email: " + email + "</p>");
        response.getWriter().println("<p>Per Piece Price Quoted Just for you: CAD$ " + totalQuoteTypeSingleValue + "</p>");
        response.getWriter().println("<p> Total Quote Value : CAD$  " + totalQuoteValue + "</p>");
    	
    }

}
