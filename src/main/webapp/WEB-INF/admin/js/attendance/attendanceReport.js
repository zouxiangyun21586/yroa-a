layui.use(['table','form','tree','element'], function(){
  var table = layui.table,
  form = layui.form,
  $=layui.jquery,
  element=layui.element,
  tree=layui.tree,
  strFullPath = window.document.location.href,
  	strPath = window.document.location.pathname,
	pos = strFullPath.indexOf(strPath),
	prePath = strFullPath.substring(0, pos),
	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
  
  
		table.render({
		  elem: '#demo',
		  loading:true,
		  url: path+"attendance/report?ckStatus=AM", //请求路径
		  limit:7,
		  limits:[4,7,10,15],
		  page:true,
		  where: {
			   name:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{type:'numbers',title:'编号',width:50},
				{field: 'name', title: '上午考勤情况', unresize: true, align:'center', templet: function(d){
					var state;
					if(0==d.status && "AM"==d.checkStatus){
						state=d.name+'<span style="font-size:5px;color:#00FF00;">'+d.statusDesc+'</span>'
					}else if(1==d.status && "AM"==d.checkStatus){
						state=d.name+'<span style="font-size:5px;color:#FF2400;">'+d.statusDesc+'</span>'
					}else if(2==d.status && "AM"==d.checkStatus){
						state=d.name+'<span style="font-size:5px;color:#FF0000;">'+d.statusDesc+'</span>'
					}else if(3==d.status && "AM"==d.checkStatus){
						state=d.name+'<span style="font-size:5px;color:#0000FF;">'+d.statusDesc+'</span>'
					}else if(4==d.status && "AM"==d.checkStatus){
						state=d.name+'<span style="font-size:5px;color:#BC1717;">'+d.statusDesc+'</span>'
					}
					return state;
				}}
		 ]]
		});
		table.render({
			  elem: '#demo1',
			  loading:true,
			  url: path+"attendance/report?ckStatus=PM", //请求路径
			  limit:7,
			  limits:[4,7,10,15],
			  page:true,
			  where: {
				   name:null,
			  },cols: [[//需显示的字段
					{type:'checkbox', fixed: 'left'},
					{type:'numbers',title:'编号',width:50},
					{field: 'name', title: '下午考勤情况', unresize: true, align:'center', templet: function(d){
						var state;
						if(0==d.status && "PM"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#00FF00;">'+d.statusDesc+'</span>'
						}else if(1==d.status && "PM"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#FF2400;">'+d.statusDesc+'</span>'
						}else if(2==d.status && "PM"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#FF0000;">'+d.statusDesc+'</span>'
						}else if(3==d.status && "PM"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#0000FF;">'+d.statusDesc+'</span>'
						}else if(4==d.status && "PM"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#BC1717;">'+d.statusDesc+'</span>'
						}
						return state;
					}}
			 ]]
			});
		table.render({
			  elem: '#demo2',
			  loading:true,
			  url: path+"attendance/report?ckStatus=NT", //请求路径
			  limit:7,
			  limits:[4,7,10,15],
			  page:true,
			  where: {
				   name:null,
			  },cols: [[//需显示的字段
					{type:'checkbox', fixed: 'left'},
					{type:'numbers',title:'编号',width:50},
					{field: 'name', title: '晚上考勤情况', unresize: true, align:'center', templet: function(d){
						var state;
						if(0==d.status && "NT"==d.setCheckStatus){
							state=d.name+'<span style="font-size:5px;color:#00FF00;">'+d.statusDesc+'</span>'
						}else if(1==d.status && "NT"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#FF2400;">'+d.statusDesc+'</span>'
						}else if(2==d.status && "NT"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#FF0000;">'+d.statusDesc+'</span>'
						}else if(3==d.status && "NT"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#0000FF;">'+d.statusDesc+'</span>'
						}else if(4==d.status && "NT"==d.checkStatus){
							state=d.name+'<span style="font-size:5px;color:#BC1717;">'+d.statusDesc+'</span>'
						}
						return state;
					}}
			 ]]
			});
});