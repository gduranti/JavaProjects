package br.unisinos.unitunes.model;

public class MediaFilter extends Media {

	private static final long serialVersionUID = 1L;

	private MyMediaFilter myMediaFilter;
	private MediaCategory categoryFilter;
	private MediaType typeFilter;

	public MyMediaFilter getMyMediaFilter() {
		return myMediaFilter;
	}

	public void setMyMediaFilter(MyMediaFilter myMediaFilter) {
		this.myMediaFilter = myMediaFilter;
	}

	public MediaCategory getCategoryFilter() {
		return categoryFilter;
	}

	public void setCategoryFilter(MediaCategory categoryFilter) {
		this.categoryFilter = categoryFilter;
	}

	public MediaType getTypeFilter() {
		return typeFilter;
	}

	public void setTypeFilter(MediaType typeFilter) {
		this.typeFilter = typeFilter;
	}

}
