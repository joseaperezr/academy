package es.uned;



import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 * Class object to abstract all JNDI usage and to hide the complexities of 
 * initial context creation and EJB home object lookup. Multiple clients can 
 * reuse this service to reduce code complexity, provide a single point of 
 * control, and improve performance by providing a caching facility.
 * 
 * @author José Antonio Pérez Reyes
 */
public class ServiceLocator {
	/** instance of this singleton class */
	private static ServiceLocator instance = null;
	/** intial context of the JNDI service */
	private InitialContext initialContext;
	/** services cache */
	private Map<String, Object> cache;
	
	/**
	 * Constructor of this singleton class. 
	 * @throws NamingException if a jndi naming error occurred.
	 */
	private ServiceLocator() throws NamingException {
        initialContext = new InitialContext();
        cache = Collections.synchronizedMap(new HashMap<String, Object>());
	}
	/**
     * Initializes this singleton class and creates a new instance
     * if it's already not.
     * 
     * @return instance of this singleton class.
     * @throws NamingException if a jndi naming error occurred.
	 */
	public static ServiceLocator init() throws NamingException {
		if (instance == null)
			instance = new ServiceLocator();
		return instance;
	}
	/**
     * Gets the instance of this singleton class.
     * 
     * @return instance of this singleton class.
	 */
	public static ServiceLocator getInstance() {
		return instance;		
	}
	/**
	 * Gets the service object bound by the jndi value.
	 * 
	 * @param jndi jndi binding name.
	 * @return service object bound, null if it does not exist.
	 * @throws NamingException if a jndi naming error occurred.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getService(String jndi) throws NamingException {	
		Object service = null;
		
		if (cache.containsKey(jndi)) {
			service = cache.get(jndi);
		} else {         
			service = initialContext.lookup(jndi);
			cache.put(jndi, service);
		}		
		return (T)service;		
	}
	
	/*@SuppressWarnings("unchecked")
	public <T> T getController(String beanName) throws NamingException {
		System.out.println("getController............");
		FacesContext context = FacesContext.getCurrentInstance();
		return (T)context.getApplication().  getELResolver().getValue(context.getELContext(), null, beanName);
	}*/
	
	
	  /**
     * Gets a controller at the current faces context by the 
     * managed bean name.
     * 
     * @param controllerName controller name to find.
     * @return controller found, null if it does not exist.
     */
    public Object lookUpController(String controllerName) {
    	FacesContext context = FacesContext.getCurrentInstance();
		Object controller = null;
		controller = (Object)context.getApplication().getVariableResolver().resolveVariable(context, controllerName);
		return controller;
    }	
	
	
}
