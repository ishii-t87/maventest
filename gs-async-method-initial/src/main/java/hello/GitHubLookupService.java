package hello;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @brief GitHubに接続して対象のデータを探すサービス
 */
@Service
public class GitHubLookupService {

	/** ログ出力管理 */
	private static final Logger mLogger = LoggerFactory.getLogger(GitHubLookupService.class);
	/** GitHubURLの接頭辞 */
	private static final String GITHUB_URL_PREFIX = "https://api.github.com/users/";
	/** RESTapiを呼び出すためのテンプレート */
	private final RestTemplate mRestTemplate;

	/**
	 * ビルダーを受け取り、RESTTemplateを初期化する
	 * @param restTemplateBuilder 事前にbuilderで生成したオブジェクト
	 **/
	public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        mRestTemplate = restTemplateBuilder.build();
    }

	/**
	 * 指定したユーザーのGitHubアカウント情報を取得する
	 * @param userName 検索対象のユーザーアカウント名
	 * @throws 割り込み処理が発生した場合に例外を投げる
	 * @return 取得したユーザー情報
	 */
	@Async
	public CompletableFuture<User> findUser(String userName) throws InterruptedException {

		// 検索を開始した旨をログに出力
		mLogger.info("Looking up " + userName);
		// ユーザー名を元にURLの文字列を生成
        String url = GITHUB_URL_PREFIX + userName;
        // 対象URLから取得したJSONオブジェクトをUser型にキャストする
        User results = mRestTemplate.getForObject(url, User.class);
        // 非同期処理の確認のために1秒間のスリープを挟む
        Thread.sleep(1000L);
        // Userの取得が完了したら呼び出し元に返却する
        return CompletableFuture.completedFuture(results);
	}
}
