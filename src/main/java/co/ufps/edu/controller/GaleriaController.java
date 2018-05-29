package co.ufps.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import co.ufps.edu.dao.GaleriaDao;

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
public class GaleriaController {

  private GaleriaDao galeriaDao;

  /**
   * Constructor de la clase en donde se inicializan las variables
   */
  public GaleriaController() {
    galeriaDao = new GaleriaDao();
  }

  /**
   * Método que retorna una pagina con todas los contenidos en el sistema.
   * 
   * @return La página principal de contenidos.
   */
  @GetMapping("/galerias") // Base
  public String index(Model model) {
    // Cargamos los contenidos para poder mostrarlas en el cuadro.
    model.addAttribute("contenidos", galeriaDao.getCantidadGalerias());
    return "Administrador/Galeria/galerias"; // Nombre del archivo jsp
  }

 
  
}
