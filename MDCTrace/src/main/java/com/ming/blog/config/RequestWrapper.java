package com.ming.blog.config;

import org.apache.commons.io.input.TeeInputStream;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Mr_JiangZM
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private String id;


    RequestWrapper(String requestId, HttpServletRequest request) {
        super(request);
        this.id = requestId;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStream() {
            private TeeInputStream tee = new TeeInputStream(RequestWrapper.super.getInputStream(), bos);

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return tee.read();
            }
        };
    }

    byte[] toByteArray() {
        return bos.toByteArray();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
