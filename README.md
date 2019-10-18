#                                                    会议签到管理系统

 
### 开发环境 Win10、jdk1.8、Tomcat8、mysql数据库、IntelliJ IDEA



#### 注意事项
*1. 系统调用了百度地图开发平台Web服务API的ip定位的服务,如果需要使用请将utlis包里面的AddressUtils当中的ak参数修改为你的ak，否则无法获取物理地址ak密匙在这里获取[百度地图开发平台](http://lbsyun.baidu.com/index.php?title=webapi)
*2.系统的人脸识别使用的是腾讯云的人脸识别接口，使用修改utlis包TencentUtils的secreid与secretKey参数,参数获取需要在腾讯云中获取，这是腾讯云人脸识别的api[腾讯云人脸识别接口](https://cloud.tencent.com/document/product/867/32770)
*3.数据库的参数的修改druid.properties文件
