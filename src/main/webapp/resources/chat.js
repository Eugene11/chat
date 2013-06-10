$(document).ready(function(){
	
	//обработка клика по кнопке послать сообщение
	$("#sendButton").click( function(){
		$.ajax({
			type: "post",
		    url: 'addmsg.htm',
		    data: ( {messageCont : $("#messageCont").val(), quantityShow: $("#quantityShow").val() } ),
		    success: function(data) {
		    	$("#messages").children().first().prepend(data);
		    	$("#messageCont").val("");
		    }
		});
	});
	
	//изменено количество отображаемых сообщений
	$("#quantityShow").change( function(){
		$.ajax({
			type: "get",
		    url: 'getmsgs.htm',
		    data: ( {quantityShow: $("#quantityShow").val() } ),
		    success: function(data) {
		    	
		    	$("#messages").empty();
		    	$("#messages").html(data);
		    	
		    }
		});
	});
	
	//навешивание обработчика по таймеру обновлений сообщений 
	if( $("#quantityShow").val() != undefined){
		setInterval(function() {
			$.ajax({
				type: "get",
			    url: 'getmsgs.htm',
			    data: ( {quantityShow: $("#quantityShow").val() } ),
			    success: function(data) {
			    	
			    	$("#messages").empty();
			    	$("#messages").html(data);
			    	
			    }
			});
			 
		}, 10000);
	}
});
