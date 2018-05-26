package co.ufps.edu.model;

import java.sql.Date;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Actividad {

  private long id;
  private String nombre;
  private String lugar;
  private Date fechaInicial;
  private Date fechaFinal;
  private MultipartFile imagen;
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

  public boolean isValidoParaRegistrar() {
    return (!StringUtils.isEmpty(this.nombre) && !StringUtils.isEmpty(this.lugar) && !StringUtils.isEmpty(this.fechaInicial.toString()) && !StringUtils.isEmpty(this.fechaFinal.toString()));
  }

  public String getLugar() {
    return lugar;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }

  public Date getFechaInicial() {
    return fechaInicial;
  }

  public void setFechaInicial(Date fechaInicial) {
    this.fechaInicial = fechaInicial;
  }

  public Date getFechaFinal() {
    return fechaFinal;
  }

  public void setFechaFinal(Date fechaFinal) {
    this.fechaFinal = fechaFinal;
  }

  public MultipartFile getImagen() {
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
  }

  @Override
  public String toString() {
    return "Actividad [id=" + id + ", nombre=" + nombre + ", lugar=" + lugar + ", fechaInicial="
        + fechaInicial + ", fechaFinal=" + fechaFinal + "]";
  }
  
  public boolean isValidoParaActualizar() {
    return (!StringUtils.isEmpty(this.nombre) && !StringUtils.isEmpty(this.lugar) && !StringUtils.isEmpty(fechaInicial.toString()) && !StringUtils.isEmpty(fechaFinal.toString()) && !StringUtils.isEmpty(imBase64image));
  }

}
