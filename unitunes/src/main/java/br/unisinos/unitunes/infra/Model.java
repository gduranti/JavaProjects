package br.unisinos.unitunes.infra;

import java.io.Serializable;

public interface Model extends Serializable {

	public abstract Long getId();

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

}
