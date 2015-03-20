$(document).ready(function(){
	
	
	$(".add-group>#btn-add").click(function(){
		var groupName=$("#groupName").val();
		var groupId;
		$.post("/messagebox/system/groups/add",{
			name:groupName
		},function(data){
			if(data.status=="success"){
				groupId=data.message;
				$(".group-list>p").append("<span id='"+groupId+"'>"+groupName+"</span>");
				$("#groupName").val("");
			}else{
				alert(data.message);
			}
		});
	});
	
	$("#content-right").on("dblclick",".group-list>p>span",function(){
		var groupName=$(this).html();
		var id=this.id;
		$.ajax({
			type:"POST",
			url:"/messagebox/system/groups/delete",
			data:{name:groupName},
			dataType:"json",
			success:function(data){
				$("#"+id).remove();
			},
			error:function(request, textStatus){
				alert(textStatus);
			}
		});
	});
	
	
});

