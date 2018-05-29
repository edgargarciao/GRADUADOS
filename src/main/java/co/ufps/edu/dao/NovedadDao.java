package co.ufps.edu.dao;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.model.Novedad;

public class NovedadDao {

	SpringDbMgr springDbMgr = new SpringDbMgr();
	

	/**
	 * Metodo que consulta en base de datos todas las novedades existentes.
	 * 
	 * @return Una lista con todas las novedades
	 */
	public List<Novedad> getNovedades() {

		// Lista para retornar con los datos
	    List<Novedad> novedades = new LinkedList<>();
	    // Consulta para realizar en base de datos
	    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM NOVEDAD ");
	    
	    // Recorre cada registro obtenido de base de datos
	    while (sqlRowSet.next()) {
	      // Objeto en el que sera guardada la informacion del registro
	      Novedad novedad = new Novedad();

	      novedad.setId(sqlRowSet.getLong("id"));
	      novedad.setNombre(sqlRowSet.getString("nombre"));
	      novedad.setFecha(sqlRowSet.getDate("fecha"));
	      // Guarda el registro para ser retornado
	      novedades.add(novedad);
	    }
	    // Retorna todos los contactos desde base de datos
	    return novedades;
	}
	
	/**
	   * Método que registra una novedad en base de datos
	   * 
	   * @param novedad Objeto con todos los datos de la novedad a registrar.
	   * @return El resultado de la acción.
	   */
	public String registrarNovedad(Novedad novedad) {
	    SpringDbMgr springDbMgr = new SpringDbMgr();

	    // Agrego los datos del registro (nombreColumna/Valor)
	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("nombre", novedad.getNombre());
	    map.addValue("fecha", novedad.getFecha());
	    
	    // Armar la sentencia de actualización de base de datos
	    String query = "INSERT INTO NOVEDAD(nombre, fecha) VALUES(:nombre, :fecha)";

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
	        : "La información de la novedad ya se encuentra en el sistema.";
	  }

	  
	  /**
	   * Método que consulta en base de datos la informacion de una novedad
	   * 
	   * @param idNovedad Identificador de novedad.
	   * @return La informacion de una novedad en un objeto.
	   */
	  public Novedad obtenerNovedadPorId(long idNovedad) {
	    // Lista para retornar con los datos
	    Novedad novedad = new Novedad();

	    // Consulta para realizar en base de datos
	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("id", idNovedad);
	    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM NOVEDAD WHERE id = :id", map);
	    
	    // Consulto si la novedad existe
	    if (sqlRowSet.next()) {
	      // Almaceno los datos de contacto
	      novedad.setId(sqlRowSet.getLong("id"));
	      novedad.setNombre(sqlRowSet.getString("nombre"));
	      novedad.setFecha(sqlRowSet.getDate("fecha"));
	    }

	    // Retorna contacto desde base de datos
	    return novedad;
	  }

	  
	  /**
	   * Método que consulta en base de datos la informacion de  una novedad
	   * 
	   * @param idNovedad Identificador de novedad.
	   * @return La informacion de una novedad en un objeto.
	   */
	  public String editarNovedad(Novedad novedad) {
	    SpringDbMgr springDbMgr = new SpringDbMgr();

	    // Agrego los datos del registro (nombreColumna/Valor)

	    MapSqlParameterSource map = new MapSqlParameterSource();
	    map.addValue("id", novedad.getId());
	    map.addValue("nombre", novedad.getNombre());
	    map.addValue("fecha", novedad.getFecha());

	    // Armar la sentencia de actualización debase de datos
	    String query = "UPDATE NOVEDAD SET nombre = :nombre, fecha = :fecha  WHERE id = :id";

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
	        : "La novedad ya se encuentra en el sistema.";
	  }
	  
	  
	  public String eliminarNovedad(Novedad novedad) {
		    SpringDbMgr springDbMgr = new SpringDbMgr();

		    // Agrego los datos de la eliminación (nombreColumna/Valor)
		    MapSqlParameterSource map = new MapSqlParameterSource();
		    map.addValue("id", novedad.getId());

		    // Armar la sentencia de eliminación debase de datos
		    String query = "DELETE FROM NOVEDAD WHERE id = :id";

		    // Ejecutar la sentencia
		    int result = 0;
		    try {
		      result = springDbMgr.executeDml(query, map);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
		    // de error.
		    return (result == 1) ? "Eliminacion exitosa" : "No fue posible eliminar la novedad";
	  }
	  
	  
	  /*
	   *  Método que obtiene la cantidad de novedades registradas
	   */
	  public int getCantidadNovedades() {
		  	int cant = 0;
		    // Consulta para realizar en base de datos
		    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT COUNT(*) cantidad FROM NOVEDAD "); 
		    
		    if (sqlRowSet.next()) {
		    	cant = sqlRowSet.getInt("cantidad");
		    }
		    return cant;
	  }
}
