# nginx 配置 示例中试用 url参数传递token
```conf
server {
    listen       8181;
    server_name  localhost;
    #charset koi8-r;

    #access_log  logs/host.access.log  main;
    location /proxy {    #实际代理的地址
        set $http_query $is_args$args;#请求参数 传递
        auth_request /auth; # 鉴权地址
        add_header Access-Control-Allow-Origin "*" always;
        add_header Access-Control-Allow-Methods "GET,POST,PUT,DELETE,OPTIONS" always;
        add_header Access-Control-Allow-Credentials true always;
        add_header Access-Control-Allow-Headers DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,x-auth-token always;
        add_header Access-Control-Max-Age 1728000 always;
        if ($request_method = OPTIONS) {
            return 204;
        }
        proxy_pass http://localhost:9090/;
        proxy_set_header Host $http_host;
        proxy_set_header X_Real_IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

    }
    location /auth { # 鉴权代理
        internal;
        proxy_pass http://localhost:8080/sb/checkToken$http_query; # 增加请求参数
        add_header Access-Control-Allow-Origin "*" always;
        add_header Access-Control-Allow-Methods "GET,POST,PUT,DELETE,OPTIONS" always;
        add_header Access-Control-Allow-Credentials true always;
        add_header Access-Control-Allow-Headers DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,x-auth-token always;
        add_header Access-Control-Max-Age 1728000 always;
        proxy_set_header Host $http_host;
        proxy_set_header X_Real_IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

# 鉴权 controller 
```java
@RequestMapping("checkToken")
    public ResponseEntity<Void> checkToken(HttpServletRequest request) {
        //#region 获取cookie 中的token 未实现
        var cookies = request.getCookies();
        var token = Stream.of(cookies)
                .filter(it -> it.getName().equals("token"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        //#endregion

        if (token == null) {
            token = request.getParameter("token");//直接通过url参数获得token
        }
        if ("token".equals(token)) {//校验token
            return ResponseEntity.ok(null);//通过返回200
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();//失败返回401

    }
```