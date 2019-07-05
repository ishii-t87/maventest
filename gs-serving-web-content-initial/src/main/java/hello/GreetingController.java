package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @brief "/greeting"への通信を制御する
 */
@Controller
public class GreetingController {

	/**
	 * "/greeting"へのリクエストを制御する
	 *
	 */
    @GetMapping("/greeting")
    // リクエストに"name"は含まれていなくてもよい。なかった場合は初期値が"World"になる。
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    	// GETパラメータにnameを仕込む
        model.addAttribute("name", name);
        return "greeting";
    }

}