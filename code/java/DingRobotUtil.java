package code.java;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * The type Ding robot util.
 */
@Slf4j
@AllArgsConstructor
public class DingRobotUtil {

    private String secret;
    private String webhook;


    private String signUrl() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return webhook + "&timestamp=" + timestamp + "&sign=" + URLEncoder.encode(new String(Base64.getEncoder().encode(signData), StandardCharsets.UTF_8), "UTF-8");
    }


    /**
     * 发送文本消息
     *
     * @param text the text
     * @return the boolean
     */
    public boolean send(OapiRobotSendRequest.Text text) {
        return send(text, null);
    }

    /**
     * 发送 链接
     *
     * @param link the link
     * @return the boolean
     */
    public boolean send(OapiRobotSendRequest.Link link) {
        return send(link, null);
    }

    /**
     * 发送 markdown
     *
     * @param message the message
     * @return the boolean
     */
    public boolean send(OapiRobotSendRequest.Markdown message) {
        return send(message, null);
    }

    /**
     * 发送消息
     *
     * @param message the message
     * @return the boolean
     */
    public boolean send(String message) {
        return send(message, null);
    }

    /**
     * 发送链接 并 @
     *
     * @param link the link
     * @param at   the at
     * @return the boolean
     */
    public boolean send(OapiRobotSendRequest.Link link, List<String> at) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        request.setLink(link);
        addAt(request, at);
        OapiRobotSendResponse response = execute(request);
        return response != null && response.isSuccess();
    }

    /**
     * 发送 markdown 并 @
     *
     * @param markdown the markdown
     * @param at       the at
     * @return the boolean
     */
    public boolean send(OapiRobotSendRequest.Markdown markdown, List<String> at) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        request.setMarkdown(markdown);
        addAt(request, at);
        OapiRobotSendResponse response = execute(request);
        return response != null && response.isSuccess();
    }

    /**
     * 发送消息  并 @
     *
     * @param message the message
     * @param at      the at
     * @return the boolean
     */
    public boolean send(String message, List<String> at) {

        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(message);
        return send(text, at);
    }

    /**
     * 发送文本消息 并 @
     *
     * @param text the text
     * @param at   the at
     * @return the boolean
     */
    public boolean send(OapiRobotSendRequest.Text text, List<String> at) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        request.setText(text);
        addAt(request, at);
        OapiRobotSendResponse response = execute(request);
        return response != null && response.isSuccess();
    }

    private OapiRobotSendResponse execute(OapiRobotSendRequest request) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(signUrl());
            OapiRobotSendResponse response =  client.execute(request);
            log.info("返回结果:[{}]",response);
            return response;
        } catch (ApiException e) {
            log.error("钉钉 api 报错", e);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            log.error("签名报错",e);
        }
        return null;
    }

    private void addAt(OapiRobotSendRequest request, List<String> at) {
        if (at != null && at.size() > 0) {
            OapiRobotSendRequest.At AT = new OapiRobotSendRequest.At();
            AT.setAtMobiles(at);
            request.setAt(AT);
        }
    }

}
