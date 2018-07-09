package com.akangcupez.formvalidation.rule;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aji Subastian (akangcupez@gmail.com)
 */
@SuppressWarnings({"WeakerAccess, unused"})
public class Rule {

    private List<RuleEntity> mList = new ArrayList<>();

    public Rule(){}

    public void setValidationRules(List<RuleEntity> ruleEntities) {
        mList = ruleEntities;
    }

    public List<RuleEntity> getValidationRules() {
        return mList;
    }

    public static class Builder {

        private List<RuleEntity> list;
        private boolean hasList;

        public Builder() {
            list = new ArrayList<>();
            hasList = false;
        }

        public Builder add(RuleType ruleType) {
            return this.add(ruleType, null);
        }

        public Builder add(RuleType ruleType, @Nullable Object ruleValue) {
            return this.add(ruleType, null, null);
        }

        public Builder add(RuleType ruleType, @Nullable Object ruleValue1, @Nullable Object ruleValue2) {
            list.add(new RuleEntity(ruleType, ruleValue1, ruleValue2));
            return this;
        }

        public Rule create() {
            Rule validationRule = new Rule();
            if (list != null && list.size() > 0) validationRule.setValidationRules(list);
            return validationRule;
        }
    }

}
