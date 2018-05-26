package co.ufps.edu.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.model.Logo;
import co.ufps.edu.util.ImagenUtil;

public class LogoDao {

  private SpringDbMgr springDbMgr;
  private ImagenUtil imagenUtil;

  public LogoDao() {
    springDbMgr = new SpringDbMgr();
    imagenUtil = new ImagenUtil();
  }

  /*
   * Método que obtiene la cantidad de logos registrados
   */
  public int getCantidadLogos() {
    int cant = 0;
    // Consulta para realizar en base de datos
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT COUNT(*) cantidad FROM LOGO ");

    if (sqlRowSet.next()) {
      cant = sqlRowSet.getInt("cantidad");
    }
    return cant;
  }


  public Logo getLogo(String tipo) {
    // Lista para retornar con los datos
    Logo logo = new Logo();

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("tipo", tipo);
    
    // Consulta para realizar en base de datos
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM LOGO WHERE tipo = :tipo",map);

    // Recorre cada registro obtenido de base de datos
    if (sqlRowSet.next()) {

      logo.setId(sqlRowSet.getLong("id"));
      logo.setTipo(sqlRowSet.getString("tipo"));

      Object imagenBlob = (Object) sqlRowSet.getObject("contenido");
      logo.setImBase64image(imagenUtil.convertirImagen((byte[]) imagenBlob)); 
    }

    // Retorna todos las actividades desde base de datos
    return logo;
  }
}
