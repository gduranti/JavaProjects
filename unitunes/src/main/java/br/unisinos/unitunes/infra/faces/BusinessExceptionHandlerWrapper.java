package br.unisinos.unitunes.infra.faces;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.lang3.exception.ExceptionUtils;

import br.unisinos.unitunes.infra.exception.BusinessException;

public class BusinessExceptionHandlerWrapper extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public BusinessExceptionHandlerWrapper(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable t = ExceptionUtils.getRootCause(context.getException());
			if (t instanceof BusinessException) {
				FacesUtil.addErrorMessage(t.getMessage());
				i.remove();
			}
		}

		getWrapped().handle();
	}
}