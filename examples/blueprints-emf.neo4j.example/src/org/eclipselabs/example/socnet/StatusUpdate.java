/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.example.socnet;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Status Update</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.example.socnet.StatusUpdate#getText <em>Text</em>}</li>
 *   <li>{@link org.eclipselabs.example.socnet.StatusUpdate#getDate <em>Date</em>}</li>
 *   <li>{@link org.eclipselabs.example.socnet.StatusUpdate#getPerson <em>Person</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.example.socnet.SocnetPackage#getStatusUpdate()
 * @model
 * @generated
 */
public interface StatusUpdate extends EObject {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.eclipselabs.example.socnet.SocnetPackage#getStatusUpdate_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.eclipselabs.example.socnet.StatusUpdate#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see org.eclipselabs.example.socnet.SocnetPackage#getStatusUpdate_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link org.eclipselabs.example.socnet.StatusUpdate#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

	/**
	 * Returns the value of the '<em><b>Person</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.example.socnet.Person#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Person</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Person</em>' container reference.
	 * @see #setPerson(Person)
	 * @see org.eclipselabs.example.socnet.SocnetPackage#getStatusUpdate_Person()
	 * @see org.eclipselabs.example.socnet.Person#getStatus
	 * @model opposite="status" transient="false"
	 * @generated
	 */
	Person getPerson();

	/**
	 * Sets the value of the '{@link org.eclipselabs.example.socnet.StatusUpdate#getPerson <em>Person</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Person</em>' container reference.
	 * @see #getPerson()
	 * @generated
	 */
	void setPerson(Person value);

} // StatusUpdate
