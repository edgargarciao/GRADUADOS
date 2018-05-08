<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Administrador - Login</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="apple-icon.png">
    <link rel="shortcut icon" href="favicon.ico">

    <link rel="stylesheet" href="resources/assets/css/normalize.css">
    <link rel="stylesheet" href="resources/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/assets/css/themify-icons.css">
    <link rel="stylesheet" href="resources/assets/css/flag-icon.min.css">
    <link rel="stylesheet" href="resources/assets/css/cs-skin-elastic.css">
    <!-- <link rel="stylesheet" href="resources/assets/css/bootstrap-select.less"> -->
    <link rel="stylesheet" href="resources/assets/scss/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

</head>


<body class="bg-white">
 	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    <a href="${contextPath}/">
                        <img class="align-content" src="resources/images/logo.png" alt="">
                    </a>
                </div>
               <c:if test="${not empty wrong}">
                <!--   <div class="alert alert-warning alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <h4><i class="icon fa fa-warning"></i> Error</h4>
                    <c:out value='${wrong}' />
                  </div> -->
              </c:if>
                
                <div class="login-form">
                    <!-- <form> -->
                    <form:form action="autenticar" method="post" modelAttribute="login">
                        <div class="form-group">
                            <label>Correo institucional</label>                            
                            <form:input path="correoInstitucional" type="email" class="form-control" placeholder="anamarianagh@ufps.edu.co"/>                       
          
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>                            
                             <form:password path="contraseña" class="form-control" placeholder="Password1234"/>     
                        </div>

                        <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">Iniciar sesión</button>
                        

                     </form:form>
                </div>
            </div>
        </div>
    </div>
    
    <script src="resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
    <script src="resources/assets/js/popper.min.js"></script>
    <script src="resources/assets/js/plugins.js"></script>
    <script src="resources/assets/js/main.js"></script>

</body>
</html>