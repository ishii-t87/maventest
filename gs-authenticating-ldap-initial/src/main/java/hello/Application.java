package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @brief SpringApplicationクラス
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	// アプリケーションを実行後に終了する
        SpringApplication.run(Application.class, args);
    }

}
