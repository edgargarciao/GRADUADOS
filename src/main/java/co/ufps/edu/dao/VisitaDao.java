package co.ufps.edu.dao;

import java.util.Calendar;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import co.ufps.edu.bd.SpringDbMgr;

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
    String query = "INSERT INTO visita(ip,fecha) VALUES(:ip,:fecha)";

    // Ejecutar la sentencia
    int result = 0;
    try {
      result = springDbMgr.executeDml(query, map);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
