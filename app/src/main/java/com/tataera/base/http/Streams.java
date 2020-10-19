package com.tataera.base.http;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 *   Created by Dullyoung on 2020/10/19 0019
 */
public class Streams {
    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void copyContent(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null || outputStream == null) {
            return;
        }
        byte[] bArr = new byte[16384];
        while (true) {
            int read = 0;
            try {
                read = inputStream.read(bArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (read != -1) {
                try {
                    outputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }
    }

    public static void copyContent(InputStream inputStream, OutputStream outputStream, long j) {
        if (inputStream == null || outputStream == null) {
           return;
        }
        byte[] bArr = new byte[16384];
        long j2 = 0;
        while (true) {
            int read = 0;
            try {
                read = inputStream.read(bArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (read != -1) {
                j2 += (long) read;
                if (j2 >= j) {
            return;
                }
                try {
                    outputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }
    }

    public static void readStream(InputStream inputStream, byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        do {
            int read = 0;
            try {
                read = inputStream.read(bArr, i, length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (read != -1) {
                i += read;
                length -= read;
            } else {
                return;
            }
        } while (length > 0);
    }

}
