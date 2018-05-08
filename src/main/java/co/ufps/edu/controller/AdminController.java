package co.ufps.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.ufps.edu.dao.LoginDao;
import co.ufps.edu.model.Login;
import co.ufps.edu.util.JwtUtil;

@Controller
public class AdminController {

	@Autowired
	private RedisTemplate<String, String> template;
	private JwtUtil jwtUtil = new JwtUtil();
	private LoginDao loginDao = new LoginDao();

	@GetMapping("/") // Base
	public String main() {
		return "index"; // Nombre del archivo jsp
	}

	@GetMapping("/admin") // Base
	public String index() {
		return "Administrador/Login"; // Nombre del archivo jsp
	}

	/**
	 * Método solicitado por los formularios de los archivos .jsp
	 * <p>
	 * Este metodo es usado en la etiqueta form de la siguiente manera:
	 * modelAttribute="login"
	 * 
	 * @return
	 */
	@ModelAttribute("login")
	public Login setUpUserForm() {
		return new Login();
	}
	
	@PostMapping("/autenticar")
	public String authenticateUser(@ModelAttribute("login") Login login, Model model, HttpServletRequest request) {

		if(!StringUtils.isEmpty(login.getCorreoInstitucional()) && !StringUtils.isEmpty(login.getContraseña())) {
			String resultado = loginDao.authenticate(login.getCorreoInstitucional(), login.getContraseña());
			
			if (!resultado.isEmpty()) {
				String jwt = jwtUtil.generateToken(resultado, login.getCorreoInstitucional());
				request.setAttribute("token", jwt);
				request.getSession().setAttribute("correo", login.getCorreoInstitucional());
				HttpSession session = request.getSession();
				template.opsForValue().set("SESSION:" + login.getCorreoInstitucional(), jwt);
				session.setAttribute("correo", login.getCorreoInstitucional());
				if (resultado.equals("admin")) {
					session.setAttribute("user", "Administrador");
					return "Administrador/indexAdmin";
				}
			}else {
				model.addAttribute("wrong", "Usuario o contraseña incorrectos.");	
			}
			return "Login";
		}else {
			model.addAttribute("wrong", "El usuario y la contraseña no pueden ser nulos.");	
			return "Login";
		}
	}
		
	public void validarSesion(String token, HttpServletRequest request) {
		int codigo = jwtUtil.parseToken(token);
		if (token == null || token.isEmpty() || codigo == 0
				|| template.opsForValue().get("SESSION:" + codigo) == null) {
			throw new RuntimeException("FALTA TOKEN");
		}
		request.setAttribute("token", token);
		request.getSession().setAttribute("codigo", codigo);

	}

	private void getLogOut(String token, HttpServletRequest request) {
		request.getSession().invalidate();
		int codigo = jwtUtil.parseToken(token);
		template.delete("SESSION:" + codigo);
	}	
	
}
