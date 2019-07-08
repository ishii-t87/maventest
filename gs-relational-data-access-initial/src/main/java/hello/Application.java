package hello;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @brief DB接続のデモ
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	/** ログ情報 */
	private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    /** DBアクセスを簡潔にするテンプレート(シングルトン) */
    @Autowired
    JdbcTemplate mJdbcTemplate;

	@Override
	public void run(String... args) throws Exception {

		log.info("Creating tables");

		// 古い顧客テーブルを削除
		mJdbcTemplate.execute("DROP TABLE customers IF EXISTS");

		// 新しく顧客テーブルを作り直す
		/*
		 * int id
		 * String first_name
		 * String last_name
		 */
		mJdbcTemplate.execute("CREATE TABLE customers(" +
		"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");


		// 指定した人名文字列をlist化する
		List<String> nameList = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long");
		// 文字列ストリームに変換
		Stream<String> nameStream = nameList.stream();
		// stream内の要素をすべて氏名の間の空白で分割
		Stream<Object[]> separatedNames = nameStream.map(name ->name.split(" "));
		// 空白で分割したstreamを氏と名の2次元リストへ変換する
		List<Object[]> splitUpNames = separatedNames.collect(Collectors.toList());
		// 氏名のリストをログに出力
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
		// 氏名のリストをインサートしていく
		mJdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

		// Joshという名前のレコードを取り出す(というログ)
		log.info("Querying for customer records where first_name = 'Josh':");

		/*
		 * query関数の引数
		 * 1. クエリ
		 * 2. format元(?の数だけ要素を入れればよいのか)
		 * 3. 関数コールバック(レコードの数だけ呼ばれそう)
		 *     第1引数 : カラムのセット
		 *     第2引数 : レコード番号
		 */
		mJdbcTemplate.query(

				"SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[]{ "Josh" },
				(rs, rowNum) ->
				// テーブルから読みだしたレコードをCustomerオブジェクトに入れる
				new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
		        ).forEach(customer ->
		        log.info(customer.toString()));
	}

}
