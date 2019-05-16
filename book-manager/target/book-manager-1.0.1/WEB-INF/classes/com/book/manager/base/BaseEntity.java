package com.book.manager.base;


import com.book.util.StringUtil;

/**
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: BaseEntity
 * @author Johnny_L_Q
 */
public class BaseEntity {

	private Integer id;
	
	/**
	 * 页码
	 */
	private int page;
	
	/**
	 * 页面显示条数
	 */
	private int pageSize;

	/**
	 * 记录总条数
	 */
	private int totalRows;

	/**
	 * 记录总页数
	 */
	private int totalPage;
	
	/**
	 * 排序列名
	 */
	private String sortName;
	
	/**
	 * 排序规则(desc or asc)
	 */
	private String sortOrder;

	/**
	 * TODO(这里用一句话描述这个方法的作用).
	 * @return ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * TODO(这里用一句话描述这个方法的作用).
	 * @param id 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * TODO(页码).
	 * @return 页码
	 */
    public int getPage() {
        if(0 == page){
            page = 1;
        }
        return page;
    }

    /**
     * 
     * TODO(设置页码).
     * @param page 页码
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 
     * TODO(获取页面显示条数，默认是30条).
     * @return 页面显示条数
     */
    public int getPageSize() {
        if (pageSize == 0) {
            pageSize = 20;
        }
        return pageSize;
    }

    /**
     * 
     * TODO(这里用一句话描述这个方法的作用).
     * @param pageSize 
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 
     * TODO(这里用一句话描述这个方法的作用).
     * @return
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * 
     * TODO(这里用一句话描述这个方法的作用).
     * @param sortName
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * 
     * TODO(获取排序规则，如果为空就赋默认值desc).
     * @return
     */
    public String getSortOrder() {
        if (StringUtil.isEmpty(sortOrder)) {
            sortOrder = "desc ";
        }
        return sortOrder;
    }

    /**
     * 
     * TODO(这里用一句话描述这个方法的作用).
     * @param sortOrder
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
