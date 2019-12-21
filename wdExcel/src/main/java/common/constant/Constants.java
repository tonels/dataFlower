package common.constant;

public class Constants {
    /**
     * 是否 是
     */
    public static final String YES_1 = "1";
    /**
     * 是否 否
     */
    public static final String NO_0 = "0";

    /**
     * 是否 是
     */
    public static final int INT_YES_1 = 1;
    /**
     * 是否 否
     */
    public static final int INT_NO_0 = 0;

    /**
     * 是否删除 否
     */
    public static final int DELETE_0 = 0;
    /**
     * 是否删除 是
     */
    public static final int DELETE_1 = 1;
    /**
     * deleted 停用
     */
    public static final int DELETE_2 = 2;

    /**
     * 日志新增
     */
    public static final int LOG_ADD = 1;
    /**
     * 日志更新
     */
    public static final int LOG_UPDATE = 1;
    /**
     * 日志删除
     */
    public static final int LOG_DELETE = 1;


    /**
     * 渠道包-状态 已发布
     */
    public static final int CHANNEL_PACKAGE_STATUS_1 = 1;
    /**
     * 渠道包-状态 已撤回
     */
    public static final int CHANNEL_PACKAGE_STATUS_2 = 2;
    /**
     * 渠道包-状态 待发布
     */
    public static final int CHANNEL_PACKAGE_STATUS_3 = 3;
    /**
     * 渠道包-状态 发布失败
     */
    public static final int CHANNEL_PACKAGE_STATUS_4 = 4;
    /**
     * 渠道包-状态 删除
     */
    public static final int CHANNEL_PACKAGE_STATUS_0 = 0;

    /**
     * 已选规则
     */
    public static final int RULE_TYPE_1 = 1;
    /**
     * 限制规则
     */
    public static final int RULE_TYPE_2 = 2;
    /**
     * 释放规则
     */
    public static final int RULE_TYPE_3 = 3;
    /**
     * 默认计费规则
     */
    public static final int RULE_TYPE_4 = 4;

    /**
     * <b>Fields</b> DISTON_STATUS_10 : 待分发
     */
    public static final int DISTON_STATUS_10 = 10;
    /**
     * <b>Fields</b> DISTON_STATUS_11 : 分发中
     */
    public static final int DISTON_STATUS_11 = 11;
    /**
     * <b>Fields</b> DISTON_STATUS_12 : 分发成功
     */
    public static final int DISTON_STATUS_12 = 12;
    /**
     * <b>Fields</b> DISTON_STATUS_13 : 分发失败
     */
    public static final int DISTON_STATUS_13 = 13;
    /**
     * <b>Fields</b> DISTOFF_STATUS_20 : 待下线
     */
    public static final int DISTOFF_STATUS_20 = 20;
    /**
     * <b>Fields</b> DISTOFF_STATUS_21 : 下线中
     */
    public static final int DISTOFF_STATUS_21 = 21;
    /**
     * <b>Fields</b> DISTOFF_STATUS_22 : 下线成功
     */
    public static final int DISTOFF_STATUS_22 = 22;
    /**
     * <b>Fields</b> DISTOFF_STATUS_23 : 下线失败
     */
    public static final int DISTOFF_STATUS_23 = 23;

    /**
     * <b>Fields</b> PUBON_STATUS_10 : 待发布
     */
    public static final int PUBON_STATUS_10 = 10;
    /**
     * <b>Fields</b> PUBON_STATUS_11 : 发布中
     */
    public static final int PUBON_STATUS_11 = 11;
    /**
     * <b>Fields</b> PUBON_STATUS_12 : 发布成功
     */
    public static final int PUBON_STATUS_12 = 12;
    /**
     * <b>Fields</b> PUBON_STATUS_13 : 发布失败
     */
    public static final int PUBON_STATUS_13 = 13;
    /**
     * <b>Fields</b> PUBON_STATUS_30 : 发布无分发规则
     */
    public static final int PUBON_STATUS_30 = 30;

    /**
     * <b>Fields</b> PUBOFF_STATUS_NOT : 待撤回
     */
    public static final int PUBOFF_STATUS_20 = 20;
    /**
     * <b>Fields</b> PUBOFF_STATUS_IN : 撤回中
     */
    public static final int PUBOFF_STATUS_21 = 21;
    /**
     * <b>Fields</b> PUBOFF_STATUS_SUCCESS : 撤回成功
     */
    public static final int PUBOFF_STATUS_22 = 22;
    /**
     * <b>Fields</b> PUBOFF_STATUS_FAILURE : 撤回失败
     */
    public static final int PUBOFF_STATUS_23 = 23;

    /**
     * <b>Fields</b> RECALL_STATUS_NO : 未发生撤回动作
     */
    public static final int RECALL_STATUS_0 = 0;
    /**
     * <b>Fields</b> RECALL_STATUS_YES : 已发生撤回动作
     */
    public static final int RECALL_STATUS_1 = 1;

    /**
     * <b>Fields</b> EMERGENCY_0 : 非紧急
     */
    public static final String EMERGENCY_0 = "0";
    /**
     * <b>Fields</b> EMERGENCY_1 : 紧急
     */
    public static final String EMERGENCY_1 = "1";

    /**
     * 播控状态 0:待播控
     */
    public static final int BC_STATUS_0 = 0;
    /**
     * 播控状态 1:播控通过
     */
    public static final int BC_STATUS_1 = 1;
    /**
     * 播控状态 2:播控拒绝
     */
    public static final int BC_STATUS_2 = 2;
    /**
     * 播控状态 3：未播控
     */
    public static final int BC_STATUS_3 = 3;
    /**
     * 播控状态 4：上线
     */
    public static final int BC_STATUS_4 = 4;
    /**
     * 播控状态 5：下线
     */
    public static final int BC_STATUS_5 = 5;


    /**
     * 发布状态 - 待发布
     */
    public static final int PUB_STATUS_10 = 10;
    /**
     * 发布状态 - 发布中
     */
    public static final int PUB_STATUS_11 = 11;
    /**
     * 发布状态 - 已发布
     */
    public static final int PUB_STATUS_12 = 12;
    /**
     * 发布状态 - 发布失败
     */
    public static final int PUB_STATUS_13 = 13;
    /**
     * 发布状态 - 待撤回
     */
    @Deprecated
    public static final int PUB_STATUS_20 = 20;
    /**
     * 发布状态 - 撤回中
     */
    @Deprecated
    public static final int PUB_STATUS_21 = 21;
    /**
     * 发布状态 - 已撤回
     */
    public static final int PUB_STATUS_22 = 22;
    /**
     * 发布状态 - 撤回失败
     */
    public static final int PUB_STATUS_23 = 23;
    /**
     * 发布状态 - 无发布匹配规则
     */
    public static final int PUB_STATUS_30 = 30;

    /**
     * 是否自动打券 - 1:观影券
     */
    public static final int AUTO_TICKET_1 = 1;
    /**
     * 是否自动打券 - 2观赛券
     */
    public static final int AUTO_TICKET_2 = 2;
    /**
     * 是否自动打券  - 0否
     */
    public static final int AUTO_TICKET_0 = 0;

    /**
     * 是否自动权益  - 0否
     */
    public static final int AUTO_EQUITY_0 = 0;
    /**
     * 是否自动权益  - 1是
     */
    public static final int AUTO_EQUITY_1 = 1;

    /**
     * 计费类型 - 0:产品计费
     */
    public static final int CHARGE_TYPE_0 = 0;
    /**
     * 计费类型 - 1:售价名称
     */
    public static final int CHARGE_TYPE_1 = 1;
    /**
     * 计费类型 - 2:权益节点
     */
    public static final int CHARGE_TYPE_2 = 2;

    /**
     * 计费点状态 1:待同步
     */
    public static final int CHARGE_STATUS_1 = 1;
    /**
     * 计费点状态 2:已同步
     */
    public static final int CHARGE_STATUS_2 = 2;
    /**
     * 计费点状态 3:已下线
     */
    public static final int CHARGE_STATUS_3 = 3;

    /**
     * 计费点组状态 1:已发布
     */
    public static final int GROUP_STATUS_1 = 1;
    /**
     * 计费点组状态 2:已撤回
     */
    public static final int GROUP_STATUS_2 = 2;
    /**
     * 计费点组状态 3:待发布
     */
    public static final int GROUP_STATUS_3 = 3;
    /**
     * 计费点组状态 4:发布失败
     */
    public static final int GROUP_STATUS_4 = 4;
    /**
     * 计费点组状态 5:删除
     */
    public static final int GROUP_STATUS_5 = 5;

    /**
     * 树形结构-选择类型 1:一级产品
     */
    public static final int TREE_SELECT_TYPE_1 = 1;
    /**
     * 树形结构-选择类型 2:二级产品
     */
    public static final int TREE_SELECT_TYPE_2 = 2;
    /**
     * 树形结构-选择类型 3:商品包
     */
    public static final int TREE_SELECT_TYPE_3 = 3;

    /**
     * 发布状态 1：已发布
     */
    public static final int PRDINFO_PUBLISH_STATUS_1 = 1;
    /**
     * 发布状态 0：未发布
     */
    public static final int PRDINFO_PUBLISH_STATUS_0 = 0;

    /**
     * <b>Fields</b> ASSET_FORM_TYPE_6 : 剧集（壳）
     */
    public static final String ASSET_FORM_TYPE_6 = "6";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_7 : 剧集的单集
     */
    public static final String ASSET_FORM_TYPE_7 = "7";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_8 : 非剧集
     */
    public static final String ASSET_FORM_TYPE_8 = "8";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_9 : 专辑
     */
    public static final String ASSET_FORM_TYPE_9 = "9";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_10 : 内容集
     */
    public static final String ASSET_FORM_TYPE_10 = "10";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_11 : 单篇
     */
    public static final String ASSET_FORM_TYPE_11 = "11";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_12 : 图文集
     */
    public static final String ASSET_FORM_TYPE_12 = "12";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_13 : 直播节目
     */
    public static final String ASSET_FORM_TYPE_13 = "13";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_14 : 单片节目壳
     */
    public static final String ASSET_FORM_TYPE_14 = "14";
    /**
     * <b>Fields</b> ASSET_FORM_TYPE_15 : 直播节目壳
     */
    public static final String ASSET_FORM_TYPE_15 = "15";

    /**
     * <b>Fields</b> ASSET_STATUS_0 : 新增
     */
    public static final int ASSET_STATUS_0 = 0;
    /**
     * <b>Fields</b> ASSET_STATUS_1 : 变更（不涉及媒体文件的更新）
     */
    public static final int ASSET_STATUS_1 = 1;
    /**
     * <b>Fields</b> ASSET_STATUS_2 : 删除
     */
    public static final int ASSET_STATUS_2 = 2;
    /**
     * <b>Fields</b> ASSET_STATUS_3 : 变更（涉及媒体文件的更新）
     */
    public static final int ASSET_STATUS_3 = 3;
    /**
     * <b>Fields</b> ASSET_STATUS_4 : 解绑
     */
    public static final int ASSET_STATUS_4 = 4;
    /**
     * <b>Fields</b> ASSET_STATUS_5 : 屏蔽
     */
    public static final int ASSET_STATUS_5 = 5;
    /**
     * <b>Fields</b> ASSET_STATUS_6 : 恢复上线
     */
    public static final int ASSET_STATUS_6 = 6;
    /**
     * <b>Fields</b> ASSET_STATUS_7 : 版权到期下线
     */
    public static final int ASSET_STATUS_7 = 7;
    /**
     * <b>Fields</b> ASSET_STATUS_8 : 版权替换
     */
    public static final int ASSET_STATUS_8 = 8;

    /**
     * 媒资类型 1:点播节目
     */
    public static final String CONTENT_TYPE_1 = "1";
    /**
     * 媒资类型 2:直播节目
     */
    public static final String CONTENT_TYPE_2 = "2";
    /**
     * 媒资类型 3:点播视频
     */
    public static final String CONTENT_TYPE_3 = "3";
    /**
     * 媒资类型 4:直播频道
     */
    public static final String CONTENT_TYPE_4 = "4";
    /**
     * 媒资类型 5:直播节目单
     */
    public static final String CONTENT_TYPE_5 = "5";
    /**
     * 媒资类型 6:图文
     */
    public static final String CONTENT_TYPE_6 = "6";
    /**
     * 媒资类型 7:图册
     */
    public static final String CONTENT_TYPE_7 = "7";
    /**
     * 媒资类型 8:播单
     */
    public static final String CONTENT_TYPE_8 = "8";
    /**
     * 媒资类型 9:内容关联
     */
    public static final String CONTENT_TYPE_9 = "9";

    /**
     * <b>Fields</b> SUPER_BC_TYPE_1 : 先上后审
     */
    public static final String SUPER_BC_TYPE_1 = "1";
    /**
     * <b>Fields</b> SUPER_BC_TYPE_2 : 先审后上
     */
    public static final String SUPER_BC_TYPE_2 = "2";
    /**
     * <b>Fields</b> SUPER_BC_TYPE_3 : 已内审不请求外部播控
     */
    public static final String SUPER_BC_TYPE_3 = "3";
    /**
     * <b>Fields</b> MOVE_STATUS_0 : 移包状态 0：待移动
     */
    public static final int MOVE_STATUS_0 = 0;
    /**
     * <b>Fields</b> MOVE_STATUS_1 : 移包状态 1：准备移动
     */
    public static final int MOVE_STATUS_1 = 1;
    /**
     * <b>Fields</b> MOVE_STATUS_2 : 移包状态 2：移动中
     */
    public static final int MOVE_STATUS_2 = 2;
    /**
     * <b>Fields</b> MOVE_STATUS_3 : 移包状态 3：移动成功
     */
    public static final int MOVE_STATUS_3 = 3;
    /**
     * <b>Fields</b> MOVE_STATUS_4 : 移包状态  4:放弃
     */
    public static final int MOVE_STATUS_4 = 4;
    /**
     * <b>Fields</b> MOVE_STATUS_5 : 移包状态 5:处理失败
     */
    public static final int MOVE_STATUS_5 = 5;
    /**
     * <b>Fields</b> MOVE_TYPE_1 : 移包类型 1:手动
     */
    public static final int MOVE_TYPE_1 = 1;
    /**
     * <b>Fields</b> MOVE_TYPE_2 : 移包类型 2:计划
     */
    public static final int MOVE_TYPE_2 = 2;


    /**
     * <b>Fields</b> PRD_INFO_PUBLISH_STATUS_1 : 商品包发布状态 1:待发布
     */
    public static final int PRD_INFO_PUBLISH_STATUS_1 = 1;
    /**
     * <b>Fields</b> PRD_INFO_PUBLISH_STATUS_2 : 商品包发布状态 2:已发布
     */
    public static final int PRD_INFO_PUBLISH_STATUS_2 = 2;
    /**
     * <b>Fields</b> PRD_INFO_PUBLISH_STATUS_2 : 商品包发布状态 3：已撤回
     */
    public static final int PRD_INFO_PUBLISH_STATUS_3 = 3;

    /**
     * <b>Fields</b> FORM_TYPE_6 : 媒资类型 :剧集壳
     */
    public static final String PRD_CONT_FORM_TYPE_6 = "6";


    /**
     * <b>Fields</b> PRD_CONT_TYPE_8 : 服务类型 :精简直播
     */
    public static final String PRD_CONT_TYPE_8 = "8";

    /**
     * <b>Fields</b> PRD_CONT_TYPE_9 : 服务类型 :精简模拟直播
     */
    public static final String PRD_CONT_TYPE_9 = "9";

    /**
     * <b>Fields</b> PRD_CONT_TYPE_12 : 服务类型 :互联网直播
     */
    public static final String PRD_CONT_TYPE_12 = "12";

    /**
     * <b>Fields</b> FREE_FLOW_POMS_TYPE_1 : 免流量类型：商品包
     */
    public static final String FREE_FLOW_POMS_TYPE_1 = "1";
    /**
     * <b>Fields</b> FREE_FLOW_POMS_TYPE_2 : 免流量类型：节目
     */
    public static final String FREE_FLOW_POMS_TYPE_2 = "2";

    /**
     * <b>Fields</b> ASSOCIATION_TYPE_1 : 正片
     */
    public static final String ASSOCIATION_TYPE_1 = "1";
    /**
     * <b>Fields</b> ASSOCIATION_TYPE_2 : 预告/拆条
     */
    public static final String ASSOCIATION_TYPE_2 = "2";
    /**
     * <b>Fields</b> ASSOCIATION_TYPE_3 : 周边
     */
    public static final String ASSOCIATION_TYPE_3 = "3";
    /**
     * <b>Fields</b> ASSOCIATION_TYPE_4 : 图文
     */
    public static final String ASSOCIATION_TYPE_4 = "4";
    /**
     * <b>Fields</b> ASSOCIATION_TYPE_5 : 二级频道媒资节目对应关系
     */
    public static final String ASSOCIATION_TYPE_5 = "5";
    /**
     * <b>Fields</b> ASSOCIATION_TYPE_6 : 点播媒资节目对应关系
     */
    public static final String ASSOCIATION_TYPE_6 = "6";

    /**
     * <b>Fields</b> COPYRIGHT_CHANGED_0 : 版权修改 不修改节目id
     */
    public static final int COPYRIGHT_CHANGED_0 = 0;
    /**
     * <b>Fields</b> COPYRIGHT_CHANGED_1 : 版权修改 修改节目id
     */
    public static final int COPYRIGHT_CHANGED_1 = 1;


    /**
     * validate 框架使用
     */
    public static final String VALIDATE_DEFAULT = "DEFAULT";
    public static final String VALIDATE_UPDATE = "UPDATE";
    public static final String VALIDATE_DEL = "DELETE";
    public static final String VALIDATE_FIND = "FIND";
    public static final String VALIDATE_ERROR = "validate error :{}";

    /**
     * redis 字典key
     */
    public static final String REDIS_KEY_BCDICT_TYPE = "oes-charge-setting:dict-type:";

    /**
     * redis 系统配置key
     */
    public static final String REDIS_KEY_SYSTEM_CKEY = "oes-charge-setting:system-ckey:";

    /**
     * redis 二级产品key
     */
    public static final String REDIS_KEY_PRODUCT_CKEY = "oes-charge-service:productInfoPackage-id:";

    /**
     * redis 商品包key
     */
    public static final String REDIS_KEY_PRDINFO_CKEY = "oes-charge-service:prdInfo-id:";

    /**
     * redis key ：壳id延迟topic
     */
    public static final String SHELL_DELAY_TOPIC = "shell_delay_topic";

    /**
     * 清理缓存间隔时间以及默认过期时间 秒
     */
    public static final int DEFALUT_EXPIRE = 30;


    /**
     * 3 种节目计费配置表常量
     */
    public static final String CHARGE_CONFIG_BASE = "节目基本计费配置表";
    public static final String CHARGE_CONFIG_FEE = "节目正片限免配置表";
    public static final String CHARGE_CONFIG_SALES = "节目促销配置表";

    /**
     * 没有发布
     */
    public static final Integer PUBLISH_STATE_1 = 1;
    /**
     * 已经发布
     */
    public static final Integer PUBLISH_STATE_2 = 2;

    //1：垂直栏目 2:品牌栏目 3：V+
    /**
     * 产品包类型-1-垂直栏目
     */
    public static final Integer PRD_PACKAGE_TYPE_1 = 1;
    /**
     * 产品包类型-2-品牌栏目
     */
    public static final Integer PRD_PACKAGE_TYPE_2 = 2;
    /**
     * 产品包类型-3-V+
     */
    public static final Integer PRD_PACKAGE_TYPE_3 = 3;

    /**
     * 播控状态 - 0 - 未播控
     */
    public static final int BC_STATUS_NOT = 0;// 未播控
    /**
     * 播控状态 - 1 - 播控通过
     */
    public static final int BC_STATUS_YES = 1;// 播控通过

    /**
     * 是否自动发布内容 - 否
     */
    public static final Integer AUTO_PUBLISH_NOT = 0;
    /**
     * 是否自动发布内容 - 是
     */
    public static final Integer AUTO_PUBLISH_YES = 1;

    /**
     * 系统配置表-需要分发的平台类型列表字符串列表
     */
    public static final String NEED_DIST_PLATFORM_TYPE = "NEED_DIST_PLATFORM_TYPE";

    /**
     * 分发状态 - 已经分发
     */
    public static final String DIST_RESULT_DISTRIBUTED = "1";    //1-已分发, 2-已下线
    /**
     * 分发状态 - 已经下线
     */
    public static final String DIST_RESULT_OFFLINE = "2";    //1-已分发, 2-已下线

    /**
     * 售价名称计费是否人工添加 - 是
     */
    public static final String IS_PERSON_OPERATION = "1";

    /**
     * SERIAL_SORT_ASC:剧集限免配置子集收费，收费 1.
     *
     * @since JDK 1.8
     */
    public static final Integer SERIAL_SORT_ASC = 1;

    /**
     * SERIAL_SORT_DESC:剧集限免配置子集免费，免费 2.
     *
     * @since JDK 1.8
     */
    public static final Integer SERIAL_SORT_DESC = 2;

    public static final String DATA_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMATTER_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_1 : 新增
     */
    public static final Integer OPERATE_TYPE_1 = 1;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_2 : 变更
     */
    public static final Integer OPERATE_TYPE_2 = 2;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_3 : 删除
     */
    public static final Integer OPERATE_TYPE_3 = 3;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_4 : 发布
     */
    public static final Integer OPERATE_TYPE_4 = 4;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_5 : 撤回
     */
    public static final Integer OPERATE_TYPE_5 = 5;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_6 : 导入
     */
    public static final Integer OPERATE_TYPE_6 = 6;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_7 : 导出
     */
    public static final Integer OPERATE_TYPE_7 = 7;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_8 : 模板下载
     */
    public static final Integer OPERATE_TYPE_8 = 8;
    /**
     * <b>Fields</b> 操作状态 OPERATE_TYPE_9 : 媒资入包
     */
    public static final Integer OPERATE_TYPE_9 = 9;

    /**
     * PRD_CONT_PUBLISH_12:节目发布状态，PRD_CONT_PUBLISH_12 已发布.
     *
     * @since JDK 1.8
     */
    public static final Integer PRD_CONT_PUBLISH_12 = 12;

    /**
     * PRD_CONT_PUBLISH_22:节目发布状态，PRD_CONT_PUBLISH_22 已撤回.
     *
     * @since JDK 1.8
     */
    public static final Integer PRD_CONT_PUBLISH_22 = 22;

    /**
     * PRD_CONT_DELETE_1:节目删除状态，PRD_CONT_DELETE_1 系统删除.
     *
     * @since JDK 1.8
     */
    public static final Integer PRD_CONT_DELETE_1 = 1;


    /**
     * kafka节目发布状态 1： 发布
     */
    public static final int PRD_CONT_PUBLISH_1 = 1;
    /**
     * kafka节目发布状态 2： 撤回
     */
    public static final int PRD_CONT_PUBLISH_2 = 2;
    /**
     * kafka节目发布状态 3： 删除
     * 就是撤回+删除节目
     */
    public static final int PRD_CONT_PUBLISH_3 = 3;
    /**
     * 自动发布
     */
    public static final int PRD_CONT_PUBLISH_4 = 4;


    /**
     * <b>Fields</b> COPYRIGHT_AREA_CODE : 版权地域范围字典type
     */
    public static final String COPYRIGHT_AREA_TYPE = "copyright_area_white";

    /**
     * <b>Fields</b> TERMINAL_COPYRIGHT_CODE : 版权终端字典type
     */
    public static final String TERMINAL_COPYRIGHT_TYPE = "pc_prd_info_terminal_copy_right";
    
    /**
     * SERIAL_TYPE_YS:正片类型 ys.
     * @since JDK 1.8
     */
    public static final String SERIAL_TYPE_YS = "ys";
    
    /**
     * <b>Fields</b> ASSET_HASMEDIA_0 : 媒资是否有媒体文件 0：没有
     */
    public static final int ASSET_HASMEDIA_0 = 0;
    
    /**
     * <b>Fields</b> ASSET_HASMEDIA_1 : 媒资是否有媒体文件 1：有
     */
    public static final int ASSET_HASMEDIA_1 = 1;
    
    /**
     * <b>Fields</b> RULE_HASMEDIAFILE_0 : 规则是否可无媒体文件 0:必须有媒体文件
     */
    public static final int RULE_HASMEDIAFILE_0 = 0;
    
    /**
     * <b>Fields</b> RULE_HASMEDIAFILE_1 : 规则是否可无媒体文件 1:可无媒体文件
     */
    public static final int RULE_HASMEDIAFILE_1 = 1;
    
    /** 
     * 重复数据检查，redis中存储标记过期时间
     */
    public static final int TIMEOUT_SECONDS = 5;

    /**
     * 新批价系统标记
     */
    public static final int SYSTEM_FLAG_1 = 1;

    /**
     * 商品包类型-基本计费
     */
    public static final int PRD_INFO_CHARGE_TYPE_BASIC = 1;

    /**
     * 商品包类型-促销计费
     */
    public static final int PRD_INFO_CHARGE_TYPE_SAL = 2;

}
