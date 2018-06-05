package co.ufps.edu.dto;

import java.util.Date;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Novedad {

  private long id;
  private String nombre;
  private Date fecha;
  //private MultipartFile imagen;
  //private String imBase64image;

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

  /*public MultipartFile getImagen() {
    return imagen;
  }

  public void setImagen(MultipartFile imagen) {
    this.imagen = imagen;
  }

  public String getImBase64image() {
    return imBase64image;
  }

  public void setImBase64image(String imBase64image) {
    this.imBase64image = imBase64image;
  }*/

}
