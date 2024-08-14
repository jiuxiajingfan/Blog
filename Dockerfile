FROM golang:1.20 AS builder
WORKDIR /app
COPY . .

# 编译 Go 应用程序
RUN go mod tidy
RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o app .

FROM alpine:3.18

# 设置工作目录
WORKDIR /app

# 从构建镜像中复制可执行文件到最终的运行镜像中
COPY --from=builder /app/app .

# 暴露应用程序的端口
EXPOSE 3641

# 运行可执行文件
CMD ["./app"]