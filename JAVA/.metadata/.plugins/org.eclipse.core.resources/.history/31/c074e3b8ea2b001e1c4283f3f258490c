package com.highradius.implementation;
import java.sql.SQLException;
import java.util.*;
import com.highradius.model.*;


public interface InvoiceDao {
	List<Invoice> getInvoice () throws SQLException, ClassNotFoundException;
	void insertInvoice (Invoice i) throws ClassNotFoundException;
	void updateInvoice (int Sl_no,String distribution_channel,int company_code,String order_currency)throws SQLException,ClassNotFoundException;
	void deleteInvoice (long cust_id)throws SQLException, ClassNotFoundException;	

}
