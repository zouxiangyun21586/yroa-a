/*
* @version: 1.2
* @Author:  tomato
* @Date:    2018-4-24 22:56:00
* @Last Modified by:   tomato
* @Last Modified time: 2018-5-21 22:07:26
*/
//无限级下拉框
layui.define(['jquery', 'form'], function(exports){
		var MOD_NAME = 'selectN';
		var $ = layui.jquery;
		var form = layui.form;
    var obj = function(config){
		//当前选中数据值名数据
		this.selected =[];
		//当前选中的值
		this.values = [];
		//当前选中的名
		this.names = [];
		//当前选中最后一个值
		this.lastValue = '';
		//当前选中最后一个值
		this.lastName = '';		
		//是否已选
		this.isSelected = false;
		//初始化配置
		this.config = {
			//选择器id或class
			elem: '',
			//无限级分类数据
			data: [],
			//默认选中值
			selected: [],
			
			//空值项提示，可设置为数组['请选择省','请选择市','请选择县']
			tips: '请选择',
			
			//为真只取最后一个值
			last: false,
			
			verify: '',
			
			//事件过滤器，lay-filter名
			filter: '',

			//input的name 不设置与选择器相同(去#.)
			name: '',
			
			//数据分隔符
			delimiter: ',',
			
			//数据的键名
			field:{idName:'id',titleName:'name',childName:'children'},
			
			//表单区分 form.render(type, filter); 为class="layui-form" 所在元素的 lay-filter="" 的值 
			formFilter: null
		}
		
		//实例化配置
		this.config = $.extend(this.config,config);

		//“请选择”文字
		this.getTips = function(){
			var o = this,c = o.config;
			if(Object.prototype.toString.call(c.tips)!='[object Array]'){
				return c.tips;
			}
			else{
				var i=$(c.elem).find('select').length;
				return c.tips.hasOwnProperty(i) ? c.tips[i] : '请选择'; 
			}
		}
		
		//创建一个Select
		this.createSelect = function(optionData){
			var o = this,c = o.config,f=c.field;
			var html = '';
			html+= '<div class="layui-input-inline">';
			html+= ' <select lay-filter="'+c.filter+'">';
			html+= '  <option value="">'+o.getTips()+'</option>';
			for(var i=0;i<optionData.length;i++){
				html+= '  <option value="'+optionData[i][f.idName]+'">'+optionData[i][f.titleName]+'</option>';
			}
			html+= ' </select>';
			html+= '</div>';
			return html;
		};

		//获取当前option的数据
		this.getOptionData=function(catData,optionIndex){
			var f = this.config.field;
			var item = catData;
			for(var i=0;i<optionIndex.length;i++){
				if('undefined' == typeof item[optionIndex[i]]){
					item = null;
					break;      
				}
				else if('undefined' == typeof item[optionIndex[i]][f.childName]){
					item = null;
					break;
				}
				else{
					item = item[optionIndex[i]][f.childName];
				}
			}
			return item;
		};

		//初始化
		this.set = function(selected){
			var o = this,c = o.config;
			$selectWrap = $(c.elem);
			//创建顶级select
			var verify = c.verify=='' ? '' : 'lay-verify="'+c.verify+'" ';
			$selectWrap.html('<input '+verify+'name="'+c.name+'" type="hidden">');
			var html = o.createSelect(c.data);
			$selectWrap.append(html);
			selected = typeof selected=='undefined' ? c.selected : selected;
			var index=[];
			for(var i=0;i<selected.length;i++){
				//设置最后一个select的选中值
				$selectWrap.find('select:last').val(selected[i]);
				//获取该选中值的索引
				var lastIndex = $selectWrap.find('select:last').get(0).selectedIndex-1; 
				index.push(lastIndex);
				//取出下级的选项值
				var childItem = o.getOptionData(c.data,index);
				//下级选项值存在则创建select
				if(childItem){
					var html = o.createSelect(childItem);
					$selectWrap.append(html);
				}
			}
			form.render('select',c.formFilter);
			o.getSelected();		
			
		};
		
		//下拉事件
		this.change = function(elem){
			var o = this,c = o.config;
			var $thisItem = elem.parent();
			//移除后面的select
			$thisItem.nextAll('div.layui-input-inline').remove();
			var index=[];
			//获取所有select，取出选中项的值和索引
			$thisItem.parent().find('select').each(function(){
				index.push($(this).get(0).selectedIndex-1);
			});
			
			var childItem = o.getOptionData(c.data,index);
			if(childItem){
				var html = o.createSelect(childItem);
				$thisItem.after(html);
				form.render('select',c.formFilter);
			}
			this.getSelected();			
		};

		//获取所有值-数组 每次选择后执行
		this.getSelected=function(){
			var o = this,c = o.config;
			var values =[];
			var names =[];
			var selected =[];
			$selectWrap = $(c.elem);
			$selectWrap.find('select').each(function(){
				var item = {};
				var v = $(this).val()
				var n = $(this).find('option:selected').text();
				item.value = v;
				item.name = n;
				values.push(v);
				names.push(n);
				selected.push(item);
			});
			o.selected =selected;			
			o.values = values;
			o.names = names;
			o.lastValue = $selectWrap.find('select:last').val();
			o.lastName = $selectWrap.find('option:selected:last').text();
			
			o.isSelected = o.lastValue=='' ? false : true;
			var inputVal = c.last===true ? o.lastValue : o.values.join(c.delimiter);
			$selectWrap.find('input[name='+c.name+']').val(inputVal);
		};
	};

	//渲染一个实例
  obj.prototype.render = function(){
		var o=this,c=o.config;
		$selectWrap = $(c.elem);
		if($selectWrap.length==0){
			console.error(MOD_NAME+' hint：找不到容器 '+c.elem);
			return false;
		}
			
		if(Object.prototype.toString.call(c.data)!='[object Array]'){
			console.error(MOD_NAME+' hint：缺少分类数据');
			return false;
		}
		

		c.filter = c.filter=='' ? c.elem.replace('#','').replace('.','') : c.filter;
		c.name = c.name=='' ? c.elem.replace('#','').replace('.','') : c.name;
	
		o.config = c;
		
		
		//初始化
		o.set();
		
		//监听下拉事件
		form.on('select('+c.filter+')',function(data){
			o.change($(data.elem));	
		});
		
		//重置
		var $form=$selectWrap.parents('form');
		$form.find(':reset').click(function(){
			$form[0].reset();
			o.set();
			return false;
		});
	}
	
	//输出模块
	exports(MOD_NAME, function (config) {
		var _this = new obj(config);
		_this.render();
		return _this;
  });
});