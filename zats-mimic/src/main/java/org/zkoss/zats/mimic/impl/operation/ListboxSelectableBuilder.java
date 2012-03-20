/* ListboxSelectableBuilder.java

	Purpose:
		
	Description:
		
	History:
		Mar 20, 2012 Created by pao

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic.impl.operation;

import org.zkoss.zats.mimic.node.ComponentNode;
import org.zkoss.zats.mimic.operation.Selectable;
import org.zkoss.zul.Listbox;

public class ListboxSelectableBuilder implements OperationBuilder<Selectable> {
	public Selectable getOperation(final ComponentNode target) {
		return new Selectable() {
			public Selectable select(int index) {
				// TODO check type of the target
				PostAgent.doSelect(target, target.as(Listbox.class)
						.getItemAtIndex(index).getUuid());
				return this;
			}
		};
	}
}
