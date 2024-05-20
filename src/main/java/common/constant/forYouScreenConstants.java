package common.constant;
import common.utils.ConfigLoader;

public class forYouScreenConstants {
    public static ConfigLoader loadConstants =new ConfigLoader("env/ForYou.properties");

    public static final String TITLE= loadConstants.getProperty("dash.title");
    public static  final String SUBTITLE= loadConstants.getProperty("dash.subtitle");
    public static final String EXPLANATIONS= loadConstants.getProperty("dash.explanations");

}
