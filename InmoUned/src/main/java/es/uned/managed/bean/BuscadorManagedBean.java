package es.uned.managed.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import org.springframework.dao.DataAccessException;

import es.uned.helper.BuscadorHelper;
import es.uned.helper.FotoHelper;
import es.uned.model.Foto;
import es.uned.model.Inmueble;
import es.uned.model.Interesado;
import es.uned.service.IFotoService;
import es.uned.service.IInmuebleService;
import es.uned.service.IInteresadoService;
import es.uned.service.IUbicacionService;
import es.uned.service.IUsuarioService;
import es.uned.service.MailService;
import org.apache.log4j.Logger;

/**
 * 
 * Buscador Managed Bean
 * 
 * @author José Antonio Pérez Reyes
 * @since 24 Jun 2012
 * @version 1.0.0
 * 
 */
@ManagedBean(name = "buscadorMB")
// @RequestScoped
// @ViewScoped
@SessionScoped
public class BuscadorManagedBean implements Serializable {

	//
	private static final long serialVersionUID = 7L;
        //
        private Logger logger = Logger.getLogger(BuscadorManagedBean.class);

	//
	private static final String ERROR = "error";

	// Spring User Service is injected...
	@ManagedProperty(value = "#{InmuebleService}")
	private IInmuebleService inmuebleService;

	@ManagedProperty(value = "#{FotoService}")
	private IFotoService fotoService;

	@ManagedProperty(value = "#{UsuarioService}")
	private IUsuarioService usuarioService;

	@ManagedProperty(value = "#{UbicacionService}")
	private IUbicacionService ubicacionService;

	@ManagedProperty(value = "#{InteresadoService}")
	private IInteresadoService interesadoService;

	//
	@ManagedProperty(value = "#{MailService}")
	private MailService mailService;
	//
	private BuscadorHelper buscadorHelper = new BuscadorHelper();
	//
	private InmuebleDataModel inmueblesDataModel = new InmuebleDataModel();
	//
	private Inmueble inmuebleSelected;
	//
	private Interesado interesado = new Interesado();

	private StreamedContent file;

	// private List<StreamedContent> fotos = new ArrayList<StreamedContent>();
	private List<FotoHelper> fotos = new ArrayList<FotoHelper>();

	/**
	 * 
	 */
	public BuscadorManagedBean() {

		this.inmueblesDataModel = new InmuebleDataModel();

	}

	public Inmueble getInmuebleSelected() {
		return inmuebleSelected;
	}

	public void setInmuebleSelected(Inmueble inmuebleSelected) {
		this.inmuebleSelected = inmuebleSelected;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {

	}

	/**
	 * 
	 * @return
	 */
	public IInmuebleService getInmuebleService() {
		return this.inmuebleService;
	}

	/**
	 * 
	 * @param inmuebleService
	 */
	public void setInmuebleService(IInmuebleService inmuebleService) {
		this.inmuebleService = inmuebleService;
	}

	public IInteresadoService getInteresadoService() {
		return interesadoService;
	}

	public void setInteresadoService(IInteresadoService interesadoService) {
		this.interesadoService = interesadoService;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public IUbicacionService getUbicacionService() {
		return ubicacionService;
	}

	public void setUbicacionService(IUbicacionService ubicacionService) {
		this.ubicacionService = ubicacionService;
	}

	public IFotoService getFotoService() {
		return fotoService;
	}

	public void setFotoService(IFotoService fotoService) {
		this.fotoService = fotoService;
	}

	public BuscadorHelper getBuscadorHelper() {
		return buscadorHelper;
	}

	public void setBuscadorHelper(BuscadorHelper buscadorHelper) {
		this.buscadorHelper = buscadorHelper;
	}

	public Interesado getInteresado() {
		return interesado;
	}

	public void setInteresado(Interesado interesado) {
		this.interesado = interesado;
	}

	public InmuebleDataModel getInmueblesDataModel() {
		return inmueblesDataModel;
	}

	public void setInmueblesDataModel(InmuebleDataModel inmueblesDataModel) {
		this.inmueblesDataModel = inmueblesDataModel;
	}

	/**
	 * Busqueda de inmuebles
	 * 
	 * @param actionEvent
	 */
	public String buscar() {
		// Persist inmueble
		try {
			List<Inmueble> inmuebles = new ArrayList<Inmueble>();

			inmuebles = getInmuebleService().getInmueblesByBuscador(
					this.buscadorHelper);

			this.inmueblesDataModel = new InmuebleDataModel(inmuebles);

			return "resultados";

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return this.ERROR;
	}

	public void onRowSelect(SelectEvent event) {

	}

	public String onRowSelectNavigate(SelectEvent event) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.put("inmuebleSelected", event.getObject());

		return "detalles?faces-redirect=true";
	}

	public void onRowUnselect(UnselectEvent event) {

	}

	/**
	 * Se envia email de interes
	 */
	public void interes() {
		try {
			logger.debug("interes....");
			Interesado _interesado = getInteresadoService()
					.getInteresadoByEmail(this.interesado.getEmail());

			if (_interesado == null) {
				logger.debug("noencontro");
				this.interesado.getIntereses().add(this.inmuebleSelected);
				getInteresadoService().addInteresado(this.interesado);

				// email
				getMailService()
						.sendMail(
								interesado.getEmail(),
								this.inmuebleSelected.getPropietario()
										.getEmail(),
								"Avisos InmoUned / Alguien tiene interés en tu inmueble.",
								"Hola "
										+ this.inmuebleSelected
												.getPropietario().getNombre()
										+ ". "
										+ " El Sr/Sra. "
										+ interesado.getNombre()
										+ " "
										+ interesado.getApellido1()
										+ " tiene interés en tu propiedad con número de identificación: "
										+ String.valueOf(this.inmuebleSelected
												.getId()) + ". Su email es: "
										+ interesado.getEmail()
										+ " y su móvil el: "
										+ interesado.getMovil());
			} else {

				logger.debug("encontrado");
				logger.debug(_interesado.getEmail());
				_interesado.getIntereses().add(this.inmuebleSelected);
				getInteresadoService().updateInteresado(_interesado);

				// email
				getMailService()
						.sendMail(
								_interesado.getEmail(),
								this.inmuebleSelected.getPropietario()
										.getEmail(),
								"Avisos InmoUned / Alguien tiene interés en tu inmueble.",
								"Hola "
										+ this.inmuebleSelected
												.getPropietario().getNombre()
										+ ". "
										+ " El Sr/Sra. "
										+ _interesado.getNombre()
										+ " "
										+ _interesado.getApellido1()
										+ " tiene interés en tu propiedad con número de identificación: "
										+ String.valueOf(this.inmuebleSelected
												.getId()) + " .Su email es: "
										+ _interesado.getEmail()
										+ " y su móvil el: "
										+ _interesado.getMovil());

			}

			this.interesado = new Interesado();
			FacesMessage msg = new FacesMessage("+info",
					"Solicitud de interes procesada.");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

	}

	/**
	 * Cargando detalles del inmueble seleccionado desde el buscador.
	 * 
	 * @return Regla de navegación para la página de ficha de detalles.
	 */
	public String detalles() {
		try {
			if (this.inmuebleSelected==null) return null;
			this.inmuebleSelected.setFotos(new ArrayList<Foto>());
			this.fotos = new ArrayList<FotoHelper>();

			List<Foto> _fotos = getFotoService().getFotosByInmueble(
					this.inmuebleSelected);

			this.inmuebleSelected.setFotos(_fotos);
			logger.debug("Size: " + _fotos.size());

			for (Foto _foto : _fotos) {

				logger.debug(_foto.getId());
				FotoHelper fotoHelper = new FotoHelper();

				fotoHelper.setId(String.valueOf(_foto.getId()));

				fotoHelper.setFoto(new DefaultStreamedContent(_foto.getFoto()
						.getBinaryStream(), "image/jpg", _foto.getNombre()));

				fotoHelper.setNombre(_foto.getNombre());
				fotoHelper.setMimeType(_foto.getMimeType());

				this.fotos.add(fotoHelper);

			}

			this.file = this.fotos.get(0).getFoto();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "detalles";
	}

	private void clean() {
		this.buscadorHelper = new BuscadorHelper();
		this.inmueblesDataModel = new InmuebleDataModel();
		this.inmuebleSelected = null;
		this.interesado = new Interesado();

	}

	public StreamedContent getFile() {
		logger.debug("getFile");

		String id = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("item_id");

		logger.debug("id: " + id);

		for (FotoHelper fh : fotos) {
			if (fh.getId().equals(id)) {
				return fh.getFoto();

			}
		}

		return fotos.get(0).getFoto();
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<FotoHelper> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotoHelper> fotos) {
		this.fotos = fotos;
	}

}
