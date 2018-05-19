package proxyserver;

/**
 * 功能:
 * 作者: 崔兴旺
 * 日期: 2018/4/19
 * 备注:
 */
public class NetInfo {

    private String content;
    private boolean isError;

    public NetInfo(String content) {
        this.content = content;
    }

    public NetInfo(String content, boolean isError) {
        this.content = content;
        this.isError = isError;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}
