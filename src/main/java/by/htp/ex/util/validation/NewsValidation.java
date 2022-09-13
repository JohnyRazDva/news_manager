package by.htp.ex.util.validation;

import java.util.Map;

public interface NewsValidation {

	boolean checkNewsData(String title, String brief, String content);

	Map<String, String> getInvalidNewsData();

}
