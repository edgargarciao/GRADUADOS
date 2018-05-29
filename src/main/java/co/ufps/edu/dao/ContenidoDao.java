package co.ufps.edu.dao;

import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.dto.Contenido;

public class ContenidoDao {

  private SpringDbMgr springDbMgr;

  public ContenidoDao() {
    springDbMgr = new SpringDbMgr();
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
    SqlRowSet sqlRowSet = springDbMgr.executeQuery( " SELECT    contenido.id            id, "
                                                  + "           contenido.titulo        nombre,"
                                                  + "           tipocontenido.nombre    tipo_con,"
                                                  + "           contenido.asosiacion    asosiacion,"
                                                  + "           CASE    WHEN contenido.tipoasociacion = 'Subcategoria' "
                                                  + "                   THEN (SELECT subcategoria.nombre FROM subcategoria WHERE subcategoria.id = contenido.id)"
                                                  + "                   WHEN contenido.tipoasociacion = 'Noticia' "
                                                  + "                   THEN (SELECT noticia.nombre FROM noticia WHERE noticia.id = contenido.id)"
                                                  + "                   WHEN contenido.tipoasociacion = 'Novedad' "
                                                  + "                   THEN (SELECT novedad.nombre FROM novedad WHERE novedad.id = contenido.id) "
                                                  + "                   WHEN contenido.tipoasociacion = 'Actividad' "
                                                  + "                   THEN (SELECT proximaactividad.nombre FROM proximaactividad WHERE proximaactividad.id = contenido.id)"
                                                  + "           END AS tipo_asoc " 
                                                  + "  FROM     contenido, tipocontenido "
                                                  + " WHERE     contenido.TipoContenido_id = tipocontenido.id ");

    // Recorre cada registro obtenido de base de datos
    while (sqlRowSet.next()) {
      // Objeto en el que sera guardada la informacion del registro
      Contenido contenido = new Contenido();

      contenido.setId(sqlRowSet.getLong("id"));
      contenido.setNombre(sqlRowSet.getString("nombre"));
      contenido.setTipoContenido(sqlRowSet.getString("tipo_con"));
      contenido.setTipoAsociacion(sqlRowSet.getString("tipo_asoc"));
      contenido.setAsosiacion(sqlRowSet.getLong("asosiacion"));

      // Guarda el registro para ser retornado
      contenidos.add(contenido);
    }

    // Retorna todos las categorias desde base de datos
    return contenidos;
  }

  /*
   * Método que obtiene la cantidad de contenidos registrados
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
    // TODO Auto-generated method stub
    return "Registro exitoso";
  }

}
