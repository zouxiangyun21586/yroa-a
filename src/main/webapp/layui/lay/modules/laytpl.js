/** layui-v2.2.5 MIT License By https://www.layui.com */
;
layui.define(function(e) {
    "use strict";
    var r = {
        open: "{{",
        close: "}}"
    },
    c = {
        exp: function(e) {
            return new RegExp(e, "g")
        },
        query: function(e, c, t) {
            var o = ["#([\\s\\S])+?", "([^{#}])*?"][e || 0];
            return n((c || "") + r.open + o + r.close + (t || ""))
        },
        escape: function(e) {
            return String(e || "").replace(/&(?!#?[a-zA-Z0-9]+;)/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;")
        },
        error: function(e, r) {
            var c = "Laytpl Error：";
            return "object" == typeof console && console.error(c + e + "\n" + (r || "")),
            c + e
        }
    },
    n = c.exp,
    t = function(e) {
        this.tpl = e
    };
    t.pt = t.prototype,
    window.errors = 0,
    t.pt.parse = function(e, t) {
        var o = this,
        p = e,
        a = n("^" + r.open + "#", ""),
        l = n(r.close + "$", "");
        e = e.replace(/\s+|\r|\t|\n/g, " ").replace(n(r.open + "#"), r.open + "# ").replace(n(r.close + "}"), "} " + r.close).replace(/\\/g, "\\\\").replace(n(r.open + "!(.+?)!" + r.close),
        function(e) {
            return e = e.replace(n("^" + r.open + "!"), "").replace(n("!" + r.close), "").replace(n(r.open + "|" + r.close),
            function(e) {
                return e.replace(/(.)/g, "\\$1")
            })
        }).replace(/(?="|')/g, "\\").replace(c.query(),
        function(e) {
            return e = e.replace(a, "").replace(l, ""),
            '";' + e.replace(/\\/g, "") + ';view+="'
        }).replace(c.query(1),
        function(e) {
            var c = '"+(';
            return e.replace(/\s/g, "") === r.open + r.close ? "": (e = e.replace(n(r.open + "|" + r.close), ""), /^=/.test(e) && (e = e.replace(/^=/, ""), c = '"+_escape_('), c + e.replace(/\\/g, "") + ')+"')
        }),
        e = '"use strict";var view = "' + e + '";return view;';
        try {
            return o.cache = e = new Function("d, _escape_", e),
            e(t, c.escape)
        } catch(u) {
            return delete o.cache,
            c.error(u, p)
        }
    },
    t.pt.render = function(e, r) {
        var n, t = this;
        return e ? (n = t.cache ? t.cache(e, c.escape) : t.parse(t.tpl, e), r ? void r(n) : n) : c.error("no data")
    };
    var o = function(e) {
        return "string" != typeof e ? c.error("Template not found") : new t(e)
    };
    o.config = function(e) {
        e = e || {};
        for (var c in e) r[c] = e[c]
    },
    o.v = "1.2.0",
    e("laytpl", o)
});
layui.laytpl.toDateString = function(d, format){
	  var date = new Date(d || new Date())
	  ,ymd = [
	    this.digit(date.getFullYear(), 4)
	    ,this.digit(date.getMonth() + 1)
	    ,this.digit(date.getDate())
	  ]
	  ,hms = [
	    this.digit(date.getHours())
	    ,this.digit(date.getMinutes())
	    ,this.digit(date.getSeconds())
	  ];

	  format = format || 'yyyy-MM-dd HH:mm:ss';

	  return format.replace(/yyyy/g, ymd[0])
	  .replace(/MM/g, ymd[1])
	  .replace(/dd/g, ymd[2])
	  .replace(/HH/g, hms[0])
	  .replace(/mm/g, hms[1])
	  .replace(/ss/g, hms[2]);
	};
	 
	//数字前置补零
	layui.laytpl.digit = function(num, length, end){
	  var str = '';
	  num = String(num);
	  length = length || 2;
	  for(var i = num.length; i < length; i++){
	    str += '0';
	  }
	  return num < Math.pow(10, length) ? str + (num|0) : num;
};