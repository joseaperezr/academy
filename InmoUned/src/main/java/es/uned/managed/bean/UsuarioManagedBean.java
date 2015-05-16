package es.uned.managed.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.dao.DataAccessException;

import es.uned.helper.UsuarioHelper;
import es.uned.model.Usuario;
import es.uned.service.IUsuarioService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * Controler Usuario
 *
 * Controla las operaciones de usuario.
 *
 * @author José Antonio Pérez Reyes
 * @since 24 Jun 2012
 * @version 1.0.0
 *
 */
@ManagedBean(name = "usuarioMB")
// @RequestScoped
// @ViewScoped
@SessionScoped
public class UsuarioManagedBean implements Serializable {

    private static final long serialVersionUID = 27L;
    //
    private Logger logger = Logger.getLogger(UsuarioManagedBean.class);
    //
    private static final String ERROR = "error";
    //
    private boolean skip;
    // Spring User Service is injected...
    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;
    //
    private UsuarioHelper usuarioHelper = new UsuarioHelper();
    //
    private UsuarioDataModel usuarioDataModel = new UsuarioDataModel();
    //
    private Usuario usuarioSelected;
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     *
     */
    @PostConstruct
    public void init() {

        this.usuarioDataModel = new UsuarioDataModel();

        usuarioSelected = new Usuario();

        this.usuarioDataModel = new UsuarioDataModel(getUsuarioService()
                .getUsuarios());

    }

    /**
     * Add User
     *
     * @return String - Response Message
     */
    public void addUsuario() {
        logger.debug("Agredando usuario...");
        try {
            //
            if (!validateEmail(this.usuarioHelper.getEmail())) {
                FacesContext.getCurrentInstance().addMessage(ERROR, new FacesMessage("+info",
                        "Formato campo email inválido"));
                return;
            }
            //
            Usuario usuario = new Usuario();

            usuario.setNombre(this.usuarioHelper.getNombre());
            usuario.setApellido1(this.usuarioHelper.getApellido1());
            usuario.setApellido2(this.usuarioHelper.getApellido2());
            usuario.setDni(this.usuarioHelper.getDni());

            usuario.setEmail(this.usuarioHelper.getEmail());
            usuario.setMovil(this.usuarioHelper.getMovil());

            usuario.setPassword(this.usuarioHelper.getPassword());
            usuario.setTipoUsuario(this.usuarioHelper.getTipoUsuario());

            getUsuarioService().addUsuario(usuario);

            FacesMessage msg = new FacesMessage("+info",
                    "Usuario creado correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            this.usuarioHelper = new UsuarioHelper();

            this.usuarioDataModel = new UsuarioDataModel(getUsuarioService()
                    .getUsuarios());

            // return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        // return ERROR;
    }

    /**
     * Actualización de usuario
     *
     * @return String - Response Message
     */
    public String updUsuario() {
        System.out.print("Actualizando usuario...");
        logger.debug(this.usuarioSelected.getEmail());

        try {

            logger.debug(this.usuarioSelected.getId());

            Usuario usu = getUsuarioService().getUsuarioById(
                    this.usuarioSelected.getId());

            usu.setNombre(this.usuarioSelected.getNombre());
            usu.setApellido1(this.usuarioSelected.getApellido1());
            usu.setApellido2(this.usuarioSelected.getApellido2());
            usu.setMovil(this.usuarioSelected.getMovil());
            usu.setEmail(this.usuarioSelected.getEmail());


            getUsuarioService().updateUsuario(usu);

            FacesMessage msg = new FacesMessage("+info",
                    "Usuario actualizado correctamente." /*
                     * + usuario . getNombre
                     * ()
                     */);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "usuarios";
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return ERROR;
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

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

    public UsuarioDataModel getUsuarioDataModel() {
        //
        return usuarioDataModel;
    }

    public void setUsuarioDataModel(UsuarioDataModel usuarioDataModel) {
        this.usuarioDataModel = usuarioDataModel;
    }

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public void onRowSelect(SelectEvent event) {
    }

    /**
     *
     * @param event
     */
    public void onRowUnselect(UnselectEvent event) {
    }

    /**
     * Regla de navegación para la edición de un usuario.
     *
     * @return
     */
    public String edit() {
        logger.debug("edit-usuario");

        if (this.usuarioSelected == null) {
            return null;
        }

        logger.debug(this.usuarioSelected.getId());
        this.usuarioSelected = getUsuarioService().getUsuarioById(
                this.usuarioSelected.getId());
        return "edit-usuario";

    }

    public void clean() {
        this.usuarioDataModel = new UsuarioDataModel();
        this.usuarioHelper = new UsuarioHelper();
        this.usuarioSelected = null;
    }

    /**
     * Eliminación de un usuario seleccionado
     */
    public void deleteUsuario() {

        this.getUsuarioService().deleteUsuario(this.usuarioSelected);

        this.usuarioSelected = new Usuario();

        this.usuarioDataModel = new UsuarioDataModel(getUsuarioService()
                .getUsuarios());

    }

    /**
     * Validate given email with regular expression.
     *
     * @param email email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validateEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
