package co.ufps.edu.dao;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import co.ufps.edu.bd.SpringDbMgr;

public class LogoDao {

	SpringDbMgr springDbMgr = new SpringDbMgr();
	

	  /*
	   *  Método que obtiene la cantidad de logos registrados
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
}
