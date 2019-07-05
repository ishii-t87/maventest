package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @brief 挨拶を出力するページの制御
 */
@Controller
public class GreetingController {

	/** 制御対象のパス */
	private static final String PATH = "/greeting";

	/**
	 * "/greeting"へのリクエストを処理する
	 * @param model GETパラメータ
	 * @return 紐づけるhtml
	 */
    @GetMapping(PATH)
    public String greetingForm(Model model) {
    	//
        model.addAttribute("greeting", new Greeting());
        // html"greeting"と関連付ける
        return "greeting";
    }

    /**
     * "/greeting"からレスポンスを渡す
     * @param greeting
     * @return 遷移先のhtml
     */
    @PostMapping(PATH)
    // @ModelAttributeでGreeting型にバインドする
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
    	// html"result"へ飛ばす
        return "result";
    }

}
