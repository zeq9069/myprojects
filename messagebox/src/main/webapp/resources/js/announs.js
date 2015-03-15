$(document).ready(function(){
	
	$("span#announ_status").dblclick(function(){
		var announ_id=$(this).attr("data-user");
		var online=$(this).attr("data-value");
		
		$.post("/messagebox/system/announs/online/update",{announ_id:announ_id,online:online=="true"?"false":"true"},function(data){
			if(data.status=="success"){
				alert("修改成功");
				if(online=="true"){
					$("span[data-user='"+announ_id+"']").text("下线");
					$("span[data-user='"+announ_id+"']").attr("data-value","false");
				}else if(online=="false"){
					$("span[data-user='"+announ_id+"']").text("上线");
					$("span[data-user='"+announ_id+"']").attr("data-value","true");
				}
			}else{
				alert(data.message);
			}
		});
		
	});
	
});