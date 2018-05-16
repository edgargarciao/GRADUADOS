package co.ufps.edu.model;

import org.springframework.util.StringUtils;

public class Categoria {

  private long id;
  private String nombre;
  private String descripcion;
  private int orden;

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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getOrden() {
    return orden;
  }

  public void setOrden(int orden) {
    this.orden = orden;
  }

  public boolean isValidoParaRegistrar() {
    return (!StringUtils.isEmpty(this.nombre) && !StringUtils.isEmpty(this.descripcion));
  }

  @Override
  public String toString() {
    return "Categoria [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
        + ", orden=" + orden + "]";
  }
  
  

}
