var a = false;
localStorage.setItem("a",a);
var b = localStorage.getItem("a");// b 是字符串  值是false
if(b){
    //会走
}else{
    //不会走
}
b == a;//false