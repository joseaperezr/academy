//package es.uned.inmouned.view;
package es.uned.managed.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

import es.uned.model.Theme;
import es.uned.model.Usuario;
import es.uned.service.IUsuarioService;
//import com.otv.managed.bean.GuestPreferences;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

@ManagedBean(name = "themeSwitcherBean")
// @RequestScoped
@SessionScoped
public class ThemeSwitcherBean implements Serializable {

	private static final long serialVersionUID = 2L;

	private Map<String, String> themes;

	private List<Theme> advancedThemes;

	// private String theme;

	// @ManagedProperty(value="#{gp}")
	// private GuestPreferences gp;

	/*
	 * public void setGp(GuestPreferences gp) { this.gp = gp; }
	 */

	public Map<String, String> getThemes() {
		return themes;
	}

	public void setThemes(Map<String, String> themes) {
		this.themes = themes;
	}

	/*
	 * public String getTheme() { return theme; }
	 */

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@PostConstruct
	public void init() {
		theme = this.getTheme();

		advancedThemes = new ArrayList<Theme>();
		advancedThemes.add(new Theme("aristo", "aristo.png"));
		advancedThemes.add(new Theme("cupertino", "cupertino.png"));
		advancedThemes.add(new Theme("trontastic", "trontastic.png"));

		themes = new TreeMap<String, String>();
		themes.put("Aristo", "aristo");
		themes.put("Cupertino", "cupertino");
		themes.put("Afterdark", "afterdark");
		themes.put("Afternoon", "afternoon");
		themes.put("Afterwork", "afterwork");
		themes.put("Sunny", "sunny");
		themes.put("Blitzer", "blitzer");
		themes.put("Humanity", "humanity");
		themes.put("Start", "start");
		themes.put("Sunny", "sunny");

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void saveTheme() {
		this.setTheme(theme);
	}

	public List<Theme> getAdvancedThemes() {
		return advancedThemes;
	}

	private List<SelectItem> bedOptions;

	private List<SelectItem> smokingOptions;

	private List<SelectItem> creditCardExpMonths;

	private List<SelectItem> creditCardExpYears;

	private List<SelectItem> pageSizeOptions;

	public List<SelectItem> getBedOptions() {
		if (bedOptions == null) {
			bedOptions = new ArrayList<SelectItem>();
			bedOptions.add(new SelectItem(new Integer(1), "One king-size bed"));
			bedOptions.add(new SelectItem(new Integer(2), "Two double beds"));
			bedOptions.add(new SelectItem(new Integer(3), "Three beds"));
		}
		return bedOptions;
	}

	public List<SelectItem> getSmokingOptions() {
		if (smokingOptions == null) {
			smokingOptions = new ArrayList<SelectItem>();
			smokingOptions.add(new SelectItem(Boolean.TRUE, "Smoking"));
			smokingOptions.add(new SelectItem(Boolean.FALSE, "Non-Smoking"));
		}
		return smokingOptions;
	}

	public List<SelectItem> getCreditCardExpMonths() {
		if (creditCardExpMonths == null) {
			creditCardExpMonths = new ArrayList<SelectItem>();
			creditCardExpMonths.add(new SelectItem(new Integer(1), "Jan"));
			creditCardExpMonths.add(new SelectItem(new Integer(2), "Feb"));
			creditCardExpMonths.add(new SelectItem(new Integer(3), "Mar"));
			creditCardExpMonths.add(new SelectItem(new Integer(4), "Apr"));
			creditCardExpMonths.add(new SelectItem(new Integer(5), "May"));
			creditCardExpMonths.add(new SelectItem(new Integer(6), "Jun"));
			creditCardExpMonths.add(new SelectItem(new Integer(7), "Jul"));
			creditCardExpMonths.add(new SelectItem(new Integer(8), "Aug"));
			creditCardExpMonths.add(new SelectItem(new Integer(9), "Sep"));
			creditCardExpMonths.add(new SelectItem(new Integer(10), "Oct"));
			creditCardExpMonths.add(new SelectItem(new Integer(11), "Nov"));
			creditCardExpMonths.add(new SelectItem(new Integer(12), "Dec"));
		}
		return creditCardExpMonths;
	}

	public List<SelectItem> getCreditCardExpYears() {
		if (creditCardExpYears == null) {
			creditCardExpYears = new ArrayList<SelectItem>();
			creditCardExpYears.add(new SelectItem(new Integer(2008), "2008"));
			creditCardExpYears.add(new SelectItem(new Integer(2009), "2009"));
			creditCardExpYears.add(new SelectItem(new Integer(2010), "2010"));
			creditCardExpYears.add(new SelectItem(new Integer(2010), "2011"));
			creditCardExpYears.add(new SelectItem(new Integer(2010), "2012"));
		}
		return creditCardExpYears;
	}

	public List<SelectItem> getPageSizeOptions() {
		if (pageSizeOptions == null) {
			pageSizeOptions = new ArrayList<SelectItem>();
			pageSizeOptions.add(new SelectItem(new Integer(5), "5"));
			pageSizeOptions.add(new SelectItem(new Integer(10), "10"));
			pageSizeOptions.add(new SelectItem(new Integer(20), "20"));
		}
		return pageSizeOptions;
	}

	private String theme = "start"; // default

	public String getTheme() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		if (params.containsKey("theme")) {
			theme = params.get("theme");
		}

		return theme;
	}
	/*
	 * public void setTheme(String theme) { this.theme = theme; }
	 */

}
