package org.eclipselabs.blueprints.emf.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class GraphUtil {
	
	public static String getEdgeID(EObject source, EObject target, EReference reference) {
		return EcoreUtil.getURI(source) + "_" + reference.getName() + "_" + EcoreUtil.getURI(target);
	}
}
