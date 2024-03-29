/* ListboxRendererAgentBuilder.java

	Purpose:
		
	Description:
		
	History:
		2012/3/20 Created by dennis

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic.impl.operation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.impl.ConversationCtrl;
import org.zkoss.zats.mimic.impl.au.EventDataManager;
import org.zkoss.zats.mimic.operation.RendererAgent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.event.RenderEvent;
/**
 * 
 * @author dennis
 *
 */
public class ListboxRendererAgentBuilder implements OperationAgentBuilder<RendererAgent> {
	public RendererAgent getOperation(final ComponentAgent target) {
		if(!target.is(Listbox.class)){
			throw new RuntimeException("target "+target+" cannot transfer to Listbox");
		}
		return new RendererAgentImpl(target);
	}
	
	class RendererAgentImpl extends AgentDelegator implements RendererAgent{
		public RendererAgentImpl(ComponentAgent target) {
			super(target);
		}

		public void render(int x, int y) {
			Listbox listbox = target.as(Listbox.class);
			if(x==-1) x = 0;
			if(y==-1) y = listbox.getItemCount()-1;
			ArrayList<String> ids = new ArrayList<String>();
			while(true){
				if(x > y) break;
				Listitem item = listbox.getItemAtIndex(x++);
				if(item!=null && !item.isLoaded()){
					ids.add(item.getUuid());
				}
				
			}
			if(ids.size()==0) return;
			
			String desktopId = target.getDesktop().getId();
			String cmd = Events.ON_RENDER;
			Map<String, Object> data = EventDataManager.build(new RenderEvent(cmd, new HashSet(ids)));
			((ConversationCtrl)target.getConversation()).postUpdate(desktopId, target.getUuid(), cmd, data);
		};
	}
}
