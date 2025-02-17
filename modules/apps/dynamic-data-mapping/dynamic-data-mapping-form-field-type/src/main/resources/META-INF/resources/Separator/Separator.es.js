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

import '../FieldBase/FieldBase.es';

import './SeparatorRegister.soy';

import 'clay-autocomplete';
import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './Separator.soy';

class Separator extends Component {}

Separator.STATE = {
	/**
	 * @default undefined
	 * @instance
	 * @memberof Separator
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Separator
	 * @type {?(string|undefined)}
	 */

	name: Config.string().required(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Separator
	 * @type {?(bool)}
	 */

	repeatable: Config.bool(),

	/**
	 * @default true
	 * @instance
	 * @memberof Separator
	 * @type {?(bool)}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default ''
	 * @instance
	 * @memberof Separator
	 * @type {?(string|undefined)}
	 */

	style: Config.string().value(''),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Separator
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),
};

Soy.register(Separator, templates);

export default Separator;
