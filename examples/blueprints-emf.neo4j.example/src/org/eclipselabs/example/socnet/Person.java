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
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.example.socnet.Person#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipselabs.example.socnet.Person#getFriends <em>Friends</em>}</li>
 *   <li>{@link org.eclipselabs.example.socnet.Person#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.example.socnet.SocnetPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipselabs.example.socnet.SocnetPackage#getPerson_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipselabs.example.socnet.Person#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Friends</b></em>' reference list.
	 * The list contents are of type {@link org.eclipselabs.example.socnet.Person}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Friends</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Friends</em>' reference list.
	 * @see org.eclipselabs.example.socnet.SocnetPackage#getPerson_Friends()
	 * @model
	 * @generated
	 */
	EList<Person> getFriends();

	/**
	 * Returns the value of the '<em><b>Status</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.example.socnet.StatusUpdate}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.example.socnet.StatusUpdate#getPerson <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' containment reference list.
	 * @see org.eclipselabs.example.socnet.SocnetPackage#getPerson_Status()
	 * @see org.eclipselabs.example.socnet.StatusUpdate#getPerson
	 * @model opposite="person" containment="true"
	 * @generated
	 */
	EList<StatusUpdate> getStatus();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model pRequired="true"
	 * @generated
	 */
	void removeFriend(Person p);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Person> getFriendsOfFriends();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<Person> getShortestPathTo(Person p, int maxDepth);

} // Person
