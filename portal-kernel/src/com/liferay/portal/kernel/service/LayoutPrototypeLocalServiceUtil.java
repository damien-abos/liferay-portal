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

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * Provides the local service utility for LayoutPrototype. This utility wraps
 * <code>com.liferay.portal.service.impl.LayoutPrototypeLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPrototypeLocalService
 * @generated
 */
public class LayoutPrototypeLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.LayoutPrototypeLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the layout prototype to the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPrototype the layout prototype
	 * @return the layout prototype that was added
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
		addLayoutPrototype(
			com.liferay.portal.kernel.model.LayoutPrototype layoutPrototype) {

		return getService().addLayoutPrototype(layoutPrototype);
	}

	public static com.liferay.portal.kernel.model.LayoutPrototype
			addLayoutPrototype(
				long userId, long companyId,
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				boolean active, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addLayoutPrototype(
			userId, companyId, nameMap, descriptionMap, active, serviceContext);
	}

	/**
	 * Creates a new layout prototype with the primary key. Does not add the layout prototype to the database.
	 *
	 * @param layoutPrototypeId the primary key for the new layout prototype
	 * @return the new layout prototype
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
		createLayoutPrototype(long layoutPrototypeId) {

		return getService().createLayoutPrototype(layoutPrototypeId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the layout prototype from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPrototype the layout prototype
	 * @return the layout prototype that was removed
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
			deleteLayoutPrototype(
				com.liferay.portal.kernel.model.LayoutPrototype layoutPrototype)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLayoutPrototype(layoutPrototype);
	}

	/**
	 * Deletes the layout prototype with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPrototypeId the primary key of the layout prototype
	 * @return the layout prototype that was removed
	 * @throws PortalException if a layout prototype with the primary key could not be found
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
			deleteLayoutPrototype(long layoutPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLayoutPrototype(layoutPrototypeId);
	}

	public static void deleteNondefaultLayoutPrototypes(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteNondefaultLayoutPrototypes(companyId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.kernel.model.LayoutPrototype
		fetchLayoutPrototype(long layoutPrototypeId) {

		return getService().fetchLayoutPrototype(layoutPrototypeId);
	}

	public static com.liferay.portal.kernel.model.LayoutPrototype
		fetchLayoutPrototype(
			long companyId, String name, java.util.Locale locale) {

		return getService().fetchLayoutPrototype(companyId, name, locale);
	}

	/**
	 * Returns the layout prototype with the matching UUID and company.
	 *
	 * @param uuid the layout prototype's UUID
	 * @param companyId the primary key of the company
	 * @return the matching layout prototype, or <code>null</code> if a matching layout prototype could not be found
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
		fetchLayoutPrototypeByUuidAndCompanyId(String uuid, long companyId) {

		return getService().fetchLayoutPrototypeByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.model.LayoutPrototype
		fetchLayoutProtoype(long companyId, String name) {

		return getService().fetchLayoutProtoype(companyId, name);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the layout prototype with the primary key.
	 *
	 * @param layoutPrototypeId the primary key of the layout prototype
	 * @return the layout prototype
	 * @throws PortalException if a layout prototype with the primary key could not be found
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
			getLayoutPrototype(long layoutPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutPrototype(layoutPrototypeId);
	}

	public static com.liferay.portal.kernel.model.LayoutPrototype
			getLayoutPrototype(long companyId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutPrototype(companyId, name);
	}

	public static com.liferay.portal.kernel.model.LayoutPrototype
			getLayoutPrototype(
				long companyId, String name, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutPrototype(companyId, name, locale);
	}

	/**
	 * Returns the layout prototype with the matching UUID and company.
	 *
	 * @param uuid the layout prototype's UUID
	 * @param companyId the primary key of the company
	 * @return the matching layout prototype
	 * @throws PortalException if a matching layout prototype could not be found
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
			getLayoutPrototypeByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLayoutPrototypeByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the layout prototypes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutPrototypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout prototypes
	 * @param end the upper bound of the range of layout prototypes (not inclusive)
	 * @return the range of layout prototypes
	 */
	public static java.util.List
		<com.liferay.portal.kernel.model.LayoutPrototype> getLayoutPrototypes(
			int start, int end) {

		return getService().getLayoutPrototypes(start, end);
	}

	/**
	 * Returns the number of layout prototypes.
	 *
	 * @return the number of layout prototypes
	 */
	public static int getLayoutPrototypesCount() {
		return getService().getLayoutPrototypesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List
		<com.liferay.portal.kernel.model.LayoutPrototype> search(
			long companyId, Boolean active, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.LayoutPrototype> obc) {

		return getService().search(companyId, active, start, end, obc);
	}

	public static int searchCount(long companyId, Boolean active) {
		return getService().searchCount(companyId, active);
	}

	/**
	 * Updates the layout prototype in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param layoutPrototype the layout prototype
	 * @return the layout prototype that was updated
	 */
	public static com.liferay.portal.kernel.model.LayoutPrototype
		updateLayoutPrototype(
			com.liferay.portal.kernel.model.LayoutPrototype layoutPrototype) {

		return getService().updateLayoutPrototype(layoutPrototype);
	}

	public static com.liferay.portal.kernel.model.LayoutPrototype
			updateLayoutPrototype(
				long layoutPrototypeId,
				java.util.Map<java.util.Locale, String> nameMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				boolean active, ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateLayoutPrototype(
			layoutPrototypeId, nameMap, descriptionMap, active, serviceContext);
	}

	public static LayoutPrototypeLocalService getService() {
		if (_service == null) {
			_service =
				(LayoutPrototypeLocalService)PortalBeanLocatorUtil.locate(
					LayoutPrototypeLocalService.class.getName());
		}

		return _service;
	}

	private static LayoutPrototypeLocalService _service;

}