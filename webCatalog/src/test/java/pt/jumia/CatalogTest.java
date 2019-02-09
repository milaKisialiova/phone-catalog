package pt.jumia;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import pt.jumia.catalog.ICatalogManager;
import pt.jumia.catalog.entities.Customer;
import pt.jumia.catalog.impl.CatalogManagerImpl;

public class CatalogTest {

	private ICatalogManager catalogManager = new CatalogManagerImpl();

	@Test
	public void testAdd() {
		Customer customerToAdd = new Customer(41, "liudmila kisialiova", "(111) 11111111");
		Customer addedCustomer = catalogManager.add(customerToAdd);
		assertTrue(customerToAdd.equals(addedCustomer));
	}

	@Test
	public void testDelete() throws Exception {
		catalogManager.delete(41);
		assertNull(catalogManager.get(41));
	}

	@Test
	public void testGetAll() {
		List<Customer> customers = catalogManager.getAll();
		for (Customer customer : customers) {
			System.out.println(customer.toString());
		}
		assertTrue(!customers.isEmpty());
	}
}
