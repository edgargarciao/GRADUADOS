package co.ufps.edu.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Types;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
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
    SqlRowSet sqlRowSet = springDbMgr.executeQuery(" SELECT * FROM LOGO WHERE tipo = :tipo", map);

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

  public String insertarLogo(Logo logo) {

    // Agrego los datos del registro (nombreColumna/Valor)

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("tipo", logo.getTipo());
    try {
      map.addValue("contenido",
          new SqlLobValue(new ByteArrayInputStream(logo.getContenido().getBytes()),
              logo.getContenido().getBytes().length, new DefaultLobHandler()),
          Types.BLOB);
    } catch (IOException e) {
      new Exception();
    }

    // Armar la sentencia de actualización debase de datos
    String query = "INSERT INTO LOGO(tipo,contenido) VALUES(:tipo,:contenido)";

    // Ejecutar la sentencia
    int result = 0;
    try {
      result = springDbMgr.executeDml(query, map);
    } catch (Exception e) {
      new Exception();
    }
    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
    // de error.
    return (result == 1) ? "Registro exitoso"
        : "Error al registrar el logo. Contacte al administrador del sistema.";
  }

  public String actualizarLogo(Logo logo) {
    // Agrego los datos del registro (nombreColumna/Valor)

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("id", logo.getId());
    map.addValue("tipo", logo.getTipo());
    String sqlImagen = "";

    try {
      map.addValue("contenido",
          new SqlLobValue(new ByteArrayInputStream(logo.getContenido().getBytes()),
              logo.getContenido().getBytes().length, new DefaultLobHandler()),
          Types.BLOB);
    } catch (IOException e) {
      new Exception();
    }

    // Armar la sentencia de actualización debase de datos
    String query =
        "UPDATE LOGO SET tipo = :tipo , contenido = :contenido  WHERE id = :id";

    // Ejecutar la sentencia
    int result = 0;
    try {
      result = springDbMgr.executeDml(query, map);
    } catch (Exception e) {
      new Exception();
    }
    // Si hubieron filas afectadas es por que si hubo registro, en caso contrario muestra el mensaje
    // de error.
    return (result == 1) ? "Actualizacion exitosa"
        : "Error al actualizar el logo. Contacte al administrador del sistema.";
  }

}
