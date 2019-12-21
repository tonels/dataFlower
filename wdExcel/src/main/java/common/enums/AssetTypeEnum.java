package common.enums;

import org.springframework.util.StringUtils;

/**
 * @Title com.wondertek.data.convert.common.enums
 * @Author beckhamyht
 * @Created 2019/8/10 19:36
 */
public enum AssetTypeEnum {

    /**
     * 媒资类型：
     * 6:剧集(壳)
     * 7:剧集的单集
     * 8:非剧集
     * 9:专辑
     * 10:预留(内容集)
     * 11:图文单篇
     * 12:图文集
     * 13:直播节目
     * 14:单片节目壳
     * 15:直播节目壳
     */
    ASSET_TYPE_6("6", "剧集(壳)"),
    ASSET_TYPE_7("7", "剧集的单集"),
    ASSET_TYPE_8("8", "非剧集"),
    ASSET_TYPE_9("9", "专辑"),
    ASSET_TYPE_10("10", "预留(内容集)"),
    ASSET_TYPE_11("11", "图文单篇"),
    ASSET_TYPE_12("12", "图文集"),
    ASSET_TYPE_13("13", "直播节目"),
    ASSET_TYPE_14("14", "单片节目壳"),
    ASSET_TYPE_15("15", "直播节目壳"),
    ;

    private String type;
    private String typeName;

    AssetTypeEnum(String type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static String getAssetTypeName(String type) {
        if (!StringUtils.isEmpty(type)) {
            for (AssetTypeEnum assetTypeEnum : AssetTypeEnum.values()) {
                if (assetTypeEnum.type.equals(type)) {
                    return assetTypeEnum.typeName;
                }
            }
        }
        return null;
    }

    /**
     * 根据媒资类型判断内容侧媒资是否为壳,前提条件判断mamPlatform!=4,非直播平台
     * 强版权只有壳有评优，子集获取壳的评优；弱版权子集、壳单独评优。直播的壳和子集也各自评优。
     * 此处将判断：内容(6、10、14);视作为壳
     * @param type
     * @return
     * @description
     * @version 1.0
     * @author mgdong
     * @update 2019年9月12日 下午2:22:21
     */
    public static boolean isContentParentAsset(String type) {
        if (!StringUtils.isEmpty(type) && (ASSET_TYPE_6.type.equals(type)||
        		ASSET_TYPE_10.type.equals(type)||ASSET_TYPE_14.type.equals(type))) {
            return true;
        }
        return false;
    }

    /**
     * 根据媒资类型判断直播侧媒资是否为壳,前提条件判断mamPlatform=4 是直播平台
     * 直播平台媒资类型：8、13(已弃用)、15
     * 直播(13(已弃用)、15) ;视作为壳
     * @description 
     * @param type
     * @return
     * @description
     * @version 1.0
     * @author mgdong
     * @update 2019年9月17日 下午5:14:43
     */
    public static boolean isLiveParentAsset(String type) {
        if (!StringUtils.isEmpty(type) && (ASSET_TYPE_15.type.equals(type)||ASSET_TYPE_13.type.equals(type))) {
            return true;
        }
        return false;
    }
}
