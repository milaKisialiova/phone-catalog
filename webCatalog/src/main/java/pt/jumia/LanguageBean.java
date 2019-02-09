package pt.jumia;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "lngBean")
@SessionScoped
public class LanguageBean {

	private String localeCode;

	private static Map<String, Locale> countries;

	static {
		countries = new HashMap<String, Locale>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Portuguese", new Locale("pt", "PT"));
	}

	public void changeLanguage(String lng) {
		for (Map.Entry<String, Locale> entry : countries.entrySet()) {
			if (entry.getValue().getLanguage().equals(lng)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public static Map<String, Locale> getCountries() {
		return countries;
	}

	public static void setCountries(Map<String, Locale> countries) {
		LanguageBean.countries = countries;
	}

}
