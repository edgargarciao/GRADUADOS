package co.ufps.edu.dto;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class RedSocial {

	private long id;
	private String nombre;
	private String url;
	private MultipartFile contenido;
	private String imBase64image;
	
	
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
	
	
	public String getImBase64image() {
		return imBase64image;
	}

	public void setImBase64image(String imBase64image) {
		this.imBase64image = imBase64image;
	}

	public MultipartFile getContenido() {
		return contenido;
	}

	public void setContenido(MultipartFile contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "RedSocial [id=" + id + ", nombre=" + nombre + ", url=" + url + "]";
	}
	
	public boolean isValidoParaRegistrar() {
	    return (!StringUtils.isEmpty(this.nombre)
	        && !StringUtils.isEmpty(this.url));
	  }
	
}
