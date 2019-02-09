package pt.jumia.number.rules;

public interface INumberRule {
	
	boolean isValid(String phoneNumber);
	
	boolean startsWithCode(String phoneNumber);
	
	String getCountry();
	
	String getRegex();
	
	String getCode();
	
	String getShortNumber(String phoneNumber);
}
