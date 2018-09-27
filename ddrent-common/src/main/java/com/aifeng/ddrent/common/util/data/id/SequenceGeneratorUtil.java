/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.data.id 
 * @author imart·deng
 * @date 创建时间：2018年9月25日 上午10:36:32
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.data.id;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/** 
 * @ClassName: SequenceGeneratorUtil 
 * @Description: 序列号生成器（模仿snowflake {@link snowflake https://github.com/twitter/snowflake/tree/snowflake-2010} ），64bit<br/>
 * <strong>PS:</strong>&nbsp;&nbsp;前期使用这个序列机器做一个分布式单独节点的序列号生成器，后期可以把改进，作为一统一的序列号服务
 * <li>Epoch timestamp in millisecond precision - 41 bits (gives us 69 years with a custom epoch)</li>
 * <li>Configured machine id - 10 bits (gives us up to 1024 machines)</li>
 * <li>Sequence number - 12 bits (A local counter per machine that rolls over every 4096)</li>
 * <li>They have reserved 1-bit for future purpose. Since the IDs use timestamp as the first component, they are sortable.</li>
 * @author: imart·deng
 * @date: 2018年9月25日 上午10:36:32
 */
public class SequenceGeneratorUtil {
	private static final AtomicInteger counter = new AtomicInteger(new SecureRandom().nextInt());
	
	private static final int TOTAL_BITS = 64;
	private static final int EPOCH_BITS = 42;
	private static final int MACHINE_ID_BITS = 10;
	
	private static final int MACHINE_ID;
	private static final int LOWER_ORDER_TEN_BITS = 0x3FF;
	private static final int LOWER_ORDER_TWELVE_BITS = 0xFFF;
	
	static {
		MACHINE_ID = createMachineId();
	}
	
	private static int createMachineId() {
		int machineId;
		try {
			StringBuilder sb = new StringBuilder();
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				byte[] mac = networkInterface.getHardwareAddress();
				if (mac != null) {
					for(int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X", mac[i]));
					}
				}
			}
			machineId = sb.toString().hashCode();
		} catch (Exception e) {
			machineId = (new SecureRandom().nextInt());
		}
		machineId = machineId & LOWER_ORDER_TEN_BITS;
		return machineId;
	}
	
	public static long nextId() {
		long curMs = Instant.now().toEpochMilli();
		long id = curMs << (TOTAL_BITS - EPOCH_BITS);
		id |= (MACHINE_ID << (TOTAL_BITS - EPOCH_BITS - MACHINE_ID_BITS));
		id |= (getNextCounter() & LOWER_ORDER_TWELVE_BITS);
		return id;
	}

	/**
	 * 获得下一个序列号
	 * @return
	 */
	private static int getNextCounter() {
		return counter.getAndIncrement();
	}
	
	public static void main(String[] args) {
		
		System.out.format("1 << 1 %s \r\n", 1 << 1);
		
		System.out.format("%12s|%12s\r\n", "第几个", "序列号");
		
		for(int i = 0; i< 1 ;) {
			System.out.format("%12d|%12s\r\n", ++i, nextId());
		}
	}
	
}
