(()=>{var n=[],t=!1,o=!1,e=!1,r=new Proxy({now:" ",history:" "},{set(n,t,o){n[t]=""+o,a()}}),a=function(){$("#show").text(r.now),length=r.history.length,$("#history").text(r.history.substring(length-50,length))};$(".buttons").on("click","button",(function(){let n=$(this);switch(n.attr("func")){case"num":i(n.attr("num"));break;case"op":h(n.attr("char"),n.attr("op"));break;case"tri":k(n.attr("op"));break;case"pow2":w();break;case"pow10":y();break;case"sqrt":m();break;case"log":g();break;case"C":u();break;case"CE":f();break;case"Delete":p();break;case"factorial":d();break;case"Br":s(n.attr("char"));break;case"result":l();break;case"rev":b()}}));var i=function(n){o&&(r.history=""),t&&!o||(r.now="",t=!0,o=!1),"PI"==n?r.now=String(Math.PI):"EXP"==n?r.now=String(Math.E):r.now+=n},s=function(o){t||"("!=o?t&&")"==o&&(r.history+=r.now+o,n.push(r.now,o),r.now=""):(r.history+=o,n.push(o))},h=function(e,a){o?(r.history=r.now+e,n.push(r.now,null==a?e:a),o=!1,t=!1):t&&(r.history+=r.now+e,n.push(r.now,null==a?e:a),t=!1)},l=function(){o||!t||e||(n.push(r.now),e=!0,fetch("/api/handle",{method:"POST",body:JSON.stringify(n),headers:new Headers({"Content-type":"application/json"})}).then((n=>n.json())).then((t=>{console.log(t),r.history+=r.now+"=",r.now=t,n=[],o=!0})).finally((()=>e=!1)))},c=function(){o&&(r.history="",o=!1)},w=function(){t&&!e&&(c(),e=!0,fetch("/api/pow",{method:"POST",body:JSON.stringify({num:r.now,pow:2,headers:{"Content-type":"application/json"}})}).then((n=>{if(n.ok)return console.log(n),n.json();throw new Error("网络错误！")})).then((n=>{r.now=n.result})).catch((n=>alert(n))).finally((()=>e=!1)))},u=function(){r.now="",r.history="",n=[],t=!1,o=!1},f=function(){r.now=""},p=function(){r.now=r.now.substring(0,r.now.length-1),0==r.now.length&&(t=!1)},y=function(){t&&(c(),e=!0,fetch("/api/pow",{method:"POST",body:JSON.stringify({num:10,pow:r.now,headers:{"Content-type":"application/json"}})}).then((n=>{if(n.ok)return console.log(n),n.json();throw new Error("网络错误！")})).then((n=>{r.now=n.result})).catch((n=>alert(n))).finally((()=>e=!1)))},g=function(){t&&!e&&(c(),e=!0,fetch("/api/log",{method:"POST",body:JSON.stringify({num:r.now,headers:{"Content-type":"application/json"}})}).then((n=>{if(n.ok)return console.log(n),n.json();throw new Error("网络错误！")})).then((n=>{r.now=n.result})).catch((n=>alert(n))).finally((()=>e=!1)))},b=function(){t&&(c(),e=!0,fetch("/api/rev",{method:"POST",body:JSON.stringify({num:r.now,headers:{"Content-type":"application/json"}})}).then((n=>{if(n.ok)return console.log(n),n.json();throw new Error("网络错误！")})).then((n=>{r.now=n.result})).catch((n=>alert(n))).finally((()=>e=!1)))},d=function(){t&&!e&&(Number.isInteger(Number.parseFloat(r.now))?(c(),e=!0,fetch("/api/factorial",{method:"POST",body:JSON.stringify({num:r.now,headers:{"Content-type":"application/json"}})}).then((n=>{if(n.ok)return console.log(n),n.json();throw new Error("网络错误！")})).then((n=>{r.now=n.result})).catch((n=>alert(n))).finally((()=>e=!1))):alert("暂时无法对浮点数求阶乘！"))},k=function(n){t&&(c(),e=!0,fetch("/api/triangle",{method:"POST",body:JSON.stringify({type:n,num:r.now}),headers:new Headers({"Content-type":"application/json"})}).then((n=>{if(n.ok)return console.log(n),n.json();throw new Error("网络错误！")})).then((n=>{n.state?alert(n.state):r.now=n.result})).catch((n=>alert(n))).finally((()=>e=!1)))},m=function(){t&&!e&&(c(),e=!0,fetch("/api/sqrt",{method:"POST",body:JSON.stringify({num:r.now}),headers:{"Content-type":"application/json"}}).then((n=>{if(n.ok)return console.log(n),n.json();throw new Error("网络错误！")})).then((n=>{r.now=n.result})).finally((()=>e=!1)))}})();