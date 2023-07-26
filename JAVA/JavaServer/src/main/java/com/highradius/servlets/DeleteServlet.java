package com.highradius.servlets;
import com.highradius.connection.*;
import com.highradius.model.*;
import com.highradius.implementation.*;

import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InvoiceDao invoiceDao;

    public void init() {
        invoiceDao = new InvoiceDaoImpl();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete functionality implementation
        // Retrieve data from the request parameters and delete the corresponding record from the database
        // ...
		
		System.out.println("helo"+" "+request.getParameter("Sl_no"));
        int Sl_no=Integer.parseInt(request.getParameter("Sl_no"));
//        DatabaseConnection dc=new DatabaseConnection();
//        
//        List<Invoice> ls=dc.getInvoices();
//
//        for(int i=0;i<ls.size();i++){
//            if(ls.get(i).getUnique_cust_id()==unique_cust_id){
//                ls.remove(i);
//                break;
//            }
//        }
        
        // Redirect back to the ReadServlet or any other appropriate page
        
        try {
        	invoiceDao.deleteInvoice(Sl_no); 
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        finally{
        	response.getWriter().append("Deleted! "+Sl_no);
        }
        
    }
	
}
