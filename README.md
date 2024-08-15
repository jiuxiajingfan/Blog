# Blog
一个简洁的博客项目 \
[前端](https://github.com/jiuxiajingfan/blog-vue)为Vue3项目 \
~~[后端](https://github.com/jiuxiajingfan/blog)为Java项目~~\
[后端](https://github.com/jiuxiajingfan/blog)为Go项目\
前端页面仿造[hexo-theme-volantis](https://volantis.js.org)编写\
部分页面截图
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)

## 快速部署：
- 新建 .env文件
```

#redis
REDIS_HOST=172.20.0.2
REDIS_PORT=6379
REDIS_PASSWORD=1234567qwer
REDIS_DB=0
#db的docker内网ip
DB_HOST=172.20.0.3
DB_USER=postgres
DB_PORT=15432
DB_PASSWORD=1234567qwer


#docker network的配置
SUBNET=172.20.0.0/16


#数据存储的文件夹位置（默认当前路径生成Nblog文件夹）
DATA_DIRECTORY=./Nblog
```
- 新建 docker-compose.yml文件
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
        
  nblog-postgres:
    #仅支持amd64
    image: jiuxiajingfan/nblog-postgres:1.0
    container_name: nblog-postgres
    restart: always
    volumes:
      - ${DATA_DIRECTORY}/data/postgres/data:/var/lib/postgresql/data
    environment:
      - POSTGRES_PASSWORD=${DB_PASSWORD} # mysql数据库root账号的密码
      - TZ=Asia/Shanghai
    ports:
      - ${DB_PORT:-15432}:5432
    networks:
      nblog-network:
        ipv4_address: ${DB_HOST:-172.20.0.3}
      
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
    image: jiuxiajingfan/nblog:1.0
    container_name: nblog
    restart: always
    depends_on:
      - nblog-redis
      - nblog-postgres
    environment:
      - DB_USER=${DB_USER:-postgres}
      - DB_HOST=${DB_HOST:-172.20.0.3}
      - DB_PASSWORD=${DB_PASSWORD}
      - REDIS_HOST=${REDIS_HOST:-172.20.0.2}
      - REDIS_PASSWORD=${REDIS_PASSWORD}
      - REDIS_DB=${REDIS_DB:-0}
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
- 命令执行
```shell
docker-compose up -d
```