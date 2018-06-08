package co.ufps.edu.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.Charsets;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.multipart.MultipartFile;
import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.constantes.Constantes;
import co.ufps.edu.dto.Archivo;
import co.ufps.edu.dto.Contenido;
import co.ufps.edu.dto.Noticia;
import co.ufps.edu.dto.ResultDB;
import co.ufps.edu.dto.TipoContenido;
import co.ufps.edu.util.ImagenUtil;

public class ContenidoDao {

  private SpringDbMgr springDbMgr;
  private ImagenUtil imagenUtil;

  public ContenidoDao() {
    springDbMgr = new SpringDbMgr();
    imagenUtil = new ImagenUtil();
  }

  /**
   * Metodo que consulta en base de datos todas los contenidos existentes.
   * 
   * @return Una lista con todas los contenidos
   */
  public List<Contenido> getContenidos() {

    // Lista para retornar con los datos
    List<Contenido> contenidos = new LinkedList<>();

    // Consulta para realizar en base de datos
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT     contenido.id            id, "
                                                  + "           contenido.titulo        nombre,"
                                                  + "           tipocontenido.nombre    tipo_con,"
                                                  + "           contenido.asociacion    asociacion,"
                                                  + "           CASE    WHEN contenido.tipoasociacion = 'Subcategoria' "
                                                  + "                   THEN (SELECT CONCAT('Subcategoria: ',subcategoria.nombre) FROM subcategoria WHERE subcategoria.id = contenido.asociacion)"
                                                  + "                   WHEN contenido.tipoasociacion = 'Noticia' "
                                                  + "                   THEN (SELECT CONCAT('Noticia: ',noticia.nombre) FROM noticia WHERE noticia.id = contenido.asociacion)"
                                                  + "                   WHEN contenido.tipoasociacion = 'Novedad' "
                                                  + "                   THEN (SELECT CONCAT('Novedad: ',novedad.nombre) FROM novedad WHERE novedad.id = contenido.asociacion) "
                                                  + "                   WHEN contenido.tipoasociacion = 'Actividad' "
                                                  + "                   THEN (SELECT CONCAT('Actividad: ',proximaactividad.nombre) FROM proximaactividad WHERE proximaactividad.id = contenido.asociacion)"
                                                  + "           END AS tipo_asoc " 
                                                  + "  FROM     contenido, tipocontenido "
                                                  + " WHERE     contenido.TipoContenido_id = tipocontenido.id ");

    // Recorre cada registro obtenido de base de datos
    while (sqlRowSet.next()) {
      // Objeto en el que sera guardada la informacion del registro
      Contenido contenido = new Contenido();

      contenido.setId(sqlRowSet.getLong("id"));
      contenido.setNombre(sqlRowSet.getString("nombre"));
      contenido.setTipoAsociacion(sqlRowSet.getString("tipo_asoc"));
      contenido.setAsociacion(sqlRowSet.getLong("asociacion"));

      TipoContenido tipoContenido = new TipoContenido();
      tipoContenido.setNombre(sqlRowSet.getString("tipo_con"));

      contenido.setTipoContenido(tipoContenido);

      // Guarda el registro para ser retornado
      contenidos.add(contenido);
    }

    // Retorna todos las categorias desde base de datos
    return contenidos;
  }

  /*
   * M�todo que obtiene la cantidad de contenidos registrados
   */
  public int getCantidadContenidos() {
    int cant = 0;
    // Consulta para realizar en base de datos
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT COUNT(*) cantidad FROM CONTENIDO ");

    if (sqlRowSet.next()) {
      cant = sqlRowSet.getInt("cantidad");
    }
    return cant;
  }

  public String registrarContenido(Contenido contenido) {
    // Agrego los datos del registro (nombreColumna/Valor)

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("titulo", contenido.getNombre());
    map.addValue("tipoasociacion", contenido.getTipoAsociacion());
    map.addValue("TipoContenido_id", contenido.getTipoContenido().getId());
    map.addValue("asociacion", contenido.getAsociacion());
    
    map.addValue("contenido",contenido.getContenido().getBytes(Charsets.UTF_8));

    // Armar la sentencia de actualizaci�n debase de datos
    String query =
        "INSERT INTO CONTENIDO(titulo,tipoasociacion,TipoContenido_id,asociacion,contenido) VALUES(:titulo,:tipoasociacion,:TipoContenido_id,:asociacion,:contenido)";

    // Ejecutar la sentencia
    int result = 0;
    try {
      result = springDbMgr.executeDml(query, map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
    // de error.
    return (result == 1) ? "Registro exitoso"
        : "Error al registrar el contenido. Contacte al administrador del sistema.";
  }

  public Map<Integer, String> getAsociaciones(String tipoAsociacion) {

    Map<Integer, String> asociaciones = new HashMap<>();
    String tabla = (tipoAsociacion.equalsIgnoreCase(Constantes.ACTIVIDAD)) ? "PROXIMAACTIVIDAD"
        : tipoAsociacion;
    // Consulta para realizar en base de datos
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM " + tabla + " WHERE ID NOT IN (SELECT ASOCIACION FROM CONTENIDO)");

    while (sqlRowSet.next()) {
      asociaciones.put(sqlRowSet.getInt("id"), sqlRowSet.getString("nombre"));
    }

    return asociaciones;
  }

  public long registrarArchivo(MultipartFile archivo) {
    // Agrego los datos del registro (nombreColumna/Valor)

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("nombre", archivo.getOriginalFilename());
    map.addValue("tama�o", archivo.getSize());
    map.addValue("tipo", archivo.getContentType());
    
    try {
      map.addValue("contenido",
          new SqlLobValue(new ByteArrayInputStream(archivo.getBytes()),
              archivo.getBytes().length, new DefaultLobHandler()),
          Types.BLOB);
    } catch (IOException e) {
      new Exception();
      
    }
    
    // Armar la sentencia de actualizaci�n debase de datos
    String query =
        "INSERT INTO ARCHIVO(nombre,contenido,tama�o,tipo) VALUES(:nombre,:contenido,:tama�o,:tipo)";

    // Ejecutar la sentencia
    ResultDB result = null;
    try {
      result = springDbMgr.executeDmlWithKey(query, map);
    } catch (Exception e) {
      new Exception();
    }
    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
    // de error.
    return result.getKey();
  }

  public String solicitarImagen(String tipo) {
    
    String contenido = "";
    // Consulta para realizar en base de datos
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("tipo", tipo);
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM ARCHIVO WHERE nombre = :tipo", map);

    // Consulto si la noticia existe
    if (sqlRowSet.next()) {
      
      // Almaceno los datos de la noticia
      Object imagen1Blob = sqlRowSet.getObject("contenido");
      
      contenido = (imagenUtil.convertirImagen((byte[]) imagen1Blob));      
    }

    // Retorna la noticia desde base de datos
    return contenido;
  }

  public Archivo obtenerArchivo(long id) {
    Archivo archivo = new Archivo();
    // Consulta para realizar en base de datos
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", id);
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM ARCHIVO WHERE id = :id", map);

    // Consulto si la noticia existe
    if (sqlRowSet.next()) {
      
      // Almaceno los datos de la noticia
      Object imagen1Blob = sqlRowSet.getObject("contenido");
      archivo.setContenido(new ByteArrayInputStream((byte[]) imagen1Blob));
      archivo.setNombre(sqlRowSet.getString("nombre"));
      archivo.setTipo(sqlRowSet.getString("tipo"));
    }

    // Retorna la noticia desde base de datos
    return archivo;
  }



}
