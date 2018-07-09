package com.akangcupez.formvalidation.rule;

/**
 * @author Aji Subastian (akangcupez@gmail.com)
 */
@SuppressWarnings({"unused"})
public class RuleEntity {

    private RuleType rt;
    private Object v1;
    private Object v2;

    public RuleEntity() {}

    public RuleEntity(RuleType ruleType, Object value) {
        this.rt = ruleType;
        this.v1 = value;
    }

    public RuleEntity(RuleType ruleType, Object value1, Object value2) {
        this.rt = ruleType;
        this.v1 = value1;
        this.v2 = value2;
    }

    public RuleType getRuleType() {
        return rt;
    }

    public void setRuleType(RuleType ruleType) {
        this.rt = ruleType;
    }

    public Object getRuleValue1() {
        return v1;
    }

    public void setRuleValue1(Object value) {
        this.v1 = value;
    }

    public Object getRuleValue2() {
        return v2;
    }

    public void setRuleValue2(Object value) {
        this.v2 = value;
    }

}
