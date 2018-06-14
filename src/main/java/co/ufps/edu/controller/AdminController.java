package co.ufps.edu.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.ufps.edu.config.SessionManager;
import co.ufps.edu.constantes.Constantes;
import co.ufps.edu.dao.ActividadDao;
import co.ufps.edu.dao.CategoriaDao;
import co.ufps.edu.dao.ComponenteDao;
import co.ufps.edu.dao.ContactoDao;
import co.ufps.edu.dao.ContenidoDao;
import co.ufps.edu.dao.EnlaceDeInteresDao;
import co.ufps.edu.dao.GaleriaDao;
import co.ufps.edu.dao.LoginDao;
import co.ufps.edu.dao.LogoDao;
import co.ufps.edu.dao.NoticiaDao;
import co.ufps.edu.dao.NovedadDao;
import co.ufps.edu.dao.RedSocialDao;
import co.ufps.edu.dao.SubCategoriaDao;
import co.ufps.edu.dto.Contenido;
import co.ufps.edu.dto.Galeria;
import co.ufps.edu.dto.Login;
import co.ufps.edu.dto.Novedad;
import co.ufps.edu.util.JwtUtil;

@Controller
public class AdminController {

  @Autowired
  private SessionManager sessionManager;

  private JwtUtil jwtUtil;
  private LoginDao loginDao;
  private CategoriaDao categoriaDao;
  private SubCategoriaDao subCategoriaDao;
  private ContenidoDao contenidoDao;
  private NoticiaDao noticiaDao;
  private ActividadDao actividadDao;
  private NovedadDao novedadDao;
  private LogoDao logoDao;
  private EnlaceDeInteresDao enlaceDeInteresDao;
  private GaleriaDao galeriaDao;
  private ContactoDao contactoDao;
  private RedSocialDao redSocialDao;
  private ComponenteDao componenteDao;
  private int i = 0;

  public AdminController() {
    jwtUtil = new JwtUtil();
    loginDao = new LoginDao();
    categoriaDao = new CategoriaDao();
    subCategoriaDao = new SubCategoriaDao();
    contenidoDao = new ContenidoDao();
    noticiaDao = new NoticiaDao();
    actividadDao = new ActividadDao();
    novedadDao = new NovedadDao();
    logoDao = new LogoDao();
    enlaceDeInteresDao = new EnlaceDeInteresDao();
    galeriaDao = new GaleriaDao();
    contactoDao = new ContactoDao();
    redSocialDao = new RedSocialDao();
    componenteDao = new ComponenteDao();
  }

  @GetMapping("/") // Base
  public String main(Model model, HttpServletRequest request) {
    
    //guardarVisita(request);
    cargarModelo(model);
    return "index"; // Nombre del archivo jsp
  }


  
  private void cargarModelo(Model model) {

    model.addAttribute("categorias", categoriaDao.getCategoriasConSubcategorias());
    model.addAttribute("noticias", noticiaDao.getUltimasNoticias());
    model.addAttribute("novedades", novedadDao.getUltimasNovedades());
    model.addAttribute("actividades", actividadDao.getUltimasActividades());
    model.addAttribute("galerias", galeriaDao.getGalerias());
    
    model.addAttribute("redes", redSocialDao.getRedesSociales());
    model.addAttribute("enlaces", enlaceDeInteresDao.getEnlacesDeInteres());
    model.addAttribute("contactos", contactoDao.getContactos());
    model.addAttribute("logoHorizontal", logoDao.getLogo("LogoHorizontal"));
    model.addAttribute("logoVertical", logoDao.getLogo("LogoVertical"));
    model.addAttribute("dependencia",Constantes.PROYECTO);
    
  }

  @GetMapping("/admin") // Base
  public String index() {
    return "Administrador/Login"; // Nombre del archivo jsp
  }

  @GetMapping("/indexAdmin") // Base
  public String indexAdmin(Model model) {
    this.getCantidadRegistros(model);
    return "Administrador/indexAdmin"; // Nombre del archivo jsp
  }

  /**
   * Método solicitado por los formularios de los archivos .jsp
   * <p>
   * Este metodo es usado en la etiqueta form de la siguiente manera: modelAttribute="login"
   * 
   * @return
   */
  @ModelAttribute("login")
  public Login setUpUserForm() {
    return new Login();
  }

  /**
   * Método para autenticar al usuario al usuario.
   * 
   * @param login Objeto con los datos de autenticacion
   * @param model Clase para enviar datos desde los servicios a los archivos .jsp
   * @param request Objeto con los datos de sesion que por el instante es nulo.
   * @return La pagina a donde fue redireccionado.
   */
  @PostMapping("/autenticar")
  public String authenticateUser(@ModelAttribute("login") Login login,Model model,
      HttpServletRequest request) {
    
    login.setCorreoInstitucional("edgaryesidgo@ufps.edu.co");
    login.setContraseña("1234");
    
    /*
     * Consulto si los datos no vienen nulos
     */
    if (!StringUtils.isEmpty(login.getCorreoInstitucional())
        && !StringUtils.isEmpty(login.getContraseña())) {
      // Consulto en base de datos si se encuentra ese correo y esa contraseña
      String resultado = loginDao.authenticate(login.getCorreoInstitucional(), login.getContraseña());

      // Si el resultado no es vacio es por que si existe ese correo y esa contraseña
      if (!resultado.isEmpty()) {

        // Creo un Json Web Token para validar si la sesión esta activa
        String jwt = "s"+i;//jwtUtil.generateToken(resultado, login.getCorreoInstitucional());

        // Guardo el JWT como atributo de sesión
        request.getSession().setAttribute("token", jwt);

        // Guarda la sesion en el manejador de sesiones
        sessionManager.guardarSession("SESSION:" + login.getCorreoInstitucional(), jwt);

        //this.getCantidadRegistros(model);

        // Redirijo al index debido a que el usuario ya fue autenticado con exito
        return "Administrador/indexAdmin";

      } else {

        /**
         * Guardo en una variable el mensaje de error indicando que el usuario o la contraseña
         * fueron incorrectos debido a que no se encuentran en la base de datos y asi pueda ser
         * entendida por los archivos .JSP
         */
        model.addAttribute("wrong", "Usuario o contraseña incorrectos.");
      }
      // Redirecciono al login debido a que la autenticación fue incorrecta
      return "Administrador/Login";
    } else {
      /**
       * Guardo en una variable el mensaje de error indicando que el usuario o la contraseña son
       * nulos siendo estos datos son obligatorios, y asi pueda ser entendida por los archivos .JSP
       */
      model.addAttribute("wrong", "El usuario y la contraseña no pueden ser nulos.");
      // Redirecciono al login debido a que la autenticación fue incorrecta
      return "Administrador/Login";
    }
  }

  @GetMapping("/logout")
  private String getLogOut(String token, HttpServletRequest request) {
    request.getSession().invalidate();
    String codigo = jwtUtil.parseToken(token);
    sessionManager.eliminarSesion("SESSION:" + codigo);
    return "Administrador/Login"; // Nombre del archivo jsp
  }

  private void getCantidadRegistros(Model model) {
    model.addAttribute("catidadCategorias", this.categoriaDao.getCantidadCategorias());
    model.addAttribute("catidadSubCategorias", this.subCategoriaDao.getCantidadSubCategorias());
    model.addAttribute("catidadContenidos", this.contenidoDao.getCantidadContenidos());
    model.addAttribute("catidadNoticias", this.noticiaDao.getCantidadNoticias());
    model.addAttribute("catidadActividades", this.actividadDao.getCantidadActividades());
    model.addAttribute("catidadNovedades", this.novedadDao.getCantidadNovedades());
    model.addAttribute("catidadLogos", this.logoDao.getCantidadLogos());
    model.addAttribute("catidadEnlaces", this.enlaceDeInteresDao.getCantidadEnlacesDeInteres());
    model.addAttribute("catidadGalerias", this.galeriaDao.getCantidadGalerias());
    model.addAttribute("catidadContactos", this.contactoDao.getCantidadContactos());
    model.addAttribute("catidadRedesSociales", this.redSocialDao.getCantidadRedesSociales());
  }
  
  /**
   * Método que obtiene la pagina de obtener un componente dado un ID.
   * 
   * @param idCategoria Identificador del componente
   * @param model Objeto para enviar información a los archivos .JSP
   * @return La pagina con la información del contenido cargado.
   */
  @GetMapping(value = "/servicios/componente")
  public String obtenerContenido(@RequestParam("id") long idComponente,@RequestParam("componente") String tipo, Model model) {
    // Consulto que el Id sea mayor a 0.
    if (idComponente <= 0) {
      return "index";
    }
    Contenido contenido = componenteDao.obtenerContenidoComponentePorId(idComponente,tipo);
    
    
    cargarModelo(model);
    model.addAttribute("titulo",(contenido == null)?"":contenido.getNombre());
    model.addAttribute("codigo",(contenido == null)?"":contenido.getContenido());
    if(contenido.getId() != 0) {
      return "contenido"; // Nombre del archivo jsp
    }else {
      return "index";
    }
  }
  
  /**
   * Método que obtiene la pagina de una galeria dado un ID.
   * 
   * @param idGaleria Identificador de la galeria
   * @param model Objeto para enviar información a los archivos .JSP
   * @return La pagina con la información de la galeria cargada.
   */
  @GetMapping(value = "/servicios/galeria")
  public String obtenerContenido(@RequestParam("id") long idGaleria, Model model) {
    // Consulto que el Id sea mayor a 0.
    if (idGaleria <= 0) {
      return "index";
    }
    Galeria galeria = galeriaDao.obtenerGaleriaPorId(idGaleria);
    
    cargarModelo(model);
    model.addAttribute("galeria",galeria);
    return "galeria"; // Nombre del archivo jsp
  }  

  /**
   * Método que obtiene la pagina de todas las novedades.
   *
   * @param model Objeto para enviar información a los archivos .JSP
   * @return La pagina con la información de las novedades cargada.
   */
  @GetMapping(value = "/servicios/novedades")
  public String obtenerNovedades(Model model) {
    
    List<Novedad> novedades= novedadDao.getNovedades();    
    cargarModelo(model);
    model.addAttribute("novedadesCom",novedades);
    return "novedades"; // Nombre del archivo jsp
  }  
  
  /**
   * Método que obtiene la pagina de todas las galerias.
   *
   * @param model Objeto para enviar información a los archivos .JSP
   * @return La pagina con la información de las galerias cargada.
   */
  @GetMapping(value = "/servicios/galerias")
  public String obtenerGalerias(Model model) {
    
    List<Galeria> galerias = galeriaDao.getGalerias();    
    cargarModelo(model);
    model.addAttribute("galeriasCom" , galerias);
    return "galerias"; // Nombre del archivo jsp
  }    
}
