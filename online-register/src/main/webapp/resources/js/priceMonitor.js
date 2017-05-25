$(function(){
	new PriceMonitor();

})
function PriceMonitor(){
	this.initialize();
	this.loadData(this.province_id,this.month,1);
	this.getUrlArgument(name);
	this.serchBox();
}

PriceMonitor.prototype={
	initialize:function(){
		this.checkBox = $(".selectBox");
		this.select_cor = $(this.checkBox).find("#select_area");
		this.month_input = $(this.checkBox).find("#cycleMonth");
		this.select_input =$(this.select_cor).find("#provinceId");
		this.select_hide_box =$(this.select_cor).find(".hide_select_box2");
		this.select_li =$(this.select_cor).find(".select_demo").children("li");
		this.serachBtn = $(this.checkBox).find(".refreshBtn");
		this.download_btn = $(".download_pc");
		//创建表格
		this.table = $(".priceMonitor");
		this.headName = $('<table class="tableHead"><tr></tr></table>');
		this.dataWrap = $('<div class="tableList"></div>');
		this.tableList =$('<table></table>');	
		//获取url参数
		this.province_id =this.getUrlArgument("groupprovince");
		this.month = '';
		this.pageSize = 20;
		
	},
	serchBox:function(){
		var own = this;
				//点击省下拉效果
		$(own.select_input).click(function(){
			if($(own.select_hide_box).is(":hidden")){
				$(own.select_hide_box).slideDown();	
			}else{
				$(own.select_hide_box).slideUp();
			}			
		});
		//选择省份
		
		$(own.select_li).hover(function(){
			$(this).addClass("on").siblings().removeClass("on");
		}).click(function(){
			var text = $(this).text();
			var val = $(this).data("tree-id");
			$(own.select_input).val(text);
			$(own.select_input).attr("data-id",val);
			$(this).addClass("on").siblings().removeClass("on");
			$(own.select_hide_box).slideUp();
		});
		//搜索按钮
		 $(own.serachBtn).click(function(e,data){
			 var pageNo = 1;
				if(data && data.pageNo){
					pageNo = data.pageNo;
				}
			 var time_val = $("#cycleMonth").val();
			 var pro_id ="";
			 if($(own.select_cor).is(":hidden")){
				 pro_id = own.province_id;
			 }else{
				 pro_id = $(own.select_input).attr("data-id");
			 }
			 if(time_val==""||time_val==undefined){
				 alert("请选择时间!")
			 }else{
				own.loadData(pro_id,time_val,pageNo);
			 }
		 });
		 //下载按钮
		 $(own.download_btn).click(function(){
			 var month = $(own.month_input).val();
			 var pro_id ="";
			 if($(own.select_cor).is(":hidden")){
				 pro_id = own.province_id;
			 }else{
				 pro_id = $(own.select_input).attr("data-id");
			 }
			 $(this).attr('href',contextPath+'/downPriceMonitor?province='+pro_id+'&month='+month)
 
		 });
		
	},
	
	loadData:function(pro,month,pageNo){
		var own = this;
		var the_url = contextPath+'/getPriceMonitor?province='+pro+'&month='+month+'&pageSize='+own.pageSize+'&pageNo='+pageNo;
		if(own.province_id==10){
			$(own.select_cor).show();
		}else{
			$(own.select_cor).hide();
		}
		$.ajax({
			url:the_url,
			type:'GET',
			dataType:'json',
			beforeSend:function(){
				$(own.table).empty()
				.append('<div class="loading"><img src="/resources/images/loading.gif" /></div>');
			},
			success:function(data){	
				$(own.table).empty();
				var json = $.parseJSON(data);
				if(json.code==0){
					own.initInfo(json.data);
					own.page(json.data.page);
				}else{
					$(".priceMonitor").empty().append('<div class="fail">数据加载失败，请稍后再试！</div>');
				}
				
			},
			error:function(){
				$(".priceMonitor").empty().append('<div class="fail">服务器繁忙，请稍后再试！</div>');
				
			}
		})
	},
	
	initInfo:function(jsonData){
		var own = this;
		$(own.month_input).val(jsonData.month);
		$(own.table).empty();
		$(own.table).append($(this.headName),$(own.dataWrap).append(own.tableList));
		var head = jsonData.headers;
		/*表头*/
		 $(own.headName).find('tr').empty();
		var i = 0;
		for(var vv in head){
			var width;
			var val = head[vv];
			if(i==0){
				width = 120
			}else if(i==1){
				width = 280
			}else{
				width = 90;
			}
			 $(this.headName).find('tr').append('<th width="'+width+'">'+val+'</th>');
			 i++;
		}
		/*表格数据列表*/
		var listData = jsonData.page.list;
		$(own.tableList).empty();
		for(var k=0; k<listData.length;k++){
			if(k%2==0){
				var $tr = $('<tr class="odd"></tr>');
			}else{
				var $tr = $("<tr></tr>");
			}
			$(own.tableList).append($tr);
			var row_val = listData[k];
			var j=0;
			for(var v in row_val){
				var td_width = $(own.headName).find('th:eq('+j+')').width();
				span_val = row_val[v];
				$tr.append('<td width="'+td_width+'">'+span_val+'</td>');
				j++;
			}	
		}
		
		
	},
	page:function(option){
		var own = this;

		$('.paginator').pagination({
			pageNo : option.pageNo,
			pageSize :  own.pageSize,
			totalPages : option.totalPages,
			totalRecords : option.totalRecords,
			
			goToPage : function(pageNo){
				$(own.serachBtn).trigger('click',{pageNo:pageNo});
			}
		})
	},
	getUrlArgument :  function(name){
	    var search = document.location.search;
	    var pattern = new RegExp("[?&]"+name+"=([^&]+)");
	    var matcher = pattern.exec(search);    
	    var items = null;
	    if(null != matcher){
	        try{
	            items = decodeURIComponent(decodeURIComponent(matcher[1]));         }catch(e){
	            try{
	                    items = decodeURIComponent(matcher[1]);
	            }catch(e){
	                    items = matcher[1];
	            }
	        }
	    }
	    return items;
	}
}