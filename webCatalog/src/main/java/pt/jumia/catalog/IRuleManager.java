package pt.jumia.catalog;

import java.util.List;

import pt.jumia.catalog.entities.CatalogItem;

public interface IRuleManager{
 
	CatalogItem parseNumber(String phoneNumber);
	
	List<String> getCountries();
 
}