<div id="menu-principal" class="header-v6 header-white-transparent header-sticky" style="position: relative;">
			
	<%@ include file="../General/TopPanel.jsp"%>
	
	<div class="header-v8 img-logo-superior" style="background-color: #aa1916;">
		<!--=== Parallax Quote ===-->
		<div class="parallax-quote parallaxBg" style="padding: 30px 30px;">
			<div class="parallax-quote-in" style="padding: 0px;">
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-5">
						<a href="${contextPath}/"> 
							<img id="logo-header" src="${logoHorizontal.imBase64image}" alt="" style="max-height: 180px;">
						</a>
					</div>
					<div class="col-md-8 col-ms-8 col-xs-8">
						<a href="http://www.ufps.edu.co/">
							<img class="header-banner" src="resources/rsc/img/logo_ufps.png" style="max-height: 160px;" alt="Escudo de la Universidad Francisco de Paula Santander">
						</a>
					</div>
				</div>
			</div>
		</div>
		<!--=== End Parallax Quote ===-->
	</div>
			
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
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
		