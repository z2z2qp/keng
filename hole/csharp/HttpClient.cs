public Response exec(HttpWevRequest request,JObject param){

    try{
        //doExec
        return reponse;
    }catch(Excption e){//未抛出异常，未做日志记录，异常被吃掉，增加排错难度
        return null;
    }
}

public Result thridApi(){
    var response = exec("",null)//response 会是null，整个程序运行到此处抛出npe，但实际原因被第六行catch吃掉
    if(reponse.getCode() == 0){
        //do something
    }
    return result;
}