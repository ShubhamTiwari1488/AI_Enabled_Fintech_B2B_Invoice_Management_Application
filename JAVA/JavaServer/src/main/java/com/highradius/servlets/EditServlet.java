package com.highradius.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;

public class EditServlet extends HttpServlet{
	private InvoiceDao invoiceDao;
	
	public void init() {
        invoiceDao = new InvoiceDaoImpl();
    }
    
    public EditServlet() {
    	super();
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException {
    	
    	 int sl_no=Integer.parseInt(req.getParameter("Sl_no"));
    	 
    	 String distribution_channel=req.getParameter("distribution_channel");
    	 
    	 int company_code= Integer.parseInt(req.getParameter("company_code"));
    	 
    	 String order_currency= req.getParameter("order_currency");
    	 
    	  try {
          	invoiceDao.updateInvoice(sl_no,distribution_channel,company_code,order_currency); 
          }
          catch(Exception e) {
          	e.printStackTrace();
          }
          finally{
          	res.getWriter().append("Updated! "+sl_no);
          }
    }
}
