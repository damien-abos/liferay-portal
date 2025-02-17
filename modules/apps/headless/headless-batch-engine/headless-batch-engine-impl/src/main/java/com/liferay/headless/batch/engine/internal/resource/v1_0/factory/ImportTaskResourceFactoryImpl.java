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

package com.liferay.headless.batch.engine.internal.resource.v1_0.factory;

import com.liferay.headless.batch.engine.resource.v1_0.ImportTaskResource;
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
 * @author Ivica Cardic
 */
@Component(immediate = true, service = ImportTaskResource.Factory.class)
public class ImportTaskResourceFactoryImpl
	implements ImportTaskResource.Factory {

	@Override
	public ImportTaskResource.Builder create() {
		return new ImportTaskResource.Builder() {

			@Override
			public ImportTaskResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				return (ImportTaskResource)ProxyUtil.newProxyInstance(
					ImportTaskResource.class.getClassLoader(),
					new Class<?>[] {ImportTaskResource.class},
					(proxy, method, arguments) -> _invoke(
						method, arguments, _checkPermissions, _user));
			}

			@Override
			public ImportTaskResource.Builder checkPermissions(
				boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public ImportTaskResource.Builder user(User user) {
				_user = user;

				return this;
			}

			private boolean _checkPermissions = true;
			private User _user;

		};
	}

	@Activate
	protected void activate() {
		ImportTaskResource.FactoryHolder.factory = this;
	}

	@Deactivate
	protected void deactivate() {
		ImportTaskResource.FactoryHolder.factory = null;
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

		ImportTaskResource importTaskResource =
			_componentServiceObjects.getService();

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		importTaskResource.setContextCompany(company);

		importTaskResource.setContextUser(user);

		try {
			return method.invoke(importTaskResource, arguments);
		}
		finally {
			_componentServiceObjects.ungetService(importTaskResource);

			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ImportTaskResource>
		_componentServiceObjects;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;

}