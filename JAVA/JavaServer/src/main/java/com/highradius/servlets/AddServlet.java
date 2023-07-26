package com.highradius.servlets;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import com.highradius.implementation.*;
import com.highradius.model.Invoice;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private InvoiceDao invoiceDao;

    public void init() {
        invoiceDao = new InvoiceDaoImpl();
    }
    
    public AddServlet() {
    	super();
    }
    
    public void addInvoice(Invoice invoice) throws SQLException, ClassNotFoundException{
        invoiceDao.insertInvoice(invoice);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the invoice data from the request parameters
        int slNo = Integer.parseInt(request.getParameter("slNo"));
        int customerOrderId = Integer.parseInt(request.getParameter("customerOrderId"));
        int salesOrg = Integer.parseInt(request.getParameter("salesOrg"));
        String distributionChannel = request.getParameter("distributionChannel");
        String division = request.getParameter("division");
        double releasedCreditValue = Double.parseDouble(request.getParameter("releasedCreditValue"));
        String purchaseOrderType = request.getParameter("purchaseOrderType");
        int companyCode = Integer.parseInt(request.getParameter("companyCode"));
        String orderCreationDate = request.getParameter("orderCreationDate");
        String orderCreationTime = request.getParameter("orderCreationTime");
        String creditControlArea = request.getParameter("creditControlArea");
        int soldToParty = Integer.parseInt(request.getParameter("soldToParty"));
        double orderAmount = Double.parseDouble(request.getParameter("orderAmount"));
        String requestedDeliveryDate = request.getParameter("requestedDeliveryDate");
        String orderCurrency = request.getParameter("orderCurrency");
        String creditStatus = request.getParameter("creditStatus");
        int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
        double amountInUSD = Double.parseDouble(request.getParameter("amountInUSD"));
        long uniqueCustomerId = Long.parseLong(request.getParameter("uniqueCustomerId"));

        // Create an Invoice object with the retrieved data
        Invoice newInvoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, division, releasedCreditValue, purchaseOrderType, companyCode, orderCreationDate, orderCreationTime, creditControlArea, soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus, customerNumber, amountInUSD, uniqueCustomerId);

        // Add the invoice to the database
        try {
        	addInvoice(newInvoice);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }   
    }
}
