package co.ufps.edu.util;

import sun.misc.BASE64Encoder;

public class ImagenUtil {

  public String convertirImagen(byte[] imagen) {
    BASE64Encoder base64Encoder = new BASE64Encoder();
    StringBuilder imageString = new StringBuilder();
    imageString.append("data:image/png;base64,");
    imageString.append(base64Encoder.encode(imagen)); 
    return imageString.toString();
  }
}
