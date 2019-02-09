package pt.jumia.catalog;

import java.util.List;

import pt.jumia.catalog.entities.Customer;

public interface ICatalogManager{
 
	Customer add(Customer customer);
	
	void delete(Integer id);
	
	Customer get(Integer id);
	
	List<Customer> getAll();
 
}