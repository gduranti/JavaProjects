package br.unisinos.unitunes.infra;

public interface Controller<T extends Model> {

	public abstract void init();

	public abstract T getModel();

	public abstract void setModel(T model);

	public void setFacade(Facade<T> facade);

	public <F extends Facade<T>> F getFacade();

	public abstract String getViewTitle();

	public abstract String close();

}
