/**
 * Copyright 2013  Simon Arlott
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.lp0.slf4j.android;

import static eu.lp0.slf4j.android.MockUtil.createTag;
import static eu.lp0.slf4j.android.MockUtil.mockConfigCompact;
import static eu.lp0.slf4j.android.MockUtil.mockLogLevelRestricted;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Marker;

import android.util.Log;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = CompactLoggerTest.class, fullyQualifiedNames = { "android.util.Log", "eu.lp0.slf4j.android.LoggerFactory" })
public class CompactLoggerTest {
	@Mock
	private Throwable throwable;

	@Mock
	private Marker marker;

	@SuppressWarnings("unchecked")
	@Before
	public void mockLog() {
		mockStatic(Log.class);

		// Not used
		Mockito.when(Log.getStackTraceString(any(Throwable.class))).thenThrow(AssertionError.class);
		Mockito.when(Log.println(anyInt(), anyString(), anyString())).thenThrow(AssertionError.class);
	}

	/* Name, Levels */

	@Test
	public void testName() {
		Assert.assertEquals("Logger Name!", new LogAdapter("Logger Name!", mockConfigCompact()).getName());
	}

	@Test
	public void testLevel_Native_SUPPRESS() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact());

		Assert.assertFalse(log.isErrorEnabled());
		Assert.assertFalse(log.isWarnEnabled());
		Assert.assertFalse(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertFalse(log.isErrorEnabled(marker));
		Assert.assertFalse(log.isWarnEnabled(marker));
		Assert.assertFalse(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Native_ERROR() {
		mockLogLevelRestricted(LogLevel.ERROR);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact());

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertFalse(log.isWarnEnabled());
		Assert.assertFalse(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertFalse(log.isWarnEnabled(marker));
		Assert.assertFalse(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Native_WARN() {
		mockLogLevelRestricted(LogLevel.WARN);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact());

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertFalse(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertFalse(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Native_INFO() {
		mockLogLevelRestricted(LogLevel.INFO);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact());

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertTrue(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertTrue(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Native_DEBUG() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact());

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertTrue(log.isInfoEnabled());
		Assert.assertTrue(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertTrue(log.isInfoEnabled(marker));
		Assert.assertTrue(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Native_VERBOSE() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact());

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertTrue(log.isInfoEnabled());
		Assert.assertTrue(log.isDebugEnabled());
		Assert.assertTrue(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertTrue(log.isInfoEnabled(marker));
		Assert.assertTrue(log.isDebugEnabled(marker));
		Assert.assertTrue(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Override_SUPPRESS() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact(LogLevel.SUPPRESS));

		Assert.assertFalse(log.isErrorEnabled());
		Assert.assertFalse(log.isWarnEnabled());
		Assert.assertFalse(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertFalse(log.isErrorEnabled(marker));
		Assert.assertFalse(log.isWarnEnabled(marker));
		Assert.assertFalse(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Override_ERROR() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact(LogLevel.ERROR));

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertFalse(log.isWarnEnabled());
		Assert.assertFalse(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertFalse(log.isWarnEnabled(marker));
		Assert.assertFalse(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Override_WARN() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact(LogLevel.WARN));

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertFalse(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertFalse(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Override_INFO() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact(LogLevel.INFO));

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertTrue(log.isInfoEnabled());
		Assert.assertFalse(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertTrue(log.isInfoEnabled(marker));
		Assert.assertFalse(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Override_DEBUG() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact(LogLevel.DEBUG));

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertTrue(log.isInfoEnabled());
		Assert.assertTrue(log.isDebugEnabled());
		Assert.assertFalse(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertTrue(log.isInfoEnabled(marker));
		Assert.assertTrue(log.isDebugEnabled(marker));
		Assert.assertFalse(log.isTraceEnabled(marker));
	}

	@Test
	public void testLevel_Override_VERBOSE() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		LogAdapter log = new LogAdapter("logger.name.here", mockConfigCompact(LogLevel.VERBOSE));

		Assert.assertTrue(log.isErrorEnabled());
		Assert.assertTrue(log.isWarnEnabled());
		Assert.assertTrue(log.isInfoEnabled());
		Assert.assertTrue(log.isDebugEnabled());
		Assert.assertTrue(log.isTraceEnabled());

		Assert.assertTrue(log.isErrorEnabled(marker));
		Assert.assertTrue(log.isWarnEnabled(marker));
		Assert.assertTrue(log.isInfoEnabled(marker));
		Assert.assertTrue(log.isDebugEnabled(marker));
		Assert.assertTrue(log.isTraceEnabled(marker));
	}

	/* Error Enabled */

	@Test
	public void testERROR_errorEnabled() {
		mockLogLevelRestricted(LogLevel.ERROR);
		Assert.assertTrue(new LogAdapter("logger.name.here", mockConfigCompact()).isErrorEnabled());
	}

	@Test
	public void testERROR_error_Msg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 1");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testERROR_error_MsgArg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 2 {}", "arg");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testERROR_error_Msg2Args() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 3 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testERROR_error_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testERROR_error_MsgExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 5", throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testERROR_error_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 6", (Throwable)null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testERROR_error_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 7", (Object)throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testERROR_error_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 8", (Object)null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testERROR_error_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 9 {}", "arg1", throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testERROR_error_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 10 {}", "arg1", null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testERROR_error_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testERROR_error_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testERROR_error_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 13");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testERROR_error_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 14 {}", "arg");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testERROR_error_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testERROR_error_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testERROR_error_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 17", throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testERROR_error_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 18", (Throwable)null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testERROR_error_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 19", (Object)throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testERROR_error_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 20", (Object)null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testERROR_error_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testERROR_error_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 22 {}", "arg1", null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testERROR_error_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testERROR_error_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.e(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Error Disabled */

	@Test
	public void testSUPPRESS_errorEnabled() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		Assert.assertFalse(new LogAdapter("logger.name.here", mockConfigCompact()).isErrorEnabled());
	}

	@Test
	public void testSUPPRESS_error_Msg() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 1");

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testSUPPRESS_error_MsgArg() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 2 {}", "arg");

		verifyStatic(never());
		Log.e(eq(createTag(0)), anyString());
	}

	@Test
	public void testSUPPRESS_error_Msg2Args() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 3 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testSUPPRESS_error_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testSUPPRESS_error_MsgExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 5", throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testSUPPRESS_error_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 6", (Throwable)null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testSUPPRESS_error_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 7", (Object)throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testSUPPRESS_error_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 8", (Object)null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testSUPPRESS_error_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 9 {}", "arg1", throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testSUPPRESS_error_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 10 {}", "arg1", null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testSUPPRESS_error_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testSUPPRESS_error_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testSUPPRESS_error_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 13");

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testSUPPRESS_error_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 14 {}", "arg");

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testSUPPRESS_error_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testSUPPRESS_error_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testSUPPRESS_error_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 17", throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testSUPPRESS_error_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 18", (Throwable)null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testSUPPRESS_error_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 19", (Object)throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testSUPPRESS_error_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 20", (Object)null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testSUPPRESS_error_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testSUPPRESS_error_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 22 {}", "arg1", null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testSUPPRESS_error_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testSUPPRESS_error_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.SUPPRESS);
		new LogAdapter("logger.name.here", mockConfigCompact()).error(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.e(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Warn Enabled */

	@Test
	public void testWARN_warnEnabled() {
		mockLogLevelRestricted(LogLevel.WARN);
		Assert.assertTrue(new LogAdapter("logger.name.here", mockConfigCompact()).isWarnEnabled());
	}

	@Test
	public void testWARN_warn_Msg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 1");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testWARN_warn_MsgArg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 2 {}", "arg");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testWARN_warn_Msg2Args() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 3 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testWARN_warn_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testWARN_warn_MsgExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 5", throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testWARN_warn_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 6", (Throwable)null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testWARN_warn_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 7", (Object)throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testWARN_warn_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 8", (Object)null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testWARN_warn_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 9 {}", "arg1", throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testWARN_warn_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 10 {}", "arg1", null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testWARN_warn_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testWARN_warn_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testWARN_warn_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 13");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testWARN_warn_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 14 {}", "arg");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testWARN_warn_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testWARN_warn_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testWARN_warn_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 17", throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testWARN_warn_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 18", (Throwable)null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testWARN_warn_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 19", (Object)throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testWARN_warn_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 20", (Object)null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testWARN_warn_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testWARN_warn_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 22 {}", "arg1", null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testWARN_warn_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testWARN_warn_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.w(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Warn Disabled */

	@Test
	public void testERROR_warnEnabled() {
		mockLogLevelRestricted(LogLevel.ERROR);
		Assert.assertFalse(new LogAdapter("logger.name.here", mockConfigCompact()).isWarnEnabled());
	}

	@Test
	public void testERROR_warn_Msg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 1");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testERROR_warn_MsgArg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 2 {}", "arg");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testERROR_warn_Msg2Args() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 3 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testERROR_warn_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testERROR_warn_MsgExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 5", throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testERROR_warn_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 6", (Throwable)null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testERROR_warn_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 7", (Object)throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testERROR_warn_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 8", (Object)null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testERROR_warn_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 9 {}", "arg1", throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testERROR_warn_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 10 {}", "arg1", null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testERROR_warn_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testERROR_warn_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testERROR_warn_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 13");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testERROR_warn_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 14 {}", "arg");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testERROR_warn_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testERROR_warn_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testERROR_warn_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 17", throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testERROR_warn_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 18", (Throwable)null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testERROR_warn_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 19", (Object)throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testERROR_warn_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 20", (Object)null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testERROR_warn_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testERROR_warn_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 22 {}", "arg1", null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testERROR_warn_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testERROR_warn_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.ERROR);
		new LogAdapter("logger.name.here", mockConfigCompact()).warn(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.w(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Info Enabled */

	@Test
	public void testINFO_warnEnabled() {
		mockLogLevelRestricted(LogLevel.INFO);
		Assert.assertTrue(new LogAdapter("logger.name.here", mockConfigCompact()).isInfoEnabled());
	}

	@Test
	public void testINFO_info_Msg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 1");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testINFO_info_MsgArg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 2 {}", "arg");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testINFO_info_Msg2Args() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 3 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testINFO_info_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testINFO_info_MsgExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 5", throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testINFO_info_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 6", (Throwable)null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testINFO_info_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 7", (Object)throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testINFO_info_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 8", (Object)null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testINFO_info_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 9 {}", "arg1", throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testINFO_info_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 10 {}", "arg1", null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testINFO_info_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testINFO_info_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testINFO_info_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 13");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testINFO_info_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 14 {}", "arg");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testINFO_info_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testINFO_info_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testINFO_info_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 17", throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testINFO_info_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 18", (Throwable)null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testINFO_info_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 19", (Object)throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testINFO_info_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 20", (Object)null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testINFO_info_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testINFO_info_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 22 {}", "arg1", null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testINFO_info_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testINFO_info_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.i(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Info Disabled */

	@Test
	public void testWARN_infoEnabled() {
		mockLogLevelRestricted(LogLevel.WARN);
		Assert.assertFalse(new LogAdapter("logger.name.here", mockConfigCompact()).isInfoEnabled());
	}

	@Test
	public void testWARN_info_Msg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 1");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testWARN_info_MsgArg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 2 {}", "arg");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testWARN_info_Msg2Args() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 3 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testWARN_info_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testWARN_info_MsgExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 5", throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testWARN_info_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 6", (Throwable)null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testWARN_info_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 7", (Object)throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testWARN_info_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 8", (Object)null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testWARN_info_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 9 {}", "arg1", throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testWARN_info_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 10 {}", "arg1", null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testWARN_info_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testWARN_info_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testWARN_info_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 13");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testWARN_info_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 14 {}", "arg");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testWARN_info_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testWARN_info_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testWARN_info_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 17", throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testWARN_info_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 18", (Throwable)null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testWARN_info_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 19", (Object)throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testWARN_info_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 20", (Object)null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testWARN_info_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testWARN_info_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 22 {}", "arg1", null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testWARN_info_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testWARN_info_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.WARN);
		new LogAdapter("logger.name.here", mockConfigCompact()).info(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.i(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Debug Enabled */

	@Test
	public void testDEBUG_warnEnabled() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		Assert.assertTrue(new LogAdapter("logger.name.here", mockConfigCompact()).isDebugEnabled());
	}

	@Test
	public void testDEBUG_debug_Msg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 1");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testDEBUG_debug_MsgArg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 2 {}", "arg");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testDEBUG_debug_Msg2Args() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 3 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testDEBUG_debug_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testDEBUG_debug_MsgExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 5", throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testDEBUG_debug_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 6", (Throwable)null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testDEBUG_debug_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 7", (Object)throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testDEBUG_debug_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 8", (Object)null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testDEBUG_debug_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 9 {}", "arg1", throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testDEBUG_debug_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 10 {}", "arg1", null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testDEBUG_debug_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testDEBUG_debug_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testDEBUG_debug_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 13");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testDEBUG_debug_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 14 {}", "arg");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testDEBUG_debug_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testDEBUG_debug_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testDEBUG_debug_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 17", throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testDEBUG_debug_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 18", (Throwable)null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testDEBUG_debug_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 19", (Object)throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testDEBUG_debug_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 20", (Object)null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testDEBUG_debug_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testDEBUG_debug_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 22 {}", "arg1", null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testDEBUG_debug_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testDEBUG_debug_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.d(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Debug Disabled */

	@Test
	public void testINFO_debugEnabled() {
		mockLogLevelRestricted(LogLevel.INFO);
		Assert.assertFalse(new LogAdapter("logger.name.here", mockConfigCompact()).isDebugEnabled());
	}

	@Test
	public void testINFO_debug_Msg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 1");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testINFO_debug_MsgArg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 2 {}", "arg");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testINFO_debug_Msg2Args() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 3 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testINFO_debug_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testINFO_debug_MsgExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 5", throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testINFO_debug_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 6", (Throwable)null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testINFO_debug_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 7", (Object)throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testINFO_debug_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 8", (Object)null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testINFO_debug_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 9 {}", "arg1", throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testINFO_debug_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 10 {}", "arg1", null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testINFO_debug_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testINFO_debug_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testINFO_debug_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 13");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testINFO_debug_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 14 {}", "arg");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testINFO_debug_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testINFO_debug_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testINFO_debug_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 17", throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testINFO_debug_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 18", (Throwable)null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testINFO_debug_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 19", (Object)throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testINFO_debug_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 20", (Object)null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testINFO_debug_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testINFO_debug_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 22 {}", "arg1", null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testINFO_debug_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testINFO_debug_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.INFO);
		new LogAdapter("logger.name.here", mockConfigCompact()).debug(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.d(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Trace Enabled */

	@Test
	public void testTRACE_warnEnabled() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		Assert.assertTrue(new LogAdapter("logger.name.here", mockConfigCompact()).isTraceEnabled());
	}

	@Test
	public void testTRACE_trace_Msg() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 1");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testTRACE_trace_MsgArg() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 2 {}", "arg");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testTRACE_trace_Msg2Args() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 3 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testTRACE_trace_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testTRACE_trace_MsgExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 5", throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testTRACE_trace_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 6", (Throwable)null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testTRACE_trace_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 7", (Object)throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testTRACE_trace_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 8", (Object)null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testTRACE_trace_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 9 {}", "arg1", throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testTRACE_trace_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 10 {}", "arg1", null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testTRACE_trace_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testTRACE_trace_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testTRACE_trace_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 13");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testTRACE_trace_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 14 {}", "arg");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testTRACE_trace_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testTRACE_trace_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testTRACE_trace_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 17", throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testTRACE_trace_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 18", (Throwable)null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testTRACE_trace_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 19", (Object)throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testTRACE_trace_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 20", (Object)null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testTRACE_trace_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testTRACE_trace_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 22 {}", "arg1", null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testTRACE_trace_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testTRACE_trace_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.VERBOSE);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic();
		Log.v(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}

	/* Trace Disabled */

	@Test
	public void testDEBUG_traceEnabled() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		Assert.assertFalse(new LogAdapter("logger.name.here", mockConfigCompact()).isTraceEnabled());
	}

	@Test
	public void testDEBUG_trace_Msg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 1");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 1");
	}

	@Test
	public void testDEBUG_trace_MsgArg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 2 {}", "arg");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 2 arg");
	}

	@Test
	public void testDEBUG_trace_Msg2Args() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 3 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 3 arg1 arg2");
	}

	@Test
	public void testDEBUG_trace_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 4 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 4 arg1 arg2 arg3");
	}

	@Test
	public void testDEBUG_trace_MsgExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 5", throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 5", throwable);
	}

	@Test
	public void testDEBUG_trace_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 6", (Throwable)null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 6");
	}

	@Test
	public void testDEBUG_trace_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 7", (Object)throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 7", throwable);
	}

	@Test
	public void testDEBUG_trace_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 8", (Object)null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 8");
	}

	@Test
	public void testDEBUG_trace_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 9 {}", "arg1", throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 9 arg1", throwable);
	}

	@Test
	public void testDEBUG_trace_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 10 {}", "arg1", null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 10 arg1");
	}

	@Test
	public void testDEBUG_trace_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 11 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 11 arg1 arg2", throwable);
	}

	@Test
	public void testDEBUG_trace_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace("Message 12 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 12 arg1 arg2");
	}

	@Test
	public void testDEBUG_trace_Marker_Msg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 13");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 13");
	}

	@Test
	public void testDEBUG_trace_Marker_MsgArg() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 14 {}", "arg");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 14 arg");
	}

	@Test
	public void testDEBUG_trace_Marker_Msg2Args() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 15 {} {}", "arg1", "arg2");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 15 arg1 arg2");
	}

	@Test
	public void testDEBUG_trace_Marker_MsgManyArgs() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 16 {} {} {}", "arg1", "arg2", "arg3");

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 16 arg1 arg2 arg3");
	}

	@Test
	public void testDEBUG_trace_Marker_MsgExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 17", throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 17", throwable);
	}

	@Test
	public void testDEBUG_trace_Marker_MsgNullExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 18", (Throwable)null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 18");
	}

	@Test
	public void testDEBUG_trace_Marker_MsgObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 19", (Object)throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 19", throwable);
	}

	@Test
	public void testDEBUG_trace_Marker_MsgObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 20", (Object)null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 20");
	}

	@Test
	public void testDEBUG_trace_Marker_Msg2ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 21 {}", "arg1", throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 21 arg1", throwable);
	}

	@Test
	public void testDEBUG_trace_Marker_Msg2ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 22 {}", "arg1", null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 22 arg1");
	}

	@Test
	public void testDEBUG_trace_Marker_Msg3ObjExc() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 23 {} {}", "arg1", "arg2", throwable);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 23 arg1 arg2", throwable);
	}

	@Test
	public void testDEBUG_trace_Marker_Msg3ObjNull() {
		mockLogLevelRestricted(LogLevel.DEBUG);
		new LogAdapter("logger.name.here", mockConfigCompact()).trace(marker, "Message 24 {} {}", "arg1", "arg2", null);

		verifyStatic(never());
		Log.v(createTag(0), "l.n.here: Message 24 arg1 arg2");
	}
}
