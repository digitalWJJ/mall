package com.ouc.mallmbg.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionIsNull() {
            addCriterion("product_description is null");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionIsNotNull() {
            addCriterion("product_description is not null");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionEqualTo(String value) {
            addCriterion("product_description =", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionNotEqualTo(String value) {
            addCriterion("product_description <>", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionGreaterThan(String value) {
            addCriterion("product_description >", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("product_description >=", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionLessThan(String value) {
            addCriterion("product_description <", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionLessThanOrEqualTo(String value) {
            addCriterion("product_description <=", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionLike(String value) {
            addCriterion("product_description like", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionNotLike(String value) {
            addCriterion("product_description not like", value, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionIn(List<String> values) {
            addCriterion("product_description in", values, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionNotIn(List<String> values) {
            addCriterion("product_description not in", values, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionBetween(String value1, String value2) {
            addCriterion("product_description between", value1, value2, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductDescriptionNotBetween(String value1, String value2) {
            addCriterion("product_description not between", value1, value2, "productDescription");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("product_price is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("product_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(BigDecimal value) {
            addCriterion("product_price =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(BigDecimal value) {
            addCriterion("product_price <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(BigDecimal value) {
            addCriterion("product_price >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_price >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(BigDecimal value) {
            addCriterion("product_price <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_price <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<BigDecimal> values) {
            addCriterion("product_price in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<BigDecimal> values) {
            addCriterion("product_price not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_price between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_price not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andConfigurationIsNull() {
            addCriterion("configuration is null");
            return (Criteria) this;
        }

        public Criteria andConfigurationIsNotNull() {
            addCriterion("configuration is not null");
            return (Criteria) this;
        }

        public Criteria andConfigurationEqualTo(String value) {
            addCriterion("configuration =", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationNotEqualTo(String value) {
            addCriterion("configuration <>", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationGreaterThan(String value) {
            addCriterion("configuration >", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationGreaterThanOrEqualTo(String value) {
            addCriterion("configuration >=", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationLessThan(String value) {
            addCriterion("configuration <", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationLessThanOrEqualTo(String value) {
            addCriterion("configuration <=", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationLike(String value) {
            addCriterion("configuration like", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationNotLike(String value) {
            addCriterion("configuration not like", value, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationIn(List<String> values) {
            addCriterion("configuration in", values, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationNotIn(List<String> values) {
            addCriterion("configuration not in", values, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationBetween(String value1, String value2) {
            addCriterion("configuration between", value1, value2, "configuration");
            return (Criteria) this;
        }

        public Criteria andConfigurationNotBetween(String value1, String value2) {
            addCriterion("configuration not between", value1, value2, "configuration");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andProductImage1IsNull() {
            addCriterion("product_image1 is null");
            return (Criteria) this;
        }

        public Criteria andProductImage1IsNotNull() {
            addCriterion("product_image1 is not null");
            return (Criteria) this;
        }

        public Criteria andProductImage1EqualTo(String value) {
            addCriterion("product_image1 =", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1NotEqualTo(String value) {
            addCriterion("product_image1 <>", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1GreaterThan(String value) {
            addCriterion("product_image1 >", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1GreaterThanOrEqualTo(String value) {
            addCriterion("product_image1 >=", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1LessThan(String value) {
            addCriterion("product_image1 <", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1LessThanOrEqualTo(String value) {
            addCriterion("product_image1 <=", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1Like(String value) {
            addCriterion("product_image1 like", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1NotLike(String value) {
            addCriterion("product_image1 not like", value, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1In(List<String> values) {
            addCriterion("product_image1 in", values, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1NotIn(List<String> values) {
            addCriterion("product_image1 not in", values, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1Between(String value1, String value2) {
            addCriterion("product_image1 between", value1, value2, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage1NotBetween(String value1, String value2) {
            addCriterion("product_image1 not between", value1, value2, "productImage1");
            return (Criteria) this;
        }

        public Criteria andProductImage2IsNull() {
            addCriterion("product_image2 is null");
            return (Criteria) this;
        }

        public Criteria andProductImage2IsNotNull() {
            addCriterion("product_image2 is not null");
            return (Criteria) this;
        }

        public Criteria andProductImage2EqualTo(String value) {
            addCriterion("product_image2 =", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2NotEqualTo(String value) {
            addCriterion("product_image2 <>", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2GreaterThan(String value) {
            addCriterion("product_image2 >", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2GreaterThanOrEqualTo(String value) {
            addCriterion("product_image2 >=", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2LessThan(String value) {
            addCriterion("product_image2 <", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2LessThanOrEqualTo(String value) {
            addCriterion("product_image2 <=", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2Like(String value) {
            addCriterion("product_image2 like", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2NotLike(String value) {
            addCriterion("product_image2 not like", value, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2In(List<String> values) {
            addCriterion("product_image2 in", values, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2NotIn(List<String> values) {
            addCriterion("product_image2 not in", values, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2Between(String value1, String value2) {
            addCriterion("product_image2 between", value1, value2, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage2NotBetween(String value1, String value2) {
            addCriterion("product_image2 not between", value1, value2, "productImage2");
            return (Criteria) this;
        }

        public Criteria andProductImage3IsNull() {
            addCriterion("product_image3 is null");
            return (Criteria) this;
        }

        public Criteria andProductImage3IsNotNull() {
            addCriterion("product_image3 is not null");
            return (Criteria) this;
        }

        public Criteria andProductImage3EqualTo(String value) {
            addCriterion("product_image3 =", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3NotEqualTo(String value) {
            addCriterion("product_image3 <>", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3GreaterThan(String value) {
            addCriterion("product_image3 >", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3GreaterThanOrEqualTo(String value) {
            addCriterion("product_image3 >=", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3LessThan(String value) {
            addCriterion("product_image3 <", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3LessThanOrEqualTo(String value) {
            addCriterion("product_image3 <=", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3Like(String value) {
            addCriterion("product_image3 like", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3NotLike(String value) {
            addCriterion("product_image3 not like", value, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3In(List<String> values) {
            addCriterion("product_image3 in", values, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3NotIn(List<String> values) {
            addCriterion("product_image3 not in", values, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3Between(String value1, String value2) {
            addCriterion("product_image3 between", value1, value2, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage3NotBetween(String value1, String value2) {
            addCriterion("product_image3 not between", value1, value2, "productImage3");
            return (Criteria) this;
        }

        public Criteria andProductImage4IsNull() {
            addCriterion("product_image4 is null");
            return (Criteria) this;
        }

        public Criteria andProductImage4IsNotNull() {
            addCriterion("product_image4 is not null");
            return (Criteria) this;
        }

        public Criteria andProductImage4EqualTo(String value) {
            addCriterion("product_image4 =", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4NotEqualTo(String value) {
            addCriterion("product_image4 <>", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4GreaterThan(String value) {
            addCriterion("product_image4 >", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4GreaterThanOrEqualTo(String value) {
            addCriterion("product_image4 >=", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4LessThan(String value) {
            addCriterion("product_image4 <", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4LessThanOrEqualTo(String value) {
            addCriterion("product_image4 <=", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4Like(String value) {
            addCriterion("product_image4 like", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4NotLike(String value) {
            addCriterion("product_image4 not like", value, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4In(List<String> values) {
            addCriterion("product_image4 in", values, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4NotIn(List<String> values) {
            addCriterion("product_image4 not in", values, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4Between(String value1, String value2) {
            addCriterion("product_image4 between", value1, value2, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage4NotBetween(String value1, String value2) {
            addCriterion("product_image4 not between", value1, value2, "productImage4");
            return (Criteria) this;
        }

        public Criteria andProductImage5IsNull() {
            addCriterion("product_image5 is null");
            return (Criteria) this;
        }

        public Criteria andProductImage5IsNotNull() {
            addCriterion("product_image5 is not null");
            return (Criteria) this;
        }

        public Criteria andProductImage5EqualTo(String value) {
            addCriterion("product_image5 =", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5NotEqualTo(String value) {
            addCriterion("product_image5 <>", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5GreaterThan(String value) {
            addCriterion("product_image5 >", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5GreaterThanOrEqualTo(String value) {
            addCriterion("product_image5 >=", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5LessThan(String value) {
            addCriterion("product_image5 <", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5LessThanOrEqualTo(String value) {
            addCriterion("product_image5 <=", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5Like(String value) {
            addCriterion("product_image5 like", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5NotLike(String value) {
            addCriterion("product_image5 not like", value, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5In(List<String> values) {
            addCriterion("product_image5 in", values, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5NotIn(List<String> values) {
            addCriterion("product_image5 not in", values, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5Between(String value1, String value2) {
            addCriterion("product_image5 between", value1, value2, "productImage5");
            return (Criteria) this;
        }

        public Criteria andProductImage5NotBetween(String value1, String value2) {
            addCriterion("product_image5 not between", value1, value2, "productImage5");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}