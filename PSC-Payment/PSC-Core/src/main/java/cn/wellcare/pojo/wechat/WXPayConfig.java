package cn.wellcare.pojo.wechat;

import java.io.InputStream;

public interface WXPayConfig {

    String getAppID();

    String getMchID();

    String getKey();

    InputStream getCertStream();

    int getHttpConnectTimeoutMs();

    abstract int getHttpReadTimeoutMs();
}
