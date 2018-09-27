/**
 * Copyright © 2018 aifeng club All rights reserved.
 * @Package: com.aifeng.ddrent.common.util.data.id 
 * @author imart·deng
 * @date 创建时间：2018年9月18日 下午11:47:55
 * @version 1.0
 */
package com.aifeng.ddrent.common.util.data.id;

/** 
 * @ClassName: IDObfuscation 
 * @Description: 主键混淆器
 * @author: imart·deng
 * @date: 2018年9月18日 下午11:47:55  
 */
public class IDObfuscationUtils {
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
			.toCharArray();
	private static final int BASE = ALPHABET.length;
	private static final byte[] PADDING = { 0x29, 0x28, 0x27, 0x26, 0x25, 0x24, 0x23, 0x22, 0x21, 0x20, 0x1F, 0x1E,
			0x1D, 0x1C, 0x1B, 0x1A, 0x19, 0x18, 0x17 };
	private static final byte NEGATIVE = 0x2A;
	private int key;
	
	public IDObfuscationUtils(int key) {
		this.key = key;
	}

	public String obfuscate(long id) {
		return toStr(swap(id));
	}

	private String toStr(long n) {
		byte[] bytes = Long.toString(n).getBytes();
		int len = bytes.length;
		int paddingLen = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 16; i += 4) {
			int chunk;
			if (i + 4 <= len) {
				chunk = encode(bytes, i, i + 4);
			} else if (i <= len) {
				chunk = encode(bytes, i, len, paddingLen, 4 + i - len);
				paddingLen += len - i;
			} else {
				chunk = encode(paddingLen, 4);
				paddingLen += 4;
			}
			while (chunk > 0) {
				chunk = mod(chunk, sb);
			}
		}
		return sb.reverse().toString();
	}

	private int mod(int i, StringBuilder sb) {
		sb.append(ALPHABET[i % BASE]);
		return i / BASE;
	}

	private int encode(int paddingStartIndex, int paddingNum) {
		int n = 0;
		for (int i = 0; i < paddingNum; i++) {
			n += PADDING[paddingStartIndex + i] << (paddingNum - 1 - i) * 8;
		}
		return n;
	}

	private int encode(byte[] bytes, int start, int end, int paddingStartIndex, int paddingNum) {
		return encode(bytes, start, end) + encode(paddingStartIndex, paddingNum);
	}

	private int encode(byte[] bytes, int start, int end) {
		int n = 0;
		for (int i = start; i < end; i++) {
			n += (bytes[i] - 3) << (3 - i) * 8;
		}
		return n;
	}

	private long swap(long i) {
		long low = i & 0xFFFFFFFFL;
		long high = i >> 32 & 0xFFFFFFFFL ^ opaque(low);
		return ((low ^ opaque(high)) << 32) + high;
	}

	private long opaque(long i) {
		i = ((i ^ key) + 0x7886) * 0xEA03122921L;
		return i >> (i & 0xF) & 0xFFFFFFFFL;
	}

	public long restore(String obfuscatedId) {
		if (obfuscatedId == null || obfuscatedId.length() != 25)
			throw new IllegalArgumentException("invalid obfuscatedId");
		try {
			return swap(toLong(obfuscatedId));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("invalid obfuscatedId");
		}
	}

	private long toLong(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 25; i >= 5; i -= 5) {
			String chunk = decode(base(s.substring(i - 5, i)));
			sb.append(chunk);
			if (chunk.length() < 4)
				break;
		}
		return Long.parseLong(sb.toString());
	}

	private String decode(int n) {
		char[] c = new char[4];
		int i = 3;
		for (; i >= 0; i--) {
			int x = (n >> i * 8) & 0xFF;
			if (x <= PADDING[0]) {
				return i == 3 ? "" : new String(c, 0, 3 - i);
			} else if (x == NEGATIVE) {
				c[3 - i] = '-';
			} else {
				c[3 - i] = (char) (x + 3);
			}
		}
		return new String(c);
	}

	private int base(String s) {
		int n = 0;
		int power = 1;
		for (int i = s.length() - 1; i >= 0; i--) {
			int digit = s.charAt(i);
			if (digit <= '9') {
				digit += 4;// 52 - 48
			} else if (digit >= 'a') {
				digit -= 71;// 26 - 97
			} else {
				digit -= 65;
			}
			n += digit * power;
			power *= BASE;
		}
		return n;
	}
	
	public static void main(String[] args) {
		IDObfuscationUtils ob = new IDObfuscationUtils(0x9213de);
//		Long originId = 2018073127075121L;
//		String s = ob.obfuscate(originId);// 9iP2Z9fwbN2tVHvzTk1231b90
//		long id = ob.restore(s);
//		
//		System.out.println(String.format("加密前id[%d],加密后str[%s],解密后id[%d]", originId, s, id));
		
		for (int key = 2147480000; key < Integer.MAX_VALUE; key++) {
            System.out.format("| %d  | %-13s |\n", key, ob.obfuscate(key));
            assert key == ob.restore(ob.obfuscate(key));
        }
	}
}
