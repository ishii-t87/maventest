package hello;

/**
 * @brief 挨拶のパラメータ
 */
public class Greeting {

	/** 多分ユーザーのid */
    private long id;
    /** メッセージ内容 */
    private String content;

    /** アクセサ群 */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
