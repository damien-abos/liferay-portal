/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.segments.asah.rest.internal.resource.v1_0.factory;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.segments.asah.rest.resource.v1_0.StatusResource;

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
@Component(immediate = true, service = StatusResource.Factory.class)
public class StatusResourceFactoryImpl implements StatusResource.Factory {

	@Override
	public StatusResource.Builder create() {
		return new StatusResource.Builder() {

			@Override
			public StatusResource build() {
				if (_user == null) {
					throw new IllegalArgumentException("User is not set");
				}

				return (StatusResource)ProxyUtil.newProxyInstance(
					StatusResource.class.getClassLoader(),
					new Class<?>[] {StatusResource.class},
					(proxy, method, arguments) -> _invoke(
						method, arguments, _checkPermissions, _user));
			}

			@Override
			public StatusResource.Builder checkPermissions(
				boolean checkPermissions) {

				_checkPermissions = checkPermissions;

				return this;
			}

			@Override
			public StatusResource.Builder user(User user) {
				_user = user;

				return this;
			}

			private boolean _checkPermissions = true;
			private User _user;

		};
	}

	@Activate
	protected void activate() {
		StatusResource.FactoryHolder.factory = this;
	}

	@Deactivate
	protected void deactivate() {
		StatusResource.FactoryHolder.factory = null;
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

		StatusResource statusResource = _componentServiceObjects.getService();

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		statusResource.setContextCompany(company);

		statusResource.setContextUser(user);

		try {
			return method.invoke(statusResource, arguments);
		}
		finally {
			_componentServiceObjects.ungetService(statusResource);

			PrincipalThreadLocal.setName(name);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<StatusResource> _componentServiceObjects;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private UserLocalService _userLocalService;

}