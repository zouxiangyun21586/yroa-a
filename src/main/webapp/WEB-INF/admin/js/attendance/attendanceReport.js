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
  
		table.render({
		  elem: '#demo',
		  loading:true,
		  url: path+"attendance/report", //请求路径
		  limit:7,
		  limits:[4,7,10,15],
		  page:true,
		  where: {
			   name:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{type:'numbers',title:'编号',width:50},
				{field: 'name', title: '用户名', unresize: true},
				{field: 'userName', title: '账号', unresize: true},
				{field: 'passWord', title: '密码',  unresize: true},
				{field: 'email', title: '邮箱',  unresize: true},
				{field: 'insertTime', title: '注册时间',templet: function(d) {
                    return d.insertTime.time;
                }, unresize: true},
				{field: 'status', title:'状态', width:90,align:'center', templet: function(d){
					var state;
					if(0==d.status){
						state='<span style="font-size:5px;color:#009688;">可使用</span>'
					}else if(1==d.status){
						state='<span style="font-size:5px;color:#FFB800;">未激活</span>'
					}else if(2==d.status){
						state='<span style="font-size:5px;color:#ff0000;">已禁用</span>'
					}
					return state;
				}, unresize: true},
				{fixed: 'right',title:'操作', width:80, align:'center', toolbar: '#barDemo',unresize:true}
		 ]]
		});
});