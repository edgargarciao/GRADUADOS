package co.ufps.edu.dao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.model.Categoria;
import co.ufps.edu.model.SubCategoria;

public class SubCategoriaDao {
  
  SpringDbMgr springDbMgr = new SpringDbMgr();

  /**
   * Metodo que consulta en base de datos todas las subcategorias existentes y las devuelve
   * ordenadamente
   * 
   * @return Una lista con todas las categorias
   */
  public List<SubCategoria> getSubCategorias() {

    // Lista para retornar con los datos
    List<SubCategoria> subCategorias = new LinkedList<>();

    // Consulta para realizar en base de datos
    SqlRowSet sqlRowSet = springDbMgr.executeQuery( " SELECT    CATEGORIA.ID                    idCategoria,            "
                                                  + "           CATEGORIA.NOMBRE                nombreCategoria,        "
                                                  + "           CATEGORIA.DESCRIPCION           descripcionCategoria,   "
                                                  + "           CATEGORIA.ORDEN                 ordenCategoria,         "
                                                  + "           SUBCATEGORIA.ID                 idSubcategoria,         "
                                                  + "           SUBCATEGORIA.NOMBRE             nombreSubCategoria,     "
                                                  + "           SUBCATEGORIA.DESCRIPCION        descripcionSubCategoria,"
                                                  + "           SUBCATEGORIA.ORDEN              ordenSubCategoria       "
                                                  + "   FROM    SUBCATEGORIA                                            "
                                                  + "INNER JOIN CATEGORIA  ON CATEGORIA.id = SUBCATEGORIA.Categoria_id  "
                                                  + "ORDER BY   CATEGORIA.ID ASC,SUBCATEGORIA.ORDEN ASC                 ");

    // Recorre cada registro obtenido de base de datos
    while (sqlRowSet.next()) {
      // Objeto en el que sera guardada la informacion del registro
      SubCategoria subCategoria = new SubCategoria();

      subCategoria.setId(sqlRowSet.getLong("idSubcategoria"));
      subCategoria.setNombre(sqlRowSet.getString("nombreSubCategoria"));
      subCategoria.setDescripcion(sqlRowSet.getString("descripcionSubCategoria"));
      subCategoria.setOrden(sqlRowSet.getInt("ordenSubCategoria"));
      
      Categoria categoria = new Categoria();
      categoria.setId(sqlRowSet.getLong("idCategoria"));
      categoria.setNombre(sqlRowSet.getString("nombreCategoria"));
      categoria.setDescripcion(sqlRowSet.getString("descripcionCategoria"));
      categoria.setOrden(sqlRowSet.getInt("ordenCategoria"));
      subCategoria.setCategoria(categoria);
      
      // Guarda el registro para ser retornado
      subCategorias.add(subCategoria);
    }

    // Retorna todos las categorias desde base de datos
    return subCategorias;
  }

  /**
   * Método que registra una categoria en base de datos
   * 
   * @param categoria Objeto con todos los datos de la categoria a registrar.
   * @return El resultado de la acción.
   */
  public String registrarSubCategoria(SubCategoria categoria) {
    SpringDbMgr springDbMgr = new SpringDbMgr();

    // Agrego los datos del registro (nombreColumna/Valor)

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("nombre", categoria.getNombre());
    map.addValue("descripcion", categoria.getDescripcion());
    map.addValue("idCategoria", categoria.getCategoria().getId());
    map.addValue("orden", getUltimoNumeroDeOrden(categoria.getCategoria().getId()) + 1);

    // Armar la sentencia de actualización debase de datos
    String query =
        "INSERT INTO SUBCATEGORIA(nombre,descripcion,orden,Categoria_id) VALUES(:nombre,:descripcion,:orden,:idCategoria)";

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
        : "El nombre de la subcategoria ya se encuentra en el sistema.";

  }

  /**
   * Metodo que consulta en la base de datos cual es el ultimo de numero de ordenamiento que hay
   * entre todas las categorias
   * @param idCategoria 
   * 
   * @return El último numero de orden de categoria.
   */
  public int getUltimoNumeroDeOrden(long idCategoria) {

    // Consulta en base de datos el ultimo numero de ordenamiento
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", idCategoria);
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM SUBCATEGORIA WHERE Categoria_id = :id ORDER BY ORDEN DESC",map);

    // Si existe al menos una categoria retorna el numero
    if (sqlRowSet.next()) {
      return (sqlRowSet.getInt("orden"));
      // Si no existen categorias retorna el 0
    } else {
      return 0;
    }
  }

  /**
   * Metodo que baja un nivel a la categoria en base de datos.
   * 
   * @param idCategoria Identificador de la categoria.
   * @param orden
   */
  public void descenderOrden(long idCategoria,long idSubCategoria, int orden) {

    // Extraemos el id de la siguiente
    long idSubCategoriaSiguiente = getIdSubCategoriaPorOrden(idCategoria,orden + 1);

    // Modificamos el orden actual
    cambiarOrdenDeSubCategoria(idCategoria,idSubCategoria, -1);

    // Modificamos el orden de la siguiente categoria
    cambiarOrdenDeSubCategoria(idCategoria,idSubCategoriaSiguiente, orden);

    // Modificamos el orden de la categoria actual
    cambiarOrdenDeSubCategoria(idCategoria, idSubCategoria,orden + 1);
  }

  /**
   * Metodo que consulta en base de datos el ID de una categoria dado un numero de orden.
   * @param idCategoria 
   * 
   * @param orden Numero de orden por el cual se filtrara la busqueda.
   * @return El ID de la categoria.
   */
  public long getIdSubCategoriaPorOrden(long idCategoria, int orden) {
    // Consulta en base de datos el ultimo numero de ordenamiento
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("orden", orden);
    map.addValue("cat", idCategoria);
    SqlRowSet sqlRowSet =
        springDbMgr.executeQuery(" SELECT * FROM SUBCATEGORIA WHERE orden = :orden AND Categoria_id = :cat", map);

    // Si existe al menos una categoria retorna el numero
    if (sqlRowSet.next()) {
      return (sqlRowSet.getLong("id"));
      // Si no existen categorias retorna el 0
    } else {
      return 0l;
    }
  }

  /**
   * Metodo que actualiza el orden de una categoria.
   * 
   * @param id de la categoria.
   * @param orden para actualizar a la categoria.
   */
  public void cambiarOrdenDeSubCategoria(long idCategoria,long idSubCategoria, int orden) {
    SpringDbMgr springDbMgr = new SpringDbMgr();

    // Agrego los datos del registro (nombreColumna/Valor)
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", idSubCategoria);
    map.addValue("orden", orden);
    map.addValue("cat", idCategoria);
    // Armar la sentencia de actualización debase de datos
    String query = "UPDATE SUBCATEGORIA SET orden = :orden WHERE id = :id AND Categoria_id = :cat";

    // Ejecutar la sentencia
    try {
      springDbMgr.executeDml(query, map);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void ascenderOrden(long idCategoria,long idSubCategoria, int orden) {
    // Extraemos el id de la categoria anterior
    long idCategoriaAnterior = getIdSubCategoriaPorOrden(idCategoria,orden - 1);

    // Modificamos el orden actual
    cambiarOrdenDeSubCategoria(idCategoria,idSubCategoria, -1);

    // Modificamos el orden de la anterior categoria
    cambiarOrdenDeSubCategoria(idCategoria,idCategoriaAnterior, orden);

    // Modificamos el orden de la categoria actual
    cambiarOrdenDeSubCategoria(idCategoria,idSubCategoria, orden - 1);

  }

  /**
   * Metodo que consulta en base de datos la informacion de una categoria dada
   * 
   * @param idCategoria Identificador de la categoria.
   * @return La informacion de una categoria en un objeto.
   */
  public SubCategoria obtenerSubCategoriaPorId(long idSubCategoria) {
    // Lista para retornar con los datos
    SubCategoria subCategoria = new SubCategoria();

    // Consulta para realizar en base de datos
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", idSubCategoria);
    SqlRowSet sqlRowSet = springDbMgr.executeQuery( " SELECT    CATEGORIA.ID                    idCategoria,            "
                                                  + "           CATEGORIA.NOMBRE                nombreCategoria,        "
                                                  + "           CATEGORIA.DESCRIPCION           descripcionCategoria,   "
                                                  + "           CATEGORIA.ORDEN                 ordenCategoria,         "
                                                  + "           SUBCATEGORIA.ID                 idSubcategoria,         "
                                                  + "           SUBCATEGORIA.NOMBRE             nombreSubCategoria,     "
                                                  + "           SUBCATEGORIA.DESCRIPCION        descripcionSubCategoria,"
                                                  + "           SUBCATEGORIA.ORDEN              ordenSubCategoria       "
                                                  + "   FROM    SUBCATEGORIA                                            "
                                                  + "INNER JOIN CATEGORIA  ON CATEGORIA.id = SUBCATEGORIA.Categoria_id  "
                                                  + "WHERE      SUBCATEGORIA.ID = :id                                   "
                                                  + "ORDER BY   CATEGORIA.ID ASC,SUBCATEGORIA.ORDEN ASC                 ", map);

    // Consulto si la categoria existe
    if (sqlRowSet.next()) {

      subCategoria.setId(sqlRowSet.getLong("idSubcategoria"));
      subCategoria.setNombre(sqlRowSet.getString("nombreSubCategoria"));
      subCategoria.setDescripcion(sqlRowSet.getString("descripcionSubCategoria"));
      subCategoria.setOrden(sqlRowSet.getInt("ordenSubCategoria"));
      
      Categoria categoria = new Categoria();
      categoria.setId(sqlRowSet.getLong("idCategoria"));
      categoria.setNombre(sqlRowSet.getString("nombreCategoria"));
      categoria.setDescripcion(sqlRowSet.getString("descripcionCategoria"));
      categoria.setOrden(sqlRowSet.getInt("ordenCategoria"));
      subCategoria.setCategoria(categoria);
      
    }

    // Retorna la categoria desde base de datos
    return subCategoria;
  }

  public String editarSubCategoria(SubCategoria subcategoria) {
    SpringDbMgr springDbMgr = new SpringDbMgr();

    // Agrego los datos del registro (nombreColumna/Valor)

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", subcategoria.getId());
    map.addValue("nombre", subcategoria.getNombre());
    map.addValue("descripcion", subcategoria.getDescripcion());

    // Armar la sentencia de actualización debase de datos
    String query =
        "UPDATE SUBCATEGORIA SET nombre = :nombre, descripcion = :descripcion WHERE id = :id";

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
        : "La categoria tiene contenido asociado. Debe eliminar el contenido y las subcategorias asociadas a esta categoria para poder realizar el eliminado.";
  }

  public String eliminarSubCategoria(SubCategoria categoria) {
    SpringDbMgr springDbMgr = new SpringDbMgr();

    // Agrego los datos de la eliminación (nombreColumna/Valor)
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", categoria.getId());

    // Armar la sentencia de actualización debase de datos
    String query = "DELETE FROM SUBCATEGORIA WHERE id = :id";

    // Ejecutar la sentencia
    int result = 0;
    try {
      result = springDbMgr.executeDml(query, map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
    // de error.
    return (result == 1) ? "Eliminacion exitosa"
        : "La subcategoria tiene contenido asociado. Debe eliminar el contenido y las subcategorias asociadas a esta categoria para poder realizar el eliminado.";
  }

  public boolean esNombreValido(long id, String nombre) {

    // Consulta en base de datos el ultimo numero de ordenamiento
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", id);
    map.addValue("nombre", nombre);
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM SUBCATEGORIA WHERE Categoria_id = :id AND NOMBRE = :nombre",map);

    // Si existe al menos una categoria retorna el numero
    if (sqlRowSet.next()) {
      return false;
      // Si no existen categorias retorna el 0
    } else {
      return true;
    }
  }
  
    public boolean esNombreValidoParaActualizar(long id,long sub, String nombre) {

    // Consulta en base de datos el ultimo numero de ordenamiento
    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", id);
    map.addValue("nombre", nombre);
    map.addValue("sub", sub);
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM SUBCATEGORIA WHERE Categoria_id = :id AND NOMBRE = :nombre AND NOT id=:sub",map);

    // Si existe al menos una categoria retorna el numero
    if (sqlRowSet.next()) {
      return false;
      // Si no existen categorias retorna el 0
    } else {
      return true;
    }
  }
}
