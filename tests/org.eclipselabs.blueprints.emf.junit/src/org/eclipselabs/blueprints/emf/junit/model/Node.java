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
package org.eclipselabs.blueprints.emf.junit.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.Node#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.Node#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.Node#getOneRefNode <em>One Ref Node</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.Node#getManyRefNode <em>Many Ref Node</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.Node#getParentOne <em>Parent One</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.Node#getParentMany <em>Parent Many</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.blueprints.emf.junit.model.ModelPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject {
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
	 * @see org.eclipselabs.blueprints.emf.junit.model.ModelPackage#getNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipselabs.blueprints.emf.junit.model.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.eclipselabs.blueprints.emf.junit.model.ModelPackage#getNode_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getChildren();

	/**
	 * Returns the value of the '<em><b>One Ref Node</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getParentOne <em>Parent One</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>One Ref Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>One Ref Node</em>' reference.
	 * @see #setOneRefNode(Node)
	 * @see org.eclipselabs.blueprints.emf.junit.model.ModelPackage#getNode_OneRefNode()
	 * @see org.eclipselabs.blueprints.emf.junit.model.Node#getParentOne
	 * @model opposite="parentOne"
	 * @generated
	 */
	Node getOneRefNode();

	/**
	 * Sets the value of the '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getOneRefNode <em>One Ref Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>One Ref Node</em>' reference.
	 * @see #getOneRefNode()
	 * @generated
	 */
	void setOneRefNode(Node value);

	/**
	 * Returns the value of the '<em><b>Many Ref Node</b></em>' reference list.
	 * The list contents are of type {@link org.eclipselabs.blueprints.emf.junit.model.Node}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getParentMany <em>Parent Many</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many Ref Node</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Many Ref Node</em>' reference list.
	 * @see org.eclipselabs.blueprints.emf.junit.model.ModelPackage#getNode_ManyRefNode()
	 * @see org.eclipselabs.blueprints.emf.junit.model.Node#getParentMany
	 * @model opposite="parentMany"
	 * @generated
	 */
	EList<Node> getManyRefNode();

	/**
	 * Returns the value of the '<em><b>Parent One</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getOneRefNode <em>One Ref Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent One</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent One</em>' reference.
	 * @see #setParentOne(Node)
	 * @see org.eclipselabs.blueprints.emf.junit.model.ModelPackage#getNode_ParentOne()
	 * @see org.eclipselabs.blueprints.emf.junit.model.Node#getOneRefNode
	 * @model opposite="oneRefNode"
	 * @generated
	 */
	Node getParentOne();

	/**
	 * Sets the value of the '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getParentOne <em>Parent One</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent One</em>' reference.
	 * @see #getParentOne()
	 * @generated
	 */
	void setParentOne(Node value);

	/**
	 * Returns the value of the '<em><b>Parent Many</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getManyRefNode <em>Many Ref Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Many</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Many</em>' reference.
	 * @see #setParentMany(Node)
	 * @see org.eclipselabs.blueprints.emf.junit.model.ModelPackage#getNode_ParentMany()
	 * @see org.eclipselabs.blueprints.emf.junit.model.Node#getManyRefNode
	 * @model opposite="manyRefNode"
	 * @generated
	 */
	Node getParentMany();

	/**
	 * Sets the value of the '{@link org.eclipselabs.blueprints.emf.junit.model.Node#getParentMany <em>Parent Many</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Many</em>' reference.
	 * @see #getParentMany()
	 * @generated
	 */
	void setParentMany(Node value);

} // Node
