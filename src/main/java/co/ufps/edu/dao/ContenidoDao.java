package co.ufps.edu.dao;

import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.model.Categoria;
import co.ufps.edu.model.SubCategoria;

public class ContenidoDao {
  
  SpringDbMgr springDbMgr = new SpringDbMgr();

  /**
   * Metodo que consulta en base de datos todas las subcategorias existentes y las devuelve
   * ordenadamente
   * 
   * @return Una lista con todas las categorias
   */
  public List<SubCategoria> getContenidos() {

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
                                                  + "   FROM    CONTENIDO                                               "
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
  
  /*
   *  Método que obtiene la cantidad de contenidos registrados
   */
  public int getCntContenidos() {
	  	int cant = 0;
	    // Consulta para realizar en base de datos
	    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT COUNT(*) cantidad FROM CONTENIDO "); 
	    
	    if (sqlRowSet.next()) {
	    	cant = sqlRowSet.getInt("cantidad");
	    }
	    return cant;
  }

}
