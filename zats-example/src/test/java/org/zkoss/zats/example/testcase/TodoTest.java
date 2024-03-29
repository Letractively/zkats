package org.zkoss.zats.example.testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zkoss.zats.mimic.ComponentAgent;
import org.zkoss.zats.mimic.Conversations;
import org.zkoss.zats.mimic.DesktopAgent;
import org.zkoss.zats.mimic.operation.SelectAgent;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Textbox;

public class TodoTest {
	@BeforeClass
	public static void init() {
		Conversations.start("./src/main/webapp"); 
	}

	@AfterClass
	public static void end() {
		Conversations.stop();
	}

	@After
	public void after() {
		Conversations.closeAll();
	}

	@Test
	public void test() {
		//visit the target page
		DesktopAgent desktop = Conversations.open().connect("/todo.zul");

		//find components
		ComponentAgent itemName = desktop.query("textbox");
		ComponentAgent priority = desktop.query("intbox");
		ComponentAgent date = desktop.query("datebox");

		//add
		//itemName.as(TypeAgent.class).type("one-item");
		itemName.type("one-item");
		priority.type("3");
		date.type("2012-03-16");
		desktop.query("button[label='Add']").click();
		
		//verify each listcell's label
		ComponentAgent listbox = desktop.query("listbox");
		List<ComponentAgent> cells = listbox.queryAll("listitem").get(0).getChildren();
		assertEquals("one-item",cells.get(0).as(Listcell.class).getLabel());
		assertEquals("3",cells.get(1).as(Listcell.class).getLabel());
		assertEquals("2012/03/16",cells.get(2).as(Listcell.class).getLabel());
		
		//update
		desktop.queryAll("listbox > listitem").get(0).as(SelectAgent.class).select();
		//verify selected
		assertEquals("one-item",itemName.as(Textbox.class).getValue());
		assertEquals((Integer)3,priority.as(Intbox.class).getValue());
		assertEquals("2012-03-16",date.as(Datebox.class).getRawText());
		//modify the todo item
		itemName.type("one-item modified");
		priority.type("5");
		desktop.query("button[label='Update']").click();
		//retrieve Listitem again to verify it
		cells = listbox.queryAll("listitem").get(0).getChildren();
		assertEquals("one-item modified",cells.get(0).as(Listcell.class).getLabel());
		assertEquals("5",cells.get(1).as(Listcell.class).getLabel());
		
		//reset
		desktop.queryAll("listbox > listitem").get(0).as(SelectAgent.class).select();
		assertNotNull(itemName.as(Textbox.class).getValue());
		desktop.query("button[label='Reset']").click();
		assertEquals("",itemName.as(Textbox.class).getValue());
		assertEquals((Integer)0,priority.as(Intbox.class).getValue());
		assertNull(date.as(Datebox.class).getValue());

		//delete
		assertEquals(1,listbox.queryAll("listitem").size());
		desktop.queryAll("listbox > listitem").get(0).as(SelectAgent.class).select();
		desktop.query("button[label='Delete']").click();
		assertEquals(0,listbox.queryAll("listitem").size());
		
		//The next line causes IllegalStateException: Components can be accessed only in event listeners
		//find("textbox").as(Textbox.class).setValue("abc"); 
	}
}
