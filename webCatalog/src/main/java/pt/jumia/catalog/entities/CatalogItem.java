package pt.jumia.catalog.entities;

public class CatalogItem {

	private String name;
	private String code;
	private String shortNumber;
	private String country;
	private String state;
	
	public CatalogItem() {
	}
	
	public CatalogItem(String name, String code, String shortNumber, String country, String state) {
		this();
		this.name = name;
		this.code = code;
		this.shortNumber = shortNumber;
		this.country = country;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public String getShortNumber() {
		return shortNumber;
	}

	public void setShortNumber(String shortNumber) {
		this.shortNumber = shortNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
