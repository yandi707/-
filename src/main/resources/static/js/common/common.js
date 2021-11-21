function loadSessionUser(){
    axios.get("/login/loadsession").then(function(res){
        // 把获取的用户信息放入到你指定的div位置即可
        console.log(res);
    })
}
// 执行页面获取用户信息
loadSessionUser();