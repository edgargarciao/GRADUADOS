package co.ufps.edu.dao;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.dto.Galeria;

/**
 * Clase que permite acceder a la capa de datos en el entorno de galerias.
 * @author ufps
 *
 */
public class GaleriaDao {

	SpringDbMgr springDbMgr = new SpringDbMgr();
	
	/**
	 * Metodo que consulta en base de datos todas las galerias existentes.
	 * 
	 * @return Una lista con todas las novedades
	 */
	public List<Galeria> getGalerias() {

		// Lista para retornar con los datos
	    List<Galeria> galerias = new LinkedList<>();
	    // Consulta para realizar en base de datos
	    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM GALERIA ");
	    
	    // Recorre cada registro obtenido de base de datos
	    while (sqlRowSet.next()) {
	      // Objeto en el que sera guardada la informacion del registro
	      Galeria galeria = new Galeria();

	      galeria.setId(sqlRowSet.getLong("id"));
	      galeria.setNombre(sqlRowSet.getString("nombre"));
	      galeria.setDescripcion(sqlRowSet.getString("descripcion"));
	      galeria.setFecha(sqlRowSet.getDate("fecha"));
	      // Guarda el registro para ser retornado
	      galerias.add(galeria);
	    }
	    // Retorna todos las galerias desde base de datos
	    return galerias;
	}
	
	/**
	   * Método que registra una galeria en base de datos
	   * 
	   * @param galeria Objeto con todos los datos de la galeria a registrar.
	   * @return El resultado de la acción.
	   */
	public String registrarGaleria(Galeria galeria) {
	    SpringDbMgr springDbMgr = new SpringDbMgr();

	    // Agrego los datos del registro (nombreColumna/Valor)
	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("nombre", galeria.getNombre());
	    map.addValue("descripcion", galeria.getDescripcion());
	    map.addValue("fecha", galeria.getFecha());
	    
	    // Armar la sentencia de actualización de base de datos
	    String query = "INSERT INTO GALERIA(nombre, descripcion, fecha) VALUES(:nombre, :descripcion, :fecha)";

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
	        : "La información de la galeria ya se encuentra en el sistema.";
	  }

	  
	  /**
	   * Método que consulta en base de datos la informacion de una galeria
	   * 
	   * @param idGaleria Identificador de galeria.
	   * @return La informacion de una galeria en un objeto.
	   */
	  public Galeria obtenerGaleriaPorId(long idGaleria) {
	    // Lista para retornar con los datos
	    Galeria  galeria = new Galeria();

	    // Consulta para realizar en base de datos
	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("id", idGaleria);
	    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM GALERIA WHERE id = :id", map);
	    
	    // Consulto si la galeria existe
	    if (sqlRowSet.next()) {
	      // Almaceno los datos de contacto
	    	galeria.setId(sqlRowSet.getLong("id"));
	    	galeria.setNombre(sqlRowSet.getString("nombre"));
	    	galeria.setDescripcion(sqlRowSet.getString("descripcion"));
	    	galeria.setFecha(sqlRowSet.getDate("fecha"));
	    }

	    // Retorna galeria desde base de datos
	    return galeria;
	  }

	  
	  /**
	   * Método que consulta en base de datos la informacion de  una galeria
	   * 
	   * @param idGaleria Identificador de galeria.
	   * @return La informacion de una galeria en un objeto.
	   */
	  public String editarGaleria(Galeria galeria) {
	    SpringDbMgr springDbMgr = new SpringDbMgr();

	    // Agrego los datos del registro (nombreColumna/Valor)

	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("id", galeria.getId());
	    map.addValue("nombre", galeria.getNombre());
	    map.addValue("descripcion", galeria.getDescripcion());
	    map.addValue("fecha", galeria.getFecha());

	    // Armar la sentencia de actualización debase de datos
	    String query = "UPDATE GALERIA SET nombre = :nombre, descripcion = :descripion, fecha = :fecha  WHERE id = :id";

	    // Ejecutar la sentencia
	    int result = 0;
	    try {
	      result = springDbMgr.executeDml(query, map);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
	    // de error.
	    return (result == 1) ? "Actualizacion exitosa"
	        : "La galeria ya se encuentra en el sistema.";
	  }
	  
	  
	  public String eliminarGaleria(Galeria galeria) {
		    SpringDbMgr springDbMgr = new SpringDbMgr();

		    // Agrego los datos de la eliminación (nombreColumna/Valor)
		    MapSqlParameterSource map = new MapSqlParameterSource();
		    map.addValue("id", galeria.getId());

		    // Armar la sentencia de eliminación debase de datos
		    String query = "DELETE FROM GALERIA WHERE id = :id";

		    // Ejecutar la sentencia
		    int result = 0;
		    try {
		      result = springDbMgr.executeDml(query, map);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
		    // de error.
		    return (result == 1) ? "Eliminacion exitosa" : "No fue posible eliminar la galeria";
	  }

	  /*
	   *  Método que obtiene la cantidad de galerias registradas
	   */
	  public int getCantidadGalerias() {
		  	int cant = 0;
		    // Consulta para realizar en base de datos
		    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT COUNT(*) cantidad FROM GALERIA "); 
		    
		    if (sqlRowSet.next()) {
		    	cant = sqlRowSet.getInt("cantidad");
		    }
		    return cant;
	  }
}
