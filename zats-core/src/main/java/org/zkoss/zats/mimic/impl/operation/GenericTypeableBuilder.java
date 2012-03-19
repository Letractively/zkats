package org.zkoss.zats.mimic.impl.operation;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zats.mimic.node.ComponentNode;
import org.zkoss.zats.mimic.operation.Typeable;
import org.zkoss.zk.ui.event.Events;

public class GenericTypeableBuilder implements OperationBuilder<Typeable>
{
	public Typeable getOperation(final ComponentNode target)
	{
		return new Typeable() {
			
			public Typeable type(String value) {
		        Map<String, Object> data = new HashMap<String, Object>();
		        data.put("value", value);
		        data.put("bySelectBack", false);
		        data.put("start", 0);
		        target.getConversation().postUpdate(target, Events.ON_CHANGING, data);
		        target.getConversation().postUpdate(target, Events.ON_CHANGE, data);
		        return this;
			}
		};
	}
}