/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.example.socnet.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipselabs.example.socnet.Person;
import org.eclipselabs.example.socnet.SocialNetwork;
import org.eclipselabs.example.socnet.SocnetFactory;
import org.eclipselabs.example.socnet.SocnetPackage;
import org.eclipselabs.example.socnet.StatusUpdate;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SocnetPackageImpl extends EPackageImpl implements SocnetPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass socialNetworkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statusUpdateEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipselabs.example.socnet.SocnetPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SocnetPackageImpl() {
		super(eNS_URI, SocnetFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SocnetPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SocnetPackage init() {
		if (isInited) return (SocnetPackage)EPackage.Registry.INSTANCE.getEPackage(SocnetPackage.eNS_URI);

		// Obtain or create and register package
		SocnetPackageImpl theSocnetPackage = (SocnetPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SocnetPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SocnetPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theSocnetPackage.createPackageContents();

		// Initialize created meta-data
		theSocnetPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSocnetPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SocnetPackage.eNS_URI, theSocnetPackage);
		return theSocnetPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSocialNetwork() {
		return socialNetworkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSocialNetwork_Persons() {
		return (EReference)socialNetworkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPerson() {
		return personEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPerson_Name() {
		return (EAttribute)personEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerson_Friends() {
		return (EReference)personEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerson_Status() {
		return (EReference)personEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatusUpdate() {
		return statusUpdateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatusUpdate_Text() {
		return (EAttribute)statusUpdateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStatusUpdate_Date() {
		return (EAttribute)statusUpdateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStatusUpdate_Person() {
		return (EReference)statusUpdateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SocnetFactory getSocnetFactory() {
		return (SocnetFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		socialNetworkEClass = createEClass(SOCIAL_NETWORK);
		createEReference(socialNetworkEClass, SOCIAL_NETWORK__PERSONS);

		personEClass = createEClass(PERSON);
		createEAttribute(personEClass, PERSON__NAME);
		createEReference(personEClass, PERSON__FRIENDS);
		createEReference(personEClass, PERSON__STATUS);

		statusUpdateEClass = createEClass(STATUS_UPDATE);
		createEAttribute(statusUpdateEClass, STATUS_UPDATE__TEXT);
		createEAttribute(statusUpdateEClass, STATUS_UPDATE__DATE);
		createEReference(statusUpdateEClass, STATUS_UPDATE__PERSON);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(socialNetworkEClass, SocialNetwork.class, "SocialNetwork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSocialNetwork_Persons(), this.getPerson(), null, "persons", null, 0, -1, SocialNetwork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(personEClass, Person.class, "Person", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPerson_Name(), ecorePackage.getEString(), "name", null, 1, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerson_Friends(), this.getPerson(), null, "friends", null, 0, -1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerson_Status(), this.getStatusUpdate(), this.getStatusUpdate_Person(), "status", null, 0, -1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(personEClass, null, "removeFriend", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPerson(), "p", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(personEClass, this.getPerson(), "getFriendsOfFriends", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(personEClass, this.getPerson(), "getShortestPathTo", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPerson(), "p", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEInt(), "maxDepth", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(statusUpdateEClass, StatusUpdate.class, "StatusUpdate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStatusUpdate_Text(), ecorePackage.getEString(), "text", null, 0, 1, StatusUpdate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatusUpdate_Date(), ecorePackage.getEDate(), "date", null, 0, 1, StatusUpdate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStatusUpdate_Person(), this.getPerson(), this.getPerson_Status(), "person", null, 0, 1, StatusUpdate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SocnetPackageImpl
