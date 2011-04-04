/*
 * Copyright 2010-2011 Excilys (www.excilys.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.excilys.utils.web.flash;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores the current FlashScope in a ThreadLocal so that it can be accessed
 * without relying on the underliying HTTP storage.
 * 
 * @author <a href="mailto:slandelle@excilys.com">Stephane LANDELLE</a>
 */
// NotThreadSafe
public class FlashScopeHandler {

	// TODO handle InheritableThreadLocal
	private static final ThreadLocal<Map<String, Object>> HOLDER = new ThreadLocal<Map<String, Object>>();

	static Map<String, Object> getFlash() {

		Map<String, Object> flash = HOLDER.get();
		if (flash == null) {
			flash = new HashMap<String, Object>();
			HOLDER.set(flash);
		}

		return flash;
	}

	static void clear() {
		HOLDER.set(null);
	}

	public static void setFlashAttribute(String name, Object value) {
		getFlash().put(name, value);
	}
}