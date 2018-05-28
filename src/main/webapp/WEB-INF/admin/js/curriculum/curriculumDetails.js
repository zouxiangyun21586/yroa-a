layui.use(['table','form','tree'], function(){
  var table = layui.table,
  form = layui.form,
  $=layui.jquery,
  tree=layui.tree,
  strFullPath = window.document.location.href,
  	strPath = window.document.location.pathname,
	pos = strFullPath.indexOf(strPath),
	prePath = strFullPath.substring(0, pos),
	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
		
	  	$.getUrlParam = function (name) {  
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
			if (r != null) return decodeURI(r[2]); return null; //返回参数值  
		}
		$.getUrlParam('code');

  
		table.render({
		  elem: '#sous',
		  loading:true,
		  url: path+"clasDetails?code="+data.code, //请求路径
		  limit:7,
		  limits:[4,7,10,15],
		  page:true,
		  where: {
			   year:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{type:'name',title:'学生名',width:50},
				{type:'finishTime',title:'毕业时间',width:50},
				{field: 'offerTime', title: '工作时间', unresize: true},
				{field: 'offerIncome', title: '工资', unresize: true},
		 ]]
		});
		
		//搜索
		$(".search_details").on("click",function(){
			if($(".searchDetailsVal").val() != ''){
				table.reload('sous',{
					where: {
					   year:$(".searchDetailsVal").val()
					 },page:{
						 curr:1
					 }
				});
			}else{
                layui.layer.tips('请输入内容', '.searchDetailsVal', {
                    tips: 3
                });
			}
		});
		
		
});