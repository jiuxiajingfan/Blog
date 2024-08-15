# 第一阶段：构建阶段
FROM golang:1.22 AS builder
ENV GOPROXY=https://goproxy.io,direct

# 设置工作目录
WORKDIR /app

# 复制 go.mod 和 go.sum 并下载依赖
COPY go.mod go.sum ./

RUN go mod download

# 复制源代码并编译
COPY . .

RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o app .

# 第二阶段：最小化镜像
FROM alpine:3.18

# 设置工作目录
WORKDIR /app

# 复制编译好的二进制文件
COPY --from=builder /app/app .

# 复制配置文件
COPY config/config.ini /app/config/config.ini

# 运行二进制文件
CMD ["./app"]

EXPOSE 3641
