import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String regexEmotion = "\\[emoji_0[0-9]{2}\\]";
        Pattern patternEmotion = Pattern.compile(regexEmotion);
        String spannableString = "是大方的说法大飞[emoji_070]xdsfdfsf";
        Matcher matcherEmotion = patternEmotion.matcher(spannableString);
        while (matcherEmotion.find()) {
        	System.out.println(1);
        }
	}

}
