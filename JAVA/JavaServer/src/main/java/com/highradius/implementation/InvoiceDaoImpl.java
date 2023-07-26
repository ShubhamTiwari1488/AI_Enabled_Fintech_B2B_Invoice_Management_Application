package com.highradius.implementation;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

import java.util.*;
import java.sql.*;

public class InvoiceDaoImpl implements InvoiceDao{
	private static final String INSERT_INVOICES_SQL = "INSERT INTO h2h_oap" + "VALUES" + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


	//private static final String SELECT_INVOICE_BY_ID = "select invoiceNumber, salesOrg, distributionChannel, division, releasedCreditValue, purchaseOrderType, companyCode, orderCreationDate, orderCreationTime, creditControlArea, soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus, customerNumber, amountInUSD, uniqueCustID from h2hsql.h2h_oap where id =?";

	private static final String SELECT_ALL_INVOICES = "select * from h2h_oap limit 100000";
	
	private static final String DELETE_INVOICES_SQL = "delete from h2h_oap where id = ?;";
	
	//private static final String UPDATE_INVOICS_SQL = "update h2hsql.h2h_oap set salesOrg = ?, distributionChannel = ?, division = ?, releasedCreditValue = ?, purchaseOrderType = ?, companyCode = ?, orderCreationDate = ?, orderCreationTime = ?, creditControlArea = ?, soldToParty = ?, orderAmount = ?, requestedDeliveryDate = ?, orderCurrency = ?, creditStatus = ?, customerNumber = ?, amountInUSD = ?, uniqueCustID = ? where invoiceNumber = ?;";

	
    @SuppressWarnings("unused")
	private DatabaseConnection dbConnection;

    public InvoiceDaoImpl() {
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public List<Invoice> getInvoices() {
    			List<Invoice> invoices = new ArrayList<>();
    			// Step 1: Establishing a Connection
    			try (Connection connection = DatabaseConnection.connect();
    					// Step 2:Create a statement using connection object
    					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INVOICES);) {
    				System.out.println(preparedStatement);
    				
    				// Step 3: Execute the query or update query
    				ResultSet rs = preparedStatement.executeQuery();
    				
    				// Step 4: Process the ResultSet object
    				while (rs.next()) {
    					int slNo = rs.getInt("Sl_no");
    				    int customerOrderId = rs.getInt("CUSTOMER_ORDER_ID");
    				    int salesOrg = rs.getInt("SALES_ORG");
    				    String distributionChannel = rs.getString("DISTRIBUTION_CHANNEL");
    				    String division = rs.getString("DIVISION");
    				    double releasedCreditValue = rs.getDouble("RELEASED_CREDIT_VALUE");
    				    String purchaseOrderType = rs.getString("PURCHASE_ORDER_TYPE");
    				    int companyCode = rs.getInt("COMPANY_CODE");
    				    String orderCreationDate = rs.getString("ORDER_CREATION_DATE");
    				    String orderCreationTime = rs.getString("ORDER_CREATION_TIME");
    				    String creditControlArea = rs.getString("CREDIT_CONTROL_AREA");
    				    int soldToParty = rs.getInt("SOLD_TO_PARTY");
    				    double orderAmount = rs.getDouble("ORDER_AMOUNT");
    				    String requestedDeliveryDate = rs.getString("REQUESTED_DELIVERY_DATE");
    				    String orderCurrency = rs.getString("ORDER_CURRENCY");
    				    String creditStatus = rs.getString("CREDIT_STATUS");
    				    int customerNumber = rs.getInt("CUSTOMER_NUMBER");
    				    double amountInUSD = rs.getDouble("AMOUNT_IN_USD");
    				    long uniqueCustId = rs.getLong("UNIQUE_CUST_ID");
    					invoices.add(new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, division, releasedCreditValue, purchaseOrderType, companyCode, orderCreationDate, orderCreationTime, creditControlArea, soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus, customerNumber, amountInUSD, uniqueCustId));
    				}
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    			return invoices;
    }

    @Override
    public void insertInvoice(Invoice invoice)  throws SQLException {
        System.out.println(INSERT_INVOICES_SQL);
        try (Connection connection = DatabaseConnection.connect();
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVOICES_SQL)){
        	preparedStatement.setInt(1, invoice.getSlNo());
            preparedStatement.setInt(2, invoice.getCustomerOrderId());
            preparedStatement.setInt(3, invoice.getSalesOrg());
            preparedStatement.setString(4, invoice.getDistributionChannel());
            preparedStatement.setString(5, invoice.getDivision());
            preparedStatement.setDouble(6, invoice.getReleasedCreditValue());
            preparedStatement.setString(7, invoice.getPurchaseOrderType());
            preparedStatement.setInt(8, invoice.getCompanyCode());
            preparedStatement.setString(9, invoice.getOrderCreationDate());
            preparedStatement.setString(10, invoice.getOrderCreationTime());
            preparedStatement.setString(11, invoice.getCreditControlArea());
            preparedStatement.setInt(12, invoice.getSoldToParty());
            preparedStatement.setDouble(13, invoice.getOrderAmount());
            preparedStatement.setString(14, invoice.getRequestedDeliveryDate());
            preparedStatement.setString(15, invoice.getOrderCurrency());
            preparedStatement.setString(16, invoice.getCreditStatus());
            preparedStatement.setInt(17, invoice.getCustomerNumber());
            preparedStatement.setDouble(18, invoice.getAmountInUSD());
            preparedStatement.setLong(19, invoice.getUniqueCustomerId());
           
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    
    @Override
    public boolean deleteInvoice(int id) throws SQLException {
    	boolean rowDeleted;
		try (Connection connection = DatabaseConnection.connect();
				PreparedStatement statement = connection.prepareStatement(DELETE_INVOICES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
    }

	@Override
	public List<Invoice> getInvoice() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInvoice(int Sl_no, String distribution_channel, int company_code, String order_currency)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	public void deleteInvoice(long cust_id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
