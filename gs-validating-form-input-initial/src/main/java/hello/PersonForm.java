package hello;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @brief 入力フォームで扱う人物に関するパラメータ
 */
public class PersonForm {

	/** 入力対象の名前(字数制限あり) */
	@NotNull
	@Size(min=2, max=30)
	private String mName;

	/** 入力対象の年齢(下限あり) */
	@NotNull
	@Min(18)
	private Integer mAge;

	/** アクセサ群 */
	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public Integer getAge() {
		return mAge;
	}

	public void setAge(Integer age) {
		mAge = age;
	}

	/**
	 * 入力情報を文字列化して返却する
	 * @return 名前と年齢を記述した文字列
	 */
	 public String toString() {
		 return "Person(Name: " + mName + ", Age: " + mAge + ")";
	}

}
