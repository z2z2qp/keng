# Git 请求合并说明
0. 在本地创建本地分支
```shell
git checkout -b {name}
```
1. 修改代码
2. 推送分支
```shell
git add {文件}
git commit -a -m "信息"
git push -u origin {name}
```
3. web页面 请求合并

# 7z 命令
1. 解压 
```shell
7z x archive.zip
```
2. 压缩
```shell
7z a archive.zip archive
```

# tar 命令
1. 解压 
```shell
tar -xzvf archive.tar.gz
```
2. 压缩
```shell
tar -czvf archive.tar.gz archive
```

1 Byte(字节) = 8 bit(位)


# docker 命令
1. 打包
```docker
docker build -t [name] .
```
2. 标记
```docker
docker tag [name] [tagName]:[version]
```
3. 推送
```docker
docker push [tagName]:[version]
```