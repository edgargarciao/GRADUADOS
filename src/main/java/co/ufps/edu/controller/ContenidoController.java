package co.ufps.edu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import co.ufps.edu.constantes.Constantes;
import co.ufps.edu.dao.ContenidoDao;
import co.ufps.edu.dao.TipoContenidoDao;
import co.ufps.edu.dto.Archivo;
import co.ufps.edu.dto.Contenido;


/**
 * Controlador de contenidos. Los contenidos son las paginas que se abren cuando se da click a una
 * información. Todos los servicios publicados en esta clase seran expuestos para ser consumidos por
 * los archivos .JSP
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
  private TipoContenidoDao tipoContenidoDao;

  /**
   * Constructor de la clase en donde se inicializan las variables
   */
  public ContenidoController() {
    contenidoDao = new ContenidoDao();
    tipoContenidoDao = new TipoContenidoDao();
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
   * 
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
    model.addAttribute("tiposAsociacion", getAsosiaciones());
    model.addAttribute("tiposContenido", tipoContenidoDao.getContenidos());
    return "Administrador/Contenido/RegistrarContenido"; // Nombre del archivo jsp
  }

  /**
   * Metodo en donde se almacenan las asosiaciones existentes.
   * 
   * @return todas las asosiaciones existentes.
   */
  private Map<String, String> getAsosiaciones() {
    Map<String, String> mapaDeAsosiaciones = new HashMap<>();
    mapaDeAsosiaciones.put(Constantes.SUBCATEGORIA, "Subcategoria");
    mapaDeAsosiaciones.put(Constantes.NOTICIA, "Noticia");
    mapaDeAsosiaciones.put(Constantes.NOVEDAD, "Novedad");
    mapaDeAsosiaciones.put(Constantes.ACTIVIDAD, "Actividad");
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

  @PostMapping(value = "servicios/asosiaciones")
  public @ResponseBody ResponseEntity<Map<Integer, String>> getAsosiacionesPorTipo(
      @RequestBody String tipoAsociacion) {
    
    Map<Integer, String> asociaciones = contenidoDao.getAsociaciones(tipoAsociacion);
    return new ResponseEntity<Map<Integer, String>>(asociaciones, HttpStatus.OK);
  }

  @PostMapping(value = "servicios/recibirInformacion" )
  public @ResponseBody ResponseEntity<String> recibirInformacion(@RequestBody Contenido contenido) {

    // Consulta si tiene todos los campos llenos
    if (contenido.isValidoParaRegistrar()) {
      String mensaje = contenidoDao.registrarContenido(contenido);
      if (mensaje.equals("Registro exitoso")) {
        //model.addAttribute("result", "Contenido registrado con éxito.");
        //return index(model);
        return new ResponseEntity<String>("REGISTRO EXITOSO", HttpStatus.OK);
      } else {
        //model.addAttribute("wrong", mensaje);
        // return registrarContenido(model); // Nombre del archivo jsp
        return new ResponseEntity<String>("REGISTRO NO EXITOSO", HttpStatus.OK);
      }
      //
    } else {
      //model.addAttribute("wrong", "Debes llenar todos los campos.");
      //return registrarContenido(model);
      return new ResponseEntity<String>("CAMPOS INVALIDOS", HttpStatus.OK);
    }
    
    
    //return new ResponseEntity<String>("LLEGO LA INFO", HttpStatus.OK);
  }
  
  @PostMapping(value = "servicios/registrarArchivo" )
  public @ResponseBody ResponseEntity<String> registrarArchivo(@RequestParam("archivo") MultipartFile multipartFile) {    
    long id = contenidoDao.registrarArchivo(multipartFile);
    return new ResponseEntity<String>(String.valueOf(id), HttpStatus.OK);
  }
  
  @PostMapping(value = "servicios/solicitarImagen" )
  public @ResponseBody ResponseEntity<String> solicitarImagen(@RequestParam("tipo") String tipo) {    
    String imagenBase64 = contenidoDao.solicitarImagen(tipo);
    if(StringUtils.isEmpty(imagenBase64)) {
      imagenBase64 = contenidoDao.solicitarImagen("RecibirImagenCualquiera");
    }
    return new ResponseEntity<String>(imagenBase64, HttpStatus.OK);
  }
  
  @GetMapping(value = "servicios/download" )
  public void download(@RequestParam("id") long id, HttpServletResponse response) {    
   
    Archivo archivo = contenidoDao.obtenerArchivo(id);
    try {
      // get your file as InputStream
      InputStream is = archivo.getContenido();
      
      // copy it to response's OutputStream
      response.setContentType(archivo.getTipo());      
      response.setHeader("Content-Disposition", "attachment; filename=\""+archivo.getNombre()+"\""); 
      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
      
      response.flushBuffer();
    } catch (IOException ex) {
      
      throw new RuntimeException("IOError writing file to output stream");
    }
    
  }
}
