(function($) {
	$.fn.plusTab = function (options) {
        var opts = {
            opt_1: 'cur',
            opt_2: '.tabBox',
            opt_3: 'hide'
        };
        var opt = $.extend(opts, options);
        return this.each(function () {
            var _obj = $(this);
            _obj.click(function (e) { //click
                e.stopPropagation();
                _obj.addClass(opt.opt_1).siblings().removeClass(opt.opt_1);
                var i = _obj.index();
                $(opt.opt_2 + '> div').eq(i).removeClass(opt.opt_3).siblings().addClass(opt.opt_3);
            });
        });
        return this;
    }
})(jQuery);

$(function(){
	//Tab切换
	$('.purchase_menu li').plusTab({ opt_2: '.purchaseBox' });
	$('.dateMenu li').plusTab({ opt_2: '.dateBox' });
	$(".leftwarp a.first").click(function(){
		if( $(this).parent().children().hasClass("middle_list"))
		{
		$(this).toggleClass("open");
		$(this).next('ol').toggle();
		}
	});
	$(".date_tab tr:even td").addClass("even");
	$(".up_donw").click(function(){
		$(".tab_search").toggle();
		$(this).toggleClass("cur")
	});
	$(".up_donw i").click(function(){
		if($(this).text() == "深入选择展开"){
           $(this).text("深入选择收缩");
        }else{
           $(this).text("深入选择展开");
        }
	});
});

//下拉框
(function($) {
	$.fn.plusSelect = function(options) {
		//定义要用的参数
		var opts = {
			opt_1:'.selectauto',
			opt_2:'.count_Top li',
			opt_3:'.shtitle em',
			opt_num: 8 ,
			opt_4:'input[name=searchType]'
		};
		//合并参数供下面调用
		var opt = $.extend(opts, options);
		//fn内容开始
		return this.each(function() {
			var _obj = $(this);
			_obj.click(function(e) {
				e.stopPropagation();
				if(_obj.children(opt.opt_1).is(":hidden")){
                    _obj.children(opt.opt_1).show();
                    _obj.css({'z-index':6});
                }else{
                    _obj.children(opt.opt_1).hide();
                    _obj.css({'z-index':3});
                }

			})
			$(document).bind("click", function() {
				$(opt.opt_1).hide();
                _obj.css({'z-index':2});
			});
			var shsectHi = _obj.width();
			$(opt.opt_1).css({})
			$(opt.opt_2).click(function(e) {
				e.stopPropagation();
				var thisHtml = $(this).html();		
				$(opt.opt_3).html(thisHtml);
				$(opt.opt_1).hide();
			})
			// value
			$(opt.opt_2).click(function(){
				$(opt.opt_4).val($(this).attr('Svalue'));
			});
		});
		return this;
	}
})(jQuery);

$(function(){
	$(".shtitle").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top li',opt_3:'.shtitle em',opt_num: 8 ,opt_4: "input[name=searchType]"	});
	$(".shtitle2").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top2 li',opt_3:'.shtitle2 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle3").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top3 li',opt_3:'.shtitle3 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle4").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top4 li',opt_3:'.shtitle4 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle5").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top5 li',opt_3:'.shtitle5 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle6").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top6 li',opt_3:'.shtitle6 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle7").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top7 li',opt_3:'.shtitle7 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle8").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top8 li',opt_3:'.shtitle8 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle9").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top9 li',opt_3:'.shtitle9 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle10").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top10 li',opt_3:'.shtitle10 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
	$(".shtitle11").plusSelect({opt_1:'.selectauto',opt_2:'.count_Top11 li',opt_3:'.shtitle11 em',opt_num: 8 ,opt_4: "input[name=searchType]"});
});

//复选框
(function($) {
	$.fn.checkbox = function(options) {
		$(':checkbox+span', this).each(function() {
			$(this).addClass('checkbox');
			if ($(this).prev().is(':disabled') == false) {
				if ($(this).prev().is(':checked'))
					$(this).addClass("checked");
			} else {
				$(this).addClass('disabled');
			}
		}).click(function(event) {
			if (!$(this).prev().is(':checked')) {
				$(this).addClass("checked");
				$(this).prev()[0].checked = true;
			} else {
				$(this).removeClass('checked');
				$(this).prev()[0].checked = false;
			}
			event.stopPropagation();
		}).prev().hide();
	}
})(jQuery)