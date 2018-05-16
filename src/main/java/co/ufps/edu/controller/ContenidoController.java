package co.ufps.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.ufps.edu.dao.CategoriaDao;
import co.ufps.edu.model.Categoria;

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

  //private CategoriaDao categoriaDao;

  /**
   * Constructor de la clase en donde se inicializan las variables
   */
  public ContenidoController() {
    //categoriaDao = new CategoriaDao();
  }

  /**
   * Método que retorna una pagina con todas las categorias en el sistema.
   * 
   * @return La página principal de categorias.
   */
  @GetMapping("/contenidos") // Base
  public String index(Model model) {
    // Cargamos las categorias para poder mostrarlas en el cuadro.
    //model.addAttribute("categorias", categoriaDao.getCategorias());
    return "Administrador/Contenido/contenidos"; // Nombre del archivo jsp
  }

 
  
}
