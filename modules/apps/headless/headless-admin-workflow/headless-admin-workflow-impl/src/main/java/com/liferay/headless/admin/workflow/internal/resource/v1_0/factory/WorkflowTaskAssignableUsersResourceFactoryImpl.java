/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.admin.workflow.internal.resource.v1_0.factory;

import com.liferay.headless.admin.workflow.resource.v1_0.WorkflowTaskAssignableUsersResource;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.lang.reflect.Method;

import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	immediate = true,
	service = WorkflowTaskAssignableUsersResource.Factory.class
)
public class WorkflowTaskAssignableUsersResourceFactoryImpl
	implements WorkflowTaskAssignableUsersResource.Factory {

	@Override
	public WorkflowTaskAssignableUsersResource.Builder create() {
		return new WorkflowTaskAssignableUsersResource.Builder() {

			@Override
			public WorkflowTaskAssignableUsersResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				return (WorkflowTaskAssignableUsersResource)
					ProxyUtil.newProxyInstance(
						WorkflowTaskAssignableUsersResource.class.
							getClassLoader(),
						new Class<?>[] {
							WorkflowTaskAssignableUsersResource.class
						},
						(proxy, method, arguments) -> _invoke(
							method, arguments, _checkPermissions, _user));
			}

			@Override
			public WorkflowTaskAssignableUsersResource.Builder checkPermissions(
				boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public WorkflowTaskAssignableUsersResource.Builder user(User user) {
				_user = user;

				return this;
			}

			private boolean _checkPermissions = true;
			private User _user;

		};
	}

	@Activate
	protected void activate() {
		WorkflowTaskAssignableUsersResource.FactoryHolder.factory = this;
	}

	@Deactivate
	protected void deactivate() {
		WorkflowTaskAssignableUsersResource.FactoryHolder.factory = null;
	}

	private Object _invoke(
			Method method, Object[] arguments, boolean checkPermissions,
			User user)
		throws Exception {

		String name = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			_permissionCheckerFactory.create(user));

		WorkflowTaskAssignableUsersResource
			workflowTaskAssignableUsersResource =
				_componentServiceObjects.getService();

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		workflowTaskAssignableUsersResource.setContextCompany(company);

		workflowTaskAssignableUsersResource.setContextUser(user);

		try {
			return method.invoke(
				workflowTaskAssignableUsersResource, arguments);
		}
		finally {
			_componentServiceObjects.ungetService(
				workflowTaskAssignableUsersResource);

			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<WorkflowTaskAssignableUsersResource>
		_componentServiceObjects;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;

}