package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @brief ユーザー入力フォームのコントローラ
 */
@Controller
public class WebController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// "/results"にresults.htmlを配置する
		registry.addViewController("/results").setViewName("results");
	}

	/**
	 * トップに対するリクエストのGETメソッド
	 * @param personForm personForm型にバインドして受け取る
	 * @return form.htmlに飛ばす
	 */
	@GetMapping("/")
	public String showForm(PersonForm personForm) {
		return "form";
	}

	/**
	 * トップからのPOSTメソッド
	 * @param personForm バインドした入力情報をvalidateして受け取る
	 * @param bindingResult バインド＆validateした結果の情報
	 * @return 遷移先画面
	 */
	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
		// validateに失敗した場合はform.htmlを表示して再入力を促す
		if (bindingResult.hasErrors()) {
			return "form";
		}
		// 問題なければ結果画面にリダイレクトする
		return "redirect:/results";
	}

}
