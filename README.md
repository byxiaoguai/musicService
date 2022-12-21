# vipSystem

#### 介绍
不做过多介绍
音乐播放器
QQ音乐
咪咕音乐

### VIPcookie 设置
QA:不加这2个东西你咋vip解析音乐了？
!!!! 2个注意

linux 环境

/opt/qq.txt 创建个文件 里面放qqcookie就好了。

/opt/mg.txt 创建个文件 里面放json

{
"by":"咪咕音乐网站获取by :hader",
"cookie":"咪咕cookie",
"ua":"咪咕请求的ua"

}

### 命令脚本

！！！！前提你linux 服务器 系统安装好java JDK JDK JDK 1.8 1.8 1.8

创建启动脚本，别问放那当前目录 跟jar包在一起就好了！

文件名称  start.sh   #可以取名字可以不取随便你把~

里面的代码

nohup java -jar vip-0.0.2-SNAPSHOT.jar  > nohup.out 2>&1 &  

停止运行脚本

stop.sh

里面代码

PID=$(ps -ef | grep vip-0.0.2-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
echo Application is already stopped
else
echo kill -9 $PID
kill -9 $PID
fi
