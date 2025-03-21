package com.inyaw.blog.bean;import com.fasterxml.jackson.annotation.JsonFormat;import com.inyaw.base.BaseDto;import com.mybatisflex.annotation.Id;import com.mybatisflex.annotation.KeyType;import com.mybatisflex.annotation.Table;import lombok.Getter;import lombok.Setter;import java.time.LocalDateTime;@Table("blog_info")@Setter@Getterpublic class BlogInfo extends BaseDto {    @Id(keyType = KeyType.Auto)    private Integer id;    /**     * 文章标题     */    private String title;    /**     * 封面图     */    private String cover;    /**     * 是否打开评论     */    private Boolean isComment;    /**     * 是否热门     */    private Boolean isHot;    /**     * 发布状态     */    private Boolean status;    /**     * 类型id     */    private String typeId;    /**     * 摘要     */    private String summary;    /**     * 浏览次数     */    private Integer views;    /**     * 创建时间     */    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")    private LocalDateTime createTime;    /**     * 更新时间     */    private LocalDateTime updateTime;}