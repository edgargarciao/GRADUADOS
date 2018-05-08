<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminUfps</title>
    <meta name="description" content="AdminUFPS">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="resources/assets/css/normalize.css">
    <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/assets/css/themify-icons.css">
    <link rel="stylesheet" href="resources/assets/css/flag-icon.min.css">
    <link rel="stylesheet" href="resources/assets/css/cs-skin-elastic.css">
    <!-- <link rel="stylesheet" href="assets/css/bootstrap-select.less"> -->
    <link rel="stylesheet" href="resources/assets/scss/style.css">
    <link href="resources/assets/css/lib/vector-map/jqvmap.min.css" rel="stylesheet">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->

</head>
<body>

  <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        String t =String.valueOf(request.getAttribute("token"));
        String nombre = (String)request.getSession().getAttribute("user");
    %>

        <!-- Left Panel -->

    <aside id="left-panel" class="left-panel ">
        <nav class="navbar navbar-expand-sm navbar-default ">

            <div class="navbar-header ">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand " href="./"><img src="resources/images/logo.png" alt="Logo"></a>
                <a class="navbar-brand hidden" href="./"><img src="resources/images/logo2.png" alt="Logo"></a>
            </div>

            <div id="main-menu" class="main-menu collapse navbar-collapse ">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="index.html"> <i class="menu-icon fa fa-dashboard"></i>Panel de control </a>
                    </li>
                    <h3 class="menu-title">Negocio</h3><!-- /.menu-title -->
                    <li>
                        <a href="categorias.html"> <i class="menu-icon ti-list"></i>Categorias </a>
                    </li>
                    <li>
                        <a href="widgets.html"> <i class="menu-icon ti-list-ol"></i>Subcategorias </a>
                    </li>
                    <li>
                        <a href="widgets.html"> <i class="menu-icon fa fa-edit"></i>Contenido </a>
                    </li>
                    <li>
                        <a href="widgets.html"> <i class="menu-icon fa fa-newspaper-o"></i>Noticias </a>
                    </li>
                    <li>
                        <a href="widgets.html"> <i class="menu-icon fa fa-tasks"></i>Próximas actividades </a>
                    </li>                    
                    <li>
                        <a href="widgets.html"> <i class="menu-icon fa fa-bullhorn"></i>Novedades </a>
                    </li>
                    <li>
                        <a href="widgets.html"> <i class="menu-icon fa fa-university"></i>Logos </a>
                    </li>
                    <li>
                        <a href="widgets.html"> <i class="menu-icon ti-link"></i>Enlaces de interés </a>
                    </li>
                    <li>
                            <a href="widgets.html"> <i class="menu-icon fa fa-picture-o"></i>Galería </a>
                    </li>                    
                    <li>
                        <a href="widgets.html"> <i class="menu-icon ti-email"></i>Contactos </a>
                    </li>
                    <li>
                        <a href="widgets.html"> <i class="menu-icon fa fa-facebook"></i>Redes sociales </a>
                    </li>                    

                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </aside><!-- /#left-panel -->

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
        <header id="header" class="header">

                <div class="header-menu">
                    <div class="col-sm-7">
                            <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
                            <div class="page-header float-left">
                                    <div class="page-title">
                                        <ol class="breadcrumb text-right">
                                            <li class="active"><a href="#">Panel de control</a></li>
                                            
                                        </ol>
                                    </div>
                                </div>    
    
                    </div>
                    <div class="col-sm-5">
                       
                        <div class="user-area dropdown float-right">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img class="user-avatar rounded-circle" src="resources/images/admin.jpg" alt="User Avatar">
                            </a>
    
                            <div class="user-menu dropdown-menu">
                                    <a class="nav-link" href="#"><i class="fa fa- user"></i>Mi perfil</a>
    
                                    
    
                                    <a class="nav-link" href="#"><i class="fa fa -cog"></i>Configuración de la cuenta</a>
    
                                    <a class="nav-link" href="#"><i class="fa fa-power -off"></i>Salir</a>
                            </div>
                        </div>
    
                       
    
                    </div>
                </div>
    
            </header><!-- /header -->
            <!-- Header-->

            <div class="breadcrumbs">
                <div class="col-sm-4">
                    <div class="page-header float-left">
                        <div class="page-title">
                            <h1>Panel de control</h1>
                        </div>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="page-header float-right">
                        <div class="page-title">
                            <ol class="breadcrumb text-right">
                                <li class="active">Panel de control</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>

        <div class="content mt-3">

            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-list"></i>
                            <strong><span class="count">7</span></strong>
                            <span>Categorias</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col--> 


            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-list-ol"></i>
                            <strong><span class="count">34</span></strong>
                            <span>Subcategorias</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col--> 


            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-edit"></i>
                            <strong><span class="count">1200</span></strong>
                            <span>Contenido</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col--> 


            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-newspaper-o"></i>
                            <strong><span class="count">100</span></strong>
                            <span>Noticias</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col--> 

            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-tasks"></i>
                            <strong><span class="count">7</span></strong>
                            <span>Próximas actividades</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col--> 

            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-bullhorn"></i>
                            <strong><span class="count">7</span></strong>
                            <span>Novedades</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col-->             


            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-university"></i>
                            <strong><span class="count">2</span></strong>
                            <span>Logos</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col--> 
            
            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="fa fa-link"></i>
                            <strong><span class="count">10</span></strong>
                            <span>Enlaces de interés</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col-->    

            <div class="col-lg-3 col-md-6">
                    <div class="social-box google-plus">
                        <i class="fa fa-picture-o"></i>
                                <strong><span class="count">150</span></strong>
                                <span>Galería</span>
                        </div>
                    <!--/social-box-->
                </div><!--/.col-->                 
            
            <div class="col-lg-3 col-md-6">
                <div class="social-box google-plus">
                    <i class="ti-email"></i>
                            <strong><span class="count">8</span></strong>
                            <span>Contactos</span>
                    </div>
                <!--/social-box-->
            </div><!--/.col-->         
            
            <div class="col-lg-3 col-md-6">
                    <div class="social-box google-plus">
                        <i class="fa fa-google-plus"></i>
                                <strong><span class="count">4</span></strong>
                                <span>Redes sociales</span>
                        </div>
                    <!--/social-box-->
                </div><!--/.col-->               

        </div> <!-- .content -->
    </div><!-- /#right-panel -->

    <!-- Right Panel -->

    <script src="resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="resources/assets/js/plugins.js"></script>
    <script src="resources/assets/js/main.js"></script>


    <script src="resources/assets/js/lib/chart-js/Chart.bundle.js"></script>
    <script src="resources/assets/js/dashboard.js"></script>
    <script src="resources/assets/js/widgets.js"></script>
    <script src="resources/assets/js/lib/vector-map/jquery.vmap.js"></script>
    <script src="resources/assets/js/lib/vector-map/jquery.vmap.min.js"></script>
    <script src="resources/assets/js/lib/vector-map/jquery.vmap.sampledata.js"></script>
    <script src="resources/assets/js/lib/vector-map/country/jquery.vmap.world.js"></script>

</body>
</html>
