package co.ufps.edu.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

public class Novedad {

	private long id;
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isValidoParaRegistrar() {
	    return (!StringUtils.isEmpty(this.nombre) && !StringUtils.isEmpty(this.fecha));
	}
	
	@Override
	public String toString() {
		return "Novedad [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + "]";
	}
	
}
