package com.highradius.servlets;

import com.highradius.implementation.*;
import com.highradius.model.Invoice;
import com.google.gson.Gson;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;



@WebServlet("/ReadServlet")

public class ReadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InvoiceDao invoiceDao;
	
	public void init(){
        invoiceDao = new InvoiceDaoImpl();
    }
	
	public List<Invoice> getInvoice(){
        return invoiceDao.getInvoices();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String jsonResponse = new String();
		PrintWriter out = response.getWriter();
		System.out.println();
		
        // Retrieve invoices from the database based on the provided Invoice object
        List<Invoice> invoicesList = getInvoice();

        // Convert invoices to JSON using Gson library
        Gson gson = new Gson();
        String json = gson.toJson(invoicesList);

        // Set response content type to JSON
        response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonResponse);
    }

}
