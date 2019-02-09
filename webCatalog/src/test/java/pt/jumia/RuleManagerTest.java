package pt.jumia;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.jumia.catalog.IRuleManager;
import pt.jumia.catalog.entities.CatalogItem;
import pt.jumia.catalog.impl.RuleManagerImpl;

public class RuleManagerTest {

	private IRuleManager ruleManager = new RuleManagerImpl();
	
	private static final String NUMBER_VALID = "(258) 847651504";
	private static final String NUMBER_VALID_DASH = "(258) 847-651-504";
	private static final String NUMBER_VALID_SPACE = "(258) 847 651 504";
	private static final String NUMBER_INVALID_CODE = "(111) 847651504";
	private static final String NUMBER_INVALID_REGEX_DIGIT = "(258) 047651504";
	private static final String NUMBER_INVALID_REGEX_LENGTH = "(258) 847651504000000";

	@Test
	public void testParseNumberValid() {
		CatalogItem item = ruleManager.parseNumber(NUMBER_VALID);
		assertEquals("(258)", item.getCode());
		assertEquals("Mozambique", item.getCountry());
		assertEquals("847651504", item.getShortNumber());
		assertEquals("Valid", item.getState());
	}

	@Test
	public void testParseNumber() {
		CatalogItem item = ruleManager.parseNumber(NUMBER_VALID_DASH);
		assertEquals("Valid", item.getState());
		
		item = ruleManager.parseNumber(NUMBER_VALID_SPACE);
		assertEquals("Valid", item.getState());
	}
	
	@Test
	public void testParseNumberInvalid() {
		CatalogItem item = ruleManager.parseNumber(NUMBER_INVALID_CODE);
		assertEquals("", item.getCode());
		assertEquals("", item.getCountry());
		assertEquals(NUMBER_INVALID_CODE, item.getShortNumber());
		assertEquals("Not valid", item.getState());
		
		item = ruleManager.parseNumber(NUMBER_INVALID_REGEX_DIGIT);
		assertEquals("Not valid", item.getState());
		
		item = ruleManager.parseNumber(NUMBER_INVALID_REGEX_LENGTH);
		assertEquals("Not valid", item.getState());
	}
}
