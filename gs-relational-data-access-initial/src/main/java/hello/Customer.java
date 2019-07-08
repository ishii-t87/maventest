package hello;

/**
 * 顧客情報
 */
public class Customer {

	/** 顧客id */
	private long mId;
	/** 名 */
	private String mFirstName;
	/** 氏 */
	private String mLastName;

	/**
	 * 顧客IDと氏名を元に初期化
	 */
	public Customer(long id, String firstName, String lastName) {
		mId = id;
		mFirstName = firstName;
		mLastName = lastName;
	}

	/**
	 * 入力情報を文字列化して返却する
	 * @return 顧客idと氏名を記述した文字列
	 */
	@Override
	public String toString() {
		return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                mId, mFirstName, mLastName);
	}
}
