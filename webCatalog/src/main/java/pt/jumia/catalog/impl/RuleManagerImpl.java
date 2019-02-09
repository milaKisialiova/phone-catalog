package pt.jumia.catalog.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import pt.jumia.catalog.IRuleManager;
import pt.jumia.catalog.entities.CatalogItem;
import pt.jumia.number.rules.INumberRule;
import pt.jumia.number.rules.NumberRule;

@Named
public class RuleManagerImpl implements IRuleManager{

	private List<INumberRule> rules;

	public RuleManagerImpl() {
		init();
	}

	private void init() {
		rules = new ArrayList<INumberRule>();
		rules.add(new NumberRule("Cameroon", "(237)", "\\(237\\)\\ ?[2368]\\d{7,8}$"));
		rules.add(new NumberRule("Ethiopia", "(251)", "\\(237\\)\\ ?[2368]\\d{7,8}$"));
		rules.add(new NumberRule("Morocco", "(212)", "\\(212\\)\\ ?[5-9]\\d{8}$"));
		rules.add(new NumberRule("Mozambique", "(258)", "\\(258\\)\\ ?[28]\\d{7,8}$"));
		rules.add(new NumberRule("Uganda", "(256)", "\\(256\\)\\ ?\\d{9}$"));
	}

	public CatalogItem parseNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return null;
		}
		phoneNumber = formatNumber(phoneNumber);
		CatalogItem catalogItem = new CatalogItem();
		for (INumberRule rule : rules) {
			if (rule.startsWithCode(phoneNumber)) {
				catalogItem.setCode(rule.getCode());
				catalogItem.setCountry(rule.getCountry());
				catalogItem.setShortNumber(rule.getShortNumber(phoneNumber));
				catalogItem.setState(rule.isValid(phoneNumber) ? "Valid" : "Not valid");
				return catalogItem;
			}
		}
		catalogItem.setCode("");
		catalogItem.setCountry("");
		catalogItem.setShortNumber(phoneNumber);
		catalogItem.setState("Not valid");
		return catalogItem;
	}
	
	private String formatNumber(String phoneNumber) {
		// replace dashes between numbers
		phoneNumber = phoneNumber.replaceAll("(?<=\\d)\\-+(?=\\d)", "");
		// replace spaces between numbers
		phoneNumber = phoneNumber.replaceAll("(?<=\\d) +(?=\\d)", "");
		return phoneNumber;
	}

	public List<String> getCountries() {
		List<String> countries = new ArrayList<String>();
		for (INumberRule rule : rules) {
			countries.add(rule.getCountry());
		}
		return countries;
	}
}
