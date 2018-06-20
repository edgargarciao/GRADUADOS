package co.ufps.edu.dto;

import org.springframework.util.StringUtils;

public class Usuario {

  private String contrase�aAntigua;
  private String contrase�aNueva;
  private String contrase�aNueva2;
  
  public String getContrase�aAntigua() {
    return contrase�aAntigua;
  }
  public void setContrase�aAntigua(String contrase�aAntigua) {
    this.contrase�aAntigua = contrase�aAntigua;
  }
  public String getContrase�aNueva() {
    return contrase�aNueva;
  }
  public void setContrase�aNueva(String contrase�aNueva) {
    this.contrase�aNueva = contrase�aNueva;
  }
  public String getContrase�aNueva2() {
    return contrase�aNueva2;
  }
  public void setContrase�aNueva2(String contrase�aNueva2) {
    this.contrase�aNueva2 = contrase�aNueva2;
  }
  
  public boolean isValidoParaRegistrar() {
    return (!StringUtils.isEmpty(this.contrase�aAntigua) && !StringUtils.isEmpty(this.contrase�aNueva) && !StringUtils.isEmpty(this.contrase�aNueva2));
  }
  
  public boolean seRepiten() {
    return this.contrase�aNueva.equals(this.contrase�aNueva2);
  }

}
