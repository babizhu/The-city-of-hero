package net;

import core.GameMainLogic;
import game.events.EventBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.*;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.text.MessageFormat;

/**
 * @author liukun 2012-8-16
 */
public class GameHandler implements IDataHandler, IConnectHandler,
		IIdleTimeoutHandler, IDisconnectHandler {

    Logger logger = LoggerFactory.getLogger( GameHandler.class);
    /**
     * 除去包头包尾允许接收的最大的包长度
     */
    private static final int MAX_PACKAGE_LEN;

    static {
        MAX_PACKAGE_LEN = 8192;
    }

    private final GameMainLogic gameLogic = GameMainLogic.getInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xsocket.connection.IIdleTimeoutHandler#onIdleTimeout(org.xsocket.
	 * connection.INonBlockingConnection)
	 */
	@Override
	public boolean onIdleTimeout(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		con.available();// 避免findbugs的警告
		return false;
		// return true;//不切断连接
	}

	@Override
	public boolean onConnect(INonBlockingConnection con) throws BufferUnderflowException, MaxReadSizeExceededException {
		
		//con = ConnectionUtils.synchronizedConnection(con);
		//System.out.println(con.getId() + " onConnect");

//		UserInfo user = new UserInfo( con );
//		con.setAttachment(user);
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection con) throws IOException, BufferUnderflowException {

		byte    head;
		byte    foot;
		short   packageNo;
		short   dataLength;
		byte    data[];

		con = ConnectionUtils.synchronizedConnection( con );
		// synchronized (con) {
		int available = con.available();

		if (available > 0) {
//			System.out.println(con.getId() + " onData" + ",available:" + available + "byte");
			con.markReadPosition();
			try {
				head = con.readByte();
				packageNo = con.readShort();
				dataLength = con.readShort();
				if (dataLength < 0 || dataLength > MAX_PACKAGE_LEN) {
                    logger.info(MessageFormat.format("{0}网络错误，dataLength = {1}", con.getRemoteAddress(), dataLength));
					con.close();
					return true;
				}
				data = con.readBytesByLength( dataLength );
				foot = con.readByte();
				con.removeReadMark();

			} catch (BufferUnderflowException bue) {
				con.resetToReadMark();
				return true;
			}
			if (!inputDataIsRight(head, foot)) {
				con.close();
				return true;
			}

			gameLogic.packageRun( con, packageNo, data );

		}

		return true;
	}

	/**
	 * 检测客户端所发送的首尾标识位是否正确
	 * 
	 * @param head      包头
	 * @param foot      包尾
	 * @return true 首尾包号正确 false 错误
	 */
	private boolean inputDataIsRight(byte head, byte foot) {
        return !(head != EventBase.HEAD || foot != EventBase.FOOT);
    }
	
	/**
	 * 主动响应玩家关闭连接的事件
	 */
	@Override
	public boolean onDisconnect(INonBlockingConnection con) throws IOException {
		con = ConnectionUtils.synchronizedConnection(con);
		//System.out.println(con.getId() + " onDisconnect " + System.nanoTime() );
		gameLogic.userExit( con );
		return false;
	}
}
