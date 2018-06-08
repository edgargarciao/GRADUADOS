  var colorPalette = ['000000', 'FF9966', '6699FF', '99FF66', 'CC0000', '00CC00', '0000CC', '333333', '0066FF', 'FFFFFF'];
  var forePalette = $('.fore-palette');
  var backPalette = $('.back-palette');

  for (var i = 0; i < colorPalette.length; i++) {
    forePalette.append('<a href="#" data-command="forecolor" data-value="' + '#' + colorPalette[i] + '" style="background-color:' + '#' + colorPalette[i] + ';" class="palette-item"></a>');
    backPalette.append('<a href="#" data-command="backcolor" data-value="' + '#' + colorPalette[i] + '" style="background-color:' + '#' + colorPalette[i] + ';" class="palette-item"></a>');
  }

  
  /**
   * Evento que se acciona cuando dan clic en alguna opcion de la paleta.
   * @param e Evento.
   * @returns
   */
  $('.toolbar a').click(function(e) {
    var command = $(this).data('command');
    if (command == 'h1' || command == 'h2' || command == 'p') {
      document.execCommand('formatBlock', false, command);
    }
    if (command == 'forecolor' || command == 'backcolor') {
      document.execCommand($(this).data('command'), false, $(this).data('value'));
    }
    if (command == 'createlink') {
    	alert("test --> ");
    	url = prompt('Enter the link here: ', 'http:\/\/');
    	alert("url --> "+url);
    	document.execCommand($(this).data('command'), false, url);
    }if (command == 'insertimage') {
    	document.getElementById('imagen').click();

    }if (command == 'insertVideo') {
    	document.getElementById('video').click();

    }if (command == 'insertFile') {
    	document.getElementById('archivo').click();

    } else document.execCommand($(this).data('command'), false, null);
  });
  
  /**
   * Metodo que permite cargar una imagen en la seccion editable.
   * @returns
   */
  function cargarImagen(){
	  
	  var file    = document.getElementById('imagen').files[0];
	  var reader  = new FileReader();

	  if (file) {
		  reader.onloadend = function () {
			  var image = new Image();

			    image.src = reader.result;

			    image.onload = function() {
			    	  var ancho = image.height;
					  var id = "rand" + Math.random();
					  var doc = document.getElementById("editor");
					  doc = doc.document;
					  if(ancho <= 700){
						  img = "<img src='" +  image.src + "' id=" + id + ">";
					  }else{
						  img = "<img src='" +  image.src + "' id=" + id + " style='width:100%;height:100%'>";
					  }

					  if(document.all) {
					    var range = doc.selection.createRange();
					    range.pasteHTML(img);
					    range.collapse(false);
					    range.select();
					  } else {
					    document.execCommand("insertHTML", false, img);
					  }  
			    };
		  }
		  
		  reader.readAsDataURL(file);		  
		  document.getElementById("imagen").value = "";
	  } else {
		  alert("NO Selecciono algun archivo");
	  }
  }
  
  /**
   * Metodo que coloca el video dentro de la seccion editable.
   * @param input Video a colocar.
   * @returns
   */
  function revisarVideo(input) {
      //THE METHOD THAT SHOULD SET THE VIDEO SOURCE
      if (input.files && input.files[0]) {
    	  var file = input.files[0];
          var url = URL.createObjectURL(file);
          console.log(url);
          var reader = new FileReader();
          reader.onload = function() {
        	  var id = "rand" + Math.random();
        	  var doc = document.getElementById("editor");
        	  doc = doc.document;
        	  img = "<video id='video1' height='300' width='600' controls><source src='" +  url + "' id=" + id + "/></video>";
        	  document.execCommand("insertHTML", false, img);
          }
          reader.readAsDataURL(file);
          document.getElementById("video").value = "";
      }
  }
  
  /**
   * Metodo que guarda un archivo en base de datos y luego pinta una imagen colocando
   * como direccionamiento el codigo del archivo en base de datos para que cuando le den
   * clic se descargue el archivo.
   * @param input Archivo.
   * @returns 
   */
  function revisarArchivo(input) {
	  // Consulta si cargo algun archivo
    if (input.files && input.files[0]) {
        // Obtiene el archivo
    	var file = input.files[0];    	
        
        // Creo la url a consumir
        var url = window.location.protocol + "//" + window.location.host + "/" + (window.location.pathname).split("/")[1];
        
        var formData = new FormData();
        formData.append('archivo', file);
        
        // Aca guardo el archivo en base de datos y genero un ID
		$.ajax({
			type : "POST",
			enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    cache: false,
			url : url + "/servicios/registrarArchivo",
			data: (formData),
			success : function(result) {
				var id  = result;
				// Si guardo el archivo lo pintamos en pantalla
				pintarImagen(id,file.type);    	
				
			},
			error : function(e) {
				alert("Error! --> "+String(e));
				console.log("ERROR: ", e);
			}
		}); 
        
        document.getElementById("archivo").value = "";
    }
}
  
  function pintarImagen(id,fileType){
	  
	  	// Creo la url a consumir
      	var url = window.location.protocol + "//" + window.location.host + "/" + (window.location.pathname).split("/")[1];
      
        var formData = new FormData();
        formData.append('tipo', fileType);
      	
		// Aca guardo el archivo en base de datos y genero un ID
		$.ajax({
			type : "POST",
			enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    cache: false,
			url : url + "/servicios/solicitarImagen",
			data: (formData),
			success : function(result) {
				var doc = document.getElementById("editor").document;		
				var img = "<a href='/ufps-graduados/servicios/download?id="+id+"'><img src='" +  result + "' id=" + id + " height ='50' width ='50' /><a/>";
				document.execCommand("insertHTML", false, img);
				
			},
			error : function(e) {
				alert("Error! --> "+String(e));
				console.log("ERROR: ", e);
			}
		}); 		
  }

  