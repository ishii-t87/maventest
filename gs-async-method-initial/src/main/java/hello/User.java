package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @brief Githubユーザーの情報管理
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	/**	ユーザーアカウント名 */
	private String mName;
	/** ブログURL */
	private String mBlog;


	/** アクセサ群 */
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		mName = name;
	}
	public String getBlog() {
		return mBlog;
	}
	public void setBlog(String blog) {
		mBlog = blog;
	}

	/**
	 * ユーザー情報を文字列化して返却する
	 * @return ユーザー名とブログURLを記述した文字列
	 */
	@Override
	public String toString() {
		return "User [name=" + mName + ", blog=" + mBlog + "]";
	}
}
