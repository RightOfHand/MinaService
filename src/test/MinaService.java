package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @des: 一个mina服务器
 * @author song
 * 
 */
public class MinaService {

	private static int PORT = 10018;

	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();

		try {
			// 田间日志过滤器
			acceptor.getFilterChain().addLast("logger", new LoggingFilter());
			//
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
			acceptor.setHandler(new DemoHandler());
			acceptor.getSessionConfig().setReadBufferSize(2048);
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
			acceptor.bind(new InetSocketAddress("192.168.0.24",10018));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class DemoHandler extends IoHandlerAdapter {
		public DemoHandler() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void sessionCreated(IoSession session) throws Exception {
			// TODO Auto-generated method stub
			super.sessionCreated(session);
		}

		@Override
		public void sessionOpened(IoSession session) throws Exception {
			// TODO Auto-generated method stub
			super.sessionOpened(session);
		}

		@Override
		public void sessionClosed(IoSession session) throws Exception {
			// TODO Auto-generated method stub
			super.sessionClosed(session);
		}

		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			// TODO Auto-generated method stub
			super.messageReceived(session, message);

			String recieveMessage = message.toString();
			session.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("server recieve message:" + recieveMessage);

		}

		@Override
		public void messageSent(IoSession session, Object message) throws Exception {
			// TODO Auto-generated method stub
			super.messageSent(session, message);
            
			System.out.println("server send message:");
		}

	}
}
