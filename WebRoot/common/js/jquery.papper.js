;(function(){
	
	$.fn.papper = function(config){
		config = $.extend({
			limit:10,
			start:0,
			count:0,
			current:1,
			submit:true,
			callback:function(){return false;},
			step:10,
			min:10,
			max:100
		},config||{});
		return this.each(function(){
			var panel = $(this);
			var jumpPage = function(index){
				if(index>=1 && index <= this.totalPage){
					this.startC.val((index-1)*this.limit);
					this.currentC.val(index);
					$("#"+config.form).submit();
				}
			};
			var drawLink = function(){
				var c = {
					total:parseInt(config.count),
					limit:parseInt(config.limit),
					start:parseInt(config.start)
				};
				c.current = c.start > c.limit ? (Math.ceil(c.start/c.limit)+1) : 1;
				if(c.start == c.limit){
					c.current = c.current + 1;
				}
				c.totalPage = Math.ceil(c.total/c.limit);
				panel.empty();
				$('<b style="float:right;">当前第'+c.current+'页,共'+c.totalPage+'页,共'+c.total+'条数据</b>').appendTo(panel);
				c.startC = $('<input name="pageSet.start" type="hidden" id="start">').appendTo(panel).val(c.start);
				c.limitC = $('<select name="pageSet.limit" id="papperSize" style="float:left;padding:1.5px;margin:0px 5px 0px 2px;"></select>').appendTo(panel);
				c.currentC = $('<input name="current" style="float:left;width:30px;margin:0px 5px 0px 0px;border:1px solid #999;padding:2.5px;">').appendTo(panel).val(c.current);
				$('<a class="current" href="javascript:void(0);">GO</a>').appendTo(panel).click(function(){
					showBlock();
					jumpPage.apply(c,[c.currentC.val()]);
				});
				$('<a href="javascript:void(0);">首页</a>').appendTo(panel).click(function(){
					showBlock();
					jumpPage.apply(c,[1]);
				});
				if(c.current<=1){
					$('<span>上一页</span>').appendTo(panel);
				}else{
					$('<a href="javascript:void(0);" class="prev">上一页</a>').appendTo(panel).click(function(){
						showBlock();
						jumpPage.apply(c,[parseInt(c.currentC.val())-1]);
					});
				}
				if(c.current>=c.totalPage){
					$('<span>下一页</span>').appendTo(panel);
				}else{
					$('<a href="javascript:void(0);" class="next">下一页</a>').appendTo(panel).click(function(){
						showBlock();
						jumpPage.apply(c,[parseInt(c.currentC.val())+1]);
					});
				}
				$('<a href="javascript:void(0);">尾页</a>').appendTo(panel).click(function(){
					showBlock();
					jumpPage.apply(c,[c.totalPage]);
				});
				for(var i=config.min;i<=config.max;i+=config.step){
					$("<option value='"+i+"'>"+i+"</option>").appendTo(c.limitC);
				}
				c.limitC.val(c.limit);
				c.limitC.change(function(){
					config.limit = $(this).val();
					config.start = "0";
					c.limit = $(this).val();
					c.start = "0";
					drawLink();
					showBlock();
					jumpPage.apply(c,[1]);
				});
			};
			drawLink();
			if(config.callback && typeof config.callback == "function"){
				config.callback.apply(this,[config]);
			}
		});
	};
	
})();

$(document).ready(function(){
	$("button:submit").click(function(){
		$("input[name='pageSet.start']").val("0");
	});
});