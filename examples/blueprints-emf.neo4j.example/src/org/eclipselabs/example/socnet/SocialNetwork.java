/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.example.socnet;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Social Network</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.example.socnet.SocialNetwork#getPersons <em>Persons</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.example.socnet.SocnetPackage#getSocialNetwork()
 * @model
 * @generated
 */
public interface SocialNetwork extends EObject {
	/**
	 * Returns the value of the '<em><b>Persons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.example.socnet.Person}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persons</em>' containment reference list.
	 * @see org.eclipselabs.example.socnet.SocnetPackage#getSocialNetwork_Persons()
	 * @model containment="true"
	 * @generated
	 */
	EList<Person> getPersons();

} // SocialNetwork
