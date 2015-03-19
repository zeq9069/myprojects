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
				alert("添加成功!");
			}else{
				alert(data.message);
			}
		});
	});
	
	$("#content-right").on("dblclick",".group-list>p>span",function(){
		alert($(this).html());
		var groupName=$(this).html();
		var id=this.id;
		$.ajax({
			type:"POST",
			url:"/messagebox/system/groups/delete",
			data:{name:groupName},
			dataType:"json",
			success:function(data){
				alert(data.message);
				$("#"+id).remove();
			},
			error:function(request, textStatus){
				alert(textStatus);
			}
		});
	});
	
	
});

