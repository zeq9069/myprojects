$(document).ready(function(){
	
	$("#announ_submit").click(function(){
		$.post("/messagebox/system/announs/add",$("form").serialize(),function(data){
			if(data.status=="success"){
				alert("添加成功!");
			}else{
				alert(data.message);
			}
		});
	});
	
});

