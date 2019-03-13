# fictionalfiction
毕业设计——小说网站

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
> {"tatol":"10","source":"1","info":"success","rows":[{"Id":"https://quapp.1122dh.com/info/146948.html","Name":"牧神记","Author":"宅猪","Img":"https://imgapi.jiaston.com/BookFiles/BookImages/mushenji.jpg","Desc":"大墟的祖训说，天黑，别出门。大墟残老村的老弱病残ink边捡到了一个婴儿，取名秦牧，含辛茹苦将他养大。这一天夜幕降临，黑暗笼罩大墟，秦牧走出了家门……做个春风中荡漾的反派吧！瞎子对他说。秦牧的反派之路，正在崛起！","LastChapterLink":"https://quapp.1122dh.com/book/146948/7456992.html","LastChapter":"第一四三三章 蒙面谁也不识君","LastTime":"2/12/2019 20:03:39","CName":"玄幻奇幻"}]}
2. 书籍详情
> {"source":"1","info":"success","data":[{"Id":"146948","Name":"牧神记","Author":"宅猪","Img":"https://imgapi.jiaston.com/BookFiles/BookImages/mushenji.jpg","Desc":"大墟的祖训说，天黑，别出门。大墟残老村的老弱病残们从江边捡到了一个婴儿，取名秦牧，含辛茹苦将他养大。这一天夜幕降临，黑暗笼罩大墟，秦牧走出了家门……做个春风中荡漾的反派吧！瞎子对他说。秦牧的反派之路，正在崛起！","FirstChapterId":7456992,"LastChapterId":7767374,"LastChapter":"第一四三三章 蒙面谁也不识君","LastTime":"2/12/2019 8:03:39 PM","CName":"玄幻奇幻"}]}
3. 章节目录
> {"source":"1","info":"success","data":[{"Id":6948,"Nme":"牧神记","chapters":[{"id":7456992,"name":"第一章 天黑别出门"},{"id":7456993,"name":"第二章 四灵血"}]}]}
4. 章节内容
> {"source":"1","info":"success","data":[{"Id":6948,"Nme":"牧神记","cid":7456992,"cname":"第一章 天黑别出门","pid":7745964,"nid":7456993,"content":"    天黑，别 这？”他心中纳闷。\f\t\n"}]}
