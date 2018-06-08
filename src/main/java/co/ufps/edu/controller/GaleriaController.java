package co.ufps.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.ufps.edu.dao.GaleriaDao;
import co.ufps.edu.dto.Galeria;

/**
 * Controlador de galerias. Las galerias son contenedores de imagenes. Todos los
 * servicios publicados en esta clase seran expuestos para ser consumidos por los archivos .JSP
 * <p>
 * La etiqueta @Controller escanea todos los servicios para publicarlos segun el tipo de metodo
 * HTTP.
 * 
 * @author ufps
 *
 */
@Controller
public class GaleriaController {

  private GaleriaDao galeriaDao;

  /**
   * Constructor de la clase en donde se inicializan las variables
   */
  public GaleriaController() {
    galeriaDao = new GaleriaDao();
  }
  
  /**
   * Modelo con el que se realizara el formulario
   * @return Un objeto para ser llenado desde el archivo .JSP
   */
  @ModelAttribute("galeria")
  public Galeria setUpUserForm() {
    return new Galeria();
  }

  
  /**
   * Método que retorna una pagina con todas las galerias en el sistema.
   * 
   * @return La página principal de galerias.
   */
  @GetMapping("/galerias") // Base
  public String index(Model model) {
    // Cargamos las galerias para poder mostrarlas en el cuadro.
    model.addAttribute("galerias", galeriaDao.getGalerias());
    return "Administrador/Galeria/galerias"; // Nombre del archivo jsp
  }
  
  
  /**
   * Método que retorna una pagina para realizar el registro de galeria.
   * 
   * @return La página de registro de galerias.
   */
  @GetMapping("/registrarGaleria") // Base
  public String registrarGaleria(Model model) {
    return "Administrador/Galeria/RegistrarGaleria"; // Nombre del archivo jsp
  }

  
  /**
   * Servicio que permite guardar galerias
   * 
   * @param galera Objeto con la información a guardar
   * @param model Modelo con la información necesaria para transportar a los archivos .JSP
   * @return La página a donde debe redireccionar después de la acción.
   */
  @PostMapping(value = "/guardarGaleria")
  public String guardarGaleria(@ModelAttribute("galeria") Galeria galeria, Model model) {

    // Consulta si tiene todos los campos llenos
    if (galeria.isValidoParaRegistrar()) {
      String mensaje = galeriaDao.registrarGaleria(galeria);
      if (mensaje.equals("Registro exitoso")) {
        model.addAttribute("result", "Galeria registrada con éxito.");
        model.addAttribute("galerias", galeriaDao.getGalerias() );
        return "Administrador/Galeria/galerias"; // Nombre del archivo jsp
      } else {
        model.addAttribute("wrong", mensaje);
        return "Administrador/Galeria/RegistrarGaleria";
      }
      //
    } else {
      model.addAttribute("wrong", "Debes llenartodos  los campos.");
      return "Administrador/Galeria/RegistrarGaleria";
    }
  }
 
  /**
   * Método que obtiene la pagina de actualizar galeria dado un ID.
   * 
   * @param idGaleria Identificador de la galeria
   * @param model Objeto para enviar información a los archivos .JSP
   * @return La pagina con la información de la galeria cargada.
   */
  @GetMapping(value = "/actualizarGaleria")
  public String actualizarGaleria(@RequestParam("id") long idGaleria, Model model) {
    // Consulto que el Id sea mayor a 0.
    if (idGaleria <= 0) {
      model.addAttribute("galerias", galeriaDao.getGalerias());
      return "Administrador/Galeria/galerias"; // Nombre del archivo jsp
    }
    Galeria galeria = galeriaDao.obtenerGaleriaPorId(idGaleria);
    model.addAttribute("galeria", galeria);
    return "Administrador/Galeria/ActualizarGaleria"; // Nombre del archivo jsp
  }
  
  
  /**
   * Servicio que permite editar una galeria.
   * 
   * @param galeria Objeto con la información a editar.
   * @param model Modelo con la información necesaria para transportar a los archivos .JSP
   * @return La página a donde debe redireccionar después de la acción.
   */
  @PostMapping(value = "/editarGaleria")
  public String editarGaleria(@ModelAttribute("galeria") Galeria galeria, Model model) {

    // Consulta si tiene todos los campos llenos
    if (galeria.isValidoParaRegistrar()) {
      String mensaje = galeriaDao.editarGaleria(galeria);
      if (mensaje.equals("Actualizacion exitosa")) {
        model.addAttribute("result", "Información de galeria actualizada con éxito.");
        model.addAttribute("galerias", galeriaDao.getGalerias());
        return "Administrador/Galeria/galerias"; // Nombre del archivo jsp
      } else {
        model.addAttribute("wrong", mensaje);
        return "Administrador/Galeria/ActualizarGaleria";
      }
      //
    } else {
      model.addAttribute("wrong", "Debes llenar todos los campos.");
      return "Administrador/Galeria/ActualizarGaleria";
    }
  }
  
  /**
   * Método que obtiene la pagina de eliminar galeria dado un ID.
   * 
   * @param idGaleria Identificador de la galeria
   * @param model Objeto para enviar información a los archivos .JSP
   * @return La pagina con la información de la galeria cargada.
   */
  @GetMapping(value = "/eliminarGaleria")
  public String eliminarGaleria(@RequestParam("id") long idGaleria, Model model) {
    // Consulto que el Id sea mayor a 0.
    if (idGaleria <= 0) {
      model.addAttribute("galerias", galeriaDao.getGalerias());
      return "Administrador/Galeria/galerias"; // Nombre del archivo jsp
    }
    Galeria galeria = galeriaDao.obtenerGaleriaPorId(idGaleria);
    model.addAttribute("galeria", galeria);
    return "Administrador/Galeria/EliminarGaleria"; // Nombre del archivo jsp
  }
  
  
  /**
   * Servicio que permite eliminar una galeria.
   * 
   * @param galeria Objeto con la información a eliminar.
   * @param model Modelo con la información necesaria para transportar a los archivos .JSP
   * @return La página a donde debe redireccionar después de la acción.
   */
  @PostMapping(value = "/borrarGaleria")
  public String borrarGaleria(@ModelAttribute("galeria") Galeria galeria, Model model) {

    String mensaje = galeriaDao.eliminarGaleria(galeria);

    if (mensaje.equals("Eliminacion exitosa")) {
      model.addAttribute("result", "Galeria eliminada con éxito.");
      model.addAttribute("galerias", galeriaDao.getGalerias());
      return "Administrador/Galeria/galerias"; // Nombre del archivo jsp
    } else {
      model.addAttribute("wrong", mensaje);
      return "Administrador/Galeria/EliminarGaleria";
    }

  }
}
