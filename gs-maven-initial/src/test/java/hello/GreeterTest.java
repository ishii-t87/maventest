package hello;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Greeterクラスのテスト
 */
public class GreeterTest {

	/** 挨拶してくれる */
	private Greeter mGreeter = new Greeter();

	@Test
	public void greeterSaysHello() {
		// sayHelloメソッドに"Hello"という文字列が含まれているかのテスト
		assertThat(mGreeter.sayHello(), containsString("Hello"));
	}
}
