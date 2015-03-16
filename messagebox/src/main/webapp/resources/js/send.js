$(document).ready(function(){
	

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
  
	
	$("#announ_submit").click(function(){
		
		
		var value=UE.getEditor('editor').getContent();
		

		var s=new Array();
		var i=0;
		  $("input[name='group']:checked").each(function(){ 
		    s.push("group="+$(this).val());
		  }); 
	    
		  var data=s.join("&")+"&title="+$("input[name='title']").val()+"&type="+$("#type").val()
		  +"&publisher="+$("input[name='publisher']").val()+"&online="+$("input[name='online']:checked").val()
		  +"&content="+$("textarea[name='editorValue'").val();
		 alert(data);
		$.post("/messagebox/system/announs/add",$("form").serialize(),function(data){
			if(data.status=="success"){
				alert("添加成功!");
			}else{
				alert(data.message);
			}
		});
	});
	
});

