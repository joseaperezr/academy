package es.uned.managed.bean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

import es.uned.model.Foto;
import es.uned.model.Inmueble;
import es.uned.model.Interesado;
import es.uned.model.Ubicacion;
import es.uned.service.IFotoService;
import es.uned.service.IInmuebleService;
import es.uned.service.IInteresadoService;
import es.uned.service.IUbicacionService;
import es.uned.service.IUsuarioService;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 *
 * Controlador inmuebles
 *
 * @author José Antonio Pérez Reyes
 * @since 24 Jun 2012
 * @version 1.0.0
 *
 */
@ManagedBean(name = "inmuebleMB")
// @RequestScoped
// @ViewScoped
@SessionScoped
public class InmuebleManagedBean implements Serializable {

    //
    private Logger logger = Logger.getLogger(InmuebleManagedBean.class);
    //
    private static final String ROOT_VAR = "/var/";
    //
    private static final String ROOT_VAR2 = "/var2/";
    //
    private static final long serialVersionUID = 6L;
    // private Blob myblob;
    //
    private boolean skip;
    // Spring Services is injected...
    @ManagedProperty(value = "#{InmuebleService}")
    IInmuebleService inmuebleService;
    // Spring Services is injected...
    @ManagedProperty(value = "#{FotoService}")
    IFotoService fotoService;
    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;
    @ManagedProperty(value = "#{UbicacionService}")
    IUbicacionService ubicacionService;
    @ManagedProperty(value = "#{InteresadoService}")
    IInteresadoService interesadoService;
    @ManagedProperty(value = "#{usuarioMB}")
    private UsuarioManagedBean usuarioManagedBean;
    @ManagedProperty(value = "#{sessionMB}")
    // @Resource
    private SessionManagedBean sessionManagedBean;
    //
    private Inmueble inmuebleHelper = new Inmueble();
    //
    private InmuebleDataModel inmueblesDataModel = new InmuebleDataModel();
    //
    private Inmueble inmuebleSelected;
    //
    private InteresadoDataModel interesadosDataModel = new InteresadoDataModel();
    //
    private Interesado interesadoSelected;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public SessionManagedBean getSessionManagedBean() {
        return sessionManagedBean;
    }

    public void setSessionManagedBean(SessionManagedBean sessionManagedBean) {
        this.sessionManagedBean = sessionManagedBean;
    }

    public InmuebleDataModel getInmueblesDataModel() {
        return inmueblesDataModel;
    }

    public void setInmueblesDataModel(InmuebleDataModel inmueblesDataModel) {
        this.inmueblesDataModel = inmueblesDataModel;
    }

    public UsuarioManagedBean getUsuarioManagedBean() {
        return usuarioManagedBean;
    }

    public void setUsuarioManagedBean(UsuarioManagedBean usuarioManagedBean) {
        this.usuarioManagedBean = usuarioManagedBean;
    }

    public InteresadoDataModel getInteresadosDataModel() {
        return interesadosDataModel;
    }

    public void setInteresadosDataModel(InteresadoDataModel interesadosDataModel) {
        this.interesadosDataModel = interesadosDataModel;
    }

    public Inmueble getInmuebleSelected() {
        return inmuebleSelected;
    }

    public void setInmuebleSelected(Inmueble inmuebleSelected) {
        this.inmuebleSelected = inmuebleSelected;
    }

    public Interesado getInteresadoSelected() {
        return interesadoSelected;
    }

    public void setInteresadoSelected(Interesado interesadoSelected) {
        this.interesadoSelected = interesadoSelected;
    }

    public IFotoService getFotoService() {
        return fotoService;
    }

    public void setFotoService(IFotoService fotoService) {
        this.fotoService = fotoService;
    }

    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.inmuebleHelper = new Inmueble();
    }

    /**
     * Get User Service
     *
     * @return IUserService - User Service
     */
    public IInmuebleService getInmuebleService() {
        return this.inmuebleService;
    }

    /**
     * Set Inmueble Service
     *
     * @param IUserService - User Service
     */
    public void setInmuebleService(IInmuebleService inmuebleService) {
        this.inmuebleService = inmuebleService;
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

    public Inmueble getInmuebleHelper() {
        return inmuebleHelper;
    }

    public void setInmuebleHelper(Inmueble inmuebleHelper) {
        this.inmuebleHelper = inmuebleHelper;
    }

    /**
     * Añadir inmueble
     *
     * @param actionEvent
     */
    public void addInmueble(ActionEvent actionEvent) {
        // Persist inmueble
        try {

            getUbicacionService().addUbicacion(
                    this.inmuebleHelper.getUbicacion());

            logger.info("id:  "
                    + String.valueOf(this.inmuebleHelper.getId()));

            this.inmuebleHelper.setPropietario(getUsuarioService()
                    .getUsuarioById(
                    sessionManagedBean.getUsuarioLogged().getId()));

            // this.inmuebleHelper.setFile(this.myblob);

            List<Foto> _fotos = this.inmuebleHelper.getFotos();

            this.inmuebleHelper.setFotos(new ArrayList<Foto>());

            getInmuebleService().addInmueble(this.inmuebleHelper);

            // Se agregan fotos
            for (Foto _foto : _fotos) {
                _foto.setInmueble(this.inmuebleHelper);
                getFotoService().addFoto(_foto);
            }

            logger.info(" + ID: " + this.inmuebleHelper.getId());

            this.inmuebleHelper = new Inmueble();

            // return SUCCESS;
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        FacesMessage msg = new FacesMessage("+info",
                "Inmueble agregado correctamente.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public IInteresadoService getInteresadoService() {
        return interesadoService;
    }

    public void setInteresadoService(IInteresadoService interesadoService) {
        this.interesadoService = interesadoService;
    }

    /**
     * Actualización inmueble
     *
     * @param actionEvent
     */
    public String updInmueble(ActionEvent actionEvent) {
        logger.info("Actualizando propiedad...");
        try {

            Inmueble _inmueble = new Inmueble();

            _inmueble = getInmuebleService().getInmuebleById(
                    this.inmuebleHelper.getId());

            logger.info("id:  "
                    + String.valueOf(this.inmuebleHelper.getId()));

            _inmueble.setPrecio(this.inmuebleHelper.getPrecio());
            _inmueble.setTamaño(this.inmuebleHelper.getTamaño());
            _inmueble.setHabitaciones(this.inmuebleHelper.getHabitaciones());
            _inmueble.setBaños(this.inmuebleHelper.getBaños());
            _inmueble.setJardin(this.inmuebleHelper.isJardin());
            _inmueble.setParking(this.inmuebleHelper.isParking());
            _inmueble.setObservaciones(this.inmuebleHelper.getObservaciones());

            Ubicacion ubi = _inmueble.getUbicacion();
            ubi.setDetalle(this.inmuebleHelper.getUbicacion().getDetalle());
            ubi.setProvincia(this.inmuebleHelper.getUbicacion().getProvincia());
            ubi.setPiso(this.inmuebleHelper.getUbicacion().getPiso());
            ubi.setTipoVia(this.inmuebleHelper.getUbicacion().getTipoVia());
            ubi.setNombreVia(this.inmuebleHelper.getUbicacion().getNombreVia());
            ubi.setNro(this.inmuebleHelper.getUbicacion().getNro());
            ubi.setPuerta(this.inmuebleHelper.getUbicacion().getPuerta());

            this.ubicacionService.updateUbicacion(ubi);

            _inmueble.setUbicacion(ubi);

            this.inmuebleService.updateInmueble(_inmueble);

            this.inmuebleHelper = new Inmueble();

            FacesMessage msg = new FacesMessage("+info",
                    "Inmueble modificado correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "inmuebles";

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "fallo";
    }

    public void onRowSelect(SelectEvent event) {
    }

    /**
     * Carga inmuebles por usuario
     *
     * @return
     */
    public String inmueblesPorUsuarios() {
        System.out.print("Cargando los inmueble asoaciados al usuario: ");

        if (this.usuarioManagedBean.getUsuarioSelected() == null) {
            return null;
        }

        System.out.print(this.usuarioManagedBean.getUsuarioSelected()
                .getEmail());

        this.inmueblesDataModel = new InmuebleDataModel(this
                .getInmuebleService().getInmueblesByPropietario(
                this.usuarioManagedBean.getUsuarioSelected()));

        return "inmueblesPorUsuarios";
    }

    /**
     * Carga los inmuebles asociados al usuario
     *
     * @return
     */
    public String inmuebles() {
        System.out.print("Cargando los inmueble asoaciados al usuario: ");
        this.inmueblesDataModel = new InmuebleDataModel(this
                .getInmuebleService().getInmuebles());
        return "todoslosinmuebles";
    }

    /**
     * Carga los interesados por inmueble seleccionado (area administrador)
     *
     * @return
     */
    public String interesadosPorInmueble() {
        System.out.print("Cargando los interesados del inmueble: ");
        System.out.print(this.inmuebleSelected.getId());

        /*
         * this.interesadosDataModel = new InteresadoDataModel(this
         * .getInteresadoService().getInteresadosByInmueble(
         * this.sessionManagedBean.getInmuebleSelected()));
         */
        this.interesadosDataModel = new InteresadoDataModel(this
                .getInmuebleSelected().getInteresados());

        return "interesadosPorInmueble";
    }

    /**
     * Carga los interesados por inmueble seleccionado (area cliente)
     *
     * @return
     */
    public String interesadosPorInmuebleMiArea() {

        System.out.print("Cargando los interesados del inmueble: ");

        if (this.sessionManagedBean.getInmuebleSelected() == null) {
            return null;
        }

        logger.info(this.sessionManagedBean.getInmuebleSelected()
                .getId());

        this.inmuebleSelected = getInmuebleService().getInmuebleById(
                this.sessionManagedBean.getInmuebleSelected().getId());

        this.interesadosDataModel = new InteresadoDataModel(
                this.sessionManagedBean.getInmuebleSelected().getInteresados());

        return "interesadosPorInmueble";
    }

    /**
     * Eliminación inmueble seleccionado
     *
     * @return
     */
    public void eliminarInmueblePorUsuario() {
        System.out.print("Eliminando inmueble: ");

        this.getInmuebleService().deleteInmueble(
                this.getInmuebleService().getInmuebleById(
                this.inmuebleSelected.getId()));

        this.inmueblesDataModel = new InmuebleDataModel(this
                .getInmuebleService().getInmueblesByPropietario(
                this.usuarioManagedBean.getUsuarioSelected()));

        // return "inmueblesPorUsuarios";
    }

    /**
     * Eliminación inmueble seleccionado
     *
     * @return
     */
    public void eliminarInmueble() {
        System.out.print("Eliminando inmueble: ");

        if (this.inmuebleSelected != null) {

            this.getInmuebleService().deleteInmueble(
                    this.getInmuebleService().getInmuebleById(
                    this.inmuebleSelected.getId()));

            this.inmueblesDataModel = new InmuebleDataModel(this
                    .getInmuebleService().getInmuebles());

        }
    }

    /**
     *
     */
    public void clean() {
        this.inmuebleHelper = new Inmueble();
        this.inmueblesDataModel = new InmuebleDataModel();
        this.inmuebleSelected = null;
    }

    public String editarInmueble() {
        try {

            if (this.inmuebleSelected == null) {
                return null;
            }

            this.setInmuebleHelper(this.inmuebleService
                    .getInmuebleById(this.inmuebleSelected.getId()));

        } catch (Exception e) {
            // TODO: handle exception
        }
        return "editarInmueble";
    }

    public String registrarInmueble() {
        this.inmuebleHelper = new Inmueble();

        return "registrarInmueble";
    }

    public void handleFileUpload(FileUploadEvent event) {

        FacesMessage msg = new FacesMessage("Succesful", event.getFile()
                .getFileName() + " is uploaded.");
        try {

            List<Foto> _fotos = this.inmuebleHelper.getFotos();

            Foto _foto = new Foto();

            _foto.setMimeType(event.getFile().getContentType());
            _foto.setNombre(event.getFile().getFileName());
            _foto.setFoto(this.inmuebleService.getBlob(getRawData(event
                    .getFile().getInputstream())));

            _fotos.add(_foto);

            this.inmuebleHelper.setFotos(_fotos);

            processFile(event.getFile().getFileName(), event.getFile().getInputstream());

            logger.info("Succesful" + event.getFile().getFileName() + " is uploaded.");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            logger.info(e.getMessage());
        }

        logger.info(event.getFile().getFileName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void upload() {
        if (file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName()
                    + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Gets the raw byte data from a given file.
     *
     * @param file file to get the raw byte data from.
     * @return raw byte data.
     * @throws IOException
     */
    public static byte[] getRawData(InputStream is) throws IOException {
        // FileInputStream is = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = null;
        try {
            int length = -1;
            byte[] buffer = new byte[16000];
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            data = baos.toByteArray();
            baos.close();
            is.close();
        } catch (Exception ex) {
            // throw new IOException(ex);
        } finally {
            if (baos != null) {
                baos.close();
            }
            if (is != null) {
                is.close();
            }
        }
        return data;
    }

    /**
     *
     * @param nameFile
     * @param bytes
     * @throws IOException
     */
    public static void saveBytestoFile(String nameFile, byte[] bytes)
            throws IOException {
        // String path=Config.init().getString("cic.tmp.dir");
        FileOutputStream fos = new FileOutputStream(new File("./" + nameFile));
        try {
            fos.write(bytes);



        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            fos.close();
        }
    }

//logger.info("Última modificación: " + file.lastModified());
//logger.info("Última modificación: " + file.lastModified());
    public void processFile(String filename, InputStream inputStream) {
        OutputStream os = null;
        logger.info("FileName: " + filename);
        try {
            os = new BufferedOutputStream(
                    Files.newOutputStream(Paths.get(ROOT_VAR + filename),
                    StandardOpenOption.CREATE_NEW));
            Path _file1 = Paths.get(ROOT_VAR + filename);
            BasicFileAttributes attr1 = Files.readAttributes(_file1, BasicFileAttributes.class);
            FileTime creation1 = attr1.creationTime();
            FileTime modified1 = attr1.lastModifiedTime();
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            //
            if (os != null) {
                os.close();
            }
            //
            Path _file2 = Paths.get(ROOT_VAR + filename);
            BasicFileAttributes attr2 = Files.readAttributes(_file2, BasicFileAttributes.class);
            FileTime creation2 = attr2.creationTime();
            FileTime modified2 = attr2.lastModifiedTime();
            if ((creation1.equals(creation2))
                    || (modified1.equals(modified2))) {
                File file = new File(ROOT_VAR + filename);
                os = new BufferedOutputStream(
                        Files.newOutputStream(Paths.get(ROOT_VAR2 + filename),
                        StandardOpenOption.TRUNCATE_EXISTING));
                ByteArrayOutputStream ous = null;
                InputStream ios = null;
                byte[] buffer = new byte[4096];
                ous = new ByteArrayOutputStream();
                ios = new FileInputStream(file);
                read = 0;
                while ((read = ios.read(buffer)) != -1) {
                    ous.write(buffer, 0, read);
                }
                // Put data in your baos
                os.write(ous.toByteArray());
            } else {
                logger.error("File was tampered!!!");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
