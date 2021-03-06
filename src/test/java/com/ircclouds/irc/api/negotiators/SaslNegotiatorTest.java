/*
 * Copyright 2015 danny.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ircclouds.irc.api.negotiators;

import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.commands.CapCmd;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 * @author danny
 */
public class SaslNegotiatorTest {

	public SaslNegotiatorTest() {
	}

	@Before
	public void setUp() {
	}

	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	public void testConstructNullUser() {
		new SaslNegotiator(null, "password", "role");
	}

	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	public void testConstructNullPassword() {
		new SaslNegotiator("user", null, "role");
	}

	@Test
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	public void testConstructNullRole() {
		new SaslNegotiator("user", "password", null);
	}

	@Test
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	public void testConstructWithAllParams() {
		new SaslNegotiator("User", "pass", "role");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInitiateNull() {
		SaslNegotiator neg = new SaslNegotiator("user", "pass", "role");
		neg.initiate(null);
	}

	@Test
	public void testInitiateValid() {
		SaslNegotiator neg = new SaslNegotiator("user", "pass", "role");
		IRCApi irc = mock(IRCApi.class);
		CapCmd cmd = neg.initiate(irc);
		assertNotNull(cmd);
		assertEquals("CAP REQ :sasl", cmd.asString().trim());
	}
}
