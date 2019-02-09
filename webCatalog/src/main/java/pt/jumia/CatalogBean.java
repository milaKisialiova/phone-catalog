package pt.jumia;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

import pt.jumia.catalog.ICatalogManager;
import pt.jumia.catalog.IRuleManager;
import pt.jumia.catalog.entities.CatalogItem;
import pt.jumia.catalog.entities.Customer;

@Named
@Scope("session")
public class CatalogBean {

	@Inject
	ICatalogManager catalogManager;
	
	@Inject
	IRuleManager ruleManager;

	private List<String> countries;
	private String filterCountry;
	
	private List<String> states;
	private String filterState;
	
	private List<CatalogItem> catalogItems;
	
	@PostConstruct
	private void init() {
		initCountries();
		initStates();
		initCatalog();
	}

	private void initCountries() {
		countries = ruleManager.getCountries();
		countries.add(0, "Any");
		
		filterCountry = countries.get(0);
	}
	
	private void initStates() {
		states = new ArrayList<String>();
		states.add("Any");
		states.add("Valid");
		states.add("Not valid");
		
		filterState = states.get(0);
	}
	
	private void initCatalog() {
		catalogItems = new ArrayList<CatalogItem>();
		for (Customer customer : catalogManager.getAll()) {
			CatalogItem catalogItem = ruleManager.parseNumber(customer.getFullPhoneNumber());
			catalogItem.setName(customer.getFullName());
			catalogItems.add(catalogItem);
		}
	}
	
	private List<CatalogItem> filterCatalog() {
		List<CatalogItem> filteredCatalog = new ArrayList<CatalogItem>();
		for (CatalogItem catalogItem : catalogItems) {
			if ((filterCountry.equals("Any") || catalogItem.getCountry().equals(filterCountry))
					&& (filterState.equals("Any") || catalogItem.getState().equals(filterState))) {
				filteredCatalog.add(catalogItem);
			}
		}
		return filteredCatalog;
	}
	
	public void setCatalogManager(ICatalogManager catalogManager) {
		this.catalogManager = catalogManager;
	}
	
	public void setRuleManager(IRuleManager ruleManager) {
		this.ruleManager = ruleManager;
	}

	public String getFilterCountry() {
		return filterCountry;
	}

	public void setFilterCountry(String filterCountry) {
		this.filterCountry = filterCountry;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public String getFilterState() {
		return filterState;
	}

	public void setFilterState(String filterState) {
		this.filterState = filterState;
	}

	public List<CatalogItem> getCatalogItems() {
		return filterCatalog();
	}

	public void setCatalogItems(List<CatalogItem> catalogItems) {
		this.catalogItems = catalogItems;
	}
	
	public void filterCountryChange(ValueChangeEvent e){
		filterCountry = e.getNewValue().toString();
	}
	
	public void filterStateChange(ValueChangeEvent e){
		filterState = e.getNewValue().toString();
	}

}
