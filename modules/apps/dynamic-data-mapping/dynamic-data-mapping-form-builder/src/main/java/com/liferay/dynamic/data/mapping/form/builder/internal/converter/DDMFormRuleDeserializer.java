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

package com.liferay.dynamic.data.mapping.form.builder.internal.converter;

import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.AutoFillDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.CalculateDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.DefaultDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.form.builder.internal.converter.model.action.JumpToPageDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.spi.converter.model.SPIDDMFormRule;
import com.liferay.dynamic.data.mapping.spi.converter.model.SPIDDMFormRuleAction;
import com.liferay.dynamic.data.mapping.spi.converter.model.SPIDDMFormRuleCondition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = DDMFormRuleDeserializer.class)
public class DDMFormRuleDeserializer {

	public List<SPIDDMFormRule> deserialize(String rules)
		throws PortalException {

		JSONArray rulesJSONArray = _jsonFactory.createJSONArray(rules);

		List<SPIDDMFormRule> spiDDMFormRules = new ArrayList<>(
			rulesJSONArray.length());

		for (int i = 0; i < rulesJSONArray.length(); i++) {
			SPIDDMFormRule spiDDMFormRule = deserializeSPIDDMFormRule(
				rulesJSONArray.getJSONObject(i));

			spiDDMFormRules.add(spiDDMFormRule);
		}

		return spiDDMFormRules;
	}

	protected SPIDDMFormRule deserializeSPIDDMFormRule(
		JSONObject ruleJSONObject) {

		SPIDDMFormRule spiDDMFormRule = new SPIDDMFormRule();

		List<SPIDDMFormRuleAction> spiDDMFormRuleActions =
			deserializeSPIDDMFormRuleActions(
				ruleJSONObject.getJSONArray("actions"));

		spiDDMFormRule.setSPIDDMFormRuleActions(spiDDMFormRuleActions);

		List<SPIDDMFormRuleCondition> spiDDMFormRuleConditions =
			deserializeSPIDDMFormRuleConditions(
				ruleJSONObject.getJSONArray("conditions"));

		spiDDMFormRule.setSPIDDMFormRuleConditions(spiDDMFormRuleConditions);

		spiDDMFormRule.setLogicalOperator(
			ruleJSONObject.getString("logical-operator"));

		return spiDDMFormRule;
	}

	protected <T extends SPIDDMFormRuleAction> SPIDDMFormRuleAction
		deserializeSPIDDMFormRuleAction(
			JSONObject actionJSONObject, Class<T> targetClass) {

		JSONDeserializer<T> jsonDeserializer =
			_jsonFactory.createJSONDeserializer();

		return jsonDeserializer.deserialize(
			actionJSONObject.toJSONString(), targetClass);
	}

	protected List<SPIDDMFormRuleAction> deserializeSPIDDMFormRuleActions(
		JSONArray actionsJSONArray) {

		List<SPIDDMFormRuleAction> spiDDMFormRuleActions = new ArrayList<>();

		for (int i = 0; i < actionsJSONArray.length(); i++) {
			JSONObject actionJSONObject = actionsJSONArray.getJSONObject(i);

			String action = actionJSONObject.getString("action");

			Class<? extends SPIDDMFormRuleAction> clazz =
				getSPIDDMFormRuleActionClass(action);

			SPIDDMFormRuleAction spiDDMFormRuleAction =
				deserializeSPIDDMFormRuleAction(actionJSONObject, clazz);

			spiDDMFormRuleActions.add(spiDDMFormRuleAction);
		}

		return spiDDMFormRuleActions;
	}

	protected List<SPIDDMFormRuleCondition> deserializeSPIDDMFormRuleConditions(
		JSONArray conditionsJSONArray) {

		JSONDeserializer<SPIDDMFormRuleCondition[]> jsonDeserializer =
			_jsonFactory.createJSONDeserializer();

		SPIDDMFormRuleCondition[] spiDDMFormRuleConditions =
			jsonDeserializer.deserialize(
				conditionsJSONArray.toJSONString(),
				SPIDDMFormRuleCondition[].class);

		return ListUtil.fromArray(spiDDMFormRuleConditions);
	}

	protected Class<? extends SPIDDMFormRuleAction>
		getSPIDDMFormRuleActionClass(String action) {

		if (action.equals("auto-fill")) {
			return AutoFillDDMFormRuleAction.class;
		}
		else if (action.equals("calculate")) {
			return CalculateDDMFormRuleAction.class;
		}
		else if (action.equals("jump-to-page")) {
			return JumpToPageDDMFormRuleAction.class;
		}

		return DefaultDDMFormRuleAction.class;
	}

	@Reference
	private JSONFactory _jsonFactory;

}