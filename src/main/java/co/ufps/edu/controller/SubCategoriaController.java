package co.ufps.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.ufps.edu.dao.CategoriaDao;
import co.ufps.edu.dao.SubCategoriaDao;
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
   * Servicio que permite guardar una subcategoria
   * 
   * @param subCategoria Objeto con la información a guardar
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
  
  /**
   * Servicio que permite bajar el numero de orden de una categoria
   * 
   * @param idCategoria Idenrificador de la categoria
   * @param orden Numero de orden actual
   * @param model Donde se cargaran las categorias actualizadas
   * @return El redireccionamiento a la pagina de categorias
   */
  @GetMapping(value = "/bajarOrdenSubCategoria")
  public String bajarOrdenDeCategoria(@RequestParam("idCat") long idCategoria,@RequestParam("id") long idSubCategoria,
      @RequestParam("orden") int orden, Model model) {
    // Cambiar orden
    bajarOrden(idCategoria,idSubCategoria, orden);

    // Cargar categorias en model
    model.addAttribute("subcategorias", subCategoriaDao.getSubCategorias());
    return "Administrador/SubCategoria/SubCategorias"; // Nombre del archivo jsp
  }

/**
 * Metodo que baja de orden una subcategoria.
 * @param idCategoria Id de la categoria
 * @param idSubCategoria Id de la subcategoria
 * @param orden Numero de ordenamiento
 */
  private void bajarOrden(long idCategoria,long idSubCategoria, int orden) {

    // Consulto que el Id sea mayor a 0.
    if (idCategoria <= 0 || idSubCategoria <= 0) {
      return;
    }

    // Obtengo el menor número de ordenamiento
    int ordenMaximo = subCategoriaDao.getUltimoNumeroDeOrden(idCategoria);

    // Si el numero de orden es el máximo es por que ya es el ultimo y no se debe hacer nada.
    if (orden == ordenMaximo) {
      return;
      // Cambio el orden
    } else {
      subCategoriaDao.descenderOrden(idCategoria,idSubCategoria, orden);
    }
  }

  /**
   * Servicio que permite subir el numero de orden de una subcategoria
   * 
   * @param idCategoria Idenrificador de la categoria
   * @param orden Numero de orden actual
   * @param model Donde se cargaran las categorias actualizadas
   * @return El redireccionamiento a la pagina de categorias
   */
  @GetMapping(value = "/subirOrdenSubCategoria")
  public String subirOrdenDeSubCategoria(@RequestParam("idCat") long idCategoria,@RequestParam("id") long idSubCategoria,
      @RequestParam("orden") int orden, Model model) {
    // Cambiar orden
    subirOrden(idCategoria,idSubCategoria, orden);

    // Cargar categorias en model para ser leidas por los archivos .JSP
    model.addAttribute("subcategorias", subCategoriaDao.getSubCategorias());
    return "Administrador/SubCategoria/SubCategorias"; // Nombre del archivo jsp
  }

  /**
   * Metodo que permite subir una categoria de orden
   * 
   * @param idCategoria Identificador de la categoria.
   * @param idSubCategoria 
   * @param orden Orden de la categoria.
   */
  private void subirOrden(long idCategoria, long idSubCategoria, int orden) {

    // Consulto que el Id sea mayor a 0.
    if (idCategoria <= 0 || idSubCategoria <= 0) {
      return;
    }

    // Si el numero de orden es el minimo es por que ya es el primero y no se debe hacer nada.
    if (orden == 1) {
      return;
      // Cambio el orden
    } else {
      subCategoriaDao.ascenderOrden(idCategoria,idSubCategoria, orden);
    }
  }
}
