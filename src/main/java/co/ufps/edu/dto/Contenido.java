package co.ufps.edu.dto;

import org.springframework.util.StringUtils;

public class Contenido {

  private long id;
  private String nombre;
  private long tipoContenidoId;
  private String tipoContenido;
  private String tipoAsociacion;
  private long asosiacion;
  private String contenido;

  
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



  public String getTipoContenido() {
    return tipoContenido;
  }



  public void setTipoContenido(String tipoContenido) {
    this.tipoContenido = tipoContenido;
  }



  public String getTipoAsociacion() {
    return tipoAsociacion;
  }



  public void setTipoAsociacion(String tipoAsociacion) {
    this.tipoAsociacion = tipoAsociacion;
  }




  public long getAsosiacion() {
    return asosiacion;
  }



  public void setAsosiacion(long asosiacion) {
    this.asosiacion = asosiacion;
  }



  public String getContenido() {
    return contenido;
  }



  public void setContenido(String contenido) {
    this.contenido = contenido;
  }



  public boolean isValidoParaRegistrar() {
    return (!StringUtils.isEmpty(this.nombre) && !StringUtils.isEmpty(this.tipoContenido) && !StringUtils.isEmpty(this.tipoAsociacion) && !StringUtils.isEmpty(this.asosiacion));
  }



  @Override
  public String toString() {
    return "Contenido [id=" + id + ", nombre=" + nombre + ", tipoContenido=" + tipoContenido
        + ", tipoAsociacion=" + tipoAsociacion + ", asosiacion=" + asosiacion + ", contenido="
        + contenido + "]";
  }

  
  
}
