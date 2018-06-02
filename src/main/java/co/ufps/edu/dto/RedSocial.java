package co.ufps.edu.dto;

import org.springframework.util.StringUtils;

public class RedSocial {

	private long id;
	private String nombre;
	private String url;
	private String tipo;
	
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
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isValidoParaRegistrar() {
	    return (!StringUtils.isEmpty(this.nombre) && !StringUtils.isEmpty(this.url) && !StringUtils.isEmpty(this.tipo) );
	}
	
	@Override
	public String toString() {
		return "RedSocial [id=" + id + ", nombre=" + nombre + ", url=" + url + ", tipo=" + tipo + "]";
	}
	
	
	
}
