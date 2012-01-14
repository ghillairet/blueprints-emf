/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.example.socnet;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipselabs.example.socnet.SocnetPackage
 * @generated
 */
public interface SocnetFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SocnetFactory eINSTANCE = org.eclipselabs.example.socnet.impl.SocnetFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Social Network</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Social Network</em>'.
	 * @generated
	 */
	SocialNetwork createSocialNetwork();

	/**
	 * Returns a new object of class '<em>Person</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Person</em>'.
	 * @generated
	 */
	Person createPerson();

	/**
	 * Returns a new object of class '<em>Status Update</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Status Update</em>'.
	 * @generated
	 */
	StatusUpdate createStatusUpdate();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SocnetPackage getSocnetPackage();

} //SocnetFactory
