/*******************************************************************************
 * Copyright (c) 2011 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Guillaume Hillairet - initial API and implementation
 *******************************************************************************/
package org.eclipselabs.blueprints.emf.util;

import org.eclipse.emf.ecore.EDataType;

public class NativeTypes {

	public static boolean isNativeType(EDataType eDataType) {
		final String instanceClassName = eDataType.getInstanceClassName();
		return
				instanceClassName == "java.lang.String"  ||
				instanceClassName == "int"               ||
				instanceClassName == "boolean"           ||
				instanceClassName == "float"             ||
				instanceClassName == "long"              ||
				instanceClassName == "double"            ||
				instanceClassName == "java.util.Date"    ||
				instanceClassName == "short"             ||
				instanceClassName == "byte[]"            ||
				instanceClassName == "byte"              ||
				instanceClassName == "java.lang.Integer" ||
				instanceClassName == "java.lang.Boolean" ||
				instanceClassName == "java.lang.Long"    ||
				instanceClassName == "java.lang.Float"   ||
				instanceClassName == "java.lang.Double"  ||
				instanceClassName == "java.lang.Short"   ||
				instanceClassName == "java.lang.Byte";
	}

}
