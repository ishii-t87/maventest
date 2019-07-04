package hello;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @brief コマンドラインから実行される処理
 */
@Component
public class AppRunner implements CommandLineRunner {

	/** ログ出力管理 */
	private static final Logger mLogger = LoggerFactory.getLogger(AppRunner.class);
	/** GitHubに接続してユーザー情報を取得するサービス */
	private final GitHubLookupService mGitHubLookupService;

	/**
	 * GitHubLookupServiceを実行対象として初期化する
	 * @param gitHubLookupService 実行対象となるサービス
	 */
	public AppRunner(GitHubLookupService gitHubLookupService) {
        mGitHubLookupService = gitHubLookupService;
    }

	/**
	 * GitHubユーザーの情報を取得してログに出す
	 * @param args コマンドライン引数
	 */
	@Override
	public void run(String... args) throws Exception {

		// タスクの開始時刻を計測
        long start = System.currentTimeMillis();

        // 各Githubアカウントを取得
        CompletableFuture<User> page1 = mGitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = mGitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = mGitHubLookupService.findUser("Spring-Projects");

        // すべて取得完了するまで待機
        CompletableFuture.allOf(page1, page2, page3).join();

        // タスクの実行にかかった時間を出力
        mLogger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        // 取得したアカウント情報を出力
        mLogger.info("--> " + page1.get());
        mLogger.info("--> " + page2.get());
        mLogger.info("--> " + page3.get());
	}

}
