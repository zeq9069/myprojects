$(document).ready(function(){
	
	$("#announ_submit").click(function(){
		
		
		var value=UE.getEditor('editor').getContent();
		 
		alert($("input[name='title']").val());
		
		$.post("/messagebox/system/announs/add",{
			title:$("input[name='title']").val(),
			type:$("#type").val(),
			group:$("input[name='group']:checked").val(),
			publisher:$("input[name='publisher']").val(),
			online:$("input[name='online']:checked").val(),
			content:value
		},function(data){
			if(data.status=="success"){
				alert("添加成功!");
			}else{
				alert(data.message);
			}
		});
	});
	
});

