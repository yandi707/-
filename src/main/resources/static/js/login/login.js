var vue = new Vue({
    el:"#app",
    data:{
        title:"MoMo登录",
        user:{
            account:"123456467",
            password:"1111",
            code:""
        }
    },
    methods:{
        toLogin:function(){
            var that = this;
            var user = that.user;

            if(!user.account){
                alert("请输入账号")
                document.getElementById("account").focus();
                return;
            }

            if(!user.password){
                alert("请输入密码")
                document.getElementById("pwd").focus();
                return;
            }

            if(!user.code){
                alert("请输入验证码")
                document.getElementById("code").focus();
                return;
            }

            axios.post("/logined",user).then(function(res){
                 if(res.data == "success"){
                     window.location.href = "/";
                 }else if(res.data=="failcode"){
                     alert("你的验证码输入有误");
                     document.getElementById("code").value = "";
                     document.getElementById("code").focus();
                     document.getElementById("ksdimgcode").src = "/kaptcha?d="+new Date().getTime();
                 }else{
                     alert("输入账号密码有误");
                     document.getElementById("pwd").value = "";
                     document.getElementById("pwd").focus();
                 }
            })
        }
    }
});
