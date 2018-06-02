package co.ufps.edu.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import co.ufps.edu.dao.ContenidoDao;
import co.ufps.edu.dto.Actividad;
import co.ufps.edu.dto.Contenido;

/**
 * Controlador de contenidos. Los contenidos son las paginas que se abren cuando se da click a una información. Todos los
 * servicios publicados en esta clase seran expuestos para ser consumidos por los archivos .JSP
 * <p>
 * La etiqueta @Controller escanea todos los servicios para publicarlos segun el tipo de metodo
 * HTTP.
 * 
 * @author ufps
 *
 */
@Controller
public class ContenidoController {

  private ContenidoDao contenidoDao;

  /**
   * Constructor de la clase en donde se inicializan las variables
   */
  public ContenidoController() {
    contenidoDao = new ContenidoDao();
  }

  /**
   * Método que retorna una pagina con todas los contenidos en el sistema.
   * 
   * @return La página principal de contenidos.
   */
  @GetMapping("/contenidos") // Base
  public String index(Model model) {
    // Cargamos los contenidos para poder mostrarlas en el cuadro.
    model.addAttribute("contenidos", contenidoDao.getContenidos());
    return "Administrador/Contenido/Contenidos"; // Nombre del archivo jsp
  }

  /**
   * Modelo con el que se realizara el formulario
   * @return Un objeto para ser llenado desde el archivo .JSP
   */
  @ModelAttribute("contenido")
  public Contenido setUpUserForm() {
    return new Contenido();
  } 
  
  /**
   * Método que retorna una pagina para realizar el registro de un contenido.
   * 
   * @return La página de registro de contenidos.
   */
  @GetMapping("/registrarContenido") // Base
  public String registrarContenido(Model model) {
    model.addAttribute("tiposAsociacion",getAsosiaciones());
    //model.addAttribute("tiposContenido",getiposD);
    return "Administrador/Contenido/RegistrarContenido"; // Nombre del archivo jsp
  }

  /**
   * Metodo en donde se almacenan las asosiaciones existentes.
   * @return todas las asosiaciones existentes.
   */
  private Map<String, String> getAsosiaciones() {
    Map<String, String> mapaDeAsosiaciones = new HashMap<>();
    mapaDeAsosiaciones.put("Subcategoria", "Subcategoria");
    mapaDeAsosiaciones.put("Noticia", "Noticia");
    mapaDeAsosiaciones.put("Novedad", "Novedad");
    mapaDeAsosiaciones.put("Actividad", "Actividad");
    return mapaDeAsosiaciones;
  }

  /**
   * Servicio que permite guardar un contenido
   * 
   * @param actividad Objeto con la información a guardar
   * @param model Modelo con la información necesaria para transportar a los archivos .JSP
   * @return La página a donde debe redireccionar después de la acción.
   */
  @PostMapping(value = "/guardarContenido")
  public String registrarContenido(@ModelAttribute("contenido") Contenido contenido, Model model) {

    // Consulta si tiene todos los campos llenos
    if (contenido.isValidoParaRegistrar()) {
      String mensaje = contenidoDao.registrarContenido(contenido);
      if (mensaje.equals("Registro exitoso")) {
        model.addAttribute("result", "Contenido registrado con éxito.");
        return index(model);
      } else {
        model.addAttribute("wrong", mensaje);
        return registrarContenido(model); // Nombre del archivo jsp
      }
      //
    } else {
      model.addAttribute("wrong", "Debes llenar todos los campos.");
      return registrarContenido(model);
    }
  }
   
}
