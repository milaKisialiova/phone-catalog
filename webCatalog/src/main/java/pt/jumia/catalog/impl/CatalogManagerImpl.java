package pt.jumia.catalog.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pt.jumia.catalog.ICatalogManager;
import pt.jumia.catalog.entities.Customer;

@Named
public class CatalogManagerImpl implements ICatalogManager{
	
	public EntityManager em = Persistence.createEntityManagerFactory("CustomerService").createEntityManager();;

	public CatalogManagerImpl() {
		
	}
	public Customer add(Customer customer) {
		em.getTransaction().begin();
		Customer customerFromDb = em.merge(customer);
		em.getTransaction().commit();
		return customerFromDb;
	}

	public void delete(Integer id) {
		em.getTransaction().begin();
		em.remove(em.find(Customer.class, id));
		em.getTransaction().commit();
	}

	public Customer get(Integer id) {
		return em.find(Customer.class, id);
	}

	public Customer getById(Integer id) {
		return em.find(Customer.class, id);
	}

	public List<Customer> getAll() {
		TypedQuery<Customer> namedQuery = em.createNamedQuery("Customer.getAll", Customer.class);
		return namedQuery.getResultList();
	}
}