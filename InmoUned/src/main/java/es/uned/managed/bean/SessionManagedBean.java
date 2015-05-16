package es.uned.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

import es.uned.ServiceLocator;
import es.uned.helper.UsuarioHelper;
import es.uned.model.Inmueble;
import es.uned.model.Opcion;
import es.uned.model.TiposUsuarios;
import es.uned.model.Ubicacion;
import es.uned.model.Usuario;
import es.uned.service.IInmuebleService;
import es.uned.service.IOpcionService;
import es.uned.service.IUbicacionService;
import es.uned.service.IUsuarioService;
import es.uned.service.MailService;
import org.apache.log4j.Logger;

/**
 * Controler de la sesion de usuario.
 *
 * Session Managed Bean
 *
 * @author José Antonio Pérez Reyes
 * @since 24 Jun 2012
 * @version 1.0.0
 *
 */
@ManagedBean(name = "sessionMB")
// @RequestScoped
// @ViewScoped
@SessionScoped
public class SessionManagedBean implements Serializable {

    private Logger logger = Logger.getLogger(SessionManagedBean.class);
    //
    private static final long serialVersionUID = 9L;
    //
    private static final String LOGGED = "logged";
    //
    private static final String NOTLOGGED = "notlogged";
    // Spring User Service is injected...
    @ManagedProperty(value = "#{InmuebleService}")
    private IInmuebleService inmuebleService;
    @ManagedProperty(value = "#{UsuarioService}")
    private IUsuarioService usuarioService;
    @ManagedProperty(value = "#{UbicacionService}")
    private IUbicacionService ubicacionService;
    @ManagedProperty(value = "#{MailService}")
    // @Resource
    private MailService mailService;
    @ManagedProperty(value = "#{OpcionService}")
    private IOpcionService opcionService;
    //
    @ManagedProperty(value = "#{usuarioMB}")
    private UsuarioManagedBean usuarioManagedBean;
    //
    private boolean logged;
    // @ManagedProperty(value = "#{inmuebleMB}")
    // @Resource
    private InmuebleManagedBean inmuebleManagedBean;
    //
    private UsuarioHelper usuarioHelper = new UsuarioHelper();
    //
    private Usuario usuarioLogged = new Usuario();
    //
    private InmuebleDataModel inmueblesDataModel = new InmuebleDataModel();
    //
    private Inmueble inmuebleSelected;
    //
    private boolean skip;

    @PostConstruct
    public void init() {
        // public SessionManagedBean() {
        try {

            this.usuarioHelper = new UsuarioHelper();

            Usuario usu = new Usuario();

            usu.setEmail("josea.perezr@gmail.com");
            usu.setNombre("Jose");
            usu.setApellido1("Pérez");
            usu.setApellido2("Reyes");
            usu.setPassword("123");
            usu.setTipoUsuario(TiposUsuarios.ADMIN.name());

            getUsuarioService().addUsuario(usu);

            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setProvincia("Tenerife");
            ubicacion.setTipoVia("AVENIDA");
            ubicacion.setNombreVia("Los Majuelos");

            getUbicacionService().addUbicacion(ubicacion);

            Inmueble inmueble = new Inmueble();

            inmueble.setTamaño("120");

            inmueble.setHabitaciones(3);
            inmueble.setBaños(3);
            inmueble.setParking(true);
            inmueble.setPrecio(215000);
            inmueble.setPropietario(usu);
            inmueble.setUbicacion(ubicacion);

            getInmuebleService().addInmueble(inmueble);

            //

            usu = new Usuario();

            usu.setEmail("guardiola004@hotmail.com");
            usu.setNombre("Jhon");
            usu.setApellido1("Rivas");
            usu.setApellido2("Rivas");
            usu.setPassword("123");
            usu.setTipoUsuario(TiposUsuarios.CLIENTE.name());
            //usu.setTipoUsuario(TiposUsuarios.ADMIN.name());

            getUsuarioService().addUsuario(usu);

            ubicacion = new Ubicacion();
            ubicacion.setProvincia("Tenerife");
            ubicacion.setTipoVia("AVENIDA");
            ubicacion.setNombreVia("Tres de Mayo");

            getUbicacionService().addUbicacion(ubicacion);

            inmueble = new Inmueble();

            inmueble.setTamaño("200");

            inmueble.setHabitaciones(4);
            inmueble.setBaños(3);
            inmueble.setParking(true);
            inmueble.setPrecio(500000);
            inmueble.setPropietario(usu);
            inmueble.setUbicacion(ubicacion);

            getInmuebleService().addInmueble(inmueble);

            Opcion opcion = new Opcion();

            opcion.setId("MENU_MA_MISDATOS");
            opcion.setHabilitada(true);

            getOpcionService().addOpcion(opcion);

            opcion = new Opcion();

            opcion.setId("MENU_MA_MISPROPIEDADES");
            opcion.setHabilitada(true);

            getOpcionService().addOpcion(opcion);

            opcion = new Opcion();

            opcion.setId("MENU_MA_REGINMUEBLE");
            opcion.setHabilitada(true);

            getOpcionService().addOpcion(opcion);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * Login
     *
     * @return String - Response Message
     */
    public String login() {
        logger.info("Inicio del autentificación");
        try {
            this.usuarioLogged = new Usuario();
            this.usuarioLogged = getUsuarioService().getLogin(
                    this.getUsuarioHelper().getEmail(),
                    this.getUsuarioHelper().getPassword());

            if (this.usuarioLogged.getEmail().equals(
                    this.usuarioHelper.getEmail())
                    && this.usuarioLogged.getPassword().equals(
                    this.usuarioHelper.getPassword())) {

                FacesMessage msg = new FacesMessage("Acceso correcto", "Hola: "
                        + this.usuarioLogged.getNombre() + "!!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);

                logger.info(LOGGED);

                this.usuarioLogged.setInmuebles(getInmuebleService()
                        .getInmueblesByPropietario(this.usuarioLogged));
                this.inmueblesDataModel = new InmuebleDataModel(
                        this.usuarioLogged.getInmuebles());
                this.logged = true;

                return LOGGED;
            }
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        FacesMessage msg = new FacesMessage("Acceso denegado", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return NOTLOGGED;
    }

    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.usuarioHelper = new UsuarioHelper();
    }

    /**
     * Get User Service
     *
     * @return IUserService - User Service
     */
    public IUsuarioService getUsuarioService() {
        return this.usuarioService;
    }

    /**
     * Set User Service
     *
     * @param IUserService - User Service
     */
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuarioHelper getUsuarioHelper() {
        return usuarioHelper;
    }

    public void setUsuarioHelper(UsuarioHelper usuarioHelper) {
        this.usuarioHelper = usuarioHelper;
    }

    public Usuario getUsuarioLogged() {
        return usuarioLogged;
    }

    public void setUsuarioLogged(Usuario usuarioLogged) {
        this.usuarioLogged = usuarioLogged;
    }

    public IInmuebleService getInmuebleService() {
        return inmuebleService;
    }

    public void setInmuebleService(IInmuebleService inmuebleService) {
        this.inmuebleService = inmuebleService;
    }

    public IUbicacionService getUbicacionService() {
        return ubicacionService;
    }

    public void setUbicacionService(IUbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    public IOpcionService getOpcionService() {
        return opcionService;
    }

    public void setOpcionService(IOpcionService opcionService) {
        this.opcionService = opcionService;
    }

    public InmuebleDataModel getInmueblesDataModel() {

        this.inmueblesDataModel = new InmuebleDataModel();

        if (isLogged()) {
            this.inmueblesDataModel = new InmuebleDataModel(this
                    .getInmuebleService().getInmueblesByPropietario(
                    this.usuarioLogged));
        }

        return inmueblesDataModel;
    }

    public void setInmueblesDataModel(InmuebleDataModel inmueblesDataModel) {
        this.inmueblesDataModel = inmueblesDataModel;
    }

    public Inmueble getInmuebleSelected() {
        return inmuebleSelected;
    }

    public void setInmuebleSelected(Inmueble inmuebleSelected) {
        this.inmuebleSelected = inmuebleSelected;
    }

    public UsuarioManagedBean getUsuarioManagedBean() {
        return usuarioManagedBean;
    }

    public void setUsuarioManagedBean(UsuarioManagedBean usuarioManagedBean) {
        this.usuarioManagedBean = usuarioManagedBean;
    }

    /**
     * Edición de un inmueble seleccionado.
     *
     * @return
     */
    public String editarInmueble() {
        try {
            inmuebleManagedBean = (InmuebleManagedBean) ServiceLocator.init()
                    .lookUpController("inmuebleMB");

            this.inmuebleManagedBean.setInmuebleHelper(this.inmuebleService
                    .getInmuebleById(this.inmuebleSelected.getId()));

        } catch (Exception e) {
            // TODO: handle exception
        }
        return "editarInmueble";
    }

    public void onRowSelect(SelectEvent event) {
    }

    /**
     * Controlador de asistente
     *
     * @param event
     * @return
     */
    public String onFlowProcess(FlowEvent event) {
        // logger.info("Current wizard step:" + event.getOldStep());
        // logger.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false; // reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public InmuebleManagedBean getInmuebleManagedBean() {
        return inmuebleManagedBean;
    }

    public void setInmuebleManagedBean(InmuebleManagedBean inmuebleManagedBean) {
        this.inmuebleManagedBean = inmuebleManagedBean;
    }

    /**
     * Es administrado ?
     *
     * @return
     */
    public boolean getAdmin() {

        if (this.usuarioLogged.getTipoUsuario() == null) {
            return false;
        }
        logger.debug("Usuario tipo: "
                + this.usuarioLogged.getTipoUsuario());
        return this.usuarioLogged.getTipoUsuario().equals(
                TiposUsuarios.ADMIN.name());

    }

    /**
     * Es perfil cliente ?
     *
     * @return
     */
    public boolean getClient() {
        return this.usuarioLogged.getTipoUsuario().equals(
                TiposUsuarios.CLIENTE.name());

    }

    /**
     * Está logeado ?
     *
     * @return
     */
    public boolean isLogged() {
        return (this.logged);
    }

    /**
     *
     * @param actionEvent
     */
    public void logout(ActionEvent actionEvent) {
        try {
            logger.debug("Saliendo...");
            this.logged = false;
            this.usuarioLogged = new Usuario();
            // return "logout";
            FacesContext context = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context
                    .getApplication().getNavigationHandler();

            this.getUsuarioManagedBean().clean();
            inmuebleManagedBean = (InmuebleManagedBean) ServiceLocator.init()
                    .lookUpController("inmuebleMB");
            this.getInmuebleManagedBean().clean();

            FacesMessage msg = new FacesMessage("+info", "Session cerrada");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            handler.performNavigation("desconectado");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public String registrarInmueble() {
        if (this.usuarioLogged.getTipoUsuario() == null) {
            if (this.usuarioLogged.getTipoUsuario().equals("")) {
                return "login";
            }
        }
        return "registrarInmueble";
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Eliminación usuario seleccionado
     *
     * @return
     */
    public String eliminarInmueble() {
        System.out.print("Eliminando inmueble: ");

        this.getInmuebleService().deleteInmueble(
                this.getInmuebleService().getInmuebleById(
                this.inmuebleSelected.getId()));

        this.inmueblesDataModel = new InmuebleDataModel();

        return "misinmuebles";
    }

    /**
     * Regla de navegación edición datos usuario
     *
     * @return
     */
    public String edit() {
        logger.debug("edit-usuario");
        logger.debug(this.usuarioLogged.getId());

        return "editarmisdatos";

    }

    /**
     * Update User
     *
     * @return String - Response Message
     */
    public String updUsuario() {
        System.out.print("Actualizando usuario...");
        logger.debug(this.usuarioLogged.getEmail());

        try {

            logger.debug(this.usuarioLogged.getId());

            Usuario usu = getUsuarioService().getUsuarioById(
                    this.usuarioLogged.getId());

            usu.setNombre(this.usuarioLogged.getNombre());
            usu.setApellido1(this.usuarioLogged.getApellido1());
            usu.setApellido2(this.usuarioLogged.getApellido2());
            usu.setMovil(this.usuarioLogged.getMovil());
            usu.setEmail(this.usuarioLogged.getEmail());

            getUsuarioService().updateUsuario(usu);

            FacesMessage msg = new FacesMessage("+info",
                    "Usuario actualizado correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "misdatos";
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }

        return "error";
    }

    public String misPropiedades() {

        return "misPropiedades";
    }

    public String misDatos() {

        return "misDatos";
    }
}