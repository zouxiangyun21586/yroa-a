/** layui-v2.2.5 MIT License By https://www.layui.com */
 ;layui.define("jquery",function(i){var e,o=layui.$,n=layui.device(),d="dropdown",t=".layui-dropdown-menu",r="px",a=n.android||n.ios?"click":"mouseover";Dropdown=function(){this.inst=null,this.currReElem=null};var s=function(i){l(i.target)&&l(i.target.parentElement)&&l(i.target.parentElement.parentElement)&&e.hide()},l=function(i){return i&&i.className.indexOf("layui-dropdown")==-1&&i.className.indexOf("layui-dropdown-menu")==-1};Dropdown.prototype.hide=function(){e&&e.inst&&e.inst.is(":visible")&&(e.inst.removeClass("show").addClass("hide"),o("body").off(a,s))},Dropdown.prototype.render=function(){e=this,o(".layui-dropdown").each(function(i,d){var l=o(d);l.data("id","dropdown-"+i),a=n.android||n.ios?"click":"mouseover",l[a](function(){if(!e.inst||e.currReElem.data("id")!=l.data("id")||e.currReElem.data("id")==l.data("id")&&!e.inst.is(":visible")){e.hide();var i=l.find(t),n=l.offset().left-o(window).scrollLeft(),d=l.offset().top,u=l.width(),c=l.height(),w=o(window).scrollTop(),f=d+c-w-2,p=i.width(),h=i.outerHeight(),m=l.width(),n=n-(p-u)/2,v=n+m,y=n+p>o(window).width(),E=d+c+h-w>o(window).height(),g={position:"fixed",top:f+r,left:n+r};y&&o.extend(!0,g,{left:v-p+u/2+r}),E&&o.extend(!0,g,{top:d-h-w+r}),i.removeClass("hide").addClass("show"),i.css(g).on("click","li",function(){e.hide()}),e.inst=i,e.currReElem=l,o("body").on(a,s)}})})};var u=new Dropdown;u.render(),o(window).scroll(function(){u.hide()}),i(d,u)});