
<%@ page language="java"
	contentType="text/html; application/octet-stream;charset=UTF-8"
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
		<%@ include file="General/Css.jsp"%>
</head>
<body class="header-fixed boxed-layout" style="position: relative; min-height: 100%; top: 0px;">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<!--Contenido-->
	<div class="wrapper">

		<%@ include file="General/UpPanel.jsp"%>

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
									<c:set var="count" value="0" scope="page" />		
									<c:forEach var="novedad" items="${novedades}">										
										<li class="col-md-3 col-sm-6 md-margin-bottom-30" style="padding-left: 14px;">										
											<div class="easy-block-v1">
												<img onclick="openModalImage('modal${count}')" src=" ${novedad.imBase64image}" alt=""
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
										<c:set var="count" value="${count + 1}" scope="page"/>
									</c:forEach>
								</ul>
								<a href="${contextPath}/servicios/novedades"
									class="btn-u btn-u-sm pull-right tooltips"
									data-toggle="tooltip" data-placement="left"
									data-original-title="Ver m&aacute;s novedades">Ver m&aacute;s<i
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
							<a href="${contextPath}/servicios/componente?id=${actividad.id}&componente=proximaactividad">
								<div class="service-block-v1 md-margin-bottom-50" style="background: #fff; border-top: 5px solid #f1c40f;">									
									<i class="icon-custom icon-lg rounded-x icon-color-yellow icon-line fa fa-bookmark" style="background: #fff;"></i>
									<h5 class="title-v3-bg text-uppercase">
										Actividad: ${actividad.nombre}
										<b></b>
									</h5>
									
									<p>Lugar: ${actividad.lugar}</p>
									<p>Fecha: ${actividad.fechaInicial}</p>
								</div>
							</a>
						</div>
					</c:forEach>					
				</div>
				<!--/row-->
				<a href="${contextPath}/servicios/eventos"
					class="btn-u btn-u-sm pull-right tooltips" data-toggle="tooltip"
					data-placement="left"
					data-original-title="Ingresar a Calendario de Eventos">Ver m&aacute;s
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
								
									<c:forEach var="galeria" items="${galerias}">																					
										<li class="col-md-2 col-sm-3 col-xs-6 md-margin-bottom-30" style="padding-left: 14px;">
											<div class="easy-block-v1">
												<a href="${contextPath}/servicios/galeria?id=${galeria.id}"> 
													<img src="${galeria.primeraImagen}" alt="">
												</a>
											</div>
											<div class="block-grid-v2-info rounded-bottom  bloques_eventos">
												<h5>
													<b>
														<a href="${contextPath}/servicios/galeria?id=${galeria.id}">
															${galeria.nombre}
														</a>
													</b>
												</h5>
											</div>
										</li>
									
									</c:forEach>	
								</ul>
								<a href="${contextPath}/servicios/galerias"
									class="btn-u btn-u-sm pull-right tooltips"
									data-toggle="tooltip" data-placement="left"
									data-original-title="Ver m&aacute;s galer&iacute;as">
									Ver m&aacute;s<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
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
		
		<!--  MODALES -->
		<c:set var="count" value="0" scope="page" />
		<c:forEach var="novedad" items="${novedades}">																					
			<div id="modal${count}" class="ufps-image-modal">
				<span class="ufps-image-modal-close">&times;</span> 
				<img class="ufps-image-modal-content" src="${novedad.imBase64image}" alt="">
				<div class="ufps-image-modal-caption">
					${novedad.nombre}
				</div>
			</div>			
			<c:set var="count" value="${count + 1}" scope="page"/>		
		</c:forEach>	
		
		<!-- Redes sociales -->
		
		<!-- ICONOS REDES SOCIALES -->
		<div id="idcuadroredes" class="cuadroredes" style="height: 190px">
			<div
				style="text-align: center; vertical-align: middle; font-size: 1.25em; cursor: pointer; margin: 4px; padding: 0px; border-bottom: 1px dotted #666;"
				onclick="document.getElementById('idcuadroredes').style.display='none';">
				<i class="icon-close"></i>
			</div>
			<ul class="social-icons social-icons-color margin-top-10">
				<li class="tooltips" data-toggle="tooltip" data-placement="bottom"
					data-original-title="S�guenos en Facebook"><a
					href="https://www.facebook.com/UFPS-C%C3%BAcuta-553833261409690"
					class="rounded social_facebook"></a></li>
				<li class="tooltips" data-toggle="tooltip" data-placement="bottom"
					data-original-title="S�guenos en Twitter"><a
					href="https://twitter.com/UFPSCUCUTA"
					class="rounded social_twitter"></a></li>
				<li class="tooltips" data-toggle="tooltip" data-placement="bottom"
					data-original-title="S�guenos en YouTube"><a
					href="https://www.youtube.com/channel/UCgPz-qqaAk4lbHfr0XH3k2"
					class="rounded social_youtube"></a></li>
				<li class="tooltips" data-toggle="tooltip" data-placement="bottom"
					data-original-title="S�guenos en Instagram"><a
					href="https://www.instagram.com/ufpscucuta/"
					class="rounded social_instagram"></a></li>
			</ul>
		</div>
		<!-- FIN ICONOS REDES SOCIALES -->
	
		
	</div>
	<!--wrapper-->
	
	
	<%@ include file="General/DownPanel.jsp"%>

	<%@ include file="General/Scripts.jsp"%>
		
</body>
</html>


