data="";
submit=function(){
    
	 var retVal = confirm("Do you want to continue ?");
     
	 if( retVal == true ){
            
	$.ajax({
		url:'saveOrUpdate',
		type:'POST',
		data:{user_id:$("#user_id").val(), username:$("#username").val(),password:$("#password").val(),firstname:$("#firstname").val(),lastname:$("#lastname").val(),email:$("#email").val(),address:$("#address").val(),phone:$("#phone").val()},
		success: function(response){
						
			alert(response.message);
			load();
			clear();
		  }
	});
	
     }
     else{
        document.write ("User does not want to continue!");
        return false;
     }
			
}
// ###Author:- Sujeet
clear=function(){
	
	$("#user_id").val("0");
	$("#username").val("");	
	$("#password").val("");
	$("#firstname").val("");
	$("#lastname").val("");
	$("#email").val("");	
	$("#address").val("");	
	$("#phone").val("");	
}

delete_=function(id){
	
		
	 var retVal = confirm("Do you want to continue ?");
     if( retVal == true ){
    	 $.ajax({
    			url:'delete',
    			type:'POST',
    			data:{user_id:id},
    			success: function(response){
    				
    				alert(response.message);
    				load();
    			}
    		});
     }
     else{
        document.write ("User does not want to continue!");
        return false;
     }

	
			
}

edit = function (index){
	
	//document.getElementById("details").style.visibility="visible"; 
	//alert(index+" Data: "+data[index].id);	
	$("#user_id").val(data[index].user_id);
	$("#username").val(data[index].username);
	$("#password").val(data[index].password);
	$("#firstname").val(data[index].firstname);
	$("#lastname").val(data[index].lastname);
	$("#email").val(data[index].email);
	$("#address").val(data[index].address);
	$("#phone").val(data[index].phone);
}

//@Author: Sujeet Yadav 
searchlist=function(){
	
	//alert(document.getElementById("search").value);
	
	$.ajax({
		url:'searchlist',
		type:'POST',
		data:{search:$("#search").val()},
		success: function(response){
			data = response.data;
			$('.tr').remove();
			for(i=0;i<response.data.length;i++){
				$("#table").append("<tr class='tr'><td> "+response.data[i].user_id+"</td><td> "+response.data[i].username+"</td><td>"+response.data[i].email+"</td><td><a href='#' onclick='edit("+i+");'>Edit</a></td><td><a href='#' onclick='delete_("+response.data[i].user_id+");'>Delete</a></td></tr>");
			}
			
		}
	});
}

load=function(){
	
	//document.getElementById("details").style.visibility="hidden";
	
	$.ajax({
		url:'list',
		type:'POST',
		success: function(response){
			data = response.data;
			$('.tr').remove();
			for(i=0;i<response.data.length;i++){
				$("#table").append("<tr class='tr'><td> "+response.data[i].user_id+"</td><td> "+response.data[i].username+"</td><td>"+response.data[i].email+"</td><td><a href='#' onclick='edit("+i+");'>Edit</a></td><td><a href='#' onclick='delete_("+response.data[i].user_id+");'>Delete</a></td></tr>");
			}
			
		}
	});
}

