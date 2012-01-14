/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.example.socnet;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipselabs.example.socnet.SocnetFactory
 * @model kind="package"
 * @generated
 */
public interface SocnetPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "socnet";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipselabs.org/socnet";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "socnet";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SocnetPackage eINSTANCE = org.eclipselabs.example.socnet.impl.SocnetPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipselabs.example.socnet.impl.SocialNetworkImpl <em>Social Network</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.example.socnet.impl.SocialNetworkImpl
	 * @see org.eclipselabs.example.socnet.impl.SocnetPackageImpl#getSocialNetwork()
	 * @generated
	 */
	int SOCIAL_NETWORK = 0;

	/**
	 * The feature id for the '<em><b>Persons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOCIAL_NETWORK__PERSONS = 0;

	/**
	 * The number of structural features of the '<em>Social Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOCIAL_NETWORK_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipselabs.example.socnet.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.example.socnet.impl.PersonImpl
	 * @see org.eclipselabs.example.socnet.impl.SocnetPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__NAME = 0;

	/**
	 * The feature id for the '<em><b>Friends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__FRIENDS = 1;

	/**
	 * The feature id for the '<em><b>Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__STATUS = 2;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipselabs.example.socnet.impl.StatusUpdateImpl <em>Status Update</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.example.socnet.impl.StatusUpdateImpl
	 * @see org.eclipselabs.example.socnet.impl.SocnetPackageImpl#getStatusUpdate()
	 * @generated
	 */
	int STATUS_UPDATE = 2;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_UPDATE__TEXT = 0;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_UPDATE__DATE = 1;

	/**
	 * The feature id for the '<em><b>Person</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_UPDATE__PERSON = 2;

	/**
	 * The number of structural features of the '<em>Status Update</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_UPDATE_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link org.eclipselabs.example.socnet.SocialNetwork <em>Social Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Social Network</em>'.
	 * @see org.eclipselabs.example.socnet.SocialNetwork
	 * @generated
	 */
	EClass getSocialNetwork();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipselabs.example.socnet.SocialNetwork#getPersons <em>Persons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Persons</em>'.
	 * @see org.eclipselabs.example.socnet.SocialNetwork#getPersons()
	 * @see #getSocialNetwork()
	 * @generated
	 */
	EReference getSocialNetwork_Persons();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.example.socnet.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see org.eclipselabs.example.socnet.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.example.socnet.Person#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipselabs.example.socnet.Person#getName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipselabs.example.socnet.Person#getFriends <em>Friends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Friends</em>'.
	 * @see org.eclipselabs.example.socnet.Person#getFriends()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Friends();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipselabs.example.socnet.Person#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Status</em>'.
	 * @see org.eclipselabs.example.socnet.Person#getStatus()
	 * @see #getPerson()
	 * @generated
	 */
	EReference getPerson_Status();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.example.socnet.StatusUpdate <em>Status Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Status Update</em>'.
	 * @see org.eclipselabs.example.socnet.StatusUpdate
	 * @generated
	 */
	EClass getStatusUpdate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.example.socnet.StatusUpdate#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipselabs.example.socnet.StatusUpdate#getText()
	 * @see #getStatusUpdate()
	 * @generated
	 */
	EAttribute getStatusUpdate_Text();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.example.socnet.StatusUpdate#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see org.eclipselabs.example.socnet.StatusUpdate#getDate()
	 * @see #getStatusUpdate()
	 * @generated
	 */
	EAttribute getStatusUpdate_Date();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipselabs.example.socnet.StatusUpdate#getPerson <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Person</em>'.
	 * @see org.eclipselabs.example.socnet.StatusUpdate#getPerson()
	 * @see #getStatusUpdate()
	 * @generated
	 */
	EReference getStatusUpdate_Person();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SocnetFactory getSocnetFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipselabs.example.socnet.impl.SocialNetworkImpl <em>Social Network</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.example.socnet.impl.SocialNetworkImpl
		 * @see org.eclipselabs.example.socnet.impl.SocnetPackageImpl#getSocialNetwork()
		 * @generated
		 */
		EClass SOCIAL_NETWORK = eINSTANCE.getSocialNetwork();

		/**
		 * The meta object literal for the '<em><b>Persons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOCIAL_NETWORK__PERSONS = eINSTANCE.getSocialNetwork_Persons();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.example.socnet.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.example.socnet.impl.PersonImpl
		 * @see org.eclipselabs.example.socnet.impl.SocnetPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

		/**
		 * The meta object literal for the '<em><b>Friends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__FRIENDS = eINSTANCE.getPerson_Friends();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSON__STATUS = eINSTANCE.getPerson_Status();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.example.socnet.impl.StatusUpdateImpl <em>Status Update</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.example.socnet.impl.StatusUpdateImpl
		 * @see org.eclipselabs.example.socnet.impl.SocnetPackageImpl#getStatusUpdate()
		 * @generated
		 */
		EClass STATUS_UPDATE = eINSTANCE.getStatusUpdate();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_UPDATE__TEXT = eINSTANCE.getStatusUpdate_Text();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATUS_UPDATE__DATE = eINSTANCE.getStatusUpdate_Date();

		/**
		 * The meta object literal for the '<em><b>Person</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATUS_UPDATE__PERSON = eINSTANCE.getStatusUpdate_Person();

	}

} //SocnetPackage
