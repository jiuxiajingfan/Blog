### Blog
个人博客 \
前端为Vue3 + pinia
后端为Springboot+Mysql+shiro+redis

前端页面仿造[hexo-theme-volantis](https://volantis.js.org)编写
部分页面截图
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)

快速部署：
新建 .env文件
```

#redis
REDIS_HOST=172.20.0.2
REDIS_PORT=6379
REDIS_PASSWORD=1234567qwer
#mysql的docker内网ip
MYSQL_HOST=172.20.0.3
#mysql暴露的端口号
MYSQL_PORT=3306
#mysql的密码
MYSQL_ROOT_PASSWORD=1234567qwer


#docker network的配置
SUBNET=172.20.0.0/16


#数据存储的文件夹位置（默认当前路径生成Nblog文件夹）
DATA_DIRECTORY=./Nblog
```
新建 docker-compose.yml文件
```yaml
version: "3"
services:

  nblog-redis:
    image: redis:5.0.9-alpine
    container_name: redis
    restart: always
    volumes:
      - ${DATA_DIRECTORY}/data/redis/data:/data
    networks:
      nblog-network:
        ipv4_address: ${REDIS_HOST:-172.20.0.2}
    ports:
      - ${REDIS_PORT:-6379}:6379
    # --requirepass 后面为redis访问密码
    command: redis-server --requirepass ${REDIS_PASSWORD} --appendonly yes
        
  nblog-mysql:
    #仅支持amd64
    image: jiuxiajingfan/nblog-mysql:0.1
    container_name: nblog-mysql
    restart: always
    volumes:
      - ${DATA_DIRECTORY}/data/mysql/data:/var/lib/mysql
    environment:
      - MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} # mysql数据库root账号的密码
      - TZ=Asia/Shanghai
    ports:
      - ${MYSQL_PORT:-5683}:3306
    networks:
      nblog-network:
        ipv4_address: ${MYSQL_HOST:-172.20.0.3}
      
  nblog-frontend:
    #仅支持amd64
    image: jiuxiajingfan/nblog-vue:0.1
    container_name: nblog-vue
    restart: always
    ports:
      - "8092:8092"
      - "4437:443"
    networks:
      nblog-network:
        ipv4_address: 172.20.0.6
  
  nblog:
    image: jiuxiajingfan/nblog:0.1
    container_name: nblog
    restart: always
    depends_on:
      - nblog-redis
      - nblog-mysql
    environment:
      - DB_USER=root
      - DB_HOST=${MYSQL_HOST:-172.20.0.3}
      - DB_PORT=${MYSQL_PORT:-5683}
      - DB_PASSWORD=${MYSQL_ROOT_PASSWORD}
      - REDIS_HOST=${REDIS_HOST:-172.20.0.2}
      - REDIS_PORT=${REDIS_PORT:-6379}
      - REDIS_PASSWORD=${REDIS_PASSWORD}
      - TZ=Asia/Shanghai
    ports:
      - 3641:3641
    networks:
      nblog-network:
        ipv4_address: 172.20.0.4
    
networks:
   nblog-network:
     driver: bridge
     ipam:
       config:
         - subnet: 172.20.0.0/16
```
命令执行
```shell
docker-compose up -d
```



前端结构：
│  App.vue \
│  main.ts\
│  registerServiceWorker.ts\
│  shims-vue.d.ts\
│\
├─api\
│      api.js \
│\
├─assets\
│      logo.png\
│\
├─components\
│      Account.vue 后台管理\
│      ArticleList.vue 首页文章列表\
│      Author.vue 作者卡片\
│      BackGround.vue 背景图\
│      Classify.vue 分类卡片\
│      CreateArticle.vue 编写文章\
│      Footer.vue 页脚\
│      Header.vue 首部导航\
│      ManageArticle.vue 文章管理\
│      UpdateArticle.vue  更新文章\
│
├─router
│      index.ts\
│
├─store
│      article.ts\
│      auth.ts token\
│      config.ts 缓存信息、背景图URL\
│      store.ts\
│\
├─utils\
│      utils.js\
│\
└─views\
├─Admin\
│      UserCenter.vue 后台页面\
│\
├─Article\
│      Archiving.vue 归档页面\
│      Read.vue 阅读页面\
│\
├─Error\
│      404.vue 404\
│\
└─Home 主页页面\

后端请参考Swagger文档
