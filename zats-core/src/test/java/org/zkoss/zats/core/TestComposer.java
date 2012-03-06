package org.zkoss.zats.core;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

public class TestComposer extends GenericForwardComposer<Component>
{
	private static final long serialVersionUID = 1L;
	private Label msg;
	private int count;

	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);
		msg.setValue("hello");
	}

	public void onClick$btn()
	{
		msg.setValue(String.valueOf(count++));
	}
}
