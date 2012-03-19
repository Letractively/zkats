package org.zkoss.zats.mimic.impl.operation;

import org.zkoss.zats.mimic.node.ComponentNode;
import org.zkoss.zats.mimic.operation.Clickable;

public class GenericClickableBuilder implements OperationBuilder<Clickable>
{
	public Clickable getOperation(final ComponentNode target)
	{
		return new Clickable()
		{
			public Clickable click()
			{
				OperationUtil.doClick(target);
				return this;
			}

			public Clickable doubleClick()
			{
				OperationUtil.doDoubleClick(target);
				return this;
			}

			public Clickable rightClick()
			{
				OperationUtil.doRightClick(target);
				return this;
			}
		};
	}
}
