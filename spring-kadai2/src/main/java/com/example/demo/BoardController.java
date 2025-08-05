package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
//	レコードを格納するためのリストをフィールドに作成。(投稿を保持するためのリスト)
	private List<Record> records = new ArrayList<>();
//	getメソッドの定義
//　初期表示 
	@RequestMapping(value = "/")
	public String showBoard(Model model) {
		
		model.addAttribute("records", records);
		model.addAttribute("error", null);
		return "board";
	}
//	投稿処理
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public String applyPost(@RequestParam String name,
													@RequestParam String contents, 
													@RequestParam String mood,
													Model model) {
//		postメソッドで受け取った値未入力であった場合の処理。
		if (name.trim().isEmpty() || contents.trim().isEmpty()) {
			// エラーメッセージの指定
			model.addAttribute("error", "名前と書き込みを入力してください");
			// 投稿は市内が、今までの投稿一覧を保持する。
			model.addAttribute("records", records);
//			board.htmlを参照する。
			return "board";
		}
		
//		正常系の処理
//		入力されたレコードを格納するためのインスタンスを生成。
		Record newRecord = new Record(name, contents, mood);
//		フィールドに内容を追加。
		records.add(newRecord);
//		更新時に2重で投稿が飛ぶことを防ぐ。
		return "redirect:/";
	}
	
	@RequestMapping(value= "/clear", method = RequestMethod.POST)
//	投稿削除用
	public String clear() {
		records.clear();
//	2重更新防止
		return "redirect:/";
	}
}
