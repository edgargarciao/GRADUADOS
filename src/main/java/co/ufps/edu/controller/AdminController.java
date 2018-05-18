package co.ufps.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import co.ufps.edu.config.SessionManager;
import co.ufps.edu.dao.ActividadDao;
import co.ufps.edu.dao.CategoriaDao;
import co.ufps.edu.dao.ContactoDao;
import co.ufps.edu.dao.ContenidoDao;
import co.ufps.edu.dao.EnlaceDeInteresDao;
import co.ufps.edu.dao.GaleriaDao;
import co.ufps.edu.dao.LoginDao;
import co.ufps.edu.dao.LogoDao;
import co.ufps.edu.dao.NoticiaDao;
import co.ufps.edu.dao.NovedadDao;
import co.ufps.edu.dao.RedSocialDao;
import co.ufps.edu.dao.SubCategoriaDao;
import co.ufps.edu.model.Login;
import co.ufps.edu.util.JwtUtil;

@Controller
public class AdminController {

	@Autowired
	private SessionManager sessionManager;
	
	private JwtUtil jwtUtil;
	private LoginDao loginDao;
	private CategoriaDao categoriaDao;
	private SubCategoriaDao subCategoriaDao;
	private	ContenidoDao contenidoDao;
	private NoticiaDao	noticiaDao;
	private ActividadDao actividadDao;
	private NovedadDao novedadDao;
	private LogoDao logoDao;
	private EnlaceDeInteresDao enlaceDeInteresDao;
	private GaleriaDao galeriaDao;
	private ContactoDao contactoDao;
	private RedSocialDao redSocialDao;

	public AdminController() {
	  jwtUtil = new JwtUtil();
	  loginDao = new LoginDao();
	  categoriaDao = new CategoriaDao();
	  subCategoriaDao = new SubCategoriaDao();
	  contenidoDao = new ContenidoDao();
	  noticiaDao = new NoticiaDao();
	  actividadDao = new ActividadDao();
	  novedadDao = new NovedadDao();
	  logoDao = new LogoDao();
	  enlaceDeInteresDao = new EnlaceDeInteresDao();
	  galeriaDao = new GaleriaDao();
	  contactoDao = new ContactoDao();
	  redSocialDao = new RedSocialDao();
    }
	
	@GetMapping("/") // Base
	public String main(Model model) {
	    model.addAttribute("categorias",categoriaDao.getCategorias());
	    
		return "index"; // Nombre del archivo jsp
	}

	@GetMapping("/admin") // Base
	public String index() {
		return "Administrador/Login"; // Nombre del archivo jsp
	}

	@GetMapping("/indexAdmin") // Base
	public String indexAdmin(Model model) {
		this.getCantidadRegistros(model);
		return "Administrador/indexAdmin"; // Nombre del archivo jsp
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
	
	/**
	 * Método para autenticar al usuario al usuario.
	 * @param login Objeto con los datos de autenticacion
	 * @param model Clase para enviar datos desde los servicios a los archivos .jsp
	 * @param request Objeto con los datos de sesion que por el instante es nulo.
	 * @return La pagina a donde fue redireccionado.
	 */
	@PostMapping("/autenticar")
	public String authenticateUser(@ModelAttribute("login") Login login, Model model, HttpServletRequest request) {

		/*
		 * Consulto si los datos no vienen nulos
		 */
		
		if(!StringUtils.isEmpty(login.getCorreoInstitucional()) && !StringUtils.isEmpty(login.getContraseña())) {
			// Consulto en base de datos si se encuentra ese correo y esa contraseña
			String resultado = loginDao.authenticate(login.getCorreoInstitucional(), login.getContraseña());
			
			// Si el resultado no es vacio es por que si existe ese correo y esa contraseña
			if (!resultado.isEmpty()) {
				
				// Creo un Json Web Token para validar si la sesión esta activa
				String jwt = jwtUtil.generateToken(resultado, login.getCorreoInstitucional());
				
				// Guardo el correo del usuaroo como atributo de sesión
				request.getSession().setAttribute("correo", login.getCorreoInstitucional());
				
				// Guardo el JWT como atributo de sesión
				request.getSession().setAttribute("token", jwt);
				
				// Guardo el tipo de usuario en caso dado que deseen manejar roles
				request.getSession().setAttribute("user", "Administrador");
				
				// Guarda la sesion en el manejador de sesiones
				sessionManager.guardarSession("SESSION:" + login.getCorreoInstitucional(), jwt);
				
				this.getCantidadRegistros(model);
				
				// Redirijo al index debido a que el usuario ya fue autenticado con exito
				return "Administrador/indexAdmin";
				
			}else {
				
				/**
				 * Guardo en una variable el mensaje de error indicando que el usuario o la
				 * contraseña fueron incorrectos debido a que no se encuentran en la base de datos
				 * y asi pueda ser entendida  por los archivos .JSP 
				 */
				model.addAttribute("wrong", "Usuario o contraseña incorrectos.");	
			}
			// Redirecciono al login debido a que la autenticación fue incorrecta
			return "Administrador/Login";
		}else {
			/**
			 * Guardo en una variable el mensaje de error indicando que el usuario o la
			 * contraseña son nulos siendo estos datos son obligatorios,
			 * y asi pueda ser entendida  por los archivos .JSP 
			 */
			model.addAttribute("wrong", "El usuario y la contraseña no pueden ser nulos.");	
			// Redirecciono al login debido a que la autenticación fue incorrecta
			return "Administrador/Login";
		}
	}

	@GetMapping("/logout")
	private String getLogOut(String token, HttpServletRequest request) {
		request.getSession().invalidate();
		String codigo = jwtUtil.parseToken(token);
		sessionManager.eliminarSesion("SESSION:" + codigo);
		return "Administrador/Login"; // Nombre del archivo jsp
	}	
	
	private void getCantidadRegistros(Model model){
		model.addAttribute("catidadCat",this.categoriaDao.getCntCategorias());
		model.addAttribute("catidadSubCat",this.subCategoriaDao.getCntSubCategorias());
		model.addAttribute("catidadCon",this.contenidoDao.getCntContenidos());
		model.addAttribute("catidadNot",this.noticiaDao.getCntNoticias());
		model.addAttribute("catidadAct",this.actividadDao.getCntActividades());
		model.addAttribute("catidadNov",this.novedadDao.getCntNovedades());
		model.addAttribute("catidadLog",this.logoDao.getCntLogos());
		model.addAttribute("catidadEnl",this.enlaceDeInteresDao.getCntEnlacesDeInteres());
		model.addAttribute("catidadGal",this.galeriaDao.getCntGalerias());
		model.addAttribute("catidadCon",this.contactoDao.getCntContactos());
		model.addAttribute("catidadRed",this.redSocialDao.getCntRedesSociales());
	}

}
