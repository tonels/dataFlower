package common.cache;

public abstract class AbstractDictUtils {

    public abstract String getDictLabel(String value, String type, String defaultValue);

    public abstract String getDictValue(String label, String type, String defaultLabel);
}
