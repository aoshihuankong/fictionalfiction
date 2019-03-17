# fictionalfiction
2019年毕业设计——基于springboot网络小说系统设计和实现

## 开发流程
### 一、扩充阿里云服务器swap交换区大小

* 参考资料：
1. https://help.aliyun.com/knowledge_detail/42534.html
* 默认内存如果小于2G，则虚拟内存大小和其相同，大于2G，则设置虚拟内存为2G

### 二、服务器安装docker
* 参考资料：
1. http://www.docker.org.cn/book/install/supported-platform-17.html
2. https://cr.console.aliyun.com/cn-hangzhou/mirrors

### 三、使用docker安装redis和mysql

* 参考资料：
1. https://hub.docker.com/_/redis
2. https://hub.docker.com/_/mysql/

### 四、报文设计
1. 书籍搜索
> {"tatol":1,"source":1,"info":"success","data":[{"id":"https://quapp.1122dh.com/info/212770.html","name":"纣临","author":"三天两觉","img":"https://imgapi.jiaston.com/BookFiles/BookImages/zhoulin.jpg","desc":"死而以行为谥。\n　　残义损善者，谓之纣。\n　　然，此恶谥之祖，实是文治武功，一代枭雄。\n　　比之无数上谥之庸才，又当如何？\n　　若纣为罪，罪当再临。\n　　当这第五王国再次腐朽之时，逆十字的旗帜自当再现。\n　　那旗下之恶才、罪徒……亦将再次掀起一场颠覆时代的狂潮。","lastChapterLink":"https://quapp.1122dh.com/book/212770/1368158.html","lastChapter":"祭者之章 四","lastTime":"2019-03-05 00:00:00","cName":"科幻灵异"}]}
2. 书籍详情
> {"source":1,"info":"success","data":{"id":"https://quapp.1122dh.com/book/212770/","name":"纣临","author":"三天两觉","img":"https://imgapi.jiaston.com/BookFiles/BookImages/zhoulin.jpg","desc":"死而以行为谥。\r\n　　残义损善者，谓之纣。\r\n　　然，此恶谥之祖，实是文治武功，一代枭雄。\r\n　　比之无数上谥之庸才，又当如何？\r\n　　若纣为罪，罪当再临。\r\n　　当这第五王国再次腐朽之时，逆十字的旗帜自当再现。\r\n　　那旗下之恶才、罪徒……亦将再次掀起一场颠覆时代的狂潮。","firstChapterLink":"https://quapp.1122dh.com/book/212770/1110792.html","lastChapterLink":"https://quapp.1122dh.com/book/212770/1368887.html","lastChapter":"祭者之章 五","lastTime":"2019-03-15 18:18:20","cName":"科幻灵异"}}
3. 章节目录
> {"source":1,"info":"success","data":{"id":212770,"name":"纣临","chapters":[{"id":"https://quapp.1122dh.com/book/212770/1110792.html","name":"序幕 十三个凶恶的联邦人"},{"id":"https://quapp.1122dh.com/book/212770/1110793.html","name":"第零章 罪徒"}]}}
4. 章节内容
> {"source":1,"info":"success","data":{"id":"https://quapp.1122dh.com/book/212770/","name":"纣临","cid":"https://quapp.1122dh.com/book/212770/1368633.html","cname":"判官之章 四","pid":"https://quapp.1122dh.com/book/212770/1368158.html","nid":"https://quapp.1122dh.com/book/212770/1368887.html","content":"兰斯笑了，“等我真翻脸了，你不就知道了？”\f\t\n"}}

### 五、后台开发
> 详情看代码提交顺序
