package co.ufps.edu.controller;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.ufps.edu.dao.SubCategoriaDao;

/**
 * Controlador de subcategorias. Las subcategorias son las llamadas pestañas hijas de las categorias 
 * en el sitio web. Todos los servicios publicados en esta clase seran expuestos para 
 * ser consumidos por los archivos .JSP
 * <p>
 * La etiqueta @Controller escanea todos los servicios para publicarlos segun el tipo de metodo
 * HTTP.
 * 
 * @author ufps
 *
 */
@Controller
public class SubCategoriaController {

  private SubCategoriaDao subCategoriaDao;

  /**
   * Constructor de la clase en donde se inicializan las variables
   */
  public SubCategoriaController() {
    subCategoriaDao = new SubCategoriaDao();
  }

  /**
   * Método que retorna una pagina con todas las subcategorias en el sistema.
   * 
   * @return La página principal de categorias.
   */
  @GetMapping("/subcategorias") // Base
  public String index(Model model) {
    // Cargamos las categorias para poder mostrarlas en el cuadro.
    model.addAttribute("subcategorias", subCategoriaDao.getSubCategorias());
    return "Administrador/SubCategoria/SubCategorias"; // Nombre del archivo jsp
  }

  
}
