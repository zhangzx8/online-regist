$(function(){
	(function($, document, undefiend){
		$.fn.pagination = function(options){
			var $this = $(this);
			var defaults = {
					pageNo : 1,
					pageSize : 50,
					totalPages : 1,
					totalRecords : 1,
					goToPage : $.noop
			}
			
			var _opt = $.extend({}, defaults, options);
			var totalRecordHtml = ' <span class="num">共'+_opt.totalRecords+'条记录</span>';
			
			var btnDivHtml = '<div class="pageBtn">' 
				            	+'<span class="pageNum pageNo">第<input type="text" value="'+_opt.pageNo+'" class="input_page"/>页</span>'
				            	+'<span class="allpage">共'+_opt.totalPages+'页</span><span>每页'+_opt.pageSize+'条</span><span class="goBtn">go</span>'
	            			+'</div>';
			
			$this.empty()
				.append(totalRecordHtml).append(btnDivHtml);
			
			var goBtn = $this.find('.goBtn');
			
			$this.on('keyup','.pageNo',function(e){
				if(e.keyCode == 13){//回车
					goBtn.click();
				}
			})
			
			goBtn.on('click',function(){
				var pageNoInput = $this.find('.pageNo').find('input');
				var _pageNo = pageNoInput.val();
				if(!isNaN(_pageNo)){
					var pageNo = parseInt(_pageNo);
					if(0<pageNo && pageNo<=_opt.totalPages){
						_opt.goToPage(pageNo);
						return;
					}
				}
				pageNoInput.val(_opt.pageNo);
			});
			
		}
	})($, document);

})