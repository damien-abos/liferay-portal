import {PagesVisitor} from 'dynamic-data-mapping-form-renderer';

import {updateField} from '../util/settingsContext.es';
import {addField} from './fieldAddedHandler.es';
import {createSection} from './sectionAddedHandler.es';

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

const handleFieldSetAdded = (props, state, event) => {
	const {fieldSet, indexes, parentFieldName} = event;
	const {pages} = state;
	const visitor = new PagesVisitor(fieldSet.pages);

	const nestedFields = [];

	visitor.mapFields(nestedField => {
		nestedFields.push(nestedField);
	});

	const sectionField = createSection(
		props,
		{skipFieldNameGeneration: false},
		nestedFields
	);

	return addField(props, {
		indexes,
		newField: updateField(props, sectionField, 'label', fieldSet.title),
		pages,
		parentFieldName,
	});
};

export default handleFieldSetAdded;
