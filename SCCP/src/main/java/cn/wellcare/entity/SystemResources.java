package cn.wellcare.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源表
 * <p>
 * Table: <strong>system_resources</strong>
 * <p>
 * <table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1
 * #666;padding:3px;">
 * <tr style="background-color:#ddd;Text-align:Left;">
 * <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th
 * nowrap>字段类型</th><th nowrap>说明</th>
 * </tr>
 * <tr>
 * <td>content</td>
 * <td>{@link String}</td>
 * <td>content</td>
 * <td>character varying</td>
 * <td>content</td>
 * </tr>
 * <tr>
 * <td>createTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>create_time</td>
 * <td>timestamp with time zone</td>
 * <td>create_time</td>
 * </tr>
 * <tr>
 * <td>id</td>
 * <td>{@link Integer}</td>
 * <td>id</td>
 * <td>integer</td>
 * <td>id</td>
 * </tr>
 * <tr>
 * <td>pid</td>
 * <td>{@link Integer}</td>
 * <td>pid</td>
 * <td>integer</td>
 * <td>pid</td>
 * </tr>
 * <tr>
 * <td>resIcon</td>
 * <td>{@link String}</td>
 * <td>res_icon</td>
 * <td>character varying</td>
 * <td>资源图标</td>
 * </tr>
 * <tr>
 * <td>resId</td>
 * <td>{@link String}</td>
 * <td>res_id</td>
 * <td>character varying</td>
 * <td>资源图标id</td>
 * </tr>
 * <tr>
 * <td>scope</td>
 * <td>{@link Integer}</td>
 * <td>scope</td>
 * <td>smallint</td>
 * <td>应用范围</td>
 * </tr>
 * <tr>
 * <td>status</td>
 * <td>{@link Integer}</td>
 * <td>status</td>
 * <td>smallint</td>
 * <td>1、未删除2、删除</td>
 * </tr>
 * <tr>
 * <td>type</td>
 * <td>{@link Integer}</td>
 * <td>type</td>
 * <td>smallint</td>
 * <td>1、菜单；2、按钮</td>
 * </tr>
 * <tr>
 * <td>url</td>
 * <td>{@link String}</td>
 * <td>url</td>
 * <td>character varying</td>
 * <td>url</td>
 * </tr>
 * </table>
 *
 */
public class SystemResources implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6921481511319766357L;
	private String content; // content
	private java.util.Date createTime; // create_time
	private Integer id; // id
	private Integer pid; // pid
	private String resIcon; // 资源图标
	private String resId; // 资源图标id
	private Integer scope; // 应用范围
	private Integer status; // 1、未删除2、删除
	private Integer type; // 1、菜单；2、按钮
	private String url; // url

	// 树结点状态
	private String state;
	// 子结点
	private List<SystemResources> children = new ArrayList<SystemResources>();

	/**
	 * 获取content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取create_time
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置create_time
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取pid
	 */
	public Integer getPid() {
		return this.pid;
	}

	/**
	 * 设置pid
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * 获取资源图标
	 */
	public String getResIcon() {
		return this.resIcon;
	}

	/**
	 * 设置资源图标
	 */
	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}

	/**
	 * 获取资源图标id
	 */
	public String getResId() {
		return this.resId;
	}

	/**
	 * 设置资源图标id
	 */
	public void setResId(String resId) {
		this.resId = resId;
	}

	/**
	 * 获取应用范围
	 */
	public Integer getScope() {
		return this.scope;
	}

	/**
	 * 设置应用范围
	 */
	public void setScope(Integer scope) {
		this.scope = scope;
	}

	/**
	 * 获取1、未删除2、删除
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置1、未删除2、删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取1、菜单；2、按钮
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 设置1、菜单；2、按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 设置url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		return this.id.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemResources other = (SystemResources) obj;
		if (this.id.intValue() == other.id.intValue())
			return true;
		return false;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<SystemResources> getChildren() {
		return this.children;
	}

	public void setChildren(List<SystemResources> children) {
		this.children = children;
	}

}