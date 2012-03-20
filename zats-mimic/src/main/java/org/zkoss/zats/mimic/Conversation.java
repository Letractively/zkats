/* Conversation.java

	Purpose:
		
	Description:
		
	History:
		Mar 20, 2012 Created by pao

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zats.mimic;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.zkoss.zats.mimic.node.ComponentNode;
import org.zkoss.zats.mimic.node.DesktopNode;

public interface Conversation {
	/**
	 * start conversation.
	 * 
	 * @param resourceRoot
	 *            resource root path.
	 */
	void start(String resourceRoot);

	/**
	 * stop conversation.
	 */
	void stop();

	/**
	 * open specify zul page.
	 * 
	 * @param zul
	 *            the path related to the resource root path
	 */
	void open(String zul);

	/**
	 * clean current Desktop and release resources.
	 */
	void clean();

	DesktopNode getDesktop();

	HttpSession getSession();

	/**
	 * post an asynchronous update event.
	 * 
	 * @param target
	 *            the component node which performed this event
	 * @param command
	 *            command
	 * @param data
	 *            data for update
	 */
	void postUpdate(ComponentNode target, String cmd, Map<String, Object> data);
}