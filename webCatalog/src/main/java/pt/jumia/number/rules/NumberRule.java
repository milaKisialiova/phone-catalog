package pt.jumia.number.rules;

public class NumberRule implements INumberRule {

	private String country;
	private String regex;
	private String code;

	public NumberRule() {
	}

	public NumberRule(String country, String code, String regex) {
		this();
		this.country = country;
		this.regex = regex;
		this.code = code;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isValid(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		}
		return phoneNumber.matches(regex);
	}

	public boolean startsWithCode(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		}
		return phoneNumber.replaceAll("[^\\d.]", "").startsWith(code.replaceAll("[^\\d.]", ""));
	}

	public String getShortNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			return null;
		}
		String numerDigits = phoneNumber.replaceAll("[^\\d.]", "");
		String codeDigits = code.replaceAll("[^\\d.]", "");
		return numerDigits.substring(codeDigits.length());
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
