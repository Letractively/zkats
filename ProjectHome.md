# Moved! #

our source code repository has moved to github https://github.com/zkoss/zats

But you still can download example here.

# ZK Application Test Suite #

# Background #
In agile software development, developers modify their codes frequently for requirement change or refactoring, they therefore also perform unit tests frequently to ensure the software quality. In ZK-based applications, it is hard to execute an unit test on the composer which is tightly-coupled to ZUL because it is instantiated when a ZUL is requested by a browser. The same problem arises if you want to verify a ZUL's zkbind expression with `ViewModel`. Hence **TDD** (Test-Driven Development) cannot proceed under this situation.

In some cases, agile developers may deploy their web applications to a server and test it within a browser. However, writing an automation test to control a browser is an issue, and testing for different browsers is also a trouble. Not to mention that running an unit test in an application server is time-consuming and can be an agile developer's darkest moment.

**ZATS (ZK Application Test Suite) Mimic** is going to solve this problem.


# No Server Test #

ZATS Mimic enables testers to test their composer **without an application server** and of course **without a browser**, either. Through this library, testers can mimic user interactions to applications such as clicking or typing to verify composer's (controller layer) data and logic. All they have to do is to **write a regular unit test case** and use Mimic's utility class to interact components on ZUL. Then, run the test case.

No deploying to server, no rendering on browser, the unit test case can be executed in a very short period of time - this is very helpful for frequent unit testing during a agile development process.

The concept is as follows:

[![http://books.zkoss.org/images/4/43/Smalltalk-MimicLibraryConcept.png](http://books.zkoss.org/images/4/43/Smalltalk-MimicLibraryConcept.png)]

Testers write test cases to simulate user action such as clicking or typing with operation agents. The operation agent communicates with server emulator and triggers the composer's event handlers to change the component's status. Testers can check component's properties from component agent to verify the result of user action. It might be a ''label'' changing its value or a ''listbox'' increases by one item. **All behaviors that reflect on the component's properties can be verified.**

## Limitation ##

As this library focuses on testing the composer's logic on the server side, there are some limitations you should know:

  * **Functions that depend on the application server cannot work.**
> > Test cases are run in simulated environment; all functions that require an application server do not work (e.g. JNDI, or JTA). If user's AUT (Application Under Test) project adopts such container-provided services, they need extra work to make it work normally out of a container, e.g. use Test Double like a fake object.

  * **Cannot test browser’s behavior.**
> > In a ZK-based application, some behaviors are handled by a browser (JavaScript), e.g. popup menu or message dialog created at client side. As server side is not aware of these behaviors, we cannot verify it.

  * **Cannot test visual effects.**
> > It cannot verify any behaviors that doesn't reflect upon component's properties such as animations, or a component's visual effect.

## Setup ##

### Maven Project ###

It will be available in the near future!


### Manually ###

Download the release zip file, add all jar under '''dist/lib''' and '''dist/lib/ext''' into your project's classpath.

Remember also add jar of your preferred unit test framework, e.g. JUnit.
