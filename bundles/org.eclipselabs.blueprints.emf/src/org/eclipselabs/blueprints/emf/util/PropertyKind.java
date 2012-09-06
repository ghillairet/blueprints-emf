package org.eclipselabs.blueprints.emf.util;

public enum PropertyKind {
	eClass {
		@Override
		public String toString() {
			return "_eClass";
		}
	},
	eContent {
		@Override
		public String toString() {
			return "_eContent";
		}
	},
	eResource {
		@Override
		public String toString() {
			return "_eResource";
		}
	},
	eURI {
		@Override
		public String toString() {
			return "_eURI";
		}
	}
}
