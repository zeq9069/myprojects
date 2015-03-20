$(document).ready(function(){
	
	
	
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
  
    $("#announ_form").validate({
    	submitHandler:function(form){

    		var value=UE.getEditor('editor').getContent();
    		
    		if(value.trim()==''){
    			alert("内容不能为空");
    			return false;
    		}
    		
    		$.post("/messagebox/system/announs/add",$("form").serialize(),function(data){
    			if(data.status=="success"){
    				alert("添加成功!");
    			}else{
    				alert(data.message);
    			}
    		});
    		
    		
    		
        }    
    });
	
//	$("#announ_submit").click(function(){
//		
//		var value=UE.getEditor('editor').getContent();
//		
//		$.post("/messagebox/system/announs/add",$("form").serialize(),function(data){
//			if(data.status=="success"){
//				alert("添加成功!");
//			}else{
//				alert(data.message);
//			}
//		});
//	});
	
});

