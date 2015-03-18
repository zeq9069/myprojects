/*ajax分页*/
define(['jquery','jsrender'],function($){
	function Page(options){
		this.itemListUrl = options.itemListUrl || "";//列表请求地址
		this.itemCountUrl = options.itemCountUrl || "";//列表数量请求地址
		this.par = options.par || {};//请求参数
		this.type = options.type || 'get';//请求方式
		this.currentIndex = options.currentIndex || 1;//当前页
		this.pageSize = options.pageSize || 2;//每页显示条数
		this.listWrap = options.listWrap || '.listWrap';//列表包含块
		this.listWrapTemp = options.listWrapTemp || '#listWrapTemp';//列表模板（jsrender模板）
		this.pageWrap = options.pageWrap || '.pageWrap';//翻页包含块
		this.datas = {};
		this.pipeStart = options.pipeStart || 1;
		this.pipeCount = options.pipeCount || 5;//分页长度
		this.pipeEnd = this.pipeStart+this.pipeCount-1;
		this.pageCount = 0;
	}
	Page.prototype = {
			itemList: function(){
				var that = this;
				//合并请求参数
				this.datas = {
						currentIndex: this.currentIndex,
						pageSize: this.pageSize
				};
				this.datas = $.extend(this.datas,this.par);
				
				$.ajax({
					url: this.itemListUrl,
					type: this.type,
					data: this.datas,
					success: function(itemList){
						if(itemList){
							$(that.listWrap).html(($.templates(that.listWrapTemp)).render(itemList));
						}
					},
					error: function(error){
						console.log('error');
					}
				});
			},
			
			itemCount: function(){
				var that = this;
				$.ajax({
					url: this.itemCountUrl,
					type: this.type,
					data: this.datas,
					success: function(counts){
						if(counts){
							that.pageCount = Math.ceil(counts/that.pageSize);
							that.page();
						}
					},
					error: function(error){
						console.log('error');
					}
				});
			},
			
			setPipe: function(){
				
				if(this.currentIndex <= this.pipeStart){
					if(this.currentIndex == 1){
						this.pipeStart = 1;
					}else{
						this.pipeStart = this.pipeStart-this.pipeCount;
						if(this.pipeStart <= 0) this.pipeStart = 1;
					}
					this.pipeEnd = this.pipeStart+this.pipeCount-1;
					if(this.pipeEnd > this.pageCount) this.pipeEnd = this.pageCount;
				}
				
				if(this.currentIndex >= this.pipeEnd){
					if(this.currentIndex == this.pageCount){
						this.pipeEnd = this.pageCount;
						this.pipeStart = this.pipeEnd-this.pipeCount+1;
						if(this.pipeStart <=0 ) this.pipeStart=1;
					}else{
						this.pipeStart = this.currentIndex-1;
						if(this.pipeStart <=0 ) this.pipeStart=1;
						this.pipeEnd = this.pipeStart+this.pipeCount-1;
						if(this.pipeEnd > this.pageCount) this.pipeEnd=this.pageCount;
					}
				}
			},
			
			page: function(){
				var pageStr = '',
					that = this;
				
				that.setPipe(this.pageCount);
				
				function pageList(start, end, index){
					var listStr = '';
					for(var i = start; i <= end; i++){
						if(index == i){
							listStr += '<li class="active"><a href="#active">'+i+'</a></li>';
						}else{
							listStr += '<li><a href="#'+i+'">'+i+'</a></li>';
						}
					}
					return listStr;
				}
				
				if(this.pipeEnd == 1){
					pageStr += '<li class="current">1</li>';
				}else if(this.pipeStart == 1 && this.pipeEnd > 1 && this.currentIndex == this.pipeStart){
					pageStr = pageList(this.pipeStart, this.pipeEnd, this.currentIndex);
					pageStr += '<li><a href="#next">&raquo;</a></li><li><a href="#last">末页</a></li>';
				}else if(this.pipeEnd == this.pageCount && this.pipeEnd > 1 && this.currentIndex == this.pipeEnd){
					pageStr += '<li><a href="#first">首页</a></li><li><a href="#prev">&laquo;</a></li>';
					pageStr +=  pageList(this.pipeStart, this.pipeEnd, this.currentIndex);
				}else{
					pageStr += '<li><a href="#first">首页</a></li><li><a href="#prev">&laquo;</a></li>';
					pageStr += pageList(this.pipeStart, this.pipeEnd, this.currentIndex);
					pageStr += '<li><a href="#next">&raquo;</a></li><li><a href="#last">末页</a></li>';
				}
				pageStr += '<li class="goPage"><span class="text">共 '+this.pageCount+' 页，</span><span class="text"> 到第 </span><input class="num" value="'+this.currentIndex+'" type="text"/><span class="text"> 页 </span><span class="btn">确定</span></li>';
				$(this.pageWrap).html(pageStr);
				
				$(this.pageWrap).unbind('click').on('click', 'li[class!="active"]>a', function(){
					var str = ($(this).attr('href').split('#'))[1];
					if(parseInt(str)) that.currentIndex = str;
					if(str == 'prev') that.currentIndex = (that.currentIndex--)>0 ? (that.currentIndex--) : 1;
					if(str == 'next') that.currentIndex = (that.currentIndex++)<that.pageCount ? that.currentIndex++ : that.pageCount;
					if(str == 'last') that.currentIndex = that.pageCount;
					if(str == 'first') that.currentIndex = 1;
					that.itemList();
					that.itemCount();
				});
				
				$(this.pageWrap).on('click', 'li.goPage>.btn',function(){
					var pageNum = parseInt($(this).siblings('.num').val(),10);
					if(pageNum != 'NaN'){
						if(pageNum >= 1 || pageNum <= that.pageCount){
							that.currentIndex = pageNum;
							that.itemList();
							that.itemCount();
						}
						
						if(pageNum <= 0){
							that.currentIndex = pageNum;
							that.itemList();
							that.itemCount();
						}
						
						if(pageNum > that.pageCount){
							that.currentIndex = that.pageCount;
							that.itemList();
							that.itemCount();
						}
					}
				});
			}
	};
	
	return {
		setting: function(options){
			var page =  new Page(options);
			page.itemList();
			page.itemCount();
			return page;
		}
	};
});