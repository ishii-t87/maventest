package hello;

import org.joda.time.LocalTime;

/**
 * @brief
 */
public class HelloWorld {
    public static void main(String[] args) {
    	//
    	LocalTime currentTime = new LocalTime();
    	System.out.println("The current local time is: " + currentTime);
    	// テスト用のクラスを生成する
        Greeter greeter = new Greeter();
        // テスト用メソッドが返す文字列を出力する
        System.out.println(greeter.sayHello());
    }
}
