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
	elem: '#leaveDemo',
	loading:true,	
	url: path+"leave/Leave", //请求路径
	limit:7,
	limits:[4,7,10,15],
	page:true,
	where: {
		name:null,
	},cols: [[//需显示的字段
			{type:'checkbox', fixed: 'left'},
			{type:'numbers',field: 'classCode',title:'届次编码',width:50},
			{field: 'className', title: '批次名', unresize: true},
			{field: 'studentName', title: '学生姓名', unresize: true},
			{field: 'leaveDate', title: '请假日期',  unresize: true},
			{field: 'leaveType', title: '请假类型', width:90, align:'center', templet: function(e){
				var leat;
				if('TH'==e.leaveType){
					leat='<span style="font-size:5px;color:#009688;">事假</span>'
				} else if('SK' ==e.leaveType) {
					leat='<span style="font-size:5px;color:#FFB800;">病假</span>'
				}
				return leat;
			}, unresize: true},
			{field: 'leaveHour', title: '请假时间' ,unresize: true},
			{field: 'leaveTimeLong', title:'请假时长',  unresize: true},
			{field: 'leaveDesc', title:'请假事因',  unresize: true},
			{field: 'imgUrl', title:'请假附条',  unresize: true},
			{field: 'leaveAccount', title:'请假账户',  unresize: true},
			{field: 'isAudit', title:'审核', width:90, align:'center', templet: function(f){
				var isA;
				if('UN'==f.isAudit || ''==f.isAudit){
					isA='<span style="font-size:5px;color:#009688;">未审核</span>'
				} else if('YE' == f.isAudit) {
					isA='<span style="font-size:5px;color:#FFB800;">允许请假</span>'
				} else if('NO' == f.isAudit) {
					isA='<span style="font-size:5px;color:#FFB800;">不允许请假</span>'
				}
				return isA;
			}, unresize: true},
			
			{field: 'auditTime', title:'审核时间',  unresize: true},
			{fixed: 'right',title:'操作', width:80, align:'center', toolbar: '#barDemo',unresize:true}
		]]
	});
		
	//搜索
	$(".search_btn").on("click",function(){
		if($(".searchVal").val() != ''){
			table.reload('leaveDemo',{
				where: {
				   name:$(".searchVal").val()
				 },page:{
					 curr:1
				 }
			});
		}else{
            layui.layer.tips('请输入内容', '.searchVal', {
                tips: 3
            });
		}
	});
		
	//监听工具条
	table.on('tool(leaveDemo)', function(obj){
	  var data = obj.data;
	  if(obj.event === 'del'){
	    layer.confirm('确定要取消么', function(index){
	    	layer.close(index);
	    	var index = top.layer.msg('正在取消...请稍候',{icon: 16,time:false,shade:0.8});
	    	$.ajax({
    	       type:"post",
    	       url:path+"leave/Leave",
    	       data: {"id":obj.data.id,"_method":"DELETE"},
    	       success:function(data){
    	    	   if(200==data.code){
    	    		   setTimeout(function(){
	   			            top.layer.close(index);
	   			        	top.layer.msg(data.msg,{icon:1});
	   			        	table.reload('leaveDemo',{
	   			        		where: {
	   			        			name:null
	   			        		}
	   			        	});
	   			        },1000);
    	    	   }else {
    	    		   setTimeout(function(){
	   			            top.layer.close(index);
	   			        	top.layer.msg(data.msg,{icon:2});
	   			        	table.reload('leaveDemo',{
	   			        		where: {
	   			        			name:null
	   			        		}
	   			        	});
	   			        },1000);
    	    	   }
    	       },error : function() {
					setTimeout(function(){
					    top.layer.close(index);
					    top.layer.msg("异常!",{icon:2});
						location.reload();
					},1000);
    	       }
	     	});
	      layer.close(index);
	    });
	  }else if(obj.event === 'edit'){
		  alert(path+"attendance/leaveUpd?code="+obj.data.studentCode);
		  alert(123456);
		  var index = layui.layer.open({
				title : "审核",
				type : 2,
				anim : 5,
				content : path+"attendance/leaveUpd?code="+obj.data.studentCode, // 审核页面路径
				success : function(layero, index) {
					setTimeout(function() {
						layui.layer.tips('点击此处返回',
							'.layui-layer-setwin .layui-layer-close', {
								tips : 3
							});
					}, 500);
				}
			});
			layui.layer.full(index);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(index);
			});
	  }
	});
		
	//添加假条
	function addStudent(edit){
		var index = layui.layer.open({
			title : "添加假条",
			type : 2,
			anim : 5,
			content : "leaveAdd",
			success : function(layero, index) {
				setTimeout(function() {
					layui.layer.tips('点击此处返回',
							'.layui-layer-setwin .layui-layer-close', {
								tips : 3
							});
				}, 500);
			}
		});
		layui.layer.full(index);
		// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
		$(window).on("resize", function() {
			layui.layer.full(index);
		});
    }
    $(".addLeave_btn").click(function(){
    	addStudent();
    });
});