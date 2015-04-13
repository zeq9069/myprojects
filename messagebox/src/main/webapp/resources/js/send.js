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
    		
    		$.post("/messagebox/system/announs",$("form").serialize(),function(data){
    			if(data.status=="success"){
    				alert("添加成功!");
    			}else{
    				alert(data.message);
    			}
    		});
    		
    		
    		
        }    
    });
    
    
    $(".targetYxdm").select2({
    	placeholder:"请选择输入",
    	minimumInputLength:2,//最小输入长度
    	ajax:{
    		url:"http://localhost:8080/messagebox/system/list",
    		dataType:"json",
    		quietMillis:20,
    		data:function (term,page){
    			return {q:term};
    		},
    		results:function(data,page){
    			return {results:data};
    		}
    	}
    	
    });
    
    $("button[name='add_targetYxdm']").on("click",function(){
    	alert($(".targetYxdm").select2("data").id);
    });
    
    alert((".tartgetYxdm").select2("data").id);
	
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

