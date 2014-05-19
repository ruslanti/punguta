package com.punguta.services.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import com.punguta.services.helpers.json.JsonCategory;

/**
 * User: ruslan
 * Date: 5/19/14
 */
public class CategoryBuilder {

    private com.punguta.domains.Category ancestry = null;
    private String name = null;
    private int budget = 0;
    private boolean hidden = false;
    private Set<CategoryBuilder> childrenBuilder = new HashSet<CategoryBuilder>();

    public CategoryBuilder() {
    }

    public CategoryBuilder(String name) {
        this.name = name;
    }

    private CategoryBuilder(JsonCategory jsonCategory) {
        name = jsonCategory.getName();
        if (jsonCategory.getChildren() != null) {
            for (JsonCategory subJsonCategory : jsonCategory.getChildren()) {
                addSubCategory(new CategoryBuilder(subJsonCategory));
            }
        }
    }

    public static CategoryBuilder loadFromJson(InputStream inputStream) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonCategory jsonCategory = objectMapper.readValue(inputStream, JsonCategory.class);
        return new CategoryBuilder(jsonCategory);
    }
    
    public com.punguta.domains.Category build() {
        if (name == null) {
            throw new IllegalArgumentException("category name is not defined");
        }
        com.punguta.domains.Category category = new com.punguta.domains.Category(ancestry);
        category.setName(name);
        category.setBudget(budget);
        category.setHidden(hidden);
        // build children
        for (CategoryBuilder categoryBuilder : childrenBuilder) {
            categoryBuilder.setAncestry(category).build();
        }

        return category;
    }

    public CategoryBuilder setAncestry(com.punguta.domains.Category ancestry) {
        this.ancestry = ancestry;
        return this;
    }

    public CategoryBuilder addSubCategory(CategoryBuilder subCategoryBuilder) {
        this.childrenBuilder.add(subCategoryBuilder);
        return this;
    }

    public CategoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withBudget(int budget) {
        this.budget = budget;
        return this;
    }

    public CategoryBuilder isHidden() {
        hidden = true;
        return this;
    }

}
