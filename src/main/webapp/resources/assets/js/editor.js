  var colorPalette = ['000000', 'FF9966', '6699FF', '99FF66', 'CC0000', '00CC00', '0000CC', '333333', '0066FF', 'FFFFFF'];
  var forePalette = $('.fore-palette');
  var backPalette = $('.back-palette');

  for (var i = 0; i < colorPalette.length; i++) {
    forePalette.append('<a href="#" data-command="forecolor" data-value="' + '#' + colorPalette[i] + '" style="background-color:' + '#' + colorPalette[i] + ';" class="palette-item"></a>');
    backPalette.append('<a href="#" data-command="backcolor" data-value="' + '#' + colorPalette[i] + '" style="background-color:' + '#' + colorPalette[i] + ';" class="palette-item"></a>');
  }

  $('.toolbar a').click(function(e) {
    var command = $(this).data('command');
    if (command == 'h1' || command == 'h2' || command == 'p') {
      document.execCommand('formatBlock', false, command);
    }
    if (command == 'forecolor' || command == 'backcolor') {
      document.execCommand($(this).data('command'), false, $(this).data('value'));
    }
    if (command == 'createlink') {
    	
    	url = prompt('Enter the link here: ', 'http:\/\/');
    	document.execCommand($(this).data('command'), false, url);
    }if (command == 'insertimage') {
    	document.getElementById('imagen').click();

    }if (command == 'insertVideo') {
    	document.getElementById('video').click();

    } else document.execCommand($(this).data('command'), false, null);
  });
  
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
  
  function revisarVideo(input) {
	  	//var video = document.getElementById("video");
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

          	
          	
          	//video.src = url;
              //video.play();
          }
          reader.readAsDataURL(file);
          document.getElementById("video").value = "";
      }
  }