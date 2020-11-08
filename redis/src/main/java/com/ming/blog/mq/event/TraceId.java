package com.ming.blog.mq.event;

import java.util.Random;

/**
 * @author unknown
 */
public class TraceId {
    private static char[][] idc = lut();
    private static Random rng = new Random();
    private long id;

    public TraceId(long id) {
        this.id = id;
    }

    private static char[][] lut() {
        char[][] rv = new char[256][2];
        int idx = 0;

        for (byte b = -128; idx < 256; ++idx) {
            byte bb = b < 0 ? b : (byte) (b + 256);
            String s = String.format("%02x", bb);
            rv[idx] = new char[]{s.charAt(0), s.charAt(1)};
            ++b;
        }

        return rv;
    }

    public static String id() {
        return (new TraceId(rng.nextLong())).toString();
    }

    private char[] byteToChars(Byte b) {
        return idc[b + 128];
    }

    @Override
    public String toString() {
        return String.valueOf(this.byteToChars((byte) ((int) (this.id >> 56 & 255L)))) +
                String.valueOf(this.byteToChars((byte) ((int) (this.id >> 48 & 255L)))) +
                String.valueOf(this.byteToChars((byte) ((int) (this.id >> 40 & 255L)))) +
                String.valueOf(this.byteToChars((byte) ((int) (this.id >> 32 & 255L)))) +
                String.valueOf(this.byteToChars((byte) ((int) (this.id >> 24 & 255L)))) +
                String.valueOf(this.byteToChars((byte) ((int) (this.id >> 16 & 255L)))) +
                String.valueOf(this.byteToChars((byte) ((int) (this.id >> 8 & 255L)))) +
                String.valueOf(this.byteToChars((byte) ((int) (this.id & 255L))));
    }
}
