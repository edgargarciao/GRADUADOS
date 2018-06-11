package co.ufps.edu.dto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Types;
import java.util.Calendar;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import co.ufps.edu.bd.SpringDbMgr;
import co.ufps.edu.util.ImagenUtil;

public class VisitaDao {
  private SpringDbMgr springDbMgr;

  public VisitaDao() {
    springDbMgr = new SpringDbMgr();
  }

  public void guardarVisita(String ip) {


    // Agrego los datos del registro (nombreColumna/Valor)

    MapSqlParameterSource map = new MapSqlParameterSource();
    map.addValue("ip", ip);
    map.addValue("fecha", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
   

    // Armar la sentencia de actualización debase de datos
    String query = "INSERT INTO VISITA(ip,fecha) VALUES(:ip,:fecha)";

    // Ejecutar la sentencia
    int result = 0;
    try {
      result = springDbMgr.executeDml(query, map);
    } catch (Exception e) {
      new Exception();
    }
  }

}
