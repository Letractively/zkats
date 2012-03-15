package org.zkoss.zats.core.component;

import java.util.List;
import org.zkoss.zk.ui.Desktop;

public interface DesktopNode extends Node
{
	List<PageNode> getPages();

	/**
	 * get the native Desktop.
	 * @return desktop
	 */
	Desktop cast();
}