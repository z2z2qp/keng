import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

/**
 * 一个 简单 的 http netty 实现
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/9/1 11:50
 */
public class NettyHttp {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)//添加 group
                .handler(new LoggingHandler(LogLevel.DEBUG))//添加handler
                .channel(NioServerSocketChannel.class)//设置channel
                .childHandler(new ChannelInitializer<SocketChannel>() {//设置子handler
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();//获取管道
                        pipeline.addLast(new HttpServerCodec());//添加http 解码
                        pipeline.addLast(new HttpObjectAggregator(512 * 1024));// 添加最大 aggregator
                        pipeline.addLast(new SimpleChannelInboundHandler<FullHttpRequest>() {//添加自定义hanadler1
                            @Override
                            public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
                                String uri = req.getUri();
                                String msg = "你请求的uri为" + uri;
                                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                                        HttpResponseStatus.OK,
                                                                                        Unpooled.copiedBuffer(msg,
                                                                                                              CharsetUtil.UTF_8));
                                response.headers().set("Content-Type", "text/html; charset=UTF-8");
                                ctx.writeAndFlush(response);
                                ctx.fireChannelRead(123);
                            }
                        });
                        pipeline.addLast(new SimpleChannelInboundHandler<Integer>() {//添加自定义hanadler1
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Integer msg) throws Exception {
                                System.out.println(msg);
                                ctx.fireChannelRead("def");
                            }
                        });
                        pipeline.addLast(new SimpleChannelInboundHandler<String>() {//添加自定义hanadler1
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                });
        ChannelFuture future = bootstrap.bind(8888).sync();//异步启动
        System.out.println("server start up on port : 8888");
        future.channel().closeFuture().sync();
    }
}
