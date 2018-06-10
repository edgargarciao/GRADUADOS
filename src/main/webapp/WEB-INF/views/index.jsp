
<%@ page language="java"
	contentType="text/html; application/octet-stream;charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es">
<!--<![endif]-->
<head>
<title>Programa de Ingeniería de Sistemas - UFPS - Cúcuta</title>
<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon -->
<link href="resources/rsc/img/favicon.ico" rel="Shortcut icon">
<!-- Web Fonts -->
<link rel='stylesheet' type='text/css'	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>
<link type="text/css" rel="stylesheet"	href="resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/ie8.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/blocks.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/plugins.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/style.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/app.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/shop.plugins.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/shop.blocks.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/style-switcher.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/shop.style.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/header-v6.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/header-v8.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/header.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/footer-v1.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/animate.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/line-icons.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/owl.carousel.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/horizontal-parallax.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/layerslider.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/ured.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/jquery.mCustomScrollbar.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/sky-forms.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/custom-sky-forms.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/profile.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/brand-buttons.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/brand-buttons-inversed.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/hover.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/custom-hover-effects.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/custom.min.css">
<link type="text/css" rel="stylesheet"	href="resources/css/pgwslider.min.css">
<link type="text/css" rel="stylesheet" href="resources/css/ufps.css">
</head>
<body class="header-fixed boxed-layout" style="position: relative; min-height: 100%; top: 0px;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<!--Contenido-->
	<div class="wrapper">
		<div id="menu-principal"
			class="header-v6 header-white-transparent header-sticky"
			style="position: relative;">
			<div id="barra-superior" class="header-v8">
				<!-- Topbar blog -->
				<div class="blog-topbar">
					<div class="topbar-search-block">
						<div class="container">
							<form name="buscar" method="get" action="index.php">
								<input type="hidden" name="modulo" value="buscar"> <input
									type="text" name="buscar" class="form-control"
									placeholder="Buscar...">
								<div class="search-close">
									<i class="icon-close"></i>
								</div>
							</form>
						</div>
					</div>
					<div class="container">
						<div class="row">
							<div class="col-sm-7 col-xs-7">
								<div class="topbar-toggler"
									style="font-size: 10px; color: #eee; letter-spacing: 1px; text-transform: uppercase;">
									<span class="fa fa-angle-down"></span> PERFILES
								</div>
								<ul class="topbar-list topbar-menu">
									<li><a
										href="https://ww2.ufps.edu.co/universidad/perfiles/aspirantes/952"
										target="_blank"><i class="fa fa-users"></i>Aspirantes</a></li>
									<li><a
										href="https://ww2.ufps.edu.co/universidad/perfiles/estudiantes/953"
										target="_blank"><i class="fa fa-user"></i>Estudiantes</a></li>
									<li><a
										href="https://ww2.ufps.edu.co/universidad/perfiles/egresados/954"
										target="_blank"><i class="fa fa-graduation-cap"></i>Graduados</a></li>
									<li><a href="https://docentes.ufps.edu.co/"
										target="_blank"><i class="fa fa-user-circle"></i>Docentes</a></li>
									<li><a href=""><i class="fa fa-briefcase"></i>Empresarios</a></li>
									<li class="cd-log_reg hidden-sm hidden-md hidden-lg"><strong><a
											class="cd-signup" href="javascript:void(0);">Lenguaje</a></strong>
										<ul class="topbar-dropdown">
											<li><a href="#">Ingl�s</a></li>
											<li><a href="#">Espa�ol</a></li>
										</ul></li>
									<li class="cd-log_reg hidden-sm hidden-md hidden-lg"><strong><a
											class="cd-signin" href="http://ingsistemas.ufps.edu.co"><i
												class="fa fa-reply"></i> Versi�n Anterior</a></strong></li>
								</ul>
							</div>
							<div class="col-sm-5 col-xs-5 clearfix">
								<i class="fa fa-search search-btn pull-right"></i>
								<ul
									class="topbar-list topbar-log_reg pull-right visible-sm-block visible-md-block visible-lg-block">
									<li class="cd-log_reg home" style="padding: 0px 12px;">
										<div id="google_translate_element"></div>
										<script type="text/javascript">
											function googleTranslateElementInit() {
												new google.translate.TranslateElement(
														{
															pageLanguage : 'es',
															includedLanguages : 'en,fr,it',
															layout : google.translate.TranslateElement.InlineLayout.SIMPLE,
															autoDisplay : false
														},
														'google_translate_element');
											}
										</script>
										<script type="text/javascript"
											src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
									</li>
									<li class="cd-log_reg home"><a
										href="http://200.93.148.29/ingsistemas_viejo/"><i
											class="fa fa-reply"></i> Versi�n Anterior</a></li>
								</ul>
							</div>
						</div>
						<!--/end row-->
					</div>
					<!--/end container-->
				</div>
				<!-- End Topbar blog -->
			</div>
			<div class="header-v8 img-logo-superior"
				style="background-color: #aa1916;">
				<!--=== Parallax Quote ===-->
				<div class="parallax-quote parallaxBg" style="padding: 30px 30px;">
					<div class="parallax-quote-in" style="padding: 0px;">
						<div class="row">
							<div class="col-md-4 col-sm-4 col-xs-5">
								<a href="${contextPath}/"> <img id="logo-header"
									src="${logoHorizontal.imBase64image}"
									alt=""
									style="max-height: 180px;">
								</a>
							</div>
							<div class="col-md-8 col-ms-8 col-xs-8">
								<a href="http://www.ufps.edu.co/"><img class="header-banner"
									src="resources/rsc/img/logo_ufps.png"
									style="max-height: 160px;"
									alt="Escudo de la Universidad Francisco de Paula Santander"></a>
							</div>
						</div>
					</div>
				</div>
				<!--=== End Parallax Quote ===-->
			</div>
			<!--/end header-v8-->
			<div class="menu-responsive">
				<!-- Logo -->
				<a class="logo logo-responsive" href="index.php"
					style="margin-left: 5px;"> <img
					src="resources/rsc/img/horizontal_logo_ingsistemas_pequeno.png"
					alt="Logo">
				</a>
				<!-- End Logo -->
				<!-- Toggle get grouped for better mobile display -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-responsive-collapse">
					<span class="sr-only">Cambiar navegaci&oacute;n</span> <span
						class="fa fa-bars"></span>
				</button>
				<!-- End Toggle -->
			</div>
			<!-- Navbar -->
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div
				class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
				<div class="containermenu">
					<ul class="nav navbar-nav" style="float: left;">
					
						<c:forEach var="categoria" items="${categorias}">
							<li class="dropdown">
								<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">${categoria.nombre}</a>
								<ul class="dropdown-menu">
									<c:forEach var="subcategoria" items="${categoria.subcategorias}">
										<li>
											<c:choose>
												<c:when test="${not empty subcategoria.contenido}">
													<c:choose>
														<c:when test="${subcategoria.contenido.tipoContenido.id == 2}">
															<a href="${subcategoria.contenido.contenido}">
																${subcategoria.nombre}
															</a>																	
														</c:when>
														<c:otherwise>
															<a href="${contextPath}/servicios/componente?id=${subcategoria.id}&componente=subcategoria">
																${subcategoria.nombre}
															</a>
														</c:otherwise>		
													</c:choose>		
												    
												</c:when>
												<c:otherwise>
												    <a>
														${subcategoria.nombre}
													</a>
											 	</c:otherwise>
											</c:choose>
																		
											
										</li>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>			         
					</ul>
				</div>
			</div>
			<!--/navbar-collapse-->
		</div>
		<!--header-v6-->
		<ul class="pgwSlider">
			
			<c:forEach var="noticia" items="${noticias}">
				<li>
					<a href="${contextPath}/servicios/componente?id=${noticia.id}&componente=noticia">
					<img
					src="${noticia.im1Base64image}" alt="">
					</a>
					<span	style="font-family: inherit; font-size: 0.95em; font-weight: normal; color: #111; cursor: auto;">
						${noticia.descripcion}
					</span>
				</li>
			</c:forEach>

		</ul>
		<!--Entra a html_noticias-->
		<!--Antes de acceder a la base de datos-->
		<!--Despues de acceder a la base de datos-->
		<div style="clear: both;"></div>
		<div style="background-color: #e8e8e8;">
			<div class="container content-prin profile">
				<div class="row margin-top-10">
					<div
						class="headline-center-v2 headline-center-v2-dark margin-bottom-10">
						<h1 class="shop-h1" style="font-size: 30px;">
							<b>Novedades</b>
						</h1>
						<span class="bordered-icon"><i class="fa fa-newspaper-o"
							aria-hidden="true"></i></span>
					</div>
					<div class="col-md-12">
						<div class="row equal-height-columns margin-bottom-10">
							<div class="container">
								<ul class="row block-grid-v2">									
									<c:forEach var="novedad" items="${novedades}">										
										<li class="col-md-3 col-sm-6 md-margin-bottom-30" style="padding-left: 14px;">										
											<div class="easy-block-v1">
												<img onclick="openModalImage('modal148')" src=" ${novedad.imBase64image}" alt=""
													style="cursor: zoom-in;">
												<div class="easy-block-v1-badge rgba-red"> ${novedad.fechaEnFormato} </div>
											</div>										
											<div
												class="block-grid-v2-info rounded-bottom  bloques_eventos">
												<h5>
													<b>
														<a href="${contextPath}/servicios/componente?id=${novedad.id}&componente=novedad">
															${novedad.nombre}													
														</a>
													</b>
												</h5>											
											</div>
										</li>
									</c:forEach>
								</ul>
								<a href="./index.php?modulo=principal"
									class="btn-u btn-u-sm pull-right tooltips"
									data-toggle="tooltip" data-placement="left"
									data-original-title="Ver m&aacute;s novedades">Ver más<i
									class="fa fa-chevron-circle-right" aria-hidden="true"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- FIN EVENTOS -->
		<div style="background-color: #b43432; padding-bottom: 10px;">
			<div class="container content-prin profile">
				<div class="row margin-bottom-10 margin-top-10">
					<div class="headline-center-v2 margin-bottom-10">
						<h1 style="font-size: 30px; color: #fff;">
							<b>Pr&oacute;ximas actividades</b>
						</h1>
						<span class="bordered-icon"><i class="fa fa-calendar-o"
							aria-hidden="true"></i></span>
					</div>
					
					<c:forEach var="actividad" items="${actividades}">																					
						<div class="col-sm-3">
							<div class="service-block-v1 md-margin-bottom-50"
								style="background: #fff; border-top: 5px solid #f1c40f;">
								<i
									class="icon-custom icon-lg rounded-x icon-color-yellow icon-line fa fa-bookmark"
									style="background: #fff;"></i>
								<h5 class="title-v3-bg text-uppercase">
									<b></b>
								</h5>
								<p>Actividad: ${actividad.nombre}</p>
								<p>Lugar: ${actividad.lugar}</p>
								<p>Fecha: ${actividad.fechaInicial}</p>
							</div>
						</div>
					</c:forEach>					
	
				</div>
				
				
				<!--/row-->
				<a href="./index.php?modulo=calendarios"
					class="btn-u btn-u-sm pull-right tooltips" data-toggle="tooltip"
					data-placement="left"
					data-original-title="Ingresar a Calendario de Eventos">Ver más
					<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
				</a>
			</div>
		</div>
		<!-- FIN DESTACADOS -->
		<div style="clear: both;"></div>
		<div style="background-color: #d4d4d4;">
			<div class="container content-prin profile">
				<div class="row margin-top-10">
					<div
						class="headline-center-v2 headline-center-v2-dark margin-bottom-10">
						<h1 class="shop-h1" style="font-size: 30px; color: #444;">
							<b>Galer&iacute;as</b>
						</h1>
						<span class="bordered-icon" style="color: #444;"><i
							class="fa fa-newspaper-o" aria-hidden="true"></i></span>
					</div>
					<div class="col-md-12">
						<div class="row equal-height-columns margin-bottom-10">
							<div class="container">
								<ul class="row block-grid-v2">
									<li class="col-md-2 col-sm-3 col-xs-6 md-margin-bottom-30"
										style="padding-left: 14px;">
										<div class="easy-block-v1">
											<a href="./index.php?modulo=detallegaleria&idgale=22"> <img
												src="resources/rsc/img/20171202_003306.jpg" alt="">
											</a>
										</div>
										<div
											class="block-grid-v2-info rounded-bottom  bloques_eventos">
											<h5>
												<b><a href="index.php?modulo=detallegaleria&idgale=22">Cena
														de Graduados 2017</a></b>
											</h5>
										</div>
									</li>
									<li class="col-md-2 col-sm-3 col-xs-6 md-margin-bottom-30"
										style="padding-left: 14px;">
										<div class="easy-block-v1">
											<a href="./index.php?modulo=detallegaleria&idgale=21"> <img
												src="resources/rsc/img/WhatsApp Image 2017-12-07 at 17.20.46.jpeg"
												alt="">
											</a>
										</div>
										<div
											class="block-grid-v2-info rounded-bottom  bloques_eventos">
											<h5>
												<b><a href="index.php?modulo=detallegaleria&idgale=21">Novena
														de Aguinaldos 2017</a></b>
											</h5>
										</div>
									</li>
									<li class="col-md-2 col-sm-3 col-xs-6 md-margin-bottom-30"
										style="padding-left: 14px;">
										<div class="easy-block-v1">
											<a href="./index.php?modulo=detallegaleria&idgale=20"> <img
												src="resources/rsc/img/encuentro1.png" alt="">
											</a>
										</div>
										<div
											class="block-grid-v2-info rounded-bottom  bloques_eventos">
											<h5>
												<b><a href="index.php?modulo=detallegaleria&idgale=20">2do
														Encuentro Acad�mico-Investigaci�n 2017</a></b>
											</h5>
										</div>
									</li>
									<li class="col-md-2 col-sm-3 col-xs-6 md-margin-bottom-30"
										style="padding-left: 14px;">
										<div class="easy-block-v1">
											<a href="./index.php?modulo=detallegaleria&idgale=18"> <img
												src="resources/rsc/img/WhatsApp Image 2017-11-30 at 18.44.43.jpeg"
												alt="">
											</a>
										</div>
										<div
											class="block-grid-v2-info rounded-bottom  bloques_eventos">
											<h5>
												<b><a href="index.php?modulo=detallegaleria&idgale=18">D�a
														Internacional de la Seguridad Inform�tica</a></b>
											</h5>
										</div>
									</li>
									<li class="col-md-2 col-sm-3 col-xs-6 md-margin-bottom-30"
										style="padding-left: 14px;">
										<div class="easy-block-v1">
											<a href="./index.php?modulo=detallegaleria&idgale=19"> <img
												src="resources/rsc/img/feriabanner.jpg" alt="">
											</a>
										</div>
										<div
											class="block-grid-v2-info rounded-bottom  bloques_eventos">
											<h5>
												<b><a href="index.php?modulo=detallegaleria&idgale=19">Feria
														de Proyectos de Aula 2.0 29/11/2017</a></b>
											</h5>
										</div>
									</li>
									<li class="col-md-2 col-sm-3 col-xs-6 md-margin-bottom-30"
										style="padding-left: 14px;">
										<div class="easy-block-v1">
											<a href="./index.php?modulo=detallegaleria&idgale=17"> <img
												src="resources/rsc/img/20171026_192324 dos.jpg" alt="">
											</a>
										</div>
										<div
											class="block-grid-v2-info rounded-bottom  bloques_eventos">
											<h5>
												<b><a href="index.php?modulo=detallegaleria&idgale=17">Visita
														de Pares Acad�micos </a></b>
											</h5>
										</div>
									</li>
								</ul>
								<a href="./index.php?modulo=galerias"
									class="btn-u btn-u-sm pull-right tooltips"
									data-toggle="tooltip" data-placement="left"
									data-original-title="Ver m&aacute;s galer&iacute;as">Ver
									m�s <i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- FIN EVENTOS -->
		<!-- ICONOS REDES SOCIALES -->
		
		<!-- FIN ICONOS REDES SOCIALES -->
		<div id="modal148" class="ufps-image-modal">
			<span class="ufps-image-modal-close">&times;</span> <img
				class="ufps-image-modal-content" src="resources/rsc/img/banne1.jpg"
				alt="">
			<div class="ufps-image-modal-caption">Curso de Profundizaci�n
				de Desarrollo de Software</div>
		</div>
		<div id="modal146" class="ufps-image-modal">
			<span class="ufps-image-modal-close">&times;</span> <img
				class="ufps-image-modal-content"
				src="resources/rsc/img/WhatsApp Image 2017-11-28 at 21.59.17 (1).jpeg"
				alt="">
			<div class="ufps-image-modal-caption">D�a Internacional de la
				Seguridad Inform�tica</div>
		</div>
		<div id="modal144" class="ufps-image-modal">
			<span class="ufps-image-modal-close">&times;</span> <img
				class="ufps-image-modal-content"
				src="resources/rsc/img/20171111_085650.jpg" alt="">
			<div class="ufps-image-modal-caption">Marat�n Latinoamericana
				de Programaci�n 2017 ICPC / ACIS - 2017-11-15</div>
		</div>
		<div id="modal143" class="ufps-image-modal">
			<span class="ufps-image-modal-close">&times;</span> <img
				class="ufps-image-modal-content"
				src="resources/rsc/img/ponencia semilleros2.png" alt="">
			<div class="ufps-image-modal-caption">Participaci�n de
				Semilleros Ingenier�a de sistemas UFPS - 2017/10/23 Bucaramanga</div>
		</div>
	</div>
	<!--wrapper-->
	<div class="footer-v1 off-container">
		<div class="footer">
			<div class="container">
				<div class="row">
					<!-- About -->
					<div class="col-md-3 col-sm-4 md-margin-bottom-40">
						<div class="footer-main">
							<a href="${contextPath}/"><img id="logo-footer" class="img-responsive"
								src="${logoVertical.imBase64image}"
								alt="Logo Pie de Página"></a>
						</div>
					</div>
					<!--/col-md-3-->
					<!-- End About -->
					<!-- Latest -->
					<div class="col-md-3 col-sm-4 md-margin-bottom-40">
						<div class="posts">
							<div class="headline" style="border-bottom: #272727;">
								<h2>Visitantes</h2>
							</div>
							<ul class="list-unstyled latest-list">
								<li style="color: #fff">Hoy: 55</li>
								<li style="color: #fff">&Uacute;ltimo mes: 4.350</li>
								<li style="color: #fff">Desde el principio: 80.012</li>
							</ul>
						</div>
					</div>
					<!--/col-md-3-->
					<!-- End Latest -->
					<!-- Link List -->
					<div class="col-md-3 col-sm-4  md-margin-bottom-40">
						<div class="headline" style="border-bottom: #272727;">
							<h2>Enlaces de Interés</h2>
						</div>
						<ul class="list-unstyled latest-list">
							<c:forEach var="enlace" items="${enlaces}">		
								<li>
									<a href="${enlace.url}" target="_blank">
										${enlace.nombre}
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!--/col-md-3-->
					<!-- End Link List -->
					<!-- Address -->
					<div class="col-md-3 col-sm-4  map-img md-margin-bottom-40">
						<div class="headline" style="border-bottom: #272727;">
							<h2>Contactos</h2>
						</div>
						<address class="md-margin-bottom-40">
						
							<c:forEach var="contacto" items="${contactos}">																					
								${contacto.nombre}
								<br>
							</c:forEach>

						</address>
					</div>
					<!--/col-md-3-->
					<!-- End Address -->
				</div>
			</div>
		</div>
		
		
		<!--/footer-->
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<p>
							2018� All Rights Reserved. Desarrollado por: <a href="#">VAVM
								- Departamento de Sistemas</a>
						</p>
					</div>
					<!-- Social Links -->
					<div class="col-md-4">
						<ul class="list-inline dark-social pull-right space-bottom-0">
							<c:forEach var="red" items="${redes}">																					
								<li>
									<a data-placement="top" data-toggle="tooltip" class="tooltips" data-original-title="${red.nombre}"
										href="${red.url}">
											<i class="fa ${red.logo}" style="color: #fff;"></i>
									</a>
								</li>
								
							</c:forEach>
							
							
						</ul>
					</div>
					<!-- End Social Links -->
				</div>
			</div>
		</div>
		<!--/copyright-->
	</div>

	<div class="owl-clients-v1"
		style="background-color: #EEE; padding: 5px;">
		<div class="item">
			<a href="http://www.mineducacion.gov.co/" target="_blank"><img
				src="resources/rsc/img/mineducacion.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.gobiernoenlinea.gov.co/" target="_blank"><img
				src="resources/rsc/img/gobiernoenlinea.png" class="hover-shadow"
				alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.contraloriagen.gov.co/" target="_blank"><img
				src="resources/rsc/img/contraloria.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.procuraduria.gov.co/" target="_blank"><img
				src="resources/rsc/img/procuraduriageneraldelanacion.png"
				class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.urnadecristal.gov.co/" target="_blank"><img
				src="resources/rsc/img/urnadecristal.png" class="hover-shadow"
				alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.icfes.gov.co/" target="_blank"><img
				src="resources/rsc/img/icfes.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.icetex.gov.co/" target="_blank"><img
				src="resources/rsc/img/icetex.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.universia.net.co/" target="_blank"><img
				src="resources/rsc/img/universia.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.colombiaaprende.edu.co/" target="_blank"><img
				src="resources/rsc/img/colombiaaprende.png" class="hover-shadow"
				alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.renata.edu.co/" target="_blank"><img
				src="resources/rsc/img/renata-logo.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.colciencias.gov.co/" target="_blank"><img
				src="resources/rsc/img/COLCIENCIAS.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a href="http://www.cna.gov.co/" target="_blank"><img
				src="resources/rsc/img/cna.png" class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a
				href="http://www.mineducacion.gov.co/sistemasdeinformacion/1735/propertyvalue-41698.html"
				target="_blank"><img src="resources/rsc/img/SACES.png"
				class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a
				href="http://www.mineducacion.gov.co/sistemasdeinformacion/1735/w3-propertyname-2672.html"
				target="_blank"><img src="resources/rsc/img/SNIES.png"
				class="hover-shadow" alt=""></a>
		</div>
		<div class="item">
			<a
				href="http://www.mineducacion.gov.co/sistemasdeinformacion/1735/w3-propertyname-2895.html"
				target="_blank"><img src="resources/rsc/img/SPADIES.png"
				class="hover-shadow" alt=""></a>
		</div>
	</div>
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-migrate.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/pgwslider.min.js"></script>
	<script type="text/javascript" src="resources/js/back-to-top.min.js"></script>
	<script type="text/javascript" src="resources/js/smoothScroll.min.js"></script>
	<script type="text/javascript" src="resources/js/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="resources/js/sequence.jquery-min.js"></script>
	<script type="text/javascript" src="resources/js/greensock.js"></script>
	<script type="text/javascript"
		src="resources/js/layerslider.transitions.js"></script>
	<script type="text/javascript"
		src="resources/js/layerslider.kreaturamedia.jquery.js"></script>
	<script type="text/javascript" src="resources/js/custom.min.js"></script>
	<script type="text/javascript" src="resources/js/app.min.js"></script>
	<script type="text/javascript" src="resources/js/owl-carousel.min.js"></script>
	<script type="text/javascript" src="resources/js/datepicker.min.js"></script>
	<script type="text/javascript" src="resources/js/validation.min.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript"
		src="resources/js/owl-recent-works.min.js"></script>
	<script type="text/javascript" src="resources/js/wow.min.js"></script>
	<script type="text/javascript" src="resources/js/ufps.js"></script>
	<!--[if lt IE 9]>
    <script src="resources/js/respond.js"></script>
    <script src="resources/js/html5shiv.js"></script>
    <script src="resources/js/placeholder-IE-fixes.js"></script>
  <![endif]-->
	<!-- For Background Image -->
	<!--<script type="text/javascript" src="resources/js/jquery.backstretch.min.js"></script>  -->
	<!--<script type="text/javascript">
          $.backstretch([
            "assets/rsc/img/bg/13.jpg"
          ])
        </script>  -->
	<!-- End For Background Image -->
</body>
</html>


