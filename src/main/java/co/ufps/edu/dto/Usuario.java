package co.ufps.edu.dto;

import org.springframework.util.StringUtils;

public class Usuario {

  private String contraseñaAntigua;
  private String contraseñaNueva;
  private String contraseñaNueva2;
  
  public String getContraseñaAntigua() {
    return contraseñaAntigua;
  }
  public void setContraseñaAntigua(String contraseñaAntigua) {
    this.contraseñaAntigua = contraseñaAntigua;
  }
  public String getContraseñaNueva() {
    return contraseñaNueva;
  }
  public void setContraseñaNueva(String contraseñaNueva) {
    this.contraseñaNueva = contraseñaNueva;
  }
  public String getContraseñaNueva2() {
    return contraseñaNueva2;
  }
  public void setContraseñaNueva2(String contraseñaNueva2) {
    this.contraseñaNueva2 = contraseñaNueva2;
  }
  
  public boolean isValidoParaRegistrar() {
    return (!StringUtils.isEmpty(this.contraseñaAntigua) && !StringUtils.isEmpty(this.contraseñaNueva) && !StringUtils.isEmpty(this.contraseñaNueva2));
  }
  
  public boolean seRepiten() {
    return this.contraseñaNueva.equals(this.contraseñaNueva2);
  }

}
