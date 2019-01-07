# AndroidToolHepler
Android开发常用工具类集合,包括图片处理,程序后台到前台的判断,Android消息通知的判断以及跳转等,不断更新中......


工具类介绍：

1. GlideUtil：是基于Glid 3.7.0库进行的又一次封装,包括加载圆形图片,圆角图片(圆角的大小可以设置)等功能,使用时,需要先添加库"compile 'com.github.bumptech.glide:glide:3.7.0'"

2. MD5Util: 用于对字符串进行MD5加密

3.NotificationPageHelper: 当app集成了极光,友盟等推送功能的时候,需要判断本地的消息通知是否已经开启,并给用户对应的提示,跳转到开启页面

4.SystemHelper: 用于收到通知后,进行程序是处于前端还是后台的判断,进而进行下部的操作(一般和第3条的工具 NotificationPageHelper 组合使用)

5.SystemUtils: 获取手机上各种系统参数

