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
package org.eclipselabs.blueprints.emf.junit.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipselabs.blueprints.emf.junit.model.ModelPackage;
import org.eclipselabs.blueprints.emf.junit.model.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.impl.NodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.impl.NodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.impl.NodeImpl#getOneRefNode <em>One Ref Node</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.impl.NodeImpl#getManyRefNode <em>Many Ref Node</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.impl.NodeImpl#getParentOne <em>Parent One</em>}</li>
 *   <li>{@link org.eclipselabs.blueprints.emf.junit.model.impl.NodeImpl#getParentMany <em>Parent Many</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends EObjectImpl implements Node {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> children;

	/**
	 * The cached value of the '{@link #getOneRefNode() <em>One Ref Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOneRefNode()
	 * @generated
	 * @ordered
	 */
	protected Node oneRefNode;

	/**
	 * The cached value of the '{@link #getManyRefNode() <em>Many Ref Node</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManyRefNode()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> manyRefNode;

	/**
	 * The cached value of the '{@link #getParentOne() <em>Parent One</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentOne()
	 * @generated
	 * @ordered
	 */
	protected Node parentOne;

	/**
	 * The cached value of the '{@link #getParentMany() <em>Parent Many</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentMany()
	 * @generated
	 * @ordered
	 */
	protected Node parentMany;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<Node>(Node.class, this, ModelPackage.NODE__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getOneRefNode() {
		if (oneRefNode != null && oneRefNode.eIsProxy()) {
			InternalEObject oldOneRefNode = (InternalEObject)oneRefNode;
			oneRefNode = (Node)eResolveProxy(oldOneRefNode);
			if (oneRefNode != oldOneRefNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.NODE__ONE_REF_NODE, oldOneRefNode, oneRefNode));
			}
		}
		return oneRefNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetOneRefNode() {
		return oneRefNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOneRefNode(Node newOneRefNode, NotificationChain msgs) {
		Node oldOneRefNode = oneRefNode;
		oneRefNode = newOneRefNode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__ONE_REF_NODE, oldOneRefNode, newOneRefNode);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOneRefNode(Node newOneRefNode) {
		if (newOneRefNode != oneRefNode) {
			NotificationChain msgs = null;
			if (oneRefNode != null)
				msgs = ((InternalEObject)oneRefNode).eInverseRemove(this, ModelPackage.NODE__PARENT_ONE, Node.class, msgs);
			if (newOneRefNode != null)
				msgs = ((InternalEObject)newOneRefNode).eInverseAdd(this, ModelPackage.NODE__PARENT_ONE, Node.class, msgs);
			msgs = basicSetOneRefNode(newOneRefNode, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__ONE_REF_NODE, newOneRefNode, newOneRefNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Node> getManyRefNode() {
		if (manyRefNode == null) {
			manyRefNode = new EObjectWithInverseResolvingEList<Node>(Node.class, this, ModelPackage.NODE__MANY_REF_NODE, ModelPackage.NODE__PARENT_MANY);
		}
		return manyRefNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getParentOne() {
		if (parentOne != null && parentOne.eIsProxy()) {
			InternalEObject oldParentOne = (InternalEObject)parentOne;
			parentOne = (Node)eResolveProxy(oldParentOne);
			if (parentOne != oldParentOne) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.NODE__PARENT_ONE, oldParentOne, parentOne));
			}
		}
		return parentOne;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetParentOne() {
		return parentOne;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentOne(Node newParentOne, NotificationChain msgs) {
		Node oldParentOne = parentOne;
		parentOne = newParentOne;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__PARENT_ONE, oldParentOne, newParentOne);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentOne(Node newParentOne) {
		if (newParentOne != parentOne) {
			NotificationChain msgs = null;
			if (parentOne != null)
				msgs = ((InternalEObject)parentOne).eInverseRemove(this, ModelPackage.NODE__ONE_REF_NODE, Node.class, msgs);
			if (newParentOne != null)
				msgs = ((InternalEObject)newParentOne).eInverseAdd(this, ModelPackage.NODE__ONE_REF_NODE, Node.class, msgs);
			msgs = basicSetParentOne(newParentOne, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__PARENT_ONE, newParentOne, newParentOne));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getParentMany() {
		if (parentMany != null && parentMany.eIsProxy()) {
			InternalEObject oldParentMany = (InternalEObject)parentMany;
			parentMany = (Node)eResolveProxy(oldParentMany);
			if (parentMany != oldParentMany) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.NODE__PARENT_MANY, oldParentMany, parentMany));
			}
		}
		return parentMany;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetParentMany() {
		return parentMany;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentMany(Node newParentMany, NotificationChain msgs) {
		Node oldParentMany = parentMany;
		parentMany = newParentMany;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__PARENT_MANY, oldParentMany, newParentMany);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentMany(Node newParentMany) {
		if (newParentMany != parentMany) {
			NotificationChain msgs = null;
			if (parentMany != null)
				msgs = ((InternalEObject)parentMany).eInverseRemove(this, ModelPackage.NODE__MANY_REF_NODE, Node.class, msgs);
			if (newParentMany != null)
				msgs = ((InternalEObject)newParentMany).eInverseAdd(this, ModelPackage.NODE__MANY_REF_NODE, Node.class, msgs);
			msgs = basicSetParentMany(newParentMany, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__PARENT_MANY, newParentMany, newParentMany));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__ONE_REF_NODE:
				if (oneRefNode != null)
					msgs = ((InternalEObject)oneRefNode).eInverseRemove(this, ModelPackage.NODE__PARENT_ONE, Node.class, msgs);
				return basicSetOneRefNode((Node)otherEnd, msgs);
			case ModelPackage.NODE__MANY_REF_NODE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getManyRefNode()).basicAdd(otherEnd, msgs);
			case ModelPackage.NODE__PARENT_ONE:
				if (parentOne != null)
					msgs = ((InternalEObject)parentOne).eInverseRemove(this, ModelPackage.NODE__ONE_REF_NODE, Node.class, msgs);
				return basicSetParentOne((Node)otherEnd, msgs);
			case ModelPackage.NODE__PARENT_MANY:
				if (parentMany != null)
					msgs = ((InternalEObject)parentMany).eInverseRemove(this, ModelPackage.NODE__MANY_REF_NODE, Node.class, msgs);
				return basicSetParentMany((Node)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case ModelPackage.NODE__ONE_REF_NODE:
				return basicSetOneRefNode(null, msgs);
			case ModelPackage.NODE__MANY_REF_NODE:
				return ((InternalEList<?>)getManyRefNode()).basicRemove(otherEnd, msgs);
			case ModelPackage.NODE__PARENT_ONE:
				return basicSetParentOne(null, msgs);
			case ModelPackage.NODE__PARENT_MANY:
				return basicSetParentMany(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.NODE__NAME:
				return getName();
			case ModelPackage.NODE__CHILDREN:
				return getChildren();
			case ModelPackage.NODE__ONE_REF_NODE:
				if (resolve) return getOneRefNode();
				return basicGetOneRefNode();
			case ModelPackage.NODE__MANY_REF_NODE:
				return getManyRefNode();
			case ModelPackage.NODE__PARENT_ONE:
				if (resolve) return getParentOne();
				return basicGetParentOne();
			case ModelPackage.NODE__PARENT_MANY:
				if (resolve) return getParentMany();
				return basicGetParentMany();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.NODE__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.NODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Node>)newValue);
				return;
			case ModelPackage.NODE__ONE_REF_NODE:
				setOneRefNode((Node)newValue);
				return;
			case ModelPackage.NODE__MANY_REF_NODE:
				getManyRefNode().clear();
				getManyRefNode().addAll((Collection<? extends Node>)newValue);
				return;
			case ModelPackage.NODE__PARENT_ONE:
				setParentOne((Node)newValue);
				return;
			case ModelPackage.NODE__PARENT_MANY:
				setParentMany((Node)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.NODE__CHILDREN:
				getChildren().clear();
				return;
			case ModelPackage.NODE__ONE_REF_NODE:
				setOneRefNode((Node)null);
				return;
			case ModelPackage.NODE__MANY_REF_NODE:
				getManyRefNode().clear();
				return;
			case ModelPackage.NODE__PARENT_ONE:
				setParentOne((Node)null);
				return;
			case ModelPackage.NODE__PARENT_MANY:
				setParentMany((Node)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.NODE__CHILDREN:
				return children != null && !children.isEmpty();
			case ModelPackage.NODE__ONE_REF_NODE:
				return oneRefNode != null;
			case ModelPackage.NODE__MANY_REF_NODE:
				return manyRefNode != null && !manyRefNode.isEmpty();
			case ModelPackage.NODE__PARENT_ONE:
				return parentOne != null;
			case ModelPackage.NODE__PARENT_MANY:
				return parentMany != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //NodeImpl
