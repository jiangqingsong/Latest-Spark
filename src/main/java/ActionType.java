/**
 * 行为分类，枚举类
 */
public enum ActionType {
    /**
     * 网站访问
     */
    ACCESS("access"),

    /**
     * 搜索引擎搜索
     */
    SEARCH("search"),
    /**
     * 商品搜索
     */
    GOODS_SEARCH("goodsSearch"),
    /**
     * 视频搜索
     */
    VIDEO_SEARCH("videoSearch"),
    /**
     * 查看商品
     */
    GOODS_VIEW("goodsView"),
    /**
     * 商品购买订单
     */
    ORDER("order"),
    /**
     * 移动端广告检测,没有带设备信息
     */
    MOBLIE_AD("ma"),

    /**
     * 移动端广告检测,媒体会传递用户设备信息
     */
    MOBLIE_DEVICE_AD("mda"),

    /**
     * PC桌面端检测
     */
    PC_AD("pa");

    private String action;

    ActionType(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
