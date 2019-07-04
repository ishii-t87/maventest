package hello;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @brief 非同期処理を伴うSpringApplicationクラス
 */
@SpringBootApplication
@EnableAsync
public class Application {

	public static void main(String[] args) {
		// アプリケーションを実行後に終了する
		SpringApplication.run(Application.class, args).close();
	}


	/**
	 * 非同期タスクを生成して返却する
	 * @return 生成したスレッド実行インスタンス
	 */
	@Bean
	public Executor taskExecutor() {

		// スレッド処理を制御するためにThreadPoolTaskExecutorを使う
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// アイドル状態でプールされるスレッドの最大数を設定
		executor.setCorePoolSize(2);
		// プール可能なスレッドの最大数を設定
		executor.setMaxPoolSize(2);
		// キューに詰め込めるジョブの最大数
		executor.setQueueCapacity(500);
		// スレッド名をわかりやすくするために接頭辞を付ける
		executor.setThreadNamePrefix("GithubLookup-");
		// 設定値を用いて初期化する
		executor.initialize();

		return executor;
	}
}
