var Config = function(render,option,values){
	this.option = option || {};
	this.values = values;
	this.render = $(render);
	this.init();
};

Config.prototype.setValue = function(value){
	if(null == value || value == ""){
		return;
	}
	var val = eval("("+value+")");
	for(var p in val){
		var value = val[p];
		$(this.ul).find("[name="+p+"]").val(value);
	}
};

Config.prototype.getValue = function(){
	var controls = $(this.ul).find("input,select");
	var values = "{";
	if(controls && controls.length > 0){
		for(var i=0;i<controls.length;i++){
			var control = $(controls[i]);
			var name = control.attr("name");
			var value = control.val();
			values[name] = value;
			values += "\"" + name + "\": \"" +value+"\"";
			if(i<controls.length-1){
				values += ",";
			}
		}
	}
	values+="}"
	return values;
};

Config.prototype.init = function(){
	if(this.option && this.option.items){
		var items = this.option.items;
		this.ul = $("<ul class='dyform'></ul>").appendTo(this.render);
		if(items && items.length > 0){
			for(var i=0;i<items.length;i++){
				var opt = items[i];
				this.draw(opt);
			}
		}
	}
};

Config.prototype.draw = function(config){
	if(config.type == "input"){
		this.renderInput(config);
	}else if(config.type == "select"){
		this.renderSelect(config);
	}else if(config.type == "radio"){
		this.renderRadio(config);
	}else if(config.type == "check"){
		this.renderCheck(config);
	}else if(config.type == "label"){
		this.renderLabel(config);
	}
};

Config.prototype.renderLabel = function(config){
	var prefix = config.prefix ? config.prefix : "";
	var suffix = config.suffix ? config.suffix : "";
	var input = $("<li><span class='label'>"+config.label+"</span>"+prefix+suffix+"</li>").appendTo(this.ul);
};

Config.prototype.renderInput = function(config){
	var prefix = config.prefix ? config.prefix : "";
	var suffix = config.suffix ? config.suffix : "";
	var input = $("<li><span class='label'>"+config.label+"</span>"+prefix+"<input type='text' name='"+config.name+"'/>"+suffix+"</li>").appendTo(this.ul);
	var $input = $($(input).children("input"));
	$input.attr("name",config.name);
	if(config.defaultValue){
		$input.val(config.defaultValue);
	}
};

Config.prototype.renderSelect = function(config){
	var select = $("<li><span class='label'>"+config.label+"</span><select name='"+config.name+"'></select></li>").appendTo(this.ul);
	var $select = $($(select).children("select"));
	$select.attr("name",config.name);
	$("<option value='' selected='selected'>--请选择--</option>").appendTo($select);
	if(config.items){
		var items = config.items;
		for(var i=0;i<items.length;i++){
			var item = items[i];
			$("<option value='"+item.key+"'>"+item.value+"</option>").appendTo($select);
		}
	}
	if(config.defaultValue){
		$select.val(config.defaultValue);
	}
};

Config.prototype.renderRadio = function(){
	var input = $("<li><span class='label'>"+config.label+"</span><input type='radio' name='"+config.name+"'/></li>").appendTo(this.ul);
	var $input = $($(input).children("input"));
	$input.attr("name",config.name);
	if(config.defaultValue){
		$input.val(config.defaultValue);
	}
};

Config.prototype.renderCheck = function(){
	var input = $("<li><span class='label'>"+config.label+"</span><input type='checkbox' name='"+config.name+"'/></li>").appendTo(this.ul);
	var $input = $($(input).children("input"));
	$input.attr("name",config.name);
	if(config.defaultValue){
		$input.val(config.defaultValue);
	}
};
