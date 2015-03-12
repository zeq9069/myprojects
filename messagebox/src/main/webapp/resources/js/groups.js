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
				alert("success! id="+groupId);
			}else{
				alert(data.message);
			}
		});
	});
	
	$(".group-list>p>span").dblclick(function(){
		alert("这将会删除群组名"+this.id);
	});
	
});

