$(document).ready(function(){
	
	
	$(".add-group>#btn-add").click(function(){
		var groupName=$("#groupName").val();
		var groupId;
		$.post("/messagebox/system/groups",{
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
		var group_id=$(this).attr("id");
		var id=this.id;
		$.ajax({
			type:"POST",
			url:"/messagebox/system/groups/"+group_id,
			data:{_method:"delete"},
			success:function(data){
				$("#"+id).remove();
			},
			error:function(request, textStatus){
				alert(textStatus);
			}
		});
	});
	
	
});

