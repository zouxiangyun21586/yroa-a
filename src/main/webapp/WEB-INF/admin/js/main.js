//获取系统时间
var newDate = '';
getLangDate();
//值小于10时，在前面补0
function dateFilter(date){
    if(date < 10){return "0"+date;}
    return date;
}
function getLangDate(){
    var dateObj = new Date(); //表示当前系统时间的Date对象
    var year = dateObj.getFullYear(); //当前系统时间的完整年份值
    var month = dateObj.getMonth()+1; //当前系统时间的月份值
    var date = dateObj.getDate(); //当前系统时间的月份中的日
    var day = dateObj.getDay(); //当前系统时间中的星期值
    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
    var hour = dateObj.getHours(); //当前系统时间的小时值
    var minute = dateObj.getMinutes(); //当前系统时间的分钟值
    var second = dateObj.getSeconds(); //当前系统时间的秒钟值
    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
    newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
    document.getElementById("nowTime").innerHTML = "亲爱的管理员，"+timeValue+"好！ 欢迎使用。当前时间为： "+newDate+"　"+week;
    setTimeout("getLangDate()",1000);
}
var tagData = [{"id":12,"name":"长者"},{"id":13,"name":"工厂"},{"id":14,"name":"小学生"},{"id":15,"name":"大学生"},{"id":16,"name":"研究生"},{"id":17,"name":"教师"},{"id":18,"name":"记者"}];    
var catData = [{"id":1,"name":"周边旅游","children":[{"id":24,"name":"广东","children":[{"id":7,"name":"广州"},{"id":23,"name":"潮州"}]}]},{"id":5,"name":"国内旅游","children":[{"id":8,"name":"华北地区","children":[{"id":9,"name":"北京"}]}]},{"id":6,"name":"出境旅游","children":[{"id":10,"name":"东南亚","children":[{"id":11,"name":"马来西亚","children":[{"id":20,"name":"沙巴","children":[{"id":21,"name":"美人鱼岛","children":[{"id":22,"name":"潜水"}]}]}]}]}]}];
var treeData = [{title: "节点1", value: "jd1", data: [{ title: "节点1.1", checked: true, disabled: true, value: "jd1.1", data: [] },{ title: "节点1.2", value: "jd1.2", checked: true, data: [] }, { title: "节点1.3", value: "jd1.3", disabled: true, data: [] }, { title: "节点1.4", value: "jd1.4", data: [] }]},{title: "节点2", value: "jd2", data: [{ title: "节点2.1", value: "jd2.1", data: [] }, { title: "节点2.2", value: "jd2.2", data: [] }, { title: "节点2.3", value: "jd2.3", data: [] }, { title: "节点2.4", value: "jd2.4", data: [] }]}, { title: "节点3", value: "jd3", data: [] }, { title: "节点4", value: "jd4", data: [] }];
layui.config({
    base : 'js/'
}).extend({
  selectN: 'layui_extends/selectN',
  selectM: 'layui_extends/selectM',
  echarts: 'echarts'
}).use(['form','element','layer','jquery','selectN','selectM'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element,
	    selectN = layui.selectN,
	    selectM = layui.selectM,
        $ = layui.jquery;
    
    	//数菜单
	    var xtree = new layuiXtree({
			elem : 'treeTest',
			data : treeData,
			form : form,
			ckall: true,
			icon: {        
	            end: "&#xe612;" 
	        }, color: {      
	            end: "#009688"   
	        }
		});
    
        //无限级分类-基本配置
        var catIns1 = selectN({
          //元素容器【必填】
          elem: '#cat_ids'
          //候选数据【必填】
          ,data: catData
        });     
        //多选下拉框标签-基本配置
        var tagIns1 = selectM({
          //元素容器【必填】
          elem: '#tag_ids'
          //候选数据【必填】
          ,data: tagData
        }); 
        form.on('submit(demo)',function(data){		
        	layer.msg("打开浏览器控制台查看内容")
          console.log('catIns 当前选中的值名：',catIns1.selected);
          console.log('catIns 当前选中的值：',catIns1.values);
          console.log('catIns 当前选中的名：',catIns1.names);
          console.log('catIns 当前最后一个选中值：',catIns1.lastValue);
          console.log('catIns 当前最后一个选中名：',catIns1.lastName);
          console.log('catIns 当前最后一个是否已选：',catIns1.isSelected);
          console.log('');

          console.log('tagIns 当前选中的值名：',tagIns1.selected);
          console.log('tagIns 当前选中的值：',tagIns1.values);
          console.log('tagIns 当前选中的名：',tagIns1.names);      
          
          console.log('');
          
          var formData = data.field;
          console.log('表单对象：',formData);
        });
         
    		//通过js动态选择
        $('.set').click(function(){
          catIns1.set([6,10]);
        });
        
    //上次登录时间【此处应该从接口获取，实际使用中请自行更换】
    $(".loginTime").html(newDate.split("日")[0]+"日</br>"+newDate.split("日")[1]);
    //icon动画
    $(".panel a").hover(function(){
        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
    },function(){
        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
    })
    $(".panel a").click(function(){
        parent.addTab($(this));
    })
    //系统基本参数
    if(window.sessionStorage.getItem("systemParameter")){
        var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
        fillParameter(systemParameter);
    }else{
        $.ajax({
            url : "json/systemParameter.json",
            type : "get",
            dataType : "json",
            success : function(data){
                fillParameter(data);
            }
        })
    }
    //填充数据方法
    function fillParameter(data){
        //判断字段数据是否存在
        function nullData(data){
            if(data == '' || data == "undefined"){
                return "未定义";
            }else{
                return data;
            }
        }
        $(".version").text(nullData(data.version));      //当前版本
        $(".author").text(nullData(data.author));        //开发作者
        $(".homePage").text(nullData(data.homePage));    //网站首页
        $(".server").text(nullData(data.server));        //服务器环境
        $(".dataBase").text(nullData(data.dataBase));    //数据库版本
        $(".maxUpload").text(nullData(data.maxUpload));    //最大上传限制
        $(".userRights").text(nullData(data.userRights)); //当前用户权限
    }

    //外部图标
    $.get(iconUrl,function(data){
        $(".outIcons span").text(data.split(".icon-").length-1);
    })
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        title : {
            text: '未知情况',
            subtext: '纯属虚构'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['意向','预购','成交']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'成交',
                type:'line',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:[10, 12, 21, 54, 260, 830, 710]
            },
            {
                name:'预购',
                type:'line',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:[30, 182, 434, 791, 390, 30, 10]
            },
            {
                name:'意向',
                type:'line',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:[1320, 1132, 601, 234, 120, 90, 20]
            }
        ]
    };     
    myChart.setOption(option);
    
});

