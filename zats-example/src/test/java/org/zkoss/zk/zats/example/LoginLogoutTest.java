package org.zkoss.zk.zats.example;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zkoss.zats.mimic.Conversations;
import org.zkoss.zats.mimic.Searcher;
import org.zkoss.zats.mimic.node.ComponentNode;
import org.zkoss.zats.mimic.operation.Clickable;
import org.zkoss.zats.mimic.operation.Typeable;
import org.zkoss.zk.zats.example.util.LoginOperation;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginLogoutTest {
	@BeforeClass
	public static void init() {
		Conversations.start("./src/main/webapp"); // user can load by
													// configuration file
	}

	@AfterClass
	public static void end() {
		Conversations.stop();
	}

	@After
	public void after() {
		Conversations.clean();
	}

	@Test
	public void test() {
		Conversations.open("/login.zul");

		ComponentNode account = Searcher.find("#account");
		ComponentNode password = Searcher.find("#password");
		ComponentNode login = Searcher.find("button");
		ComponentNode msg = Searcher.find("div > label");
		
		//login failed
		account.as(Typeable.class).type("hawk");
		password.as(Typeable.class).type("1111");
		login.as(Clickable.class).click();
		assertEquals("Login Failed", msg.cast(Label.class).getValue());
		
		//login success
		password.as(Typeable.class).type("1234");
		login.as(Clickable.class).click();
		HttpSession session = Conversations.getSession();
		assertEquals(account.cast(Textbox.class).getValue(), session.getAttribute("user"));
//		ComponentNode mainWin = Searcher.find("window"); no handle redirect for now
//		assertEquals("Main",mainWin.cast(Window.class).getTitle());

	}
	
	@Test
	public void testLoginOperation() {
		Conversations.open("/login.zul");
		assertEquals(false, LoginOperation.login("hawk","1111"));
		assertEquals(true, LoginOperation.login("hawk","1234"));
			
	}
}