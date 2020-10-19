package com.tataera.base.http;





import com.tataera.Utils.ResponseHeader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class DownloadResponse {
    private  boolean isGzip;
    private byte[] mBytes = new byte[0];
    private  long mContentLength;
    private  Header[] mHeaders;
    private  int mStatusCode;

    public DownloadResponse(HttpResponse httpResponse) {
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bufferedInputStream = new BufferedInputStream(httpResponse.getEntity().getContent());
            try {
                Streams.copyContent(bufferedInputStream, byteArrayOutputStream);
                this.mBytes = byteArrayOutputStream.toByteArray();
                Streams.closeStream(bufferedInputStream);
                Streams.closeStream(byteArrayOutputStream);
                this.mStatusCode = httpResponse.getStatusLine().getStatusCode();
                this.mContentLength = (long) this.mBytes.length;
                this.mHeaders = httpResponse.getAllHeaders();
                Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
                if (firstHeader != null) {
                    this.isGzip = "gzip".equalsIgnoreCase(firstHeader.getValue());
                    return;
                }
                Header firstHeader2 = httpResponse.getFirstHeader("content-encoding");
                if (firstHeader2 != null) {
                    this.isGzip = "gzip".equalsIgnoreCase(firstHeader2.getValue());
                } else {
                    this.isGzip = false;
                }
            } catch (Throwable th) {
                th = th;
                Streams.closeStream(bufferedInputStream);
                Streams.closeStream(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th2) {

            bufferedInputStream = null;
            Streams.closeStream(bufferedInputStream);
            Streams.closeStream(byteArrayOutputStream);

        }
    }

    public Header[] getAllHeaders() {
        return this.mHeaders;
    }

    public byte[] getByteArray() {
        return this.mBytes;
    }

    public long getContentLength() {
        return this.mContentLength;
    }

    public String getFirstHeader(ResponseHeader responseHeader) {
        for (Header header : this.mHeaders) {
            if (header.getName().equals(responseHeader.getKey())) {
                return header.getValue();
            }
        }
        return null;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public boolean isGzipContent() {
        return this.isGzip;
    }
}