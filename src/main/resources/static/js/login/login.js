var vue = new Vue({
    el:"#app",
    data:{
        title:"MoMo登录",
        user:{
            account:"10000",
            password:"123456",
            code:""
        }
    },
    methods:{
        toLogin:function(){
            var that = this;
            var user = that.user;

            axios.post("/logined",user).then(function(res){
                 if(res.data == "success"){
                     window.location.href = "/";
                 }else{
                     alert("输入账号密码有误");
                     document.getElementById("pwd").value = "";
                     document.getElementById("pwd").focus();
                 }
            })
        }
    }
});