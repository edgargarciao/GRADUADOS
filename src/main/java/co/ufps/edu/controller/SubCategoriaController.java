package co.ufps.edu.controller;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.ufps.edu.dao.CategoriaDao;
import co.ufps.edu.dao.SubCategoriaDao;
import co.ufps.edu.model.Categoria;
import co.ufps.edu.model.SubCategoria;

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
  private CategoriaDao categoriaDao;

  /**
   * Constructor de la clase en donde se inicializan las variables
   */
  public SubCategoriaController() {
    subCategoriaDao = new SubCategoriaDao();
    categoriaDao = new CategoriaDao();
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
  
  /**
   * Modelo con el que se realizara el formulario
   * @return Un objeto para ser llenado desde el archivo .JSP
   */
  @ModelAttribute("subcategoria")
  public SubCategoria setUpUserForm() {
    return new SubCategoria();
  }
  
  /**
   * Método que retorna una pagina para realizar el registro de una subcategoria.
   * 
   * @return La página de registro de subcategorias.
   */
  @GetMapping("/registrarSubCategoria") // Base
  public String registrarSubCategoria(Model model) {
    model.addAttribute("categorias",categoriaDao.getMapaDeCategorias());
    return "Administrador/SubCategoria/RegistrarSubCategoria"; // Nombre del archivo jsp
  }

  /**
   * Servicio que permite guardar una categoria
   * 
   * @param categoria Objeto con la información a guardar
   * @param model Modelo con la información necesaria para transportar a los archivos .JSP
   * @return La página a donde debe redireccionar después de la acción.
   */
  @PostMapping(value = "/guardarSubCategoria")
  public String registrarSubCategoria(@ModelAttribute("subcategoria") SubCategoria subCategoria, Model model) {

    // Consulta si tiene todos los campos llenos
    if (subCategoria.isValidoParaRegistrar()) {
      if(subCategoriaDao.esNombreValido(subCategoria.getCategoria().getId(),subCategoria.getNombre())) {
        String mensaje = subCategoriaDao.registrarSubCategoria(subCategoria);
        if (mensaje.equals("Registro exitoso")) {
          model.addAttribute("result", "Subcategoria registrada con éxito.");
          model.addAttribute("subcategorias", subCategoriaDao.getSubCategorias());
          return "Administrador/SubCategoria/SubCategorias"; // Nombre del archivo jsp
        } else {
          model.addAttribute("wrong", mensaje);
          model.addAttribute("categorias",categoriaDao.getMapaDeCategorias());
          return "Administrador/SubCategoria/RegistrarSubCategoria"; // Nombre del archivo jsp
        }
      }else {
        model.addAttribute("wrong", "El nombre para esta subcategoria ya se encuentra usado dentro de la categoria seleccionada.");
        model.addAttribute("categorias",categoriaDao.getMapaDeCategorias());
        return "Administrador/SubCategoria/RegistrarSubCategoria"; // Nombre del archivo jsp
      }
      
    } else {
      model.addAttribute("wrong", "Debes llenar todos los campos.");
      model.addAttribute("categorias",categoriaDao.getMapaDeCategorias());
      return "Administrador/SubCategoria/RegistrarSubCategoria"; // Nombre del archivo jsp
    }
  }
}
