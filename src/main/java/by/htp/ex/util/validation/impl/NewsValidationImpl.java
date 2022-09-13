package by.htp.ex.util.validation.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import by.htp.ex.util.validation.NewsValidation;

public class NewsValidationImpl implements NewsValidation {

	private final static String TITLE_PATTERN = "((.|\\s)*\\S(.|\\s)*){3,}";
	private final static String BRIEF_PATTERN = "((.|\\s)*\\S(.|\\s)*){10,}";
	private final static String CONTENT_PATTERN = "(.|\\s)*\\S(.|\\s){60,}";
	private final static String INVALID_TITLE_KEY = "title";
	private final static String INVALID_BRIEF_KEY = "brief";
	private final static String INVALID_CONTENT_KEY = "content";
	private Map<String, String> invalidNewsData;

	@Override
	public boolean checkNewsData(String title, String brief, String content) {
		Map<String, String> invalidData = new HashMap();

		if (!checkTitle(title)) {
			invalidData.put(INVALID_TITLE_KEY, INVALID_TITLE_KEY);
		}
		if (!checkBrief(brief)) {
			invalidData.put(INVALID_BRIEF_KEY, INVALID_BRIEF_KEY);
		}
		if (!checkContent(content)) {
			invalidData.put(INVALID_CONTENT_KEY, INVALID_CONTENT_KEY);
		}

		invalidNewsData = invalidData;
		return checkTitle(title) && checkBrief(brief) && checkContent(content);
	}

	@Override
	public Map<String, String> getInvalidNewsData() {
		return invalidNewsData;
	}

	private boolean checkTitle(String title) {
		return Pattern.compile(TITLE_PATTERN).matcher(title).matches();
	}

	private boolean checkBrief(String brief) {
		return Pattern.compile(BRIEF_PATTERN).matcher(brief).matches();
	}

	private boolean checkContent(String content) {
		return Pattern.compile(CONTENT_PATTERN).matcher(content).matches();
	}

}
