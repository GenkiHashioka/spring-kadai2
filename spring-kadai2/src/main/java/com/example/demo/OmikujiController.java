package com.example.demo;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// ---------------------------------------------------------------
// [概要] おみくじ結果を表示するAPIを作成する
// [詳細] おみくじの結果をランダムで表示させるためのコントローラーを作成する。
// ---------------------------------------------------------------


@Controller
public class OmikujiController {
	// getメソッドの作成。
	@RequestMapping(value ="/omikuji.html")
	public ModelAndView top(ModelAndView mv) {
		mv.setViewName("omikujiController");
		return mv;
	}
	
	// @PostMapping("/omikuji")のほうが今後はよく使いそう。
	// /omikujiにページ遷移した時にPOSTリクエストを受取、次の処理を行う。
	@RequestMapping(value ="/omikuji", method= RequestMethod.POST)
	// おみくじの結果をランダムで算出するためのメソッド。
	public String omikuji(@RequestParam String name, Model model) {
		// フォームに入力された値がnullもしくは文字数が0,空文字の場合引数nameに"ゲスト"を格納
		if (name == null || name.trim().isEmpty()) {
			name = "ゲスト";
		}
		// Randomクラスを使用して1~6までのランダムな整数を変数に取得する
		Random random = new Random();
		int luckNum = random.nextInt(6);
		// おみくじの結果を格納するための変数の宣言。
		String result;
		// ｓｗｉｔｃｈ文 指定の確率になるようにcaseを配置する。
		switch (luckNum) {
//		大吉 1/6
		case 0:
			result = "大吉";
			break;
//			吉 1/2
		case 1:
		case 2:
		case 3:
			result = "吉";
			break;
//			小吉  1/6
		case 4:
			result = "小吉";
			break;
//			凶 1/6
		case 5:
			result = "凶";
			break;
		// 理論上存在しないパターンだが、エラーハンドリングとして記述。
		default:
			result = "エラー";
			break;
		}
		// Modelクラスにデータの受け渡しをする。
		model.addAttribute("name", name); // 入力された名前
		model.addAttribute("result", result); // 算出されたおみくじの結果
		
		// ビューの指定。この場合だとomikujiResult.htmlを表示することになる。
		return "omikujiResult"; 
		
	}
}
