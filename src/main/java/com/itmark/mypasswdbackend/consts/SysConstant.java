package com.itmark.mypasswdbackend.consts;

/**
 * @功能说明 计算相关常量
 * @Author
 * @Date
 */
public interface SysConstant {

    /**
     * 1文章点赞数
     */
    String ARTICLE_LIKE_NUM_KEY= "ARTICLE_LIKE_NUM_";
    /**
     * 2文章浏览数
     */
    String ARTICLE_LOOK_NUM_KEY= "ARTICLE_LOOK_NUM_";

    /**
     * 3 文章是否点赞，VALUE里面存的是USER的ID列表
     */
    String ARTICLE_LIKE_USER_KEY= "ARTICLE_LIKE_USER_";
    Long NUM_INCREMENT_PLUS_ONE = 1L;
    Long NUM_INCREMENT_DES_ONE = 1L;

    Integer OSS_VALID_1 = 1;
    Integer OSS_VALID_0 = 0;

    /**
     * 年月日格式化
     */
    String DATE_TIME_FORMAT= "yyyy-MM-dd";

    /**
     * 文章标题占位符
     */
    String ARTICLE_TITLE = "ARTICLE_TITLE";

    /**
     * 消息分类-系统消息
     */
    String MSG_TYPE_SYSTEM = "SYS";

    /**
     * redis key
     */
    String ARTICLE_MSG_OPEN_FLAG_KYE = "ARTICLE_MSG_OPEN_FLAG";

    /**
     * 评论树最大深度
     */
    Integer COMMENT_TREE_DEEP_MAX = 3;

    /**
     * 评论树根ID
     */
    Integer COMMENT_TREE_FATHER_ID = 0;

    /**
     * 邮件主题（标题）
     */
    String EMAIL_SUBJECT_COMMENT = "小码博客验证码";

    /**
     * 匿名访问集合REDIS-KEY
     */
    String ANONYMOUSURLS_KEY="SPRINGSECURITY-ANONYMOUSURLS";

    /**
     * 请求响应体内容
     */
    String RESP_DATA_KEY = "data";
}
